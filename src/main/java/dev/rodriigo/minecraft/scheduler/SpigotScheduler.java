package dev.rodriigo.minecraft.scheduler;

import dev.rodriigo.minecraft.BackendPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class SpigotScheduler implements NormalizedScheduler {
    private final BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
    private final BackendPlugin plugin = BackendPlugin.getInstance();

    @Override
    public void run(Runnable runnable) {
        bukkitScheduler.runTask(plugin, runnable);
    }

    @Override
    public void runLater(Runnable runnable, long delayTicks) {
        bukkitScheduler.runTaskLater(plugin, runnable, delayTicks);
    }

    @Override
    public void runTimer(Runnable runnable, long delayTicks, long periodTicks) {
        bukkitScheduler.runTaskTimer(plugin, runnable, delayTicks, periodTicks);
    }

    @Override
    public void runAsync(Runnable runnable) {
        bukkitScheduler.runTaskAsynchronously(plugin, runnable);
    }

    @Override
    public void runAsyncLater(Runnable runnable, long delayTicks) {
        bukkitScheduler.runTaskLaterAsynchronously(plugin, runnable, delayTicks);
    }

    @Override
    public void runAsyncTimer(Runnable runnable, long delayTicks, long periodTicks) {
        bukkitScheduler.runTaskTimerAsynchronously(plugin, runnable, delayTicks, periodTicks);
    }
}
