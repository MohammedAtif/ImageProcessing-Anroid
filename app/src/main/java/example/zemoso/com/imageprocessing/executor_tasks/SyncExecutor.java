package example.zemoso.com.imageprocessing.executor_tasks;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * Created by atif on 28/3/17.
 */

public class SyncExecutor implements Executor {

    private final String TAG = this.getClass().getSimpleName();

    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final Executor executor;
    private Runnable active;

    public SyncExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public synchronized void execute(@NonNull final Runnable command) {
        tasks.add(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } finally {
                    scheduleNext();
                }
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
