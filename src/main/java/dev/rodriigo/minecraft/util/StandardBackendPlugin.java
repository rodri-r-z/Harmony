package dev.rodriigo.minecraft.util;

import java.io.InputStream;
import java.nio.file.Path;

public interface StandardBackendPlugin {

    void whenInitialize();
    void whenUnloaded();

    InputStream resolveFrom(String resourcePath);

    void saveFrom(String resourcePath);

    void saveFrom(String resourcePath, boolean replace);

    void safeSaveFrom(String resourcePath);

    void safeSaveFrom(String resourcePath, boolean replace);

    void safeSaveFrom(String resourcePath, Path destination);

    void safeSaveFrom(String resourcePath, Path destinationPath, boolean replace);

    PluginLogger getOwnLogger();
}