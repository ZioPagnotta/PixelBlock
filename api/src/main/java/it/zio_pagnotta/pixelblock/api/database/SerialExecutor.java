package it.zio_pagnotta.pixelblock.api.database;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final Executor executor;
    private Runnable active;

    public SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    public synchronized void execute(@NotNull Runnable runnable) {
        tasks.offer(() -> {
            try {
                runnable.run();
            } finally {
                scheduleNext();
            }
        });

        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}