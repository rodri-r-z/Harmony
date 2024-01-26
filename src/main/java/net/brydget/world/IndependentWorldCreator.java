package net.brydget.world;

import net.brydget.BackendPlugin;
import net.brydget.annotation.NeverNull;
import net.brydget.annotation.Nullable;
import net.brydget.scheduler.NormalizedScheduler;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class IndependentWorldCreator implements NormalizedWorldCreator {
    // World creator for servers without Multiverse Core
    Location DEFULT_LOCATION = Bukkit.getWorld("world").getSpawnLocation();
    NormalizedScheduler scheduler = BackendPlugin.getInstance().findScheduler();

    @Override
    public void create(
            @NeverNull
            String name,
            @NeverNull
            World.Environment environment,
            @Nullable
            String seed,
            @NeverNull
            WorldType type,
            boolean generateStructures,
            @Nullable
            String customGenerationSettings
    ) {
        final WorldCreator creator = new WorldCreator(name);
        creator.environment(environment);
        if (seed != null) {
            creator.seed(Long.parseLong(seed));
        }
        creator.type(type);
        creator.generateStructures(generateStructures);
        if (customGenerationSettings != null) {
            creator.generator(customGenerationSettings);
        }
        Bukkit.createWorld(creator);
    }

    @Override
    public void create(
            @NeverNull
            String name,
            @Nullable
            String seed
    ) {
        create(
                name,
                World.Environment.NORMAL,
                seed,
                WorldType.NORMAL,
                true,
                null
        );
    }

    @Override
    public void create(@NeverNull String name) {
        create(
                name,
                World.Environment.NORMAL,
                null,
                WorldType.NORMAL,
                true,
                null
        );
    }

    @Override
    public void unload(@NeverNull String name) {
        // Move all players out of the world
        final List<Player> players = Bukkit.getWorld(name).getPlayers();
        for (final Player player : players) {
            player.teleport(DEFULT_LOCATION);
        }
        Bukkit.unloadWorld(name, true);
    }

    @Override
    public void delete(String name) {
        unload(name);
        // This can take a while
        // To prevent lagging the server, we delete the folder in the background
        scheduler.runAsync(() -> delete(Paths.get(name)));
    }

    @Override
    public void clone(String from, String to) {
        Bukkit.createWorld(
                WorldCreator.name(to)
                        .copy(
                                Bukkit.getWorld(from)
                        )
        );
    }

    void delete(Path directory) {
        try (Stream<Path> t = Files.walk(directory)
                .sorted(Comparator.reverseOrder())) {
                    t.forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            Files.delete(directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
