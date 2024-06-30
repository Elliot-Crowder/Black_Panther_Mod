package net.elliot.blackpanthermod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class BurntBurger extends Item {
    public BurntBurger(Properties pProperties) {
        super(pProperties
                .food(BurntBurger.BURNTBURGER));
    }

    public static final FoodProperties BURNTBURGER = new FoodProperties.Builder()
            .nutrition(1)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100),1)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200),1)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 100),1)
            .build();
}
