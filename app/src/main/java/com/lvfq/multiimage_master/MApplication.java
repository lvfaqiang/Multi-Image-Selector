package com.lvfq.multiimage_master;

import android.app.Application;

/**
 * MApplication
 *
 * @author FaQiang on 2018/5/25 下午2:47
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */
public class MApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

//        MultiImageLoader.getInstance().init(new IImageLoad() {
//            @Override
//            public void loadImg(Context context, File file, int placeholderResId, int targetWidth, int targetHeight, ImageView imageView) {
//
//            }
//
//            @Override
//            public void loadImg(Context context, File file, int targetWidth, int targetHeight, ImageView imageView, int errorId) {
//
//            }
//
//            @Override
//            public void resumeTag(Context context) {
//
//            }
//
//            @Override
//            public void pauseTag(Context context) {
//
//            }
//        });
    }
}
