package net.elliot.blackpanthermod.damagetype;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> RADIATION = register("radiation");

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(BlackPantherMod.MOD_ID, name));
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> pType) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(pType));
    }
}