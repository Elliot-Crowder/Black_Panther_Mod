package net.elliot.blackpanthermod;

import com.mojang.logging.LogUtils;
import net.elliot.blackpanthermod.init.*;
import net.elliot.blackpanthermod.event.ModEventHandlers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

        // Registers event bus for all init classes
        ModBlockEntities.BLOCK_ENTITIES.register(EventBus);
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(EventBus);
        ModItems.ITEMS.register(EventBus);
        ModEffects.MOB_EFFECTS.register(EventBus);
        ModBlocks.BLOCKS.register(EventBus);
        ModSounds.SOUND_EVENTS.register(EventBus);

        // Register the commonSetup method for modloading
        EventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(ModEventHandlers.class);

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
