package net.elliot.blackpanthermod.effect;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BlackPantherMod.MOD_ID);

    public static final RegistryObject<MobEffect> VIBRANIUM_SICKNESS = MOB_EFFECTS.register("vibraniumsickness",
            () -> new VibraniumSicknessEffect(MobEffectCategory.HARMFUL,5578058));
    public static final RegistryObject<MobEffect> VIBRANIUM_DECAY = MOB_EFFECTS.register("vibraniumdecay",
            () -> new VibraniumDecayEffect(MobEffectCategory.HARMFUL, 7561558));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
