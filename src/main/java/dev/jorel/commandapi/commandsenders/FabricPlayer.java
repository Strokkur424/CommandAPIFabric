package dev.jorel.commandapi.commandsenders;

import net.minecraft.world.entity.player.Player;

public class FabricPlayer implements AbstractPlayer<Player>, FabricCommandSender<Player> {

    private final Player player;

    public FabricPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        // TODO Implement later
        return false;
    }

    @Override
    public boolean isOp() {
        // TODO Implement later
        return false;
    }

    @Override
    public Player getSource() {
        return player;
    }
}
