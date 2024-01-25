package dev.rodriigo.minecraft.scheduler;


import dev.rodriigo.minecraft.BackendPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

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
     * So I made all methods to return NormalizedTask
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

    @Override
    public NormalizedTask run(Runnable runnable) {
        try {
            return new NormalizedTask(
                    globalRegionScheduler
                    .getClass()
                    .getMethod(
                            "execute",
                            Plugin.class,
                            Runnable.class
                    ).invoke(
                            globalRegionScheduler,
                            plugin,
                            runnable
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NormalizedTask runLater(Runnable runnable, long delayTicks) {
        try {
            return new NormalizedTask(
                    globalRegionScheduler
                            .getClass()
                            .getMethod(
                                    "runDelayed",
                                    Plugin.class,
                                    Consumer.class,
                                    long.class
                            ).invoke(
                                    globalRegionScheduler,
                                    plugin,
                                    getConsumer(runnable),
                                    delayTicks
                            )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public NormalizedTask runTimer(Runnable runnable, long delayTicks, long periodTicks) {
        try {
            return new NormalizedTask(
                    globalRegionScheduler
                    .getClass()
                    .getMethod(
                            "runAtFixedRate",
                            Plugin.class,
                            Consumer.class,
                            long.class,
                            long.class
                    ).invoke(
                            globalRegionScheduler,
                            plugin,
                            getConsumer(runnable),
                            delayTicks < 1 ? 1 : delayTicks,
                            periodTicks
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // Added: Execute tasks asynchronously
    public NormalizedTask runAsync(Runnable runnable) {
        return run(runnable);
    }

    @Override
    public NormalizedTask runAsyncLater(Runnable runnable, long delayTicks) {
        return runLater(runnable, delayTicks);
    }

    @Override
    public NormalizedTask runAsyncTimer(Runnable runnable, long delayTicks, long periodTicks) {
        return runTimer(runnable, delayTicks, periodTicks);
    }

    Consumer<?> getConsumer(Runnable runnable) {
        return (ignored) -> runnable.run();
    }

}
