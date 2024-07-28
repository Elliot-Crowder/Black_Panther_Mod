package net.elliot.blackpanthermod.item;

import net.elliot.blackpanthermod.playercap.BlackPantherPowerCapability;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, Level world, @NotNull LivingEntity player) {
        if (!world.isClientSide && player instanceof ServerPlayer pTarget) {
            BlackPantherPowerCapability.resetPlayerAttributes(pTarget);
        }
        return super.finishUsingItem(stack, world, player);
    }
}