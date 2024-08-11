package net.elliot.blackpanthermod.item;

import net.elliot.blackpanthermod.player.playercap.BlackPantherPowerCapability;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class DormantBlackPantherFruit extends Item {
    public DormantBlackPantherFruit(Properties pProperties) {
        super(pProperties.rarity(Rarity.RARE).food(DormantBlackPantherFruit.DORMANTFRUIT));
    }

    public static final FoodProperties DORMANTFRUIT = new FoodProperties.Builder()
            .alwaysEat()
            .saturationMod(2)
            .nutrition(20)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 1)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 200), 1)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 1)
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 200), 1)
            .build();

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity player) {
        player.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
            if (power.hasPower()) {
                BlackPantherPowerCapability.resetPlayerAttributes(player);
                for (Player serverPlayer : world.getServer().getPlayerList().getPlayers()) {
                    serverPlayer.sendSystemMessage(Component.literal(player.getName().getString() + "'s connection to The Black Panther has faded"));
                }
            }
        });
        return super.finishUsingItem(stack, world, player);
    }
}