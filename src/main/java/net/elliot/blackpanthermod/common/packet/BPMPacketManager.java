package net.elliot.blackpanthermod.common.packet;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.elliot.blackpanthermod.common.packet.packets.BPMPacket;
import net.elliot.blackpanthermod.common.packet.packets.UpdateClientVelocityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class BPMPacketManager {

    private static final String VER = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
            new ResourceLocation(BlackPantherMod.MOD_ID,"BPM_Packets"))
            .networkProtocolVersion(()-> VER)
            .serverAcceptedVersions(VER::equals)
            .clientAcceptedVersions(VER::equals)
            .simpleChannel();


    public static void registerPackets(){
        int id = 0;
        INSTANCE.registerMessage(id++, UpdateClientVelocityPacket.class,UpdateClientVelocityPacket::encode, UpdateClientVelocityPacket::decode, UpdateClientVelocityPacket::handle);

    }
    public static void SendToPlayer(ServerPlayer player, BPMPacket packet){
        if(player.connection != null){
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packet);
        }
    }
}
