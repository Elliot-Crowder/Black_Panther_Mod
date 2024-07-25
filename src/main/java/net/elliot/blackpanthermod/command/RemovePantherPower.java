package net.elliot.blackpanthermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.player.Player;

public class RemovePantherPower {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("removepantherpower")
                .requires((command) -> {
                    return command.hasPermission(3);
                })
                .then(Commands.argument("target", EntityArgument.player()).executes((command) -> {
                    return removePantherPower(command.getSource(), EntityArgument.getPlayer(command, "target"));
                })));
    }

    private static int removePantherPower(CommandSourceStack pSource, Player pTarget) throws CommandSyntaxException {
        // include edge case for multiple players
        return 1;
    }
}