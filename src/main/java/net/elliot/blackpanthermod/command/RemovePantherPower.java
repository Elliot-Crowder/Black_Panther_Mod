package net.elliot.blackpanthermod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.elliot.blackpanthermod.player.playercap.BlackPantherPowerCapability;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class RemovePantherPower {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher
                .register(Commands.literal("removepantherpower")
                .requires((command) -> {
                    return command.hasPermission(2);
                })
                .then(Commands.argument("target", EntityArgument.player()).executes((command) -> {
                    return removePantherPower(command.getSource(), EntityArgument.getPlayer(command, "target"));
                })));
    }

    private static int removePantherPower(CommandSourceStack pSource, Player pTarget) {
        pTarget.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
            if (power.hasPower()) {
                BlackPantherPowerCapability.resetPlayerAttributes(pTarget);
                pSource.sendSuccess(() -> {
                    return Component.literal("The power of The Black Panther was removed from " + pTarget.getName().getString());
                }, true);
            } else {
                pSource.sendSuccess(() -> {
                    return Component.literal(pTarget.getName().getString() + " didn't have the power of The Black Panther");
                }, false);
            }
        });
        return 1;
    }
}