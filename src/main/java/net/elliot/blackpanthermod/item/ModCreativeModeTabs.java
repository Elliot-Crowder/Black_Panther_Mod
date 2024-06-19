package net.elliot.blackpanthermod.item;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    // Creates deferred register for all creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlackPantherMod.MOD_ID);

    // Registers the creative mode tab for the mod and adds each item
    public static final RegistryObject<CreativeModeTab> VIBRANIUM_RESOURCES = CREATIVE_MODE_TABS.register("vibraniumresources",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAWVIBRANIUM.get()))
                    .title(Component.translatable("creativetab.vibraniumresources"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAWVIBRANIUM.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
