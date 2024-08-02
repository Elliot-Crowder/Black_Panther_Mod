package net.elliot.blackpanthermod.player.playercap;


import net.elliot.blackpanthermod.player.playercap.util.PantherPower;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class BlackPantherPower implements PantherPower, INBTSerializable<CompoundTag> {

    private boolean hasPower = false;
    private int heartColor = 0xFF0000;

    @Override
    public boolean hasPower() {
        return this.hasPower;
    }

    @Override
    public void setPower(boolean power) {
        this.hasPower = power;
    }

    @Override
    public int getHeartColor() {
        return heartColor;
    }

    @Override
    public void setHeartColor(int color){
        this.heartColor = color;
    }

    public void copyFrom(BlackPantherPower source){
        this.hasPower = source.hasPower;
        this.heartColor = source.heartColor;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("HasPower", hasPower);
        tag.putInt("HeartColor", heartColor);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.hasPower = tag.getBoolean("HasPower");
        this.heartColor = tag.getInt("HeartColor");
    }
}