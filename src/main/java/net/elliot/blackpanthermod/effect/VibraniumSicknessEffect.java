package net.elliot.blackpanthermod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class VibraniumSicknessEffect extends MobEffect {
    public VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        MobEffectInstance effectInstance = pLivingEntity.getEffect(this);
        if(effectInstance !=  null) {
            int duration = effectInstance.getDuration();
            if (duration > 0) {
                // In the first 10 seconds of the effect being applied, it only applies the hunger effect
                // If the effect is longer than 10 seconds it'll start damaging you and get rid of the hunger effect
                if (duration < duration - 200) {
                    pLivingEntity.hurt(pLivingEntity.damageSources().magic(), (float)(6 << pAmplifier));
                } else {
                    if (pLivingEntity instanceof Player) {
                        Player player = (Player) pLivingEntity;
                        player.getFoodData().addExhaustion(5.0f);
                    }
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) { return true; }
}
