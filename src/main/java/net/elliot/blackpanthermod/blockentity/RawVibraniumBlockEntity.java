package net.elliot.blackpanthermod.blockentity;

import net.elliot.blackpanthermod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class RawVibraniumBlockEntity extends BlockEntity {

    public RawVibraniumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.RAW_VIBRANIUM_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
}
