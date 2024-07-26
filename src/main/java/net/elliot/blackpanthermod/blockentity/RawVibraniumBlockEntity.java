package net.elliot.blackpanthermod.blockentity;

import net.elliot.blackpanthermod.blockentity.util.TickableBlockEntity;
import net.elliot.blackpanthermod.init.ModEffects;
import net.elliot.blackpanthermod.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import java.util.List;

public class RawVibraniumBlockEntity extends BlockEntity implements TickableBlockEntity {

    private int tickCounter;

    public RawVibraniumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RAW_VIBRANIUM_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void tick() {
        tickCounter++;
        List<ServerPlayer> playersInArea = this.getLevel().getEntitiesOfClass(ServerPlayer.class, new AABB(this.worldPosition).inflate(9));
        if (tickCounter % 20 == 0) {
            tickCounter = 0;
            for (ServerPlayer player : playersInArea) {
                MobEffectInstance existingSicknessEffect = player.getEffect(ModEffects.VIBRANIUM_SICKNESS.get());
                MobEffectInstance existingDecayEffect = player.getEffect(ModEffects.VIBRANIUM_DECAY.get());
                if (existingSicknessEffect == null && existingDecayEffect == null) {
                    player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_SICKNESS.get(), 600));
                } else if (existingSicknessEffect != null && existingSicknessEffect.getDuration() <= 20) {
                    player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_DECAY.get(), -1));
                }
            }
        }
    }
}