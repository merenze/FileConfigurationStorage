package com.jellyrekt.storage.configuration.file.yaml;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.jellyrekt.storage.configuration.file.FileConfigurationProvider;

public class YamlConfigurationProvider implements FileConfigurationProvider {
    private final File file;
    private FileConfiguration config;

    public YamlConfigurationProvider(File parent, String fileName) throws IOException {
        file = new File(parent, fileName);
        reload();
    }

    @Override
    public FileConfiguration getFileConfiguration() {
        return config;
    }

    @Override
    public void reload() throws IOException {
        createFileIfNotExists();
        config = YamlConfiguration.loadConfiguration(file);
    }

    private void createFileIfNotExists() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    @Override
    public void save() throws IOException {
        createFileIfNotExists();
        config.save(file);
    }
}
