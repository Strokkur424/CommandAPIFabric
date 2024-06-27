package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.jorel.commandapi.exceptions.InvalidRangeException;
import dev.jorel.commandapi.executors.CommandArguments;

public class DoubleArgument extends SafeOverrideableArgument<Double, Double> {
    public DoubleArgument(String nodeName) {
        super(nodeName, DoubleArgumentType.doubleArg(), String::valueOf);
    }

    public DoubleArgument(String nodeName, double min) {
        super(nodeName, DoubleArgumentType.doubleArg(min), String::valueOf);
    }

    public DoubleArgument(String nodeName, double min, double max) {
        super(nodeName, DoubleArgumentType.doubleArg(min, max), String::valueOf);
        if (max < min) {
            throw new InvalidRangeException();
        }
    }

    @Override
    public Class<Double> getPrimitiveType() {
        return double.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.PRIMITIVE_DOUBLE;
    }

    @Override
    public <Source> Double parseArgument(CommandContext<Source> cmdCtx, String key, CommandArguments previousArgs) {
        return cmdCtx.getArgument(key, getPrimitiveType());
    }
}
