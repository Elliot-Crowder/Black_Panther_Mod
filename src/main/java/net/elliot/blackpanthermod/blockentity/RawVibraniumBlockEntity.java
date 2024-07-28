package net.elliot.blackpanthermod.blockentity;

import net.elliot.blackpanthermod.blockentity.util.TickableBlockEntity;
import net.elliot.blackpanthermod.init.ModEffects;
import net.elliot.blackpanthermod.init.ModBlockEntities;
import net.elliot.blackpanthermod.playercap.BlackPantherPowerCapability;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import java.util.List;
import java.util.Objects;

public class RawVibraniumBlockEntity extends BlockEntity implements TickableBlockEntity {

    private int tickCounter;

    public RawVibraniumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RAW_VIBRANIUM_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void tick() {
        if (++tickCounter == SharedConstants.TICKS_PER_SECOND) {
            tickCounter = 0;
            List<ServerPlayer> playersInArea = Objects.requireNonNull(this.getLevel()).getEntitiesOfClass(ServerPlayer.class, new AABB(this.worldPosition).inflate(7));
            for (ServerPlayer player : playersInArea) {
                player.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
                    if (!power.hasPower()) {
                        applyEffects(player);
                    }
                });
            }
        }
    }

    private void applyEffects(ServerPlayer player) {
        MobEffectInstance sicknessEffect = player.getEffect(ModEffects.VIBRANIUM_SICKNESS.get());
        MobEffectInstance decayEffect = player.getEffect(ModEffects.VIBRANIUM_DECAY.get());
        if (sicknessEffect == null && decayEffect == null) {
            player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_SICKNESS.get(), 600));
        } else if (sicknessEffect != null && sicknessEffect.getDuration() <= SharedConstants.TICKS_PER_SECOND) {
            player.addEffect(new MobEffectInstance(ModEffects.VIBRANIUM_DECAY.get(), -1));
        }
    }
}