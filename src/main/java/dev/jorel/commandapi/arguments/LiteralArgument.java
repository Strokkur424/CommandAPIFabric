package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.jorel.commandapi.exceptions.BadLiteralException;
import dev.jorel.commandapi.executors.CommandArguments;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class LiteralArgument extends Argument<String> implements Literal<Argument<String>> {

    private final String literal;

    public LiteralArgument(final String literal) {
        this(literal, literal);
    }

    public LiteralArgument(final String nodeName, final String literal) {
        super(nodeName, null);

        if (literal == null) {
            throw new BadLiteralException(true);
        }

        if (literal.isEmpty()) {
            throw new BadLiteralException(false);
        }

        this.literal = literal;
        this.setListed(false);
    }

    @Contract("_ -> new")
    public static @NotNull LiteralArgument of(final String literal) {
        return new LiteralArgument(literal);
    }

    @Contract("_ -> new")
    public static @NotNull LiteralArgument literal(final String literal) {
        return new LiteralArgument(literal);
    }

    @Contract("_, _ -> new")
    public static @NotNull LiteralArgument of(final String nodeName, final String literal) {
        return new LiteralArgument(nodeName, literal);
    }

    public static @NotNull LiteralArgument literal(final String nodeName, final String literal) {
        return new LiteralArgument(nodeName, literal);
    }

    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.LITERAL;
    }

    @Override
    public <Source> String parseArgument(CommandContext<Source> cmdCtx, String key, CommandArguments previousArgs) {
        return literal;
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}
