package net.brydget.harmony.world;

import org.bukkit.World;
import org.bukkit.WorldType;

public interface NormalizedWorldCreator {

    /**
     * Creates a new world with the specified parameters.
     *
     * @param  name                     the name of the world
     * @param  environment              the environment of the world
     * @param  seed                     the seed for world generation
     * @param  type                     the type of the world
     * @param  generateStructures       whether to generate structures
     * @param  customGenerationSettings custom generation settings for the world
     */
    void create(
            String name,
            World.Environment environment,
            String seed,
            WorldType type,
            boolean generateStructures,
            String customGenerationSettings
    );

    // Recycle the same method but with a limit of 1

    /**
     * Create a new object with the given name and seed.
     *
     * @param  name  the name of the object
     * @param  seed  the seed for the object
     */
    void create(String name, String seed);

    /**
     * Create a new instance with the given name.
     *
     * @param  name  the name of the instance
     */
    void create(String name);

    /**
     * Unloads the specified world.
     *
     * @param  name  the name of the world to be unloaded
     */
    void unload(String name);

    /**
     * Deletes the world with the given name.
     *
     * @param  name   the name of the world to be deleted
     */
    void delete(String name);

    /**
     * Clones a world from one location to another.
     *
     * @param  from  the source location to clone from
     * @param  to    the destination location to clone to
     */
    void clone(String from, String to);

}
