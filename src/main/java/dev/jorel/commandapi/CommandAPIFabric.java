package dev.jorel.commandapi;

import com.google.common.io.Files;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import dev.jorel.commandapi.arguments.SuggestionProviders;
import dev.jorel.commandapi.commandsenders.AbstractCommandSender;
import dev.jorel.commandapi.commandsenders.AbstractPlayer;
import dev.jorel.commandapi.commandsenders.FabricPlayer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.commands.CommandSource;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.logging.LogManager;

public class CommandAPIFabric implements CommandAPIPlatform<Argument<?>, CommandSource, CommandSource> {

    // TODO Add ServerCommandSource :D

    private static CommandAPIFabric instance;
    private static InternalFabricConfig config;

    private Event<CommandRegistrationCallback> callbackEvent = CommandRegistrationCallback.EVENT;
    private CommandDispatcher<CommandSource> dispatcher;

    public CommandAPIFabric() {
        instance = this;
    }

    private static final String preLoadErrorMessage
            = "Tried to access CommandAPIFabric instance, but it was null! Are you using CommandAPI features before calling CommandAPI#onLoad?";

    public static CommandAPIFabric get() {
        if (instance != null) {
            return instance;
        }
        else {
            throw new IllegalArgumentException(preLoadErrorMessage);
        }
    }

    public static InternalFabricConfig getConfiguration() {
        if (config != null) {
            return config;
        }
        else {
            throw new IllegalArgumentException(preLoadErrorMessage);
        }
    }


    @Override
    public void onLoad(CommandAPIConfig<?> config) {
        if (config instanceof CommandAPIFabricConfig fabricConfig) {
            CommandAPIFabric.setInternalConfig(new InternalFabricConfig(fabricConfig));
        }
        else {
            CommandAPI.logError("CommandAPIFabric was loaded with non-Fabric config!");
            CommandAPI.logError("Attempts to access Fabric-specific config variables will fail!");
        }

        dispatcher = new CommandDispatcher<>();
    }

    private static void setInternalConfig(InternalFabricConfig internalFabricConfig) {
        config = internalFabricConfig;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void registerPermission(String string) {
        // TODO Implement this
    }


    @Override
    public void unregister(String commandName, boolean unregisterNamespaces) {
        // TODO: https://fabricmc.net/wiki/tutorial:commands -> Can I unregister commands in runtime?
    }

    @Override
    public CommandDispatcher<CommandSource> getBrigadierDispatcher() {
        return dispatcher;
    }

    @Override
    public void createDispatcherFile(File file, CommandDispatcher<CommandSource> dispatcher) throws IOException {
        Files.asCharSink(file, StandardCharsets.UTF_8).write(new GsonBuilder().setPrettyPrinting().create()
                .toJson(serializeNodeToJson(dispatcher, dispatcher.getRoot())));
    }

    private static @NotNull JsonObject serializeNodeToJson(CommandDispatcher<CommandSource> dispatcher, CommandNode<CommandSource> node) {
        JsonObject output = new JsonObject();
        switch (node) {
            case RootCommandNode rootCommandNode -> output.addProperty("type", "root");
            case LiteralCommandNode literalCommandNode -> output.addProperty("type", "literal");
            case ArgumentCommandNode argumentCommandNode -> {
                ArgumentType<?> type = argumentCommandNode.getType();
                output.addProperty("type", "argument");
                output.addProperty("argumentType", type.getClass().getName());
                // In Bukkit, serializing to json is handled internally
                // They have an internal registry that connects ArgumentTypes to serializers that can
                //  include the specific properties of each argument as well (eg. min/max for an Integer)
                // Velocity doesn't seem to have an internal map like this, but we could create our own
                // In the meantime, I think it's okay to leave out properties here
            }
            case null, default -> {
                CommandAPI.logError("Could not serialize node %s (%s)!".formatted(node, node.getClass()));
                output.addProperty("type", "unknown");
            }
        }

        JsonObject children = new JsonObject();

        for (CommandNode<CommandSource> child : node.getChildren()) {
            children.add(child.getName(), serializeNodeToJson(dispatcher, child));
        }

        if (!children.isEmpty()) {
            output.add("children", children);
        }

        if (node.getCommand() != null) {
            output.addProperty("executable", true);
        }

        if (node.getRedirect() != null) {
            Collection<String> redirectPath = dispatcher.getPath(node.getRedirect());
            if (!redirectPath.isEmpty()) {
                JsonArray redirectInfo = new JsonArray();
                redirectPath.forEach(redirectInfo::add);
                output.add("redirect", redirectInfo);
            }
        }

        return output;
    }

    @Override
    public CommandAPILogger getLogger() {
        return CommandAPILogger.fromJavaLogger(LogManager.getLogManager().getLogger("CommandAPI"));
    }

    @Override
    public AbstractCommandSender<? extends CommandSource> getSenderForCommand(CommandContext<CommandSource> cmdCtx, boolean forceNative) {
        return getCommandSenderFromCommandSource(cmdCtx.getSource());
    }

    @Override
    public AbstractCommandSender<? extends CommandSource> getCommandSenderFromCommandSource(CommandSource commandSource) {
        if (commandSource instanceof ServerPlayer player) {
            return new FabricPlayer(player);
        }
        throw new IllegalArgumentException("Unknown CommandSource: " + commandSource);
    }

    @Override
    public AbstractCommandSender<? extends CommandSource> wrapCommandSender(CommandSource commandSource) {
        return getCommandSenderFromCommandSource(commandSource);
    }

    @Override
    public CommandSource getBrigadierSourceFromCommandSender(AbstractCommandSender<? extends CommandSource> sender) {
        return sender.getSource();
    }

    @Override
    public SuggestionProvider<CommandSource> getSuggestionProvider(SuggestionProviders suggestionProvider) {
        // TODO Implement this method
        return (context, builder) -> Suggestions.empty();
    }

    @Override
    public void preCommandRegistration(String commandName) {
        // Nothing to do
    }

    @Override
    public void postCommandRegistration(RegisteredCommand registeredCommand, LiteralCommandNode<CommandSource> resultantNode,
                                        List<LiteralCommandNode<CommandSource>> aliasNodes) {
        // Nothing to do
    }

    @Override
    public LiteralCommandNode<CommandSource> registerCommandNode(LiteralArgumentBuilder<CommandSource> node, String namespace) {
        LiteralCommandNode<CommandSource> buildNode = getBrigadierDispatcher().register(node);
        if (!namespace.isEmpty()) {
            getBrigadierDispatcher().getRoot().addChild(CommandAPIHandler.getInstance().namespaceNode(buildNode, namespace));
        }

        return buildNode;
    }

    @Override
    public void reloadDataPacks() {
        // TODO implement this
    }

    @Override
    public void updateRequirements(AbstractPlayer<?> player) {
        // TODO do this
    }

    @Override
    public Argument<?> newConcreteMultiLiteralArgument(String nodeName, String[] literals) {
        return new MultiLiteralArgument(nodeName, literals);
    }

    @Override
    public Argument<?> newConcreteLiteralArgument(String nodeName, String literal) {
        return new LiteralArgument(nodeName, literal);
    }

    @Override
    public AbstractCommandAPICommand<?, Argument<?>, CommandSource> newConcreteCommandAPICommand(CommandMetaData<CommandSource> meta) {
        return new CommandAPICommand(meta);
    }
}
