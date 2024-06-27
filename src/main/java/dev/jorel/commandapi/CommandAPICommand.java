package dev.jorel.commandapi;

import dev.jorel.commandapi.arguments.Argument;
import net.minecraft.commands.CommandSource;

public class CommandAPICommand extends AbstractCommandAPICommand<CommandAPICommand, Argument<?>, CommandSource>
        implements FabricExecutable<CommandAPICommand> {

    public CommandAPICommand(final String commandName) {
        super(commandName);
    }

    protected CommandAPICommand(CommandMetaData<CommandSource> metaData) {
        super(metaData);
    }

    @Override
    protected CommandAPICommand newConcreteCommandAPICommand(CommandMetaData<CommandSource> metaData) {
        return new CommandAPICommand(metaData);
    }

    public void register(final String namespace) {
        if (namespace.isEmpty() || !CommandAPIHandler.NAMESPACE_PATTERN.matcher(namespace).matches()) {
            super.register();
            return;
        }

        super.register(namespace);
    }

    @Override
    public CommandAPICommand instance() {
        return this;
    }
}
