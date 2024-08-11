package net.elliot.blackpanthermod.player;

import net.elliot.blackpanthermod.player.playercap.BlackPantherPowerCapability;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

public class PlayerEvents {
    public static void preInit(){
        final IEventBus eBus = MinecraftForge.EVENT_BUS;

        eBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedInEvent.class, PlayerEvents::onPlayerLogin);
        eBus.addListener(EventPriority.NORMAL,false,LivingEvent.LivingJumpEvent.class, PlayerEvents::onPlayerJump);
    }

    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
                if (power.hasPower()) {
                    BlackPantherPowerCapability.modifyPlayerAttributes(player);
                }
            });
        }
    }

    public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
                if (power.hasPower()) {
                    player.sendSystemMessage(Component.literal("attempting jump"));
                }
            });
        }
    }
}