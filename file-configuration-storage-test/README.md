# file-configuration-storage-test

[![Maven Central](https://img.shields.io/maven-central/v/com.jellyrekt.storage/file-configuration-storage-test)](https://central.sonatype.com/artifact/com.jellyrekt.storage/file-configuration-storage-test)
[![javadoc](https://javadoc.io/badge2/com.jellyrekt.storage/file-configuration-storage-test/javadoc.svg)](https://javadoc.io/doc/com.jellyrekt.storage/file-configuration-storage-test)

Test utilities for [file-configuration-storage](../README.md). Provides pre-built fakes for use in unit tests, so you can test code that depends on `FileConfigurationProvider` without touching the file system.

## Compatibility

| file-configuration-storage-test | file-configuration-storage |
|----------------------------------|---------------------------|
| 1.0.x                            | 1.1.x                     |

## Installation

Add the following dependency to your POM with `<scope>test</scope>`:

```xml
<dependency>
  <groupId>com.jellyrekt.storage</groupId>
  <artifactId>file-configuration-storage-test</artifactId>
  <version>1.0.0</version>
  <scope>test</scope>
</dependency>
```

## Utilities

### `StringYamlConfigurationProvider`

A `FileConfigurationProvider` backed by a YAML string rather than a file. Pass it wherever a `FileConfigurationProvider` is expected in tests.

```java
var provider = new StringYamlConfigurationProvider("""
    default-currency: gold
    default-world-name: world
    """);

var config = new EconomyConfig(provider);

assertEquals("gold", config.getDefaultCurrency());
```

**Behaviour:**
- `getFileConfiguration()` — returns a parsed `YamlConfiguration`
- `reload()` — re-parses the original YAML string, discarding any in-memory mutations
- `save()` — no-op; there is no backing file

### `ConfigurationSectionProvider` via lambda

For code that depends on `ConfigurationSectionProvider` (read-only access to a `ConfigurationSection`), no named class is needed — use a lambda directly:

```java
MemoryConfiguration section = new MemoryConfiguration();
section.set("balance", 100);

ConfigurationSectionProvider provider = () -> section;
```
