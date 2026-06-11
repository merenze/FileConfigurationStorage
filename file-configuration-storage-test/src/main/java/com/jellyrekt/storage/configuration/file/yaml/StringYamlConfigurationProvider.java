package com.jellyrekt.storage.configuration.file.yaml;

import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.jellyrekt.storage.configuration.file.FileConfigurationProvider;

/**
 * A {@link FileConfigurationProvider} backed by a YAML string rather than a file.
 * Intended for use in tests. {@link #save()} is a no-op.
 */
public class StringYamlConfigurationProvider implements FileConfigurationProvider {

    private final String yaml;
    private YamlConfiguration config;

    public StringYamlConfigurationProvider(String yaml) {
        this.yaml = yaml;
        reload();
    }

    @Override
    public FileConfiguration getFileConfiguration() {
        return config;
    }

    @Override
    public void reload() {
        config = new YamlConfiguration();
        try {
            config.loadFromString(yaml);
        } catch (InvalidConfigurationException e) {
            throw new IllegalArgumentException("Invalid YAML: " + e.getMessage(), e);
        }
    }

    /**
     * No-op. There is no backing file to write to.
     */
    @Override
    public void save() throws IOException {
        // intentional no-op
    }
}
