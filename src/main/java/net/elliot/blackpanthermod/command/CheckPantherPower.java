package net.elliot.blackpanthermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.elliot.blackpanthermod.playercap.BlackPantherPowerCapability;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class CheckPantherPower {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("checkpantherpower")
                .requires((command) -> {
                    return command.hasPermission(3);
                })
                .then(Commands.argument("target", EntityArgument.player()).executes((command) -> {
                    return checkPantherPower(command.getSource(), EntityArgument.getPlayer(command, "target"));
                })));
    }

    private static int checkPantherPower(CommandSourceStack pSource, Player pTarget) throws CommandSyntaxException {
        pTarget.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
            if (power.hasPower()) {
                pSource.sendSuccess(() -> {
                    return Component.literal(pTarget.getName().getString() + " has the power of The Black Panther");
                }, false);
            } else {
                pSource.sendSuccess(() -> {
                    return Component.literal(pTarget.getName().getString() + " doesn't have the power of The Black Panther");
                }, false);
            }
        });
        return 1;
    }
}