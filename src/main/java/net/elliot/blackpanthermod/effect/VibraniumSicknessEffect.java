package net.elliot.blackpanthermod.effect;

import net.minecraft.SharedConstants;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class VibraniumSicknessEffect extends MobEffect {

    private static final List<ItemStack> curativeItems = new ArrayList<>();

    public VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration == Integer.MAX_VALUE || pDuration % SharedConstants.TICKS_PER_SECOND == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player player) {
            player.causeFoodExhaustion(1.0f);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return curativeItems;
    }
}