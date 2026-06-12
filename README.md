# FileConfigurationStorage

[![Maven Central](https://img.shields.io/maven-central/v/com.jellyrekt.storage/file-configuration-storage)](https://central.sonatype.com/artifact/com.jellyrekt.storage/file-configuration-storage) 
[![javadoc](https://javadoc.io/badge2/com.jellyrekt.storage/file-configuration-storage/javadoc.svg)](https://javadoc.io/doc/com.jellyrekt.storage/file-configuration-storage)

Provides a standard API and abstraction layer over Bukkit's `FileConfiguration`.

## Installation

Add the following dependency to the `dependencies` section in your POM:

```xml
<dependency>
  <groupId>com.jellyrekt.storage</groupId>
  <artifactId>file-configuration-storage</artifactId>
  <version>1.2.0</version>
</dependency>
```

### Shading

If you are using this in a Bukkit plugin, shade the library into your plugin JAR so it is available on the server's classpath at runtime:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.5.3</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## Usage

### Single config file backed by a plugin's data folder

Extend `FileConfigurationStorage` and inject a `FileConfigurationProvider` to read and write your plugin's `config.yml`.

```java
public class MyPluginConfig extends FileConfigurationStorage {

    public MyPluginConfig(FileConfigurationProvider provider) {
        super(provider);
    }

    public String getWelcomeMessage() {
        return getFileConfiguration().getString("welcome-message", "Hello!");
    }
}
```

Wire it up in your plugin's `onEnable()` using `JavaPluginConfigurationProvider`, which automatically targets `config.yml` in the plugin's data folder:

```java
@Override
public void onEnable() {
    MyPluginConfig config = new MyPluginConfig(new JavaPluginConfigurationProvider(this));
    getLogger().info(config.getWelcomeMessage());
}
```

To reload from disk or flush changes back:

```java
FileConfigurationProvider provider = new JavaPluginConfigurationProvider(this);
provider.reload(); // re-reads from disk
provider.save();   // writes in-memory changes back to disk
```

---

### Multiple YAML files in a directory

Use `MultiYamlConfigurationStorage` when each logical entity has its own YAML file (e.g. one file per player, world, or arena).

```java
public class PlayerDataStorage extends MultiYamlConfigurationStorage {

    public PlayerDataStorage(YamlConfigurationProviderManager manager) {
        super(manager);
    }

    public void loadPlayer(UUID playerId) throws IOException {
        registerConfiguration(playerId.toString()); // loads <dataFolder>/players/<uuid>.yml
    }

    public int getBalance(UUID playerId) {
        return getConfiguration(playerId.toString())
            .map(config -> config.getInt("balance", 0))
            .orElse(0);
    }

    public void savePlayer(UUID playerId) throws IOException {
        save(playerId.toString());
    }
}
```

Create the storage with a `YamlConfigurationProviderManager` pointed at your directory:

```java
File playersDir = new File(getDataFolder(), "players");
YamlConfigurationProviderManager manager = new YamlConfigurationProviderManager(playersDir);
PlayerDataStorage storage = new PlayerDataStorage(manager);
```

---

### Decoupling domain code from Bukkit via `FileConfigurationProvider`

Inject `FileConfigurationProvider` into domain classes to keep Bukkit's `FileConfiguration` out of your core logic and improve testability:

```java
public class EconomyConfig {
    private final FileConfigurationProvider provider;

    public EconomyConfig(FileConfigurationProvider provider) {
        this.provider = provider;
    }

    public String getDefaultCurrency() {
        return provider.getFileConfiguration().getString("default-currency", "gold");
    }
}
```

In tests, supply a fake provider that returns a pre-populated `YamlConfiguration` without touching the file system. In production, supply a `JavaPluginConfigurationProvider` or a `YamlConfigurationProvider` pointed at the appropriate file.

See [file-configuration-storage-test](file-configuration-storage-test/README.md) for ready-made test utilities including `StringYamlConfigurationProvider`.
