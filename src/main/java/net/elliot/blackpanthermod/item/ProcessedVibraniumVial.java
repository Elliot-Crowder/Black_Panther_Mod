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
import org.jetbrains.annotations.NotNull;

public class ProcessedVibraniumVial extends Item {
    public ProcessedVibraniumVial(Properties pProperties) {
        super(pProperties.rarity(Rarity.RARE));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (!world.isClientSide && pPlayer instanceof ServerPlayer) {
            if (pPlayer.hasEffect(ModEffects.VIBRANIUM_DECAY.get())) {
                pPlayer.removeEffect(ModEffects.VIBRANIUM_DECAY.get());
                pPlayer.getItemInHand(pUsedHand).shrink(1);
            }
        }
        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pUsedHand), world.isClientSide());
    }
}