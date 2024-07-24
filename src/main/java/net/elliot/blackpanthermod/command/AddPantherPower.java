package net.elliot.blackpanthermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

public class AddPantherPower {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("applypantherpower")
                .requires((command) -> {
                    return command.hasPermission(3);
                })
                .then(Commands.argument("target", EntityArgument.player()).executes((command) -> {
                    return applyPantherPower(command.getSource(), EntityArgument.getPlayer(command, "target"));
                })));
    }

    private static int applyPantherPower(CommandSourceStack pSource, ServerPlayer pTarget) throws CommandSyntaxException {
        // include edge case for multiple players
        return 1;
    }
}
