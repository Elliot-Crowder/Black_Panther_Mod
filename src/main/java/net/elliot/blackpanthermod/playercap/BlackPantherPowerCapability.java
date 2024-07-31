package net.elliot.blackpanthermod.playercap;

import net.elliot.blackpanthermod.damagetype.ModDamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import java.util.Objects;

public class BlackPantherPowerCapability {

    public static final Capability<BlackPantherPower> BLACK_PANTHER_POWER_CAPABILITY = CapabilityManager.get(new CapabilityToken<BlackPantherPower>() {});

    public static void modifyPlayerAttributes(LivingEntity pTarget) {
        pTarget.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
            power.setPower(true);
            //power.setHeartColor(); // Set heart color to purple
        });
        Objects.requireNonNull(pTarget.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(40.0);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.KNOCKBACK_RESISTANCE)).setBaseValue(1.0);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.15);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(6.0);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.ATTACK_KNOCKBACK)).setBaseValue(2.0);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(1024.0);
    }

    public static void resetPlayerAttributes(LivingEntity pTarget) {
        pTarget.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
            power.setPower(false);
            //power.setHeartColor(); // Set heart color to red
        });
        float oldHealth = pTarget.getHealth();
        pTarget.removeEffect(MobEffects.ABSORPTION);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(20.0);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.KNOCKBACK_RESISTANCE)).setBaseValue(0.0);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.1);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(1.0);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.ATTACK_KNOCKBACK)).setBaseValue(0.0);
        Objects.requireNonNull(pTarget.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(4.0);
        if (oldHealth < 20) {
            pTarget.setHealth(oldHealth);
        }
        pTarget.hurt(ModDamageTypes.getDamageSource(pTarget.level(), ModDamageTypes.RADIATION), 0.1f);
    }
}