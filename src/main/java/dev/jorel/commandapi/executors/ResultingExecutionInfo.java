package dev.jorel.commandapi.executors;

import dev.jorel.commandapi.commandsenders.FabricCommandSender;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import net.minecraft.commands.CommandSource;

@FunctionalInterface
public interface ResultingExecutionInfo extends ResultingExecutor<CommandSource, FabricCommandSender<? extends CommandSource>> {

    int run(ExecutionInfo<CommandSource, FabricCommandSender<? extends CommandSource>> info) throws WrapperCommandSyntaxException;

}
