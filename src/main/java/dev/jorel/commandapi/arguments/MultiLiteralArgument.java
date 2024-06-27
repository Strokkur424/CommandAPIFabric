package dev.jorel.commandapi.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.jorel.commandapi.exceptions.BadLiteralException;
import dev.jorel.commandapi.executors.CommandArguments;

public class MultiLiteralArgument extends Argument<String> implements MultiLiteral<Argument<String>> {

    private final String[] literals;

    public MultiLiteralArgument(final String nodeName, final String... literals) {
        super(nodeName, null);

        if (literals == null) {
            throw new BadLiteralException(true);
        }

        if (literals.length == 0) {
            throw new BadLiteralException(false);
        }

        this.literals = literals;
    }

    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.MULTI_LITERAL;
    }

    @Override
    public <Source> String parseArgument(CommandContext<Source> cmdCtx, String key, CommandArguments previousArgs) {
        throw new IllegalStateException("Can not parse MultiLiteralArgument");
    }


    @Override
    public String[] getLiterals() {
        return literals;
    }
}
