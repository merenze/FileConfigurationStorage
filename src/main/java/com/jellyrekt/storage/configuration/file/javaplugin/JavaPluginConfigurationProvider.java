package com.jellyrekt.storage.configuration.file.javaplugin;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.jellyrekt.storage.configuration.file.yaml.YamlConfigurationProvider;

public class JavaPluginConfigurationProvider extends YamlConfigurationProvider {

    public JavaPluginConfigurationProvider(JavaPlugin plugin) throws IOException {
        super(plugin.getDataFolder(), "config.yml");
    }
    
}
