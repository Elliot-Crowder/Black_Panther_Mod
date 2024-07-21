package net.elliot.blackpanthermod.blockentity;

import net.elliot.blackpanthermod.blockentity.util.TickableBlockEntity;
import net.elliot.blackpanthermod.effect.ModEffects;
import net.elliot.blackpanthermod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class RawVibraniumBlockEntity extends BlockEntity implements TickableBlockEntity {


    private final BlockPos blockPos;
    private final BlockState blockState;


    public RawVibraniumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.RAW_VIBRANIUM_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.blockPos = pPos;
        this.blockState = pBlockState;

    }


    @Override
    public void tick() {
        Level pLevel = this.getLevel();
        AABB effectArea = new AABB(this.blockPos).inflate(3);
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

