package net.elliot.blackpanthermod.event;

import net.elliot.blackpanthermod.BlackPantherMod;
import net.elliot.blackpanthermod.playercap.BlackPantherPower;
import net.elliot.blackpanthermod.playercap.BlackPantherPowerCapability;
import net.elliot.blackpanthermod.playercap.util.PantherPowerProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = BlackPantherMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PlayerCapEvents {


    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(BlackPantherPower.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayer && !event.getObject().getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).isPresent()) {
            event.addCapability(new ResourceLocation(BlackPantherMod.MOD_ID,"properties"), new PantherPowerProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        float jumpMultiplier = 2F;

        if (event.getEntity() instanceof ServerPlayer player){
            player.sendSystemMessage(Component.literal("attempting jump"));
            event.getEntity().push(0,jumpMultiplier,0);
        }

//        if (event.getEntity() instanceof ServerPlayer player) {
//            player.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
//                if (power.hasPower()) {
//                    player.sendSystemMessage(Component.literal("attempting jump"));
//                    Vec3 vec3 = player.getDeltaMovement();
//                    player.setDeltaMovement(vec3.x, (double)10, vec3.z);
//                    if (player.isSprinting()) {
//                        float f = player.getYRot() * 0.017453292F;
//                        player.setDeltaMovement(player.getDeltaMovement().add((double)(-Mth.sin(f) * 0.2F), 0.0, (double)(Mth.cos(f) * 0.2F)));
//                    }
//
//                    player.hasImpulse = true;
//
//
//                }
//            });
//        }
    }

    @SubscribeEvent
    public static void onClonePlayer(PlayerEvent.Clone e) {
        if (e.getEntity() instanceof ServerPlayer playerNew && e.getOriginal() instanceof ServerPlayer playerOld) {
            playerOld.reviveCaps();
            playerNew.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(capabilityNew -> {
                playerOld.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(capabilityOld ->
                        capabilityNew.deserializeNBT(capabilityOld.serializeNBT()));
                if (capabilityNew.hasPower()) {
                    BlackPantherPowerCapability.modifyPlayerAttributes(playerNew);
                    if (e.isWasDeath()) {
                        playerNew.setHealth(40.0F);
                    } else {
                        playerNew.setHealth(playerOld.getHealth());
                    }
                }
            });
            playerOld.invalidateCaps();
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
                if (power.hasPower()) {
                    BlackPantherPowerCapability.modifyPlayerAttributes(player);
                }
            });
        }
    }
}