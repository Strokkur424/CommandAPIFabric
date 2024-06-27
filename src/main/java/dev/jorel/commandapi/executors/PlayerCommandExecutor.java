package dev.jorel.commandapi.executors;

import dev.jorel.commandapi.CommandAPIExecutor;
import dev.jorel.commandapi.commandsenders.FabricPlayer;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PlayerCommandExecutor extends NormalExecutor<Player, FabricPlayer> {

    void run(Player sender, CommandArguments args) throws WrapperCommandSyntaxException;

    @Override
    default void run(@NotNull ExecutionInfo<Player, FabricPlayer> info) throws WrapperCommandSyntaxException {
        this.run(info.sender(), info.args());
    }

    @Override
    default ExecutorType getType() {
        return ExecutorType.PLAYER;
    }
}
