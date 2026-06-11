package com.jellyrekt.storage.fileconfiguration;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * @deprecated Use {@link com.jellyrekt.storage.configuration.file.FileConfigurationProvider} instead.
 */
@Deprecated(since = "1.2.0", forRemoval = true)
public interface FileConfigurationProvider {
    FileConfiguration getFileConfiguration();

    void reload() throws IOException;

    void save() throws IOException;
}
