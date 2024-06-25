package net.elliot.blackpanthermod.effect;

import net.elliot.blackpanthermod.damagesource.ModDamageTypes;
import net.elliot.blackpanthermod.sound.ModSounds;
import net.minecraft.SharedConstants;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.server.ServerLifecycleHooks;
import java.lang.reflect.Method;

public class VibraniumDecayEffect extends MobEffect {
    protected VibraniumDecayEffect(MobEffectCategory pCategory, int pColor) { super(pCategory, pColor); }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        MobEffectInstance effectInstance = pLivingEntity.getEffect(this);
        int duration = effectInstance.getDuration();
        Level level;
        if (getRegistryAccess() != null) {
            level = getRegistryAccess();
        } else {
            level = null;
        }

        Object damageSource = getDamageSource(level, ModDamageTypes.RADIATION);
        if (pLivingEntity instanceof Player player) {
            if (duration % 60 == 0) {
                pLivingEntity.hurt((DamageSource) damageSource,3.0f);
                player.playSound(ModSounds.RADIATIONSOUND.get(), 0.75f, 1);
            }
            player.causeFoodExhaustion(1.0f);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        //take more damage over time?
        if (this instanceof VibraniumDecayEffect) {
            return pDuration % SharedConstants.TICKS_PER_SECOND == 0;
        } else {
            return false;
        }
    }

    //potentially turn into a class??
    private Object accessPrivateMethod(Object instance, String methodName, Class<?>[] paramTypes, Object... params){
        try {
            Method method = instance.getClass().getDeclaredMethod(methodName,paramTypes);
            method.setAccessible(true);
            return  method.invoke(instance,params);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object getDamageSource(Level level, Object modDamageType) {
        try {
            Object damageSources = level.damageSources();
            return accessPrivateMethod(damageSources,"source",new Class<?>[]{modDamageType.getClass()},modDamageType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Level getRegistryAccess() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            return server.getLevel(Level.OVERWORLD);
        }
        return null;
    }
}
