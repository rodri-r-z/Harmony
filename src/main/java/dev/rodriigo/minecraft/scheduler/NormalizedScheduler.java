package dev.rodriigo.minecraft.scheduler;

public interface NormalizedScheduler {
    /**
     * Run the given Runnable.
     *
     * @param  runnable   the Runnable to be executed */
    void run(Runnable runnable);
    /**
     * Runs the specified Runnable with a delay specified in ticks.
     *
     * @param  runnable     the Runnable to be executed
     * @param  delayTicks   the delay in ticks before executing the Runnable
     */
    void runLater(Runnable runnable, long delayTicks);
    /**
     * Runs the specified Runnable with the specified delay and period.
     *
     * @param  runnable     the Runnable to be run
     * @param  delayTicks   the initial delay in ticks before the task is first run
     * @param  periodTicks  the period in ticks between successive task runs
     */
    void runTimer(Runnable runnable, long delayTicks, long periodTicks);
    /**
     * Runs the given Runnable asynchronously.
     *
     * @param  runnable   the runnable to be run
     */
    void runAsync(Runnable runnable);
    /**
     * Runs the specified Runnable after the specified delay.
     *
     * @param  runnable    the Runnable to be executed
     * @param  delayTicks  the delay in ticks before execution
     */
    void runAsyncLater(Runnable runnable, long delayTicks);
    /**
     * Runs the specified runnable after a specified delay and then repeatedly at a specified interval.
     *
     * @param  runnable      the runnable to be executed
     * @param  delayTicks    the initial delay in ticks
     * @param  periodTicks   the interval in ticks
     */
    void runAsyncTimer(Runnable runnable, long delayTicks, long periodTicks);
}