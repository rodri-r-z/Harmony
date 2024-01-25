package dev.rodriigo.minecraft;

import dev.rodriigo.minecraft.annotation.Nullable;
import dev.rodriigo.minecraft.scheduler.GlobalScheduler;
import dev.rodriigo.minecraft.scheduler.NormalizedScheduler;
import dev.rodriigo.minecraft.internal.PluginLogger;
import dev.rodriigo.minecraft.internal.StandardBackendPlugin;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class BackendPlugin extends JavaPlugin implements StandardBackendPlugin {
    protected Path dataFolder;
    protected Server server;
    protected CommandSender console;
    protected PluginLogger logger;
    protected NormalizedScheduler scheduler;

    static BackendPlugin instance;

    @Override
    public void onEnable() {
        // Automatically create data folder if it doesn't exist
        dataFolder = getDataFolder().toPath();
        server = getServer();
        console = getServer().getConsoleSender();
        logger = new PluginLogger();
        scheduler = new GlobalScheduler().getScheduler();

        instance = this;

        if (!dataFolder.toFile().exists() && !dataFolder.toFile().mkdirs()) {
            throw new RuntimeException("Failed to create data folder. This may be due to insufficient permissions.");
        }
        whenInitialize();
    }

    @Override
    public void onDisable() {
        whenUnloaded();
    }

    @Override
    public void whenInitialize() {  }

    @Override
    public void whenUnloaded() {  }

    @Override
    @Nullable
    public InputStream resolveFrom(String resourcePath) {
        return getResource(resourcePath);
    }

    @Override
    public void saveFrom(String resourcePath) {
        saveResource(resourcePath, false);
    }

    @Override
    public void saveFrom(String resourcePath, boolean replace) {
        saveResource(resourcePath, replace);
    }

    @Override
    public void safeSaveFrom(String resourcePath) {
        // Recycle safeSaveFrom(String, boolean)
        safeSaveFrom(resourcePath, false);
    }

    @Override
    public void safeSaveFrom(String resourcePath, boolean replace) {
        // Use try-with-resources to ensure the file is closed
        // When we no longer need it
        try (final InputStream is = resolveFrom(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("File not found: " + resourcePath);
            }
            // Save the file
            saveFrom(resourcePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void safeSaveFrom(String resourcePath, Path destination) {
        safeSaveFrom(resourcePath, destination, false);
    }

    @Override
    public void safeSaveFrom(String resourcePath, Path destination, boolean replace) {
        // Check if the file already exists
        if (destination.toFile().exists()) return;

        // Use try-with-resources to ensure the file is closed
        // When we no longer need it
        try (final InputStream is = resolveFrom(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("File not found: " + resourcePath);
            }
            // Save the file
            Files.copy(is, destination);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BackendPlugin getInstance() {
        return instance;
    }

    public CommandSender getConsole() {
        return console;
    }

    @Override
    public PluginLogger getOwnLogger() {
        return logger;
    }

    @Override
    public NormalizedScheduler findScheduler() {
        return scheduler;
    }

    @Override
    public YamlConfiguration getConfiguration() throws RuntimeException {
        try (final InputStream is = resolveFrom("config.yml")) {
            if (is == null) {
                throw new RuntimeException("File not found: config.yml");
            }
            final File configFile = dataFolder.resolve("config.yml").toFile();

            if (!configFile.exists()) {
                safeSaveFrom("config.yml");
            }

            return YamlConfiguration.loadConfiguration(configFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}