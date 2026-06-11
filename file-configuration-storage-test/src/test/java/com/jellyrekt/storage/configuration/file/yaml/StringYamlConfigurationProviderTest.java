package com.jellyrekt.storage.configuration.file.yaml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class StringYamlConfigurationProviderTest {

    private static final String YAML = "greeting: hello\ncount: 42\n";

    @Test
    void getFileConfiguration_returnsConfigWithExpectedValues() {
        var provider = new StringYamlConfigurationProvider(YAML);

        var config = provider.getFileConfiguration();

        assertEquals("hello", config.getString("greeting"));
        assertEquals(42, config.getInt("count"));
    }

    @Test
    void reload_reparsesOriginalYaml() {
        var provider = new StringYamlConfigurationProvider(YAML);
        provider.getFileConfiguration().set("greeting", "mutated");

        provider.reload();

        assertEquals("hello", provider.getFileConfiguration().getString("greeting"));
    }

    @Test
    void save_doesNotThrow() {
        var provider = new StringYamlConfigurationProvider(YAML);

        assertDoesNotThrow(provider::save);
    }

    @Test
    void getFileConfiguration_afterConstruction_isNotNull() {
        var provider = new StringYamlConfigurationProvider(YAML);

        assertNotNull(provider.getFileConfiguration());
    }
}
