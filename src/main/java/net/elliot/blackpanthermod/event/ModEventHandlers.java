package net.elliot.blackpanthermod.event;


import net.elliot.blackpanthermod.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="blackpanthermod",bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandlers {

    @SubscribeEvent
    public static void onEffectRemove(MobEffectEvent.Expired event) {
        if (event.getEffectInstance().getEffect() == ModEffects.VIBRANIUM_SICKNESS.get()) {
            LivingEntity player = event.getEntity();
//            if (player instanceof Player && Math.random() * 100 < 33.33) {
            if(player instanceof Player){
                player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_DECAY.get(), -1));
            }
        }
    }
}
