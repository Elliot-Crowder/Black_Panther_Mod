package net.elliot.blackpanthermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.elliot.blackpanthermod.playercap.BlackPantherPowerCapability;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class RemovePantherPower {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("removepantherpower")
                .requires((command) -> {
                    return command.hasPermission(2);
                })
                .then(Commands.argument("target", EntityArgument.player()).executes((command) -> {
                    return removePantherPower(command.getSource(), EntityArgument.getPlayer(command, "target"));
                })));
    }

    private static int removePantherPower(CommandSourceStack pSource, Player pTarget) throws CommandSyntaxException {
        pTarget.getCapability(BlackPantherPowerCapability.BLACK_PANTHER_POWER_CAPABILITY).ifPresent(power -> {
            power.setPower(false);
            pSource.sendSuccess(() -> {
                return Component.literal("The power of The Black Panther was removed from " + pTarget.getName().getString());
            }, true);
        });
        return 1;
    }
}