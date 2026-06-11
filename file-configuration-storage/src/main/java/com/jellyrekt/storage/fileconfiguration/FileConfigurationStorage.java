package com.jellyrekt.storage.fileconfiguration;

import java.util.function.Consumer;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * @deprecated Use {@link com.jellyrekt.storage.configuration.file.FileConfigurationStorage} instead.
 */
@Deprecated(since = "1.2.0", forRemoval = true)
public abstract class FileConfigurationStorage {
    private final FileConfigurationProvider configurationProvider;
    private Consumer<FileConfigurationStorage> changeListener;

    public FileConfigurationStorage(FileConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    protected FileConfiguration getFileConfiguration() {
        return configurationProvider.getFileConfiguration();
    }

    protected void notifyChange() {
        if (changeListener != null) {
            changeListener.accept(this);
        }
    }

    public void setChangeListener(Consumer<FileConfigurationStorage> changeListener) {
        this.changeListener = changeListener;
    }
}
