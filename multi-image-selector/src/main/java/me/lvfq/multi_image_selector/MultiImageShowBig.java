package me.lvfq.multi_image_selector;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * MultiImageShowBig
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/10/23 上午9:03
 * @desc :
 */

public class MultiImageShowBig {
    private int curPosition = 0;
    private ArrayList<String> imgList;
    private static MultiImageShowBig imageShowBig;

    private MultiImageShowBig() {

    }

    public static MultiImageShowBig create() {
        if (imageShowBig == null) {
            imageShowBig = new MultiImageShowBig();
        }
        return imageShowBig;
    }

    /**
     * 设置当前显示的下标
     *
     * @param position
     * @return
     */
    public MultiImageShowBig index(int position) {
        curPosition = position;
        return imageShowBig;
    }

    /**
     * 设置需要显示的图片列表
     *
     * @param urls
     * @return
     */
    public MultiImageShowBig imageUrls(ArrayList<String> urls) {
        imgList = urls;
        return imageShowBig;
    }

    /**
     * 显示图片
     *
     * @param context
     */
    public void show(Context context) {
        if (imgList == null) {
            throw new RuntimeException("The imgUrls method parameter of MultiImageShowBig is Null");
        }
        if (curPosition >= imgList.size()) {
            curPosition = imgList.size() - 1;
        }
        if (curPosition < 0) {
            curPosition = 0;
        }
        startIntent(context);
    }

    private void startIntent(Context context) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        // 设置显示的当前图片下标
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, curPosition);
        // 设置显示的图片地址列表
        intent.putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, imgList);
        context.startActivity(intent);
    }

}
