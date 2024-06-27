package dev.jorel.commandapi;

public class CommandAPIFabricConfig extends CommandAPIConfig<CommandAPIFabricConfig> {

    public CommandAPIFabricConfig() {
        super.setNamespace("");
    }

    @Override
    public CommandAPIFabricConfig usePluginNamespace() {
        throw new RuntimeException("This is mod, not a plugin! can not set plugin namespace");
    }

    @Override
    public CommandAPIFabricConfig setNamespace(String namespace) {
        if (namespace == null) {
            throw new NullPointerException("Default namespace can't be null!");
        }
        if (!CommandAPIHandler.NAMESPACE_PATTERN.matcher(namespace).matches()) {
            CommandAPI.logNormal(
                    "Did not set namespace to the provided '" + namespace + "' namespace because only 0-9, a-z, underscores, periods and hyphens are allowed!");
            return this;
        }
        return super.setNamespace(namespace);
    }

    @Override
    public CommandAPIFabricConfig instance() {
        return this;
    }
}
