package dev.jorel.commandapi.executors;

import dev.jorel.commandapi.commandsenders.FabricPlayer;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import net.minecraft.world.entity.player.Player;

@FunctionalInterface
public interface PlayerResultingExecutionInfo extends ResultingExecutor<Player, FabricPlayer> {

    @Override
    int run(ExecutionInfo<Player, FabricPlayer> info) throws WrapperCommandSyntaxException;

}
