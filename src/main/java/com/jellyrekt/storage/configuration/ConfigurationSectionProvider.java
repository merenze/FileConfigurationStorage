package com.jellyrekt.storage.configuration;

import org.bukkit.configuration.ConfigurationSection;

@FunctionalInterface
public interface ConfigurationSectionProvider {
    ConfigurationSection getConfigurationSection();
}
