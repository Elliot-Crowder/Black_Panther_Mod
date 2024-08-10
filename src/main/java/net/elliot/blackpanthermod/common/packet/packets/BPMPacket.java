package net.elliot.blackpanthermod.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface BPMPacket {
    void encode(FriendlyByteBuf buffer);
    void handle(Supplier<NetworkEvent.Context> context);
}
