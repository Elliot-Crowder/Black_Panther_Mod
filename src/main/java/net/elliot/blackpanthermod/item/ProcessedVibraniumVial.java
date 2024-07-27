package net.elliot.blackpanthermod.item;

import net.elliot.blackpanthermod.init.ModEffects;
import net.minecraft.server.level.ServerPlayer;
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
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (!world.isClientSide && pPlayer instanceof ServerPlayer) {
            if (pPlayer.hasEffect(ModEffects.VIBRANIUM_DECAY.get())) {
                pPlayer.removeEffect(ModEffects.VIBRANIUM_DECAY.get());
                itemStack.shrink(1);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}