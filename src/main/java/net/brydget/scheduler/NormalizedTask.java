package net.brydget.scheduler;

public class NormalizedTask {

    final Object task;
    boolean isCancelled = false;

    public NormalizedTask(Object task) {
        this.task = task;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        isCancelled = true;
        try {
            task.getClass().getMethod("cancel").invoke(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
