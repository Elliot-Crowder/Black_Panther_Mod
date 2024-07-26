package net.elliot.blackpanthermod.playercap;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class BlackPantherPowerCapability {
    public static final Capability<BlackPantherPower> BLACK_PANTHER_POWER_CAPABILITY = CapabilityManager.get(new CapabilityToken<BlackPantherPower>() {});

    public static void modifyPlayerAttributes(ServerPlayer pTarget) {
        pTarget.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
            power.setPower(true);
            //power.setHeartColor(); // Set heart color to purple
        });
        pTarget.getAttribute(Attributes.MAX_HEALTH).setBaseValue(40.0);
        pTarget.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0);
        pTarget.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.5);
        pTarget.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(6.0);
        pTarget.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(2.0);
        pTarget.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(8.0);
    }

    public static void resetPlayerAttributes(ServerPlayer pTarget) {
        pTarget.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
            power.setPower(false);
            //power.setHeartColor(); // Set heart color to red
        });
        pTarget.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0);
        pTarget.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0);
        pTarget.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
        pTarget.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(1.0);
        pTarget.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(0.0);
        pTarget.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(4.0);
    }
}