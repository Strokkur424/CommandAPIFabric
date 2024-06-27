package dev.jorel.commandapi;

import net.minecraft.commands.CommandSource;

public interface FabricExecutable<Impl extends FabricExecutable<Impl>> extends PlatformExecutable<Impl, CommandSource> {



}
