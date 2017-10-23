package me.lvfq.multi_image_selector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiImageSelector
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/10/22 下午11:48
 * @desc :
 */

public class MultiImageSelector {
    public static final String EXTRA_RESULT = MultiImageSelectorActivity.EXTRA_RESULT;

    private boolean isShowCamera = true;    // 默认显示相机
    private int maxCount = 9;   // 默认最大 9 张图
    private int mode = MultiImageSelectorActivity.MODE_MULTI;   // 默认多选
    private ArrayList<String> mSelImgs;

    private static MultiImageSelector mSelector;

    private MultiImageSelector() {
    }

    public static MultiImageSelector create() {
        if (mSelector == null) {
            mSelector = new MultiImageSelector();
        }
        return mSelector;
    }

    /**
     * 获取结果
     *
     * @param data
     * @return
     */
    public static List<String> getResults(Intent data) {
        return data.getStringArrayListExtra(EXTRA_RESULT);
    }

    /**
     * 是否可选相机
     *
     * @param bool
     * @return
     */
    public MultiImageSelector showCamera(boolean bool) {
        isShowCamera = bool;
        return mSelector;
    }

    /**
     * 设置最大可选数量
     *
     * @param count
     * @return
     */
    public MultiImageSelector maxCount(int count) {
        maxCount = count;
        return mSelector;
    }

    /**
     * 设置默认已选择的图片
     *
     * @param list
     * @return
     */
    public MultiImageSelector selectedImgs(ArrayList<String> list) {
        mSelImgs = list;
        return mSelector;
    }

    /**
     * 单选模式
     *
     * @return
     */
    public MultiImageSelector single() {
        mode = MultiImageSelectorActivity.MODE_SINGLE;
        return mSelector;
    }

    /**
     * 多选模式
     *
     * @return
     */
    public MultiImageSelector multi() {
        mode = MultiImageSelectorActivity.MODE_MULTI;
        return mSelector;
    }


    public void start(Activity activity, int requestCode) {
        activity.startActivityForResult(initIntent(activity), requestCode);
    }

    public void start(Fragment fragment, int requestCode) {
        fragment.startActivityForResult(initIntent(fragment.getContext()), requestCode);
    }

    public void start(android.app.Fragment fragment, int requestCode) {
        fragment.startActivityForResult(initIntent(fragment.getActivity()), requestCode);
    }

    private Intent initIntent(Context context) {
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, isShowCamera);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxCount);
        if (mSelImgs != null) {
            intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelImgs);
        }
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, mode);
        return intent;
    }

}
