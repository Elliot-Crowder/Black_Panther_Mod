package net.elliot.blackpanthermod.block;

import net.elliot.blackpanthermod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import java.util.List;
import java.util.Random;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

public class RawVibraniumBlock extends Block implements EntityBlock {
    public RawVibraniumBlock(Properties pProperties) {
        super(Properties
                .copy(Blocks.NETHERITE_BLOCK)
                .lightLevel((p_152607_) -> { return 7; }));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }
}
