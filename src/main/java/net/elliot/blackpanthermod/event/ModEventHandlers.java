package net.elliot.blackpanthermod.event;

import net.elliot.blackpanthermod.command.AddPantherPower;
import net.elliot.blackpanthermod.command.CheckPantherPower;
import net.elliot.blackpanthermod.command.RemovePantherPower;
import net.elliot.blackpanthermod.playercap.BlackPantherPower;
import net.elliot.blackpanthermod.playercap.BlackPantherPowerCapability;
import net.elliot.blackpanthermod.playercap.util.PantherPowerProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import static net.elliot.blackpanthermod.BlackPantherMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandlers {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        AddPantherPower.register(event.getDispatcher());
        CheckPantherPower.register(event.getDispatcher());
        RemovePantherPower.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(BlackPantherPower.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(MOD_ID,"properties"), new PantherPowerProvider());
            }
        }
    }

    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone e) {
        if (e.isWasDeath()) {
            // Retrieve the capability from the original player
            e.getOriginal().getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(oldStore -> {
                e.getOriginal().getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
}