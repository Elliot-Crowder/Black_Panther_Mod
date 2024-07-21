package net.elliot.blackpanthermod.init;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.elliot.blackpanthermod.block.RawVibraniumBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BlackPantherMod.MOD_ID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().rarity(Rarity.RARE)));
    }

    public static final RegistryObject<Block> RAW_VIBRANIUM_BLOCK = registerBlock("rawvibraniumblock",
            () -> new RawVibraniumBlock(BlockBehaviour.Properties.of()));

    public static void register(IEventBus eventBus) { BLOCKS.register(eventBus); }
}
