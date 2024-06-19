package net.elliot.blackpanthermod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class Burger {
    // Adds the food properties for the burger
    public static final FoodProperties BURGER = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(1)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100), 1)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100), 1)
            .build();
}
