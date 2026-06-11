package com.jellyrekt.storage.fileconfiguration.yaml;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * @deprecated Use {@link com.jellyrekt.storage.configuration.file.yaml.MultiYamlConfigurationStorage} instead.
 */
@Deprecated(since = "1.2.0", forRemoval = true)
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
