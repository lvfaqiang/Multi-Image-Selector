package me.lvfq.multi_image_selector.impl;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import me.lvfq.multi_image_selector.utils.IImageLoad;

/**
 * DefaultImgaloadImpl
 *
 * @author FaQiang on 2018/5/25 下午2:32
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */
public class DefaultImgaloadImpl implements IImageLoad {
    @Override
    public void loadImg(Context context, File file, int placeholderResId, int targetWidth, int targetHeight, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderResId)
                .centerCrop()
                .override(targetWidth, targetHeight);
//
        Glide.with(context).load(file).apply(options).into(imageView);
    }

    @Override
    public void loadImg(Context context, File file, int targetWidth, int targetHeight, ImageView imageView, int errorId) {
        RequestOptions options = new RequestOptions()
                .error(errorId)
                .centerCrop()
                .override(targetWidth, targetHeight);
        Glide.with(context).load(file).apply(options).into(imageView);
    }

    @Override
    public void resumeTag(Context context) {
        Glide.with(context).resumeRequests();
    }

    @Override
    public void pauseTag(Context context) {
        Glide.with(context).pauseRequests();
    }
}
