package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.jorel.commandapi.executors.CommandArguments;

public class StringArgument extends Argument<String> {

    public StringArgument(String nodeName) {
        super(nodeName, StringArgumentType.word());
    }

    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.PRIMITIVE_STRING;
    }

    @Override
    public <Source> String parseArgument(CommandContext<Source> cmdCtx, String key, CommandArguments previousArgs) {
        return cmdCtx.getArgument(key, getPrimitiveType());
    }
}
