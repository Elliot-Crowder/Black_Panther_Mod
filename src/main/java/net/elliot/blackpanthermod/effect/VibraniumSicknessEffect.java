package net.elliot.blackpanthermod.effect;

import net.minecraft.ChatFormatting;
import net.minecraft.SharedConstants;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.living.MobEffectEvent.Expired;



public class VibraniumSicknessEffect extends MobEffect {
    public VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) { super(pCategory, pColor); }
    private int numTicksElapsed;

    protected VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.numTicksElapsed = 0;
        MinecraftForge.EVENT_BUS.register(this);
    }


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
