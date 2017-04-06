package com.lvfq.multiimage_master;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.GridView;

import com.lvfq.multiimage_master.adapter.ImageAdapter;
import com.lvfq.multiimage_master.impl.IDeletePicCallback;

import java.util.ArrayList;
import java.util.List;

import me.lvfq.multi_image_selector.MultiImageSelectorActivity;

public class MainActivity extends FragmentActivity {

    public static final int REQUEST_IMAGE = 1;

    private GridView gv_imgs;
    private ImageAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        gv_imgs = (GridView) findViewById(R.id.gv_imgs);
        mAdapter = new ImageAdapter(mList, this);
        mAdapter.setCallback(new IDeletePicCallback() {
            @Override
            public void callBack(int position) {
                mList.remove(position);
            }
        });
        gv_imgs.setAdapter(mAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_IMAGE) {
            List<String> list = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (list != null && list.size() >= 0) {
                mList.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
