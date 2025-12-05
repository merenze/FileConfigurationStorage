package com.jellyrekt.storage.fileconfiguration;

import java.io.IOException;
import java.util.Optional;

import org.bukkit.configuration.file.FileConfiguration;

public interface FileConfigurationProviderManager {
    Optional<FileConfiguration> getFileConfiguration(String key);

    void registerFileConfiguration(String key) throws IOException;

    void save(String key) throws IOException;
}
