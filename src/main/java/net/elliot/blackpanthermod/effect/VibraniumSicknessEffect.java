package net.elliot.blackpanthermod.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VibraniumSicknessEffect extends MobEffect {
    private int numTicksElapsed;

    protected VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.numTicksElapsed = 0;
    }

//    @SubscribeEvent
//    public void playerTickEvent(TickEvent.PlayerTickEvent event){
//
//    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier){
        MobEffectInstance effectInstance = pLivingEntity.getEffect(this);
        if(effectInstance !=  null){
            int duration = effectInstance.getDuration();
            this.numTicksElapsed++;
            if(duration==1) {
                resetNumTicksElapsed();
            }else if(duration>0){
                if(pLivingEntity instanceof Player player){
                    player.causeFoodExhaustion(0.1f);
                }
                if(this.numTicksElapsed<200){
                    //be sussy
                }else{
                    //hurt the player if more than 400 ticks have elapsed
                    //pLivingEntity.hurt(,1.0f);
                    ;
                }
            }
        }
    }

    private void resetNumTicksElapsed(){
        this.numTicksElapsed = 0;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier){
        return true;
    }
}
