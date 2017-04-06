package com.lvfq.multiimage_master.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lvfq.multiimage_master.R;
import com.lvfq.multiimage_master.impl.IDeletePicCallback;
import com.lvfq.multiimage_master.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import me.lvfq.multi_image_selector.ImagePagerActivity;
import me.lvfq.multi_image_selector.MultiImageSelectorActivity;

import static com.lvfq.multiimage_master.MainActivity.REQUEST_IMAGE;

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

    public void setCallback(IDeletePicCallback callback) {
        this.callback = callback;
    }

    public ImageAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list == null || list.size() <= 0) {
            return 1;
        } else {
            if (list.size() >= 9) {
                return 9;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pic_add, parent, false);
        }
        ImageView iv_del = ViewHolder.get(convertView, R.id.iv_item_del);
        ImageView iv_add = ViewHolder.get(convertView, R.id.iv_item_add);
        final ImageView iv_pic = ViewHolder.get(convertView, R.id.iv_item_pic);
        if (list.size() >= 9 && position == 8) {
            iv_add.setVisibility(View.GONE);
            iv_del.setVisibility(View.VISIBLE);
            iv_pic.setVisibility(View.VISIBLE);
            Glide.with(context).load(list.get(position)).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(iv_pic);
        } else {
            if (position == list.size()) {
                iv_add.setVisibility(View.VISIBLE);
                iv_del.setVisibility(View.GONE);
                iv_pic.setVisibility(View.GONE);
            } else {
                iv_add.setVisibility(View.GONE);
                iv_del.setVisibility(View.VISIBLE);
                iv_pic.setVisibility(View.VISIBLE);
                Glide.with(context).load(list.get(position)).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(iv_pic);
            }
        }

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MultiImageSelectorActivity.class);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9 - list.size());
                ((Activity) context).startActivityForResult(intent, REQUEST_IMAGE);
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
                Intent intent = new Intent(context, ImagePagerActivity.class);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, pos);
//                ArrayList<String> list = new ArrayList<String>();
//                list.add("file://" + img.path);
                intent.putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, (ArrayList<String>) list);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
