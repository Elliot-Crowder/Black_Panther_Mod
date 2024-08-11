package net.elliot.blackpanthermod.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.elliot.blackpanthermod.BlackPantherMod;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BlackPantherMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class GuiEvents {

    private static final ResourceLocation HEART_TEXTURE = new ResourceLocation(BlackPantherMod.MOD_ID, "textures/gui/heart.png");
    private static final ResourceLocation HALF_HEART_TEXTURE = new ResourceLocation(BlackPantherMod.MOD_ID, "textures/gui/half_heart.png");
    private static final Minecraft client = Minecraft.getInstance();
    private static long lastHealthTime, healthBlinkTime;
    private static int displayHealth, lastHealth;

    @SubscribeEvent
    public static void onRenderGuiOverlayPre(RenderGuiOverlayEvent.Pre event) {
        Player player = client.player;
        if (player.getHealth() > 20 && event.getOverlay().id().equals(VanillaGuiOverlay.PLAYER_HEALTH.id())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRenderGuiOverlayPost(RenderGuiOverlayEvent.Post event) {
        Player player = client.player;
        GuiGraphics guiGraphics = event.getGuiGraphics();
        float health = player.getHealth();
        int scaledWidth = client.getWindow().getGuiScaledWidth();
        int scaledHeight = client.getWindow().getGuiScaledHeight();
        int fullHearts = (int)((health - 20) / 2);
        int halfHearts = (int)((health - 20) % 2);
        int healthBarWidth = 182;
        int healthBarX = scaledWidth / 2 - healthBarWidth / 2;
        int healthBarY = scaledHeight - 39;
        RenderSystem.enableBlend();
        // render purple hearts if player has more than 20
        // don't forget to include all effects such as poison, frozen, wither, and absorption
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();
    }

    public static void renderHearts(RenderGuiOverlayEvent.Post event, GuiGraphics guiGraphics, float health, int healthBarX, int healthBarY, int fullHearts, int halfHearts, float red, float green, float blue) {
        RenderSystem.setShaderColor(red, green, blue, 1.0f);
        for (int i = 0; i < fullHearts; i++) {
            guiGraphics.blit(HEART_TEXTURE, healthBarX + i * 8, healthBarY, 0, 0, 9, 9, 9, 9);
        } if (halfHearts == 1) {
            guiGraphics.blit(HALF_HEART_TEXTURE, healthBarX + fullHearts * 8, healthBarY, 0, 0, 9, 9, 9, 9);
        }
    }

    public static void renderHunger() {

    }
}