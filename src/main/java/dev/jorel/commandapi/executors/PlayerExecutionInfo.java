package dev.jorel.commandapi.executors;

import dev.jorel.commandapi.commandsenders.FabricPlayer;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import net.minecraft.world.entity.player.Player;

@FunctionalInterface
public interface PlayerExecutionInfo extends NormalExecutor<Player, FabricPlayer> {

    void run(ExecutionInfo<Player, FabricPlayer> info) throws WrapperCommandSyntaxException;

}