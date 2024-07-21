package net.elliot.blackpanthermod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class BlackPantherFruit extends Item {
    public BlackPantherFruit(Properties pProperties) {
        super(pProperties.rarity(Rarity.EPIC));
    }
}
