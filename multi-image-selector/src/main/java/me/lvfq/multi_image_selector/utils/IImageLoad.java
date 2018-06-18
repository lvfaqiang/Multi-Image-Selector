package me.lvfq.multi_image_selector.utils;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

/**
 * IImageLoad
 *
 * @author FaQiang on 2018/5/23 下午3:22
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */
public interface IImageLoad {

    void loadImg(Context context, File file, int placeholderResId, int targetWidth, int targetHeight, ImageView imageView);

    void loadImg(Context context, File file, int targetWidth, int targetHeight, ImageView imageView, int errorId);

    void resumeTag(Context context);

    void pauseTag(Context context);
}
