package me.lvfq.multi_image_selector.utils;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

import me.lvfq.multi_image_selector.impl.DefaultImgaloadImpl;

/**
 * ImageLoadUtil
 *
 * @author lvfq
 * @date 2017/4/4 下午10:00
 * @mainFunction :
 */

public class MultiImageLoader {

    private IImageLoad imageLoad = new DefaultImgaloadImpl();

    public static MultiImageLoader getInstance() {
        return SingleHelper.instance;
    }

    private static class SingleHelper {
        private static final MultiImageLoader instance = new MultiImageLoader();
    }

    public void init(IImageLoad imageLoad) {
        if (imageLoad != null) {
            this.imageLoad = imageLoad;
        }
    }

    public void loadImg(Context context, File file, int placeholderResId, int targetWidth, int targetHeight, ImageView imageView) {
        imageLoad.loadImg(context, file, placeholderResId, targetWidth, targetHeight, imageView);
    }


    public void loadImg(Context context, File file, int targetWidth, int targetHeight, ImageView imageView, int errorId) {
        imageLoad.loadImg(context, file, targetWidth, targetHeight, imageView, errorId);
    }

    public void resumeTag(Context context) {
        imageLoad.resumeTag(context);
    }

    public void pauseTag(Context context) {
        imageLoad.pauseTag(context);
    }
}
