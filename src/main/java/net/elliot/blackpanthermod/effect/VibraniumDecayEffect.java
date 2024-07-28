package net.elliot.blackpanthermod.effect;

import net.elliot.blackpanthermod.damagetype.ModDamageTypes;
import net.elliot.blackpanthermod.init.ModItems;
import net.elliot.blackpanthermod.init.ModSounds;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class VibraniumDecayEffect extends MobEffect {
    public VibraniumDecayEffect(MobEffectCategory pCategory, int pColor) { super(pCategory, pColor); }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (pDuration == Integer.MAX_VALUE || pDuration % 60 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.hurt(ModDamageTypes.getDamageSource(pLivingEntity.level(), ModDamageTypes.RADIATION), 3);
        if (pLivingEntity instanceof Player player) {
            player.playSound(ModSounds.RADIATIONSOUND.get(), 0.75f, 1.0f);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        List<ItemStack> curativeItems = new ArrayList<>();
        curativeItems.add(new ItemStack(ModItems.PROCESSEDVIBRANIUMVIAL.get()));
        return curativeItems;
    }
}