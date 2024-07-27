package net.elliot.blackpanthermod.effect;

import net.elliot.blackpanthermod.init.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class VibraniumSicknessEffect extends MobEffect {
    public VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) { super(pCategory, pColor); }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (pDuration == Integer.MAX_VALUE || pDuration % 20 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer player) {
            player.causeFoodExhaustion(1.0f);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        List<ItemStack> curativeItems = new ArrayList<>();
        curativeItems.add(new ItemStack(ModItems.PROCESSEDVIBRANIUMVIAL.get()));
        return curativeItems;
    }
}