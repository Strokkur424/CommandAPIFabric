package dev.jorel.commandapi;

import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import dev.jorel.commandapi.executors.CommandExecutor;
import dev.jorel.commandapi.executors.ExecutorType;
import dev.jorel.commandapi.executors.FabricExecutionInfo;
import net.minecraft.commands.CommandSource;

public interface FabricExecutable<Impl extends FabricExecutable<Impl>> extends PlatformExecutable<Impl, CommandSource> {

    default Impl executes(CommandExecutor executor, ExecutorType... types) {
        if (types == null || types.length == 0) {
            getExecutor().addNormalExecutor(executor);
        } else {
            for (ExecutorType type : types) {
                getExecutor().addNormalExecutor(new CommandExecutor() {
                    @Override
                    public void run(CommandSource sender, CommandArguments args) throws WrapperCommandSyntaxException {
                        // TODO FIX THIS
                        //executor.executeWith(new FabricExecutionInfo<>(sender, CommandAPIFabric.get().wrapCommandSender(sender), args));
                    }

                    @Override
                    public ExecutorType getType() {
                        return type;
                    }
                });
            }
        }
        return instance();
    }

}
