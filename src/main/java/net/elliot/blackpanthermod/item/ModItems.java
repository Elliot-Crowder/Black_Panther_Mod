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

    // Registers the burger object
    public static final RegistryObject<Item> BURGER = ITEMS.register("burger",
            () -> new Item(new Item.Properties().food(Burger.BURGER)));

    // Registers the burnt burger object
    public static final RegistryObject<Item> BURNTBURGER = ITEMS.register("burntburger",
            () -> new Item(new Item.Properties().food(BurntBurger.BURNTBURGER)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}