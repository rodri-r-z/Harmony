package net.brydget.world;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;

public class WorldCreatorAPI implements NormalizedWorldCreator {
    static MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
    static MVWorldManager worldManager = core.getMVWorldManager();

    // Utility class to create worlds
    // Using the multiverse core API

    @Override
    public void create(
            String name,
            World.Environment environment,
            String seed,
            WorldType type,
            boolean generateStructures,
            String customGenerationSettings
    ) {
        worldManager.addWorld(
                name,
                environment,
                seed,
                type,
                generateStructures,
                customGenerationSettings
        );
    }

    // Recycle the same method but with a limit of 1

    @Override
    public void create(
            String name,
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
    public void create(
            String name
    ) {
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
    public void unload(String name) {
        worldManager.unloadWorld(name);
    }

    @Override
    public void delete(String name) {
        worldManager.deleteWorld(name);
    }

    @Override
    public void clone(String from, String to) {
        worldManager.cloneWorld(from, to);
    }

}
