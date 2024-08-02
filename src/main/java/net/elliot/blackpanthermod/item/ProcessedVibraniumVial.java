package net.elliot.blackpanthermod.item;

import net.elliot.blackpanthermod.init.ModEffects;
import net.minecraft.SharedConstants;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class ProcessedVibraniumVial extends Item {
    public ProcessedVibraniumVial(Properties pProperties) {
        super(pProperties.rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player pPlayer, InteractionHand pUsedHand) {
        if (pPlayer.getCooldowns().isOnCooldown(this)) {
            return super.use(world, pPlayer, pUsedHand);
        } else if (pPlayer.hasEffect(ModEffects.VIBRANIUM_DECAY.get())) {
            pPlayer.getCooldowns().addCooldown(this, SharedConstants.TICKS_PER_SECOND);
            pPlayer.removeEffect(ModEffects.VIBRANIUM_DECAY.get());
            pPlayer.getItemInHand(pUsedHand).shrink(1);
            return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pUsedHand), world.isClientSide());
        }
        return super.use(world, pPlayer, pUsedHand);
    }
}