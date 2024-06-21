package net.elliot.blackpanthermod.block;

import net.elliot.blackpanthermod.damagesource.ModDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class RawVibraniumBlock extends PipeBlock {
    public RawVibraniumBlock(BlockBehaviour.Properties builder) { super(0.25F, builder); }

//    @Override
//    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn)
//    {
//        if (entityIn instanceof Player)
//        {
//            Player playerEntity = (Player) entityIn;
//            playerEntity.hurt(level.damageSources().source(ModDamageTypes.RAWVIBRANIUM), 1.0F);
//        }
//    }
}
