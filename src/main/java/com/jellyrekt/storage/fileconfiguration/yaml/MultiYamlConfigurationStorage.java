package com.jellyrekt.storage.fileconfiguration.yaml;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

import org.bukkit.configuration.file.FileConfiguration;

public class MultiYamlConfigurationStorage {
    private final Optional<Consumer<MultiYamlConfigurationStorage>> changeListener = Optional.empty();
    
    private final YamlConfigurationProviderManager configProviderManager;

    public MultiYamlConfigurationStorage(YamlConfigurationProviderManager configurationProviderManager) {
        this.configProviderManager = configurationProviderManager;
    }

    public void save(String key) throws IOException {
        configProviderManager.save(key);
    }

    protected void notifyChange() {
        if (changeListener.isPresent()) {
            changeListener.get().accept(this);
        }
    }

    protected Optional<FileConfiguration> getConfiguration(String key) {
        return configProviderManager.getFileConfiguration(key + ".yml");
    }

    protected void registerConfiguration(String key) throws IOException {
        configProviderManager.registerFileConfiguration(key + ".yml");
    }
}
