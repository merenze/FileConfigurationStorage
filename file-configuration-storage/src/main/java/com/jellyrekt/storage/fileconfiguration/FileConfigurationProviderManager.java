package com.jellyrekt.storage.fileconfiguration;

import java.io.IOException;
import java.util.Optional;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * @deprecated Use {@link com.jellyrekt.storage.configuration.file.FileConfigurationProviderManager} instead.
 */
@Deprecated(since = "1.2.0", forRemoval = true)
public interface FileConfigurationProviderManager {
    Optional<FileConfiguration> getFileConfiguration(String key);

    void registerFileConfiguration(String key) throws IOException;

    void save(String key) throws IOException;
}
