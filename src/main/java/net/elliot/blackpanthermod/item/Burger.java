package net.elliot.blackpanthermod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class Burger extends Item {
    public Burger(Properties pProperties) {
        super(pProperties.food(Burger.BURGER));
    }

    public static final FoodProperties BURGER = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(2)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600), 1)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1)
            .build();
}