package dev.jorel.commandapi.executors;

import dev.jorel.commandapi.commandsenders.FabricCommandSender;

public record FabricExecutionInfo<Sender>(

        Sender sender,

        FabricCommandSender<? extends Sender> senderWrapper,

        CommandArguments args

) implements ExecutionInfo<Sender, FabricCommandSender<? extends Sender>> {
}
