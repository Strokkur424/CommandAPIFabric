package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.jorel.commandapi.exceptions.InvalidRangeException;
import dev.jorel.commandapi.executors.CommandArguments;

public class IntegerArgument extends SafeOverrideableArgument<Integer, Integer> {
    public IntegerArgument(String nodeName) {
        super(nodeName, IntegerArgumentType.integer(), String::valueOf);
    }

    public IntegerArgument(String nodeName, int min) {
        super(nodeName, IntegerArgumentType.integer(min), String::valueOf);
    }

    public IntegerArgument(String nodeName, int min, int max) {
        super(nodeName, IntegerArgumentType.integer(min, max), String::valueOf);
        if (max < min) {
            throw new InvalidRangeException();
        }
    }

    @Override
    public Class<Integer> getPrimitiveType() {
        return int.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.PRIMITIVE_INTEGER;
    }

    @Override
    public <Source> Integer parseArgument(CommandContext<Source> cmdCtx, String key, CommandArguments previousArgs) {
        return cmdCtx.getArgument(key, getPrimitiveType());
    }
}
