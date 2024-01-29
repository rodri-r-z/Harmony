package net.brydget.harmony.internal;

import net.brydget.harmony.packet.RegisteredPacketMode;
import net.brydget.harmony.scheduler.NormalizedScheduler;
import net.brydget.harmony.world.NormalizedWorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
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

    String getProvidedPluginName();

    PluginLogger getOwnLogger();

    NormalizedScheduler findScheduler();

    YamlConfiguration getConfiguration();

    CommandSender getConsole();

    boolean isLegacy();
    boolean isFolia();
    RegisteredPacketMode initPacketMode();

    String getServerVersion();

    NormalizedWorldCreator getWorldCreator();
    void saveResourceFolder(String resourcePath, Path destinationPath, boolean replace);
    void saveResourceFolder(String resourcePath, Path destination);
    void saveResourceFolder(String resourcePath);
}