package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.jorel.commandapi.exceptions.InvalidRangeException;
import dev.jorel.commandapi.executors.CommandArguments;

public class FloatArgument extends SafeOverrideableArgument<Float, Float> {
    public FloatArgument(String nodeName) {
        super(nodeName, FloatArgumentType.floatArg(), String::valueOf);
    }

    public FloatArgument(String nodeName, float min) {
        super(nodeName, FloatArgumentType.floatArg(min), String::valueOf);
    }

    public FloatArgument(String nodeName, float min, float max) {
        super(nodeName, FloatArgumentType.floatArg(min, max), String::valueOf);
        if (max < min) {
            throw new InvalidRangeException();
        }
    }

    @Override
    public Class<Float> getPrimitiveType() {
        return float.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.PRIMITIVE_FLOAT;
    }

    @Override
    public <Source> Float parseArgument(CommandContext<Source> cmdCtx, String key, CommandArguments previousArgs) {
        return cmdCtx.getArgument(key, getPrimitiveType());
    }
}
