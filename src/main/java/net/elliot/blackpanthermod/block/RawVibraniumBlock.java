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

public class RawVibraniumBlock extends BaseEntityBlock {
    public RawVibraniumBlock(Properties pProperties) {
        super(Properties
                .copy(Blocks.NETHERITE_BLOCK)
                .lightLevel((p_152607_) -> { return 7; }));
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
        Random rand = new Random();
        return silkTouchLevel == 0 ? rand.nextInt(6) + 3 : 0;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) { return null; }

    @Override
    public RenderShape getRenderShape(BlockState pState) { return RenderShape.MODEL; }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        AABB effectArea = new AABB(pPos).inflate(3);
        List<Player> players = pLevel.getEntitiesOfClass(Player.class, effectArea);
        for (Player player : pLevel.players()) {
            MobEffectInstance existingSicknessEffect = player.getEffect(ModEffects.VIBRANIUM_SICKNESS.get());
            MobEffectInstance existingDecayEffect = player.getEffect(ModEffects.VIBRANIUM_DECAY.get());
            // If player is inside the bounding box, it'll apply sickness
            // Once sickness runs out, it'll apply decay until the player dies
            // Decay is not properly damaging the player and neither the sickness or decay can be /cleared
            if (effectArea.contains(player.getBoundingBox().getCenter())) {
                if (existingSicknessEffect == null && existingDecayEffect == null) {
                    player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_SICKNESS.get(), 600));
                } else if (existingSicknessEffect != null && existingSicknessEffect.getDuration() < 20) {
                    if (Math.random() * 100 < 33.33) {
                        // Make it so it says infinity and not some large number
                        player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_DECAY.get(), 999999999));
                    }
                }
                // Effects slowly wither out if you leave the bounding box
            } else {
                if (existingSicknessEffect != null && existingSicknessEffect.getDuration() < 20) {
                    player.removeEffect(ModEffects.VIBRANIUM_SICKNESS.get());
                }
            }
        }
    }
}
