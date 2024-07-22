package net.elliot.blackpanthermod.blockentity;

import net.elliot.blackpanthermod.blockentity.util.TickableBlockEntity;
import net.elliot.blackpanthermod.init.ModEffects;
import net.elliot.blackpanthermod.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import java.util.List;

public class RawVibraniumBlockEntity extends BlockEntity implements TickableBlockEntity {

    private final BlockPos blockPos;

    public RawVibraniumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RAW_VIBRANIUM_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.blockPos = pPos;
    }

    @Override
    public void tick() {
        Level pLevel = this.getLevel();
        AABB effectArea = new AABB(this.blockPos).inflate(3);
        List<Player> players = pLevel.getEntitiesOfClass(Player.class, effectArea);
        for (Player player : pLevel.players()) {
            MobEffectInstance existingSicknessEffect = player.getEffect(ModEffects.VIBRANIUM_SICKNESS.get());
            MobEffectInstance existingDecayEffect = player.getEffect(ModEffects.VIBRANIUM_DECAY.get());
            if (effectArea.contains(player.getBoundingBox().getCenter())) {
                if (existingSicknessEffect == null && existingDecayEffect == null) {
                    player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_SICKNESS.get(), 600));
                } else if (existingSicknessEffect != null && existingSicknessEffect.getDuration() < 20) {
                    if (Math.random() * 100 < 33.33) {
                        player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_DECAY.get(), -1));
                    }
                }
            } else {
                if (existingSicknessEffect != null && existingSicknessEffect.getDuration() < 20) {
                    player.removeEffect(ModEffects.VIBRANIUM_SICKNESS.get());
                }
            }
        }
    }
}