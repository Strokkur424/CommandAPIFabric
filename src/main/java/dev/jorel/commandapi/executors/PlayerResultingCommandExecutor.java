package dev.jorel.commandapi.executors;

import dev.jorel.commandapi.commandsenders.FabricPlayer;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import net.minecraft.world.entity.player.Player;

@FunctionalInterface
public interface PlayerResultingCommandExecutor extends ResultingExecutor<Player, FabricPlayer> {

    int run(Player sender, CommandArguments args) throws WrapperCommandSyntaxException;

    @Override
    default int run(ExecutionInfo<Player, FabricPlayer> info) throws WrapperCommandSyntaxException {
        return run(info.sender(), info.args());
    }

    @Override
    default ExecutorType getType() {
        return ExecutorType.PLAYER;
    }

}
