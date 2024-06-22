package net.elliot.blackpanthermod.effect;

import net.elliot.blackpanthermod.damagesource.ModDamageTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.lang.reflect.Method;
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
                    Object damageSource = getDamageSource(level,ModDamageTypes.RADIATION);
                    if(damageSource != null){
                        pLivingEntity.hurt((DamageSource) damageSource,1.0f);
                    }
                    //pLivingEntity.hurt(level.damageSources().source(ModDamageTypes.RADIATION), 1.0F);
                }
            }
        }
    }

    private void resetNumTicksElapsed(){
        this.numTicksElapsed = 0;
    }


    //potentially turn into a class??
    private Object accessPrivateMethod(Object instance, String methodName, Class<?>[] paramTypes, Object... params){
        try{
            Method method = instance.getClass().getDeclaredMethod(methodName,paramTypes);
            method.setAccessible(true);
            return  method.invoke(instance,params);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


  private Object getDamageSource(Level level, Object modDamageType){
        try{
            Object damageSources = level.damageSources();
            return accessPrivateMethod(damageSources,"source",new Class<?>[]{modDamageType.getClass()},modDamageType);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
  }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) { return true; }

    private Level getRegistryAccess() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            return server.getLevel(Level.OVERWORLD);
        }
        return null;
    }
}
