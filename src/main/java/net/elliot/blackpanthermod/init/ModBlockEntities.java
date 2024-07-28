package net.elliot.blackpanthermod.init;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.elliot.blackpanthermod.blockentity.RawVibraniumBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BlackPantherMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<RawVibraniumBlockEntity>> RAW_VIBRANIUM_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("raw_vibranium_block_entity",
                    () -> BlockEntityType.Builder.of(RawVibraniumBlockEntity::new,ModBlocks.RAW_VIBRANIUM_BLOCK.get())
                            .build(null));

    public static void register(IEventBus eventBus) { BLOCK_ENTITIES.register(eventBus); }
}