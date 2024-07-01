package net.elliot.blackpanthermod.effect;

import net.minecraft.SharedConstants;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;




public class VibraniumSicknessEffect extends MobEffect {


    public VibraniumSicknessEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        MinecraftForge.EVENT_BUS.register(this);
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
        return pDuration % SharedConstants.TICKS_PER_SECOND == 0;
    }

//    private Level getRegistryAccess() {
//        //obtains registry access by returning a level object
//        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
//        if (server != null) {
//            return server.getLevel(Level.OVERWORLD);
//        }
//        return null;
//
//    }
}
