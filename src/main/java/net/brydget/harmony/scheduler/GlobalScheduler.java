package net.brydget.harmony.scheduler;

import org.bukkit.Bukkit;

/*
* The folia Scheduler works fine
* However, it has a big problem with servers that doesn't use Paper
* They'll get a ClassNotFoundException
* Because it's importing PaperMC API
* Spigot servers doesn't come with Paper API
* */

public class GlobalScheduler {
    private static final boolean isFolia = Bukkit.getVersion().contains("Folia");
    private final NormalizedScheduler scheduler;

    public GlobalScheduler() {
        // Check for Folia and instance the scheduler
        // If not, use the Bukkit scheduler
        if (isFolia) {
            try {
                scheduler = new FoliaScheduler();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            scheduler = new SpigotScheduler();
        }
    }

    /**
     * Get the scheduler.
     *
     * @return         	the scheduler
     */
    public NormalizedScheduler getScheduler() {
        return scheduler;
    }
}