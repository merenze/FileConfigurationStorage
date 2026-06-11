package com.jellyrekt.storage.fileconfiguration.javaplugin;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.jellyrekt.storage.fileconfiguration.yaml.YamlConfigurationProvider;

/**
 * @deprecated Use {@link com.jellyrekt.storage.configuration.file.javaplugin.JavaPluginConfigurationProvider} instead.
 */
@Deprecated(since = "1.2.0", forRemoval = true)
public class JavaPluginConfigurationProvider extends YamlConfigurationProvider {

    public JavaPluginConfigurationProvider(JavaPlugin plugin) throws IOException {
        super(plugin.getDataFolder(), "config.yml");
    }
    
}
