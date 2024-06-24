package net.elliot.blackpanthermod.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class Burger {
    public static final FoodProperties BURGER = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(2)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1)
            .build();
}
