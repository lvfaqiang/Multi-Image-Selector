package me.lvfq.multi_image_selector.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * ImageLoadUtil
 *
 * @author lvfq
 * @date 2017/4/4 下午10:00
 * @mainFunction :
 */

public class ImageLoadUtil {

    public static void loadImg(Context context, File file, int placeholderResId, int targetWidth, int targetHeight, ImageView imageView) {
        Glide.with(context).load(file).placeholder(placeholderResId).override(targetWidth, targetHeight).centerCrop().into(imageView);
    }


    public static void loadImg(Context context, File file, int targetWidth, int targetHeight, ImageView imageView, int errorId) {
        Glide.with(context).load(file).error(errorId).override(targetWidth, targetHeight).centerCrop().into(imageView);
    }

    public static void resumeTag(Context context) {
        Glide.with(context).resumeRequests();
    }

    public static void pauseTag(Context context) {
        Glide.with(context).resumeRequests();
    }
}
