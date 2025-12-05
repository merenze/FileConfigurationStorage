package com.jellyrekt.storage.fileconfiguration.yaml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.configuration.file.FileConfiguration;

import com.jellyrekt.storage.fileconfiguration.FileConfigurationProvider;
import com.jellyrekt.storage.fileconfiguration.FileConfigurationProviderManager;

public class YamlConfigurationProviderManager implements FileConfigurationProviderManager {
    private final Map<String, FileConfigurationProvider> configProviders = new HashMap<>();

    private final File parent;
    
    public YamlConfigurationProviderManager(File parent) {
        this.parent = parent;

        if (!parent.exists()) {
            parent.mkdirs();
        }
    }

    @Override
    public Optional<FileConfiguration> getFileConfiguration(String key) {
        var provider = configProviders.get(key);
        
        if (provider == null) {
            return Optional.empty();
        }

        var config = provider.getFileConfiguration();
        return Optional.ofNullable(config);
    }

    @Override
    public void registerFileConfiguration(String key) throws IOException {
        configProviders.put(key, new YamlConfigurationProvider(parent, key));
    }

    @Override
    public void save(String key) throws IOException {
        var provider = configProviders.get(key);
        if (provider != null) {
            provider.save();
        }
    }
    
}
