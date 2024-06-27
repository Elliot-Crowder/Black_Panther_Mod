package net.elliot.blackpanthermod.block;

import net.elliot.blackpanthermod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Random;

public class RawVibraniumBlock extends Block {
    public RawVibraniumBlock(Properties pProperties) {
        super(Properties
                .copy(Blocks.NETHERITE_BLOCK)
                .lightLevel((p_152607_) -> { return 7; }));
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof Player player) {
            MobEffectInstance existingEffect = player.getEffect(ModEffects.VIBRANIUM_SICKNESS.get());
            if (existingEffect == null || existingEffect.getDuration() < 60) {
                player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_SICKNESS.get(), 600));
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
        Random rand = new Random();
        return silkTouchLevel == 0 ? rand.nextInt(6) + 3 : 0;
    }
}
