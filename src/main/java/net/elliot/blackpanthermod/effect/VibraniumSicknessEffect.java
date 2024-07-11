package net.elliot.blackpanthermod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class VibraniumSicknessEffect extends MobEffect {
    public VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player player) {
            player.causeFoodExhaustion(1.0f);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (pDuration == Integer.MAX_VALUE || pDuration % 20 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
