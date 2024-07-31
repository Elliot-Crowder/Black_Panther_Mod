package net.elliot.blackpanthermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.elliot.blackpanthermod.player.playercap.BlackPantherPowerCapability;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class AddPantherPower {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("applypantherpower")
                .requires((command) -> {
                    return command.hasPermission(2);
                })
                .then(Commands.argument("target", EntityArgument.player()).executes((command) -> {
                    return applyPantherPower(command.getSource(), EntityArgument.getPlayer(command, "target"));
                })));
    }

    private static int applyPantherPower(CommandSourceStack pSource, ServerPlayer pTarget) throws CommandSyntaxException {
        pTarget.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
            if (power.hasPower()) {
                pSource.sendSuccess(() -> {
                    return Component.literal(pTarget.getName().getString() + " already has the power of The Black Panther");
                }, false);
            } else {
                BlackPantherPowerCapability.modifyPlayerAttributes(pTarget);
                pTarget.setHealth(40.0F);
                pSource.sendSuccess(() -> {
                    return Component.literal(pTarget.getName().getString() + " was given the power of The Black Panther");
                }, true);
            }
        });
        return 1;
    }
}