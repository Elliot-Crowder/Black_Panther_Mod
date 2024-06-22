package net.elliot.blackpanthermod.effect;

import net.elliot.blackpanthermod.damagesource.ModDamageTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.server.ServerLifecycleHooks;

public class VibraniumSicknessEffect extends MobEffect {
    private int numTicksElapsed;

    protected VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.numTicksElapsed = 0;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        MobEffectInstance effectInstance = pLivingEntity.getEffect(this);
        Level level;
        if (effectInstance != null) {
            if (getRegistryAccess() != null) {
                level = getRegistryAccess();
            } else {
                level = null;
            }
            int duration = effectInstance.getDuration();
            this.numTicksElapsed++;
            if (duration == 1) {
                resetNumTicksElapsed();
            } else if (duration > 0) {
                if (pLivingEntity instanceof Player player){
                    player.causeFoodExhaustion(0.1f);
                }
                if (this.numTicksElapsed < 200) {
                    // be sussy
                } else {
                    //pLivingEntity.hurt(level.damageSources().source(ModDamageTypes.RADIATION), 1.0F);
                }
            }
        }
    }

    private void resetNumTicksElapsed() { this.numTicksElapsed = 0; }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) { return true; }

    private Level getRegistryAccess() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            ServerLevel level = server.getLevel(Level.OVERWORLD);
            return level;
        }
        return null;
    }
}
