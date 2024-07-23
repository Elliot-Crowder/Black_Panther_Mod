package net.elliot.blackpanthermod.playercap;

import net.elliot.blackpanthermod.playercap.util.PantherPower;

public class BlackPantherPower implements PantherPower {

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
}
