package net.elliot.blackpanthermod;

import com.mojang.logging.LogUtils;
import net.elliot.blackpanthermod.item.ModCreativeModeTabs;
import net.elliot.blackpanthermod.effect.ModEffects;
import net.elliot.blackpanthermod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BlackPantherMod.MOD_ID)
public class BlackPantherMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "blackpanthermod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public BlackPantherMod() {
        IEventBus EventBus = FMLJavaModLoadingContext.get().getModEventBus();




        // Registers event bus for ModCreativeModeTabs
        ModCreativeModeTabs.register(EventBus);
        // Registers event bus for ModItems
        ModItems.register(EventBus);
        // Registers event bus for ModEffects
        ModEffects.register(EventBus);



        // Register the commonSetup method for modloading
        EventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        EventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
