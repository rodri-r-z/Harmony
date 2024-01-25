package dev.rodriigo.minecraft.scheduler;


import dev.rodriigo.minecraft.BackendPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FoliaScheduler implements NormalizedScheduler {

    /*
     * Add support for Folia Scheduler
     * Folia works with a totally different scheduler
     * Rather than using the Bukkit scheduler, we will use the Folia scheduler
     * Original source: https://github.com/kangarko/CowCannon/blob/master/src/main/java/org/mineacademy/cowcannon/model/Scheduler.java
     *
     * Modified by Rodrigo R.
     * For compatibility purposes, I needed to create an interface
     * So the schedulers are compatible with each other
     * I did not used the return object itself
     * So I made all methods to return void
     *
     * Detailed comments:
     * The folia Scheduler works fine
     * However, it has a big problem with servers that doesn't use Paper
     * They'll get a ClassNotFoundException
     * Because it's importing PaperMC API
     * Spigot servers doesn't come with Paper API
     */

    // We'll need to load the region scheduler with reflection
    // As we're using Spigot API instead of Paper API
    final Object globalRegionScheduler = Bukkit.class.getMethod("getGlobalRegionScheduler").invoke(Bukkit.class);
    final static BackendPlugin plugin = BackendPlugin.getInstance();

    public FoliaScheduler() throws Exception {}

    public void run(Runnable runnable) {
        try {
            globalRegionScheduler
                    .getClass()
                    .getMethod(
                            "execute",
                            JavaPlugin.class,
                            Runnable.class
                    ).invoke(
                            globalRegionScheduler,
                            plugin,
                            runnable
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void runLater(Runnable runnable, long delayTicks) {
        try {
            globalRegionScheduler
                    .getClass()
                    .getMethod(
                            "runDelayed",
                            JavaPlugin.class,
                            Runnable.class,
                            long.class
                    ).invoke(
                            globalRegionScheduler,
                            plugin,
                            runnable,
                            delayTicks
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void runTimer(Runnable runnable, long delayTicks, long periodTicks) {
        try {
            globalRegionScheduler
                    .getClass()
                    .getMethod(
                            "runAtFixedRate",
                            JavaPlugin.class,
                            Runnable.class,
                            long.class,
                            long.class
                    ).invoke(
                            globalRegionScheduler,
                            plugin,
                            runnable,
                            delayTicks < 1 ? 1 : delayTicks,
                            periodTicks
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Added: Execute tasks asynchronously
    public void runAsync(Runnable runnable) {
        run(runnable);
    }

    public void runAsyncLater(Runnable runnable, long delayTicks) {
        runLater(runnable, delayTicks);
    }

    public void runAsyncTimer(Runnable runnable, long delayTicks, long periodTicks) {
        runTimer(runnable, delayTicks, periodTicks);
    }

}