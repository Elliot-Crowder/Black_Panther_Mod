package net.elliot.blackpanthermod.event;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.elliot.blackpanthermod.command.AddPantherPower;
import net.elliot.blackpanthermod.command.CheckPantherPower;
import net.elliot.blackpanthermod.command.RemovePantherPower;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BlackPantherMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CommandEvents {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event){
        AddPantherPower.register(event.getDispatcher());
        CheckPantherPower.register(event.getDispatcher());
        RemovePantherPower.register(event.getDispatcher());
    }
}