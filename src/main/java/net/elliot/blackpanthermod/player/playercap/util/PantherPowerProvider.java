package net.elliot.blackpanthermod.player.playercap.util;

import net.elliot.blackpanthermod.player.playercap.BlackPantherPower;
import net.elliot.blackpanthermod.player.playercap.BlackPantherPowerCapability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PantherPowerProvider implements ICapabilitySerializable<CompoundTag> {
    private final LazyOptional<BlackPantherPower> instance = LazyOptional.of(BlackPantherPower::new);

    private BlackPantherPower blackpantherpower = null;

    private BlackPantherPower createPlayerPower(){
        if(this.blackpantherpower == null){
            this.blackpantherpower = new BlackPantherPower();
        }
        return this.blackpantherpower;
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        return capability == BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        instance.ifPresent(BPpower -> {
            tag.putBoolean("hasPower", BPpower.hasPower());
            tag.putInt("heartColor", BPpower.getHeartColor());
        });
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        instance.ifPresent(power -> {
            power.setPower(compoundTag.getBoolean("hasPower"));
            power.setHeartColor(compoundTag.getInt("heartColor"));
        });
    }

}
