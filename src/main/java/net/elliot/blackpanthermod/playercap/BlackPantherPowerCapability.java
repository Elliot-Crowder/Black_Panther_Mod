package net.elliot.blackpanthermod.playercap;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class BlackPantherPowerCapability {
    public static final Capability<BlackPantherPower> BLACK_PANTHER_POWER_CAPABILITY = CapabilityManager.get(new CapabilityToken<BlackPantherPower>() {});
}