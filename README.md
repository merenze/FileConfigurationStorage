# FileConfigurationStorage

Provides a standard API and abstraction library over Bukkit's FileConfiguration.

## Usage

**NOTE**: This package is hosted on GitHub packages.
You must [authenticate with a PAT](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-to-github-packages) to use it.

Add the following dependency to the `dependencies` section in your POM:

```xml
<dependency>
  <groupId>com.jellyrekt.storage</groupId>
  <artifactId>fileconfigurationstorage</artifactId>
  <version>1.1.0</version>
</dependency>
```

### Shading files

If you are using this in a Bukkit plugin, you likely need to shade the class files into your plugin JAR for them to be on the running server's classpath.
This is accomplished with Maven's shade plugin:

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

**TODO:** Add and host javadoc, and flesh out this README.
