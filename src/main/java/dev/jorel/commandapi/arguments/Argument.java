package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import dev.jorel.commandapi.FabricExecutable;
import net.minecraft.commands.CommandSource;

public abstract class Argument<T> extends AbstractArgument<T, Argument<T>, Argument<?>, CommandSource> implements FabricExecutable<Argument<T>> {

    protected Argument(String nodeName, ArgumentType<?> rawType) {
        super(nodeName, rawType);
    }

    @Override
    public Argument<T> instance() {
        return this;
    }

}
