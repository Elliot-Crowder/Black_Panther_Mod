package net.elliot.blackpanthermod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class BurntBurger {
    // Adds the food properties for the burnt burger
    public static final FoodProperties BURNTBURGER = new FoodProperties.Builder()
            .nutrition(1)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100),1)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200),1)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 100),1)
            .build();
}
