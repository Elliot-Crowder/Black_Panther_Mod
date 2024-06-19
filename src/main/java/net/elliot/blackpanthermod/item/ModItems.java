package net.elliot.blackpanthermod.item;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // Creates deferred register for all items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BlackPantherMod.MOD_ID);

    // Registers the raw vibranium object
    public static final RegistryObject<Item> RAWVIBRANIUM = ITEMS.register("rawvibranium",
            () -> new RawVibranium(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}