package example.zemoso.com.imageprocessing.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import example.zemoso.com.imageprocessing.runnables.ImageProcessingRunnable;
import example.zemoso.com.imageprocessing.R;
import example.zemoso.com.imageprocessing.databinding.ActivityMainBinding;
import example.zemoso.com.imageprocessing.executor_tasks.ImageProcessingExecutor;
import example.zemoso.com.imageprocessing.executor_tasks.SyncExecutor;
import example.zemoso.com.imageprocessing.view_controller.ImageViewController;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private int i = 100;
    private SyncExecutor syncExecutor;

    private final String TAG = this.getClass().getSimpleName();

    //region Lifecycle Method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ImageViewController controller = new ImageViewController();
        activityMainBinding.setImageController(controller);
        syncExecutor = new SyncExecutor(new ImageProcessingExecutor());
    }

    @Override
    protected void onResume() {
        super.onResume();
        processImage(R.drawable.saptarshi);
    }

    //endregion

    //region Image Processing Methods

    private void processImage(int imgResId){
        syncExecutor.execute(new ImageProcessingRunnable(imgResId, activityMainBinding));
    }

    //endregion


}
