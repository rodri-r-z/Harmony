package net.brydget.harmony;

import net.brydget.harmony.annotation.Nullable;
import net.brydget.harmony.gui.GUIButtonUtil;
import net.brydget.harmony.packet.RegisteredPacketMode;
import net.brydget.harmony.scheduler.GlobalScheduler;
import net.brydget.harmony.scheduler.NormalizedScheduler;
import net.brydget.harmony.internal.PluginLogger;
import net.brydget.harmony.internal.StandardBackendPlugin;
import net.brydget.harmony.util.CommandUtil;
import net.brydget.harmony.world.IndependentWorldCreator;
import net.brydget.harmony.world.NormalizedWorldCreator;
import net.brydget.harmony.world.WorldCreatorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BackendPlugin extends JavaPlugin implements StandardBackendPlugin, Listener {
    protected Path dataFolder;
    protected Server server;
    protected CommandSender console;
    protected PluginLogger logger;
    protected NormalizedScheduler scheduler;
    protected ClassLoader classLoader;

    static BackendPlugin instance;
    static boolean isLegacy;
    static boolean isFolia;
    NormalizedWorldCreator worldCreator;
    String serverVersion;

    @Override
    public void onEnable() {
        // Automatically create data folder if it doesn't exist
        instance = this;

        dataFolder = getDataFolder().toPath();
        server = getServer();
        console = getServer().getConsoleSender();
        logger = new PluginLogger();
        scheduler = new GlobalScheduler().getScheduler();

        // Load all necessary stuff for CommandUtil
        CommandUtil._init();
        GUIButtonUtil._init();
        worldCreator = server.getPluginManager().isPluginEnabled("Multiverse-Core") ? new WorldCreatorAPI() : new IndependentWorldCreator();

        // Legacy servers are considered servers running 1.12 or lower
        // For text coloring purposes, we'll consider legacy versions under 1.16.5
        Matcher matcher = Pattern.compile("\\d\\.\\d+((\\.\\d+)?)").matcher(Bukkit.getVersion());
        if (!matcher.find()) {
            // This should NOT happen on official servers
            throw new RuntimeException("This server is invalid! Please contact the author of this plugin.");
        }

        final String foundVersion = matcher.group().replaceFirst("\\d\\.", "");

        // Remove the "1." prefix, this way we can compare the version without problems
        double baseVersion = Double.parseDouble(foundVersion);

        isLegacy = baseVersion < Double.parseDouble("16"); // 1.16
        serverVersion = "1."+baseVersion;

        // If the server is running Folia, the Bukkit version will include "Folia"
        isFolia = getServer().getBukkitVersion().contains("Folia");

        if (!dataFolder.toFile().exists() && !dataFolder.toFile().mkdirs()) {
            throw new RuntimeException("Failed to create data folder. This may be due to insufficient permissions.");
        }

        classLoader = getClassLoader();

        server.getPluginManager().registerEvents(this, this);

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

            final Path destPath = dataFolder.resolve(resourcePath);
            if (!replace && Files.exists(destPath)) return;

            // Save the file
            saveFrom(resourcePath, replace);
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

    @Override
    public String getProvidedPluginName() {
        return getDescription().getName();
    }

    @Override
    public boolean isLegacy() {
        return isLegacy;
    }

    @Override
    public boolean isFolia() {
        return isFolia;
    }

    @Override
    public RegisteredPacketMode initPacketMode() {
        // Check if ProtocolLib is enabled
        if (server.getPluginManager().isPluginEnabled("ProtocolLib")) {
            return new RegisteredPacketMode();
        }
        throw new RuntimeException("ProtocolLib is not enabled! Please enable ProtocolLib to use this plugin.");
    }

    @Override
    public String getServerVersion() {
        return serverVersion;
    }

    @Override
    public NormalizedWorldCreator getWorldCreator() {
        return worldCreator;
    }

    @Override
    public void saveResourceFolder(String folderName, Path destination, boolean replace) {
        try {
            List<Path> menusPaths = getPathsFromResourceJAR(folderName);
            if (!destination.toFile().exists() && !destination.toFile().mkdirs()) {
                throw new RuntimeException("Could not create " + destination + " folder");
            }

            for (Path path : menusPaths) {
                final String fileName = path.getFileName().toString();
                if (!destination.resolve(fileName).toFile().exists()) {
                    final InputStream stream = getResource(path.toString().replaceAll("^/", ""));
                    Files.copy(stream, destination.resolve(fileName));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveResourceFolder(String folderName, Path destination) {
        saveResourceFolder(folderName, destination, false);
    }

    @Override
    public void saveResourceFolder(String folderName) {
        saveResourceFolder(folderName, dataFolder.resolve(folderName), false);
    }

    List<Path> getPathsFromResourceJAR(String folder)
            throws URISyntaxException, IOException {
        List<Path> result;
        String jarPath = getClass().getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
        URI uri = URI.create("jar:file:" + jarPath);
        try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
            Stream<Path> stream = Files.walk(fs.getPath(folder));
            result = stream
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            stream.close();
        }

        return result;
    }
}