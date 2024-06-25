package net.elliot.blackpanthermod.effect;

import net.minecraft.ChatFormatting;
import net.minecraft.SharedConstants;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import java.lang.reflect.Method;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.living.MobEffectEvent.Expired;

public class VibraniumSicknessEffect extends MobEffect {
    private int numTicksElapsed;

    protected VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.numTicksElapsed = 0;
        MinecraftForge.EVENT_BUS.register(this);
    }

//    @SubscribeEvent
//    public void onLivingDeath(LivingDeathEvent event) {
//        //event handler that resets the elapsed number of ticks since the effect has
//        //been applied to the player, after the player has died
//        if (event.getEntity() instanceof Player) {
//            resetNumTicksElapsed();
//        }
//    }


    @SubscribeEvent
    public void onEffectRemove(Expired event) {
        //event handler that resets the elapsed number of ticks since the effect has
        //been applied to the player, when the effect is nullified some way other than
        //the effect expiring on its own

        if (event.getEffectInstance().getEffect() == ModEffects.VIBRANIUM_SICKNESS.get()) {
            LivingEntity player = event.getEntity();
            if (player instanceof Player && Math.random() * 100 < 33) {
                player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_DECAY.get(),
                        SharedConstants.TICKS_PER_MINUTE / 2,
                        0));
            }
//            Component message = net.minecraft.network.chat.Component.literal("BAKA")
//                    .withStyle(ChatFormatting.RED);

//            ((Player) player).displayClientMessage(message, true);
        }
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        MobEffectInstance effectInstance = pLivingEntity.getEffect(this);
        Level level;

        //check if effect instance is null
//        if (effectInstance != null) {
//            //needs to be chcanged to ==, testing
//            if(pLivingEntity instanceof Player player){
//                Component message = net.minecraft.network.chat.Component.literal("Null Effect Instance")
//                        .withStyle(ChatFormatting.RED);
//                player.displayClientMessage(message, true);
//                }
//            }
//
//
//        //check if we have registry access
//        level = getRegistryAccess();
//        if (level == null) {
//            if (pLivingEntity instanceof Player player) {
//                Component message = net.minecraft.network.chat.Component.literal("Failed to gain registry access")
//                        .withStyle(ChatFormatting.RED);
//                player.displayClientMessage(message, true);
//            }
//        }


        if (pLivingEntity instanceof Player player){
            player.causeFoodExhaustion(1.0f);
        }
    }


    private void resetNumTicksElapsed(){
        this.numTicksElapsed = 0;
    }


    //potentially turn into a class??
    private Object accessPrivateMethod(Object instance, String methodName, Class<?>[] paramTypes, Object... params){
        //accesses a private method in order to access and return a registry key
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
        //fetches and returns a damage source using a registry key
        try{
            Object damageSources = level.damageSources();
            return accessPrivateMethod(damageSources,"source",new Class<?>[]{modDamageType.getClass()},modDamageType);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        //take more damage over time?
        if(this instanceof VibraniumSicknessEffect){
            return pDuration % SharedConstants.TICKS_PER_SECOND == 0;
        }else{
            return false;
        }

    }

    private Level getRegistryAccess() {
        //obtains registry access by returning a level object
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            return server.getLevel(Level.OVERWORLD);
        }
        return null;
    }
}
