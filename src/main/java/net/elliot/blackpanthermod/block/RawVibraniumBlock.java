package net.elliot.blackpanthermod.block;

import net.elliot.blackpanthermod.blockentity.RawVibraniumBlockEntity;
import net.elliot.blackpanthermod.blockentity.util.TickableBlockEntity;
import net.elliot.blackpanthermod.effect.ModEffects;
import net.elliot.blackpanthermod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import java.util.List;
import java.util.Random;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RawVibraniumBlock extends Block implements EntityBlock {
    public RawVibraniumBlock(Properties pProperties) {
        super(Properties
                .copy(Blocks.NETHERITE_BLOCK)
                .lightLevel((p_152607_) -> { return 7; }));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
       return BlockEntityInit.RAW_VIBRANIUM_BLOCK_ENTITY.get().create(blockPos,blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type){
        return TickableBlockEntity.getTickerHelper(level);
    }
}


