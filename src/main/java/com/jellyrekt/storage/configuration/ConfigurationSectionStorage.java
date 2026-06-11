package com.jellyrekt.storage.configuration;

import org.bukkit.configuration.ConfigurationSection;

public abstract class ConfigurationSectionStorage {
    private final ConfigurationSectionProvider configurationSectionProvider;

    public ConfigurationSectionStorage(ConfigurationSectionProvider configurationSectionProvider) {
        this.configurationSectionProvider = configurationSectionProvider;
    }

    protected ConfigurationSection getConfigurationSection() {
        return configurationSectionProvider.getConfigurationSection();
    }
}
