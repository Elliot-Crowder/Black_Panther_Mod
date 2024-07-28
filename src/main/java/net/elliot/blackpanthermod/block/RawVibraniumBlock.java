package net.elliot.blackpanthermod.block;

import net.elliot.blackpanthermod.blockentity.util.TickableBlockEntity;
import net.elliot.blackpanthermod.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RawVibraniumBlock extends Block implements EntityBlock {
    public RawVibraniumBlock(Properties pProperties) {
        super(Properties.copy(Blocks.NETHERITE_BLOCK).lightLevel(state -> 7));
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
        return silkTouchLevel == 0 ? randomSource.nextInt(6) + 3 : 0;
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
       return ModBlockEntities.RAW_VIBRANIUM_BLOCK_ENTITY.get().create(blockPos,blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return TickableBlockEntity.getTickerHelper(level);
    }
}