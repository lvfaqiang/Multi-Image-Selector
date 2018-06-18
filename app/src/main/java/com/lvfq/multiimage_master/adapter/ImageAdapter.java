package com.lvfq.multiimage_master.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lvfq.multiimage_master.MainActivity;
import com.lvfq.multiimage_master.R;
import com.lvfq.multiimage_master.impl.IDeletePicCallback;
import com.lvfq.multiimage_master.util.ImageLoadUtil;
import com.lvfq.multiimage_master.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import me.lvfq.multi_image_selector.MultiImageSelector;
import me.lvfq.multi_image_selector.MultiImageShowBig;


/**
 * ImageAdapter
 *
 * @author lvfq
 * @date 2017/4/4 下午11:38
 * @mainFunction :
 */

public class ImageAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private IDeletePicCallback callback;
    private int maxCount = 9;

    private boolean isShowCamera;
    private boolean isSingle;

    public void setCallback(IDeletePicCallback callback) {
        this.callback = callback;
    }

    public ImageAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     * 设置最多可选图片数量
     *
     * @param maxCount
     */
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * 是否显示拍照
     *
     * @param showCamera
     */
    public void setShowCamera(boolean showCamera) {
        isShowCamera = showCamera;
    }

    /**
     * 是否单选
     *
     * @param single
     */
    public void setSingle(boolean single) {
        isSingle = single;
        if (isSingle) {
            maxCount = 1;
        }
    }

    @Override
    public int getCount() {
        if (list == null || list.size() <= 0) {
            return 1;
        } else {
            if (list.size() >= maxCount) {
                return maxCount;
            } else {
                return list.size() + 1;
            }
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void isShowAdd(ImageView iv1, ImageView iv2, ImageView iv3, boolean isShow) {
        iv1.setVisibility(isShow ? View.VISIBLE : View.GONE);
        iv2.setVisibility(isShow ? View.GONE : View.VISIBLE);
        iv3.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pic_add, parent, false);
        }
        ImageView iv_del = ViewHolder.get(convertView, R.id.iv_item_del);
        ImageView iv_add = ViewHolder.get(convertView, R.id.iv_item_add);
        final ImageView iv_pic = ViewHolder.get(convertView, R.id.iv_item_pic);
        if (list.size() >= maxCount && position == maxCount - 1) {
            isShowAdd(iv_add, iv_del, iv_pic, false);
            ImageLoadUtil.loadImg(context, list.get(position), iv_pic);
        } else {
            if (position == list.size()) {
                isShowAdd(iv_add, iv_del, iv_pic, true);
            } else {
                isShowAdd(iv_add, iv_del, iv_pic, false);
                ImageLoadUtil.loadImg(context, list.get(position), iv_pic);
            }
        }

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiImageSelector selector = MultiImageSelector.create()
                        .maxCount(maxCount - list.size())
                        .showCamera(isShowCamera);
                if (isSingle) {
                    selector.single();
                } else selector.multi();
                selector.start((Activity) context, MainActivity.REQUEST_IMAGE);
            }
        });
        final int pos = position;
        iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.callBack(pos);
                }
                notifyDataSetChanged();
            }
        });

        iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, ImagePagerActivity.class);
//                // 设置显示的当前图片下标
//                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, pos);
//                // 设置显示的图片地址列表
//                intent.putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, (ArrayList<String>) list);
//                context.startActivity(intent);
                MultiImageShowBig.create()
                        .index(pos)
                        .imageUrls((ArrayList<String>) list)
                        .show(context);
            }
        });

        return convertView;
    }

}
