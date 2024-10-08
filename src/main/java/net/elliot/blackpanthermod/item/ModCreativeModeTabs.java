package net.elliot.blackpanthermod.item;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.elliot.blackpanthermod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlackPantherMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> VIBRANIUM_RESOURCES = CREATIVE_MODE_TABS.register("vibraniumresources",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAWVIBRANIUM.get()))
                    .title(Component.translatable("creativetab.vibraniumresources"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BURGER.get());
                        output.accept(ModItems.BURNTBURGER.get());
                        output.accept(ModItems.RAWVIBRANIUM.get());
                        output.accept(ModItems.PROCESSEDVIBRANIUMVIAL.get());
                        output.accept(ModBlocks.RAWVIBRANIUMBLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) { CREATIVE_MODE_TABS.register(eventBus); }
}
