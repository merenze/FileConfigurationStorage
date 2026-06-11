package com.jellyrekt.storage.configuration;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.junit.jupiter.api.Test;

class ConfigurationSectionStorageTest {

    private static class TestStorage extends ConfigurationSectionStorage {
        TestStorage(ConfigurationSectionProvider provider) {
            super(provider);
        }

        ConfigurationSection exposedSection() {
            return getConfigurationSection();
        }
    }

    @Test
    void getConfigurationSection_returnsProviderSection() {
        MemoryConfiguration section = new MemoryConfiguration();
        section.set("key", "value");

        TestStorage storage = new TestStorage(() -> section);

        assertSame(section, storage.exposedSection());
    }
}
