package dev.jorel.commandapi.executors;

import dev.jorel.commandapi.commandsenders.FabricCommandSender;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import net.minecraft.commands.CommandSource;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface CommandExecutor extends NormalExecutor<CommandSource, FabricCommandSender<? extends CommandSource>> {

    void run(CommandSource sender, CommandArguments args) throws WrapperCommandSyntaxException;

    @Override
    default void run(@NotNull ExecutionInfo<CommandSource, FabricCommandSender<? extends CommandSource>> info) throws WrapperCommandSyntaxException {
        this.run(info.sender(), info.args());
    }

}
