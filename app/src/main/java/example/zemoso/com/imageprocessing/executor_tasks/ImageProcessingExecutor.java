package example.zemoso.com.imageprocessing.executor_tasks;

import android.support.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * Created by atif on 28/3/17.
 */

public class ImageProcessingExecutor implements Executor {

    @Override
    public void execute(@NonNull Runnable command) {
        new Thread(command).start();
    }
}
