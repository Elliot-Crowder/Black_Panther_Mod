package net.elliot.blackpanthermod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class DormantBlackPantherFruit extends Item  {
    public DormantBlackPantherFruit(Properties pProperties) {
        super(pProperties.rarity(Rarity.RARE).food(DormantBlackPantherFruit.DORMANTFRUIT));
    }

    public static final FoodProperties DORMANTFRUIT = new FoodProperties.Builder()
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 1)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 200), 1)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 1)
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 200), 1)
            .build();

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity player) {
        ItemStack itemstack = super.finishUsingItem(stack, world, player);
        if (!world.isClientSide) {
            player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
        }
        return itemstack;
    }
}