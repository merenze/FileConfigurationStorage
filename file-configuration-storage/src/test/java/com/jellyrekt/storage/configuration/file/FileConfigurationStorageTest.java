package com.jellyrekt.storage.configuration.file;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Test;

class FileConfigurationStorageTest {

    private static class StubProvider implements FileConfigurationProvider {
        private final FileConfiguration config;
        int saveCalls = 0;

        StubProvider(FileConfiguration config) {
            this.config = config;
        }

        @Override public FileConfiguration getFileConfiguration() { return config; }
        @Override public void reload() {}
        @Override public void save() { saveCalls++; }
    }

    private static class TestStorage extends FileConfigurationStorage {
        TestStorage(FileConfigurationProvider provider) {
            super(provider);
        }

        void triggerChange() { notifyChange(); }
        FileConfiguration exposedConfig() { return getFileConfiguration(); }
    }

    @Test
    void getFileConfiguration_delegatesToProvider() {
        YamlConfiguration config = new YamlConfiguration();
        config.set("key", "value");
        TestStorage storage = new TestStorage(new StubProvider(config));

        assertSame(config, storage.exposedConfig());
    }

    @Test
    void notifyChange_withNoListener_doesNotThrow() {
        TestStorage storage = new TestStorage(new StubProvider(new YamlConfiguration()));

        assertDoesNotThrow(storage::triggerChange);
    }

    @Test
    void notifyChange_withListener_callsListenerWithStorage() {
        TestStorage storage = new TestStorage(new StubProvider(new YamlConfiguration()));
        AtomicReference<FileConfigurationStorage> received = new AtomicReference<>();
        storage.setChangeListener(received::set);

        storage.triggerChange();

        assertSame(storage, received.get());
    }

    @Test
    void save_delegatesToProvider() throws IOException {
        StubProvider provider = new StubProvider(new YamlConfiguration());
        TestStorage storage = new TestStorage(provider);

        storage.save();

        assertEquals(1, provider.saveCalls);
    }
}
