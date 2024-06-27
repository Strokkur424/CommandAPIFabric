package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;

public class GreedyStringArgument extends SafeOverrideableArgument<String, String> implements GreedyArgument {

    public GreedyStringArgument(String nodeName) {
        super(nodeName, StringArgumentType.greedyString(), s -> s);
    }

    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.PRIMITIVE_GREEDY_STRING;
    }

    @Override
    public <Source> String parseArgument(CommandContext<Source> cmdCtx, String key, CommandArguments previousArgs) throws CommandSyntaxException {
        return cmdCtx.getArgument(key, getPrimitiveType());
    }
}
