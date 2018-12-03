package developersancho.profilescreen.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import developersancho.profilescreen.R;

public class BindingUtils {
    @BindingAdapter("imageCircleUrl")
    public static void setImageUrl2(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).
                load(url).
                apply(RequestOptions.placeholderOf(R.drawable.load)).
                into(imageView);
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).
                load(url).
                apply(RequestOptions.placeholderOf(R.drawable.load)).
                into(imageView);
    }

    @BindingAdapter("count")
    public static void setText(TextView view, int value) {
        view.setText(String.valueOf(value));
    }
}
