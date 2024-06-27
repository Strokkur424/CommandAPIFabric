package dev.jorel.commandapi;

import dev.jorel.commandapi.arguments.Argument;
import net.minecraft.commands.CommandSource;

public class CommandTree extends AbstractCommandTree<CommandTree, Argument<?>, CommandSource> implements FabricExecutable<CommandTree> {
    /**
     * Creates a main root node for a command tree with a given command name
     *
     * @param commandName The name of the command to create
     */
    protected CommandTree(String commandName) {
        super(commandName);
    }

    @Override
    public void register(String namespace) {
        if (namespace.isEmpty() || !CommandAPIHandler.NAMESPACE_PATTERN.matcher(namespace).matches()) {
            super.register();
            return;
        }

        super.register(namespace);
    }

    @Override
    public CommandTree instance() {
        return this;
    }
}
