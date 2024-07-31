package net.elliot.blackpanthermod.player;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.elliot.blackpanthermod.player.playercap.BlackPantherPower;
import net.elliot.blackpanthermod.player.playercap.BlackPantherPowerCapability;
import net.elliot.blackpanthermod.player.playercap.util.PantherPowerProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BlackPantherMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PlayerCapEvents {

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(BlackPantherPower.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayer && !event.getObject().getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).isPresent()) {
            event.addCapability(new ResourceLocation(BlackPantherMod.MOD_ID,"properties"), new PantherPowerProvider());
        }
    }



    @SubscribeEvent
    public static void onClonePlayer(PlayerEvent.Clone e) {
        if (e.getEntity() instanceof ServerPlayer playerNew && e.getOriginal() instanceof ServerPlayer playerOld) {
            playerOld.reviveCaps();
            playerNew.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(capabilityNew -> {
                playerOld.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(capabilityOld ->
                        capabilityNew.deserializeNBT(capabilityOld.serializeNBT()));
                if (capabilityNew.hasPower()) {
                    BlackPantherPowerCapability.modifyPlayerAttributes(playerNew);
                    if (e.isWasDeath()) {
                        playerNew.setHealth(40.0F);
                    } else {
                        playerNew.setHealth(playerOld.getHealth());
                    }
                }
            });
            playerOld.invalidateCaps();
        }
    }


}