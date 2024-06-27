package dev.jorel.commandapi;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface CommandAPIVersionHandler {
    @Contract(" -> new")
    static @NotNull CommandAPIPlatform<?, ?, ?> getPlatform() {
        return new CommandAPIFabric();
    }
}
