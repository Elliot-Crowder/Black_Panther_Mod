package net.elliot.blackpanthermod.effect;

import net.elliot.blackpanthermod.init.ModDamageTypes;
import net.elliot.blackpanthermod.init.ModSounds;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.server.ServerLifecycleHooks;
import java.lang.reflect.Method;

public class VibraniumDecayEffect extends MobEffect {
    public VibraniumDecayEffect(MobEffectCategory pCategory, int pColor) { super(pCategory, pColor); }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (pDuration == Integer.MAX_VALUE || pDuration % 60 == 0) {
            return true;
        } else {
            return false;
        }
    }

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
            pLivingEntity.hurt((DamageSource) damageSource,3.0f);
            player.playSound(ModSounds.RADIATIONSOUND.get());
            player.causeFoodExhaustion(1.0f);
        }
    }

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