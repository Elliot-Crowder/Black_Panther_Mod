package net.elliot.blackpanthermod.blockentity.util;

import net.elliot.blackpanthermod.blockentity.RawVibraniumBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

public interface TickableBlockEntity {
    void tick();

    static <T extends BlockEntity> BlockEntityTicker<T> getTickerHelper(Level level) {
        return level.isClientSide() ? null : (level0, pos0, state0, blockEntity) -> ((RawVibraniumBlockEntity)blockEntity).tick();
    }
}