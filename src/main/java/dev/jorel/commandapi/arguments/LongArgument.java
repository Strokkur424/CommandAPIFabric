package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.jorel.commandapi.exceptions.InvalidRangeException;
import dev.jorel.commandapi.executors.CommandArguments;

public class LongArgument extends SafeOverrideableArgument<Long, Long> {
    public LongArgument(String nodeName) {
        super(nodeName, LongArgumentType.longArg(), String::valueOf);
    }

    public LongArgument(String nodeName, long min) {
        super(nodeName, LongArgumentType.longArg(min), String::valueOf);
    }

    public LongArgument(String nodeName, long min, long max) {
        super(nodeName, LongArgumentType.longArg(min, max), String::valueOf);
        if (max < min) {
            throw new InvalidRangeException();
        }
    }

    @Override
    public Class<Long> getPrimitiveType() {
        return long.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.PRIMITIVE_LONG;
    }

    @Override
    public <Source> Long parseArgument(CommandContext<Source> cmdCtx, String key, CommandArguments previousArgs) {
        return cmdCtx.getArgument(key, getPrimitiveType());
    }
}
