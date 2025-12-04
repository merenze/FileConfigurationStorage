package com.jellyrekt.storage.fileconfiguration.javaplugin;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.jellyrekt.storage.fileconfiguration.yaml.YamlConfigurationProvider;

public class JavaPluginConfigurationProvider extends YamlConfigurationProvider {

    public JavaPluginConfigurationProvider(JavaPlugin plugin) throws IOException {
        super(plugin.getDataFolder(), "config.yml");
    }
    
}
