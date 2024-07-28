package net.elliot.blackpanthermod.init;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.elliot.blackpanthermod.item.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BlackPantherMod.MOD_ID);

    public static final RegistryObject<Item> RAWVIBRANIUM = ITEMS.register("rawvibranium",
            () -> new RawVibranium(new Item.Properties()));
    public static final RegistryObject<Item> PROCESSEDVIBRANIUMVIAL = ITEMS.register("processedvibraniumvial",
            () -> new ProcessedVibraniumVial(new Item.Properties()));
    public static final RegistryObject<Item> DORMANTFRUIT = ITEMS.register("dormantfruit",
            () -> new DormantBlackPantherFruit(new Item.Properties()));
    public static final RegistryObject<Item> ACTIVATEDFRUIT = ITEMS.register("activatedfruit",
            () -> new BlackPantherFruit(new Item.Properties()));

    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}