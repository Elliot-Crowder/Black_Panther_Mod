package net.elliot.blackpanthermod.event;


import net.elliot.blackpanthermod.BlackPantherMod;
import net.elliot.blackpanthermod.player.playercap.BlackPantherPower;
import net.elliot.blackpanthermod.player.playercap.BlackPantherPowerCapability;
import net.elliot.blackpanthermod.player.playercap.util.PantherPowerProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid="blackpanthermod",bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandlers {


    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(BlackPantherPower.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).isPresent()){
                event.addCapability(new ResourceLocation(BlackPantherMod.MOD_ID,"properties"), new PantherPowerProvider());
            }
        }
    }

    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone e) {
        if(e.isWasDeath()){
            // Retrieve the capability from the original player
            e.getOriginal().getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(oldStore -> {
                e.getOriginal().getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });

        }
    }
}
