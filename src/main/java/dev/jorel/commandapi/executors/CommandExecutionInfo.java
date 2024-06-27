package dev.jorel.commandapi.executors;

import dev.jorel.commandapi.commandsenders.FabricCommandSender;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import net.minecraft.commands.CommandSource;

@FunctionalInterface
public interface CommandExecutionInfo extends NormalExecutor<CommandSource, FabricCommandSender<? extends CommandSource>> {

    void run(ExecutionInfo<CommandSource, FabricCommandSender<? extends CommandSource>> info) throws WrapperCommandSyntaxException;

}