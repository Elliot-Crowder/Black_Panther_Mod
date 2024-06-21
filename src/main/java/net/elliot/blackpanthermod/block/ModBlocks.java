package net.elliot.blackpanthermod.block;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    // Creates deferred register for all blocks
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BlackPantherMod.MOD_ID);

    public static void register(IEventBus eventBus) { BLOCKS.register(eventBus); }
}
