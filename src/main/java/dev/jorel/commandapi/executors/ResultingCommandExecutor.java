package dev.jorel.commandapi.executors;

import dev.jorel.commandapi.commandsenders.FabricCommandSender;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import net.minecraft.commands.CommandSource;

@FunctionalInterface
public interface ResultingCommandExecutor extends ResultingExecutor<CommandSource, FabricCommandSender<? extends CommandSource>> {

    int run(CommandSource sender, CommandArguments args) throws WrapperCommandSyntaxException;

    @Override
    default int run(ExecutionInfo<CommandSource, FabricCommandSender<? extends CommandSource>> info) throws WrapperCommandSyntaxException {
        return run(info.sender(), info.args());
    }

}
