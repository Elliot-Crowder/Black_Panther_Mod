package net.elliot.blackpanthermod.sound;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BlackPantherMod.MOD_ID);

    public static final RegistryObject<SoundEvent> RADIATIONSOUND = registerSoundEvents("radiationsound");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BlackPantherMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }
}
