package net.elliot.blackpanthermod.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class VibraniumSicknessEffect extends MobEffect {
    protected VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier){
        MobEffectInstance effectInstance = pLivingEntity.getEffect(this);
        if(effectInstance !=  null){
            int duration = effectInstance.getDuration();
            if(duration>0){
                if(duration>200){
                    //pLivingEntity.hurt(,1.0f);
                    ;
                }else{
                    if(pLivingEntity instanceof Player){
                       Player player = (Player) pLivingEntity;
                       player.causeFoodExhaustion(1.0f);
                    }

                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier){
        return true;
    }
}
