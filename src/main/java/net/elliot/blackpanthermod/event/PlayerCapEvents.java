package net.elliot.blackpanthermod.event;

import net.elliot.blackpanthermod.playercap.BlackPantherPower;
import net.elliot.blackpanthermod.playercap.BlackPantherPowerCapability;
import net.elliot.blackpanthermod.playercap.util.PantherPowerProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.elliot.blackpanthermod.BlackPantherMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PlayerCapEvents {

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(BlackPantherPower.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(MOD_ID,"properties"), new PantherPowerProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onClonePlayer(PlayerEvent.Clone e) {
        if (e.getEntity() instanceof ServerPlayer playerNew && e.getOriginal() instanceof ServerPlayer playerOld) {
            playerOld.reviveCaps();
            playerNew.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(capabilityNew ->
                    playerOld.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(capabilityOld ->
                            capabilityNew.deserializeNBT(capabilityOld.serializeNBT())));
            playerOld.invalidateCaps();
        }
    }
}