package net.brydget.scheduler;

import net.brydget.BackendPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class SpigotScheduler implements NormalizedScheduler {
    private final BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
    private final BackendPlugin plugin = BackendPlugin.getInstance();

    @Override
    public NormalizedTask run(Runnable runnable) {
        return new NormalizedTask(
            bukkitScheduler.runTask(plugin, runnable)
        );
    }

    @Override
    public NormalizedTask runLater(Runnable runnable, long delayTicks) {
        return new NormalizedTask(
                bukkitScheduler.runTaskLater(plugin, runnable, delayTicks)
        );
    }

    @Override
    public NormalizedTask runTimer(Runnable runnable, long delayTicks, long periodTicks) {
        return new NormalizedTask(
                bukkitScheduler.runTaskTimer(plugin, runnable, delayTicks, periodTicks)
        );
    }

    @Override
    public NormalizedTask runAsync(Runnable runnable) {
        return new NormalizedTask(
                bukkitScheduler.runTaskAsynchronously(plugin, runnable)
        );
    }

    @Override
    public NormalizedTask runAsyncLater(Runnable runnable, long delayTicks) {
        return new NormalizedTask(
                bukkitScheduler.runTaskLaterAsynchronously(plugin, runnable, delayTicks)
        );
    }

    @Override
    public NormalizedTask runAsyncTimer(Runnable runnable, long delayTicks, long periodTicks) {
        return new NormalizedTask(
                bukkitScheduler.runTaskTimerAsynchronously(plugin, runnable, delayTicks, periodTicks)
        );
    }
}
