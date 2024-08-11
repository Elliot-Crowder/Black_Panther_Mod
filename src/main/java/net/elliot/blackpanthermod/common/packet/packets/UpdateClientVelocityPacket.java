package net.elliot.blackpanthermod.common.packet.packets;

import net.elliot.blackpanthermod.client.ClientOperations;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.OptionalDouble;
import java.util.function.Supplier;

public class UpdateClientVelocityPacket implements BPMPacket {

    private final Operation operation;
    private final OptionalDouble x;
    private final OptionalDouble y;
    private final OptionalDouble z;

    public UpdateClientVelocityPacket(Operation operation, OptionalDouble x, OptionalDouble y, OptionalDouble z) {
        this.operation = operation;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public UpdateClientVelocityPacket(Operation operation, double x, double y, double z) {
        this(operation, OptionalDouble.of(x), OptionalDouble.of(y), OptionalDouble.of(z));
    }

    public UpdateClientVelocityPacket(Operation operation, double x, double z) {
        this(operation, OptionalDouble.of(x), OptionalDouble.empty(), OptionalDouble.of(z));
    }

    public UpdateClientVelocityPacket(Operation operation, double y) {
        this(operation, OptionalDouble.empty(), OptionalDouble.of(y), OptionalDouble.empty());
    }

    public void encode(FriendlyByteBuf buffer){
        buffer.writeInt(operation.GetOrdinal());

        //write x to buffer
        buffer.writeBoolean(x.isPresent());
        x.ifPresent(buffer::writeDouble);

        //write y to buffer
        buffer.writeBoolean(y.isPresent());
        y.ifPresent(buffer::writeDouble);

        // write z to buffer
        buffer.writeBoolean(z.isPresent());
        z.ifPresent(buffer::writeDouble);
    }

    public static UpdateClientVelocityPacket decode(FriendlyByteBuf buffer){
        Operation operation = Operation.OrdinalToEnum(buffer.readInt());

        OptionalDouble x = buffer.readBoolean() ? OptionalDouble.of(buffer.readDouble()): OptionalDouble.empty();
        OptionalDouble y = buffer.readBoolean() ? OptionalDouble.of(buffer.readDouble()): OptionalDouble.empty();
        OptionalDouble z = buffer.readBoolean() ? OptionalDouble.of(buffer.readDouble()): OptionalDouble.empty();

        return new UpdateClientVelocityPacket(operation,x,y,z);
    }

    public void handle(Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> ClientOperations.adjustPlayerMovement(x, y, z, operation));
        context.get().setPacketHandled(true);
    }

    public enum Operation {
        SET(1),
        ADD(2),
        MULTIPLY(3),
        MAX(4),
        MIN(5);

        private final int ordinal;

        Operation(int ordinal) {
            this.ordinal = ordinal;
        }

        public static Operation OrdinalToEnum(int ordinal){
            return switch(ordinal){
                case 1 -> SET;
                case 2 -> ADD;
                case 3 -> MULTIPLY;
                case 4 -> MAX;
                case 5 -> MIN;
                default -> SET;
            };
        }
        public int GetOrdinal(){
            return this.ordinal;
        }
    }
}