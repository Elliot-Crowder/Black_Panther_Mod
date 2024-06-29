package net.elliot.blackpanthermod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent.Expired;

public class VibraniumSicknessEffect extends MobEffect {
    public VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) { super(pCategory, pColor); }

    @SubscribeEvent
    public void onEffectRemove(Expired event) {
        if (event.getEffectInstance().getEffect() == ModEffects.VIBRANIUM_SICKNESS.get()) {
            LivingEntity player = event.getEntity();
            if (player instanceof Player && Math.random() * 100 < 33.33) {
                player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_DECAY.get(), -1));
            }
        }
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player player) {
            player.causeFoodExhaustion(1.0f);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) { return pDuration % 20 == 0; }
}
