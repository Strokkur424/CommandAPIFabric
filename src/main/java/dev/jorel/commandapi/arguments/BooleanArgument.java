package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.jorel.commandapi.executors.CommandArguments;

public class BooleanArgument extends SafeOverrideableArgument<Boolean, Boolean> {

    public BooleanArgument(String nodeName) {
        super(nodeName, BoolArgumentType.bool(), String::valueOf);
    }

    @Override
    public Class<Boolean> getPrimitiveType() {
        return boolean.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.PRIMITIVE_BOOLEAN;
    }

    @Override
    public <Source> Boolean parseArgument(CommandContext<Source> cmdCtx, String key, CommandArguments previousArgs) {
        return cmdCtx.getArgument(key, getPrimitiveType());
    }
}
