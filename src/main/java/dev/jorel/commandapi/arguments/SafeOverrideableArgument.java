package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.commands.CommandSource;

import java.util.function.Function;

public abstract class SafeOverrideableArgument<T, S> extends Argument<T> implements SafeOverrideable<T, S, Argument<T>, Argument<?>, CommandSource> {

    private final Function<S, String> mapper;

    protected SafeOverrideableArgument(String nodeName, ArgumentType<?> rawType, Function<S, String> mapper) {
        super(nodeName, rawType);
        this.mapper = mapper;
    }

    @Override
    public Function<S, String> getMapper() {
        return mapper;
    }
}
