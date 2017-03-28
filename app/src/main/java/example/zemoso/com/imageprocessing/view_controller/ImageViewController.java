package example.zemoso.com.imageprocessing.view_controller;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import example.zemoso.com.imageprocessing.R;

/**
 * Created by atif on 28/3/17.
 */

public class ImageViewController {

    private String imageUrl;

    public ImageViewController() {
    }

    public ImageViewController(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter({"bind:imagePath"})
    public static void loadImage(ImageView view, String path){
        if(path == null || path.equals("")){
            view.setImageResource(R.drawable.saptarshi);
        }else {
            Glide.with(view.getContext())
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate()
                    .into(view);
        }
    }

}
