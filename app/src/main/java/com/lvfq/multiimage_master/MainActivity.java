package com.lvfq.multiimage_master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.widget.GridView;
import android.widget.RadioGroup;

import com.lvfq.multiimage_master.adapter.ImageAdapter;
import com.lvfq.multiimage_master.impl.IDeletePicCallback;

import java.util.ArrayList;
import java.util.List;

import me.lvfq.multi_image_selector.MultiImageSelector;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    public static final int REQUEST_IMAGE = 1;

    private GridView gv_imgs;
    private RadioGroup rg;
    private RadioGroup rg_1;

    private ImageAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    boolean isShowCamera = false;   // 是否可选择相机
    boolean isSingle = false;   // 是否单选


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        rg.setOnCheckedChangeListener(this);
        rg_1.setOnCheckedChangeListener(this);

    }

    private void init() {

        gv_imgs = (GridView) findViewById(R.id.gv_imgs);
        rg = (RadioGroup) findViewById(R.id.rg);
        rg_1 = (RadioGroup) findViewById(R.id.rg_1);

        switch (rg.getCheckedRadioButtonId()) {
            case R.id.rb_hide_camrea:
                isShowCamera = false;
                break;
            case R.id.rb_show:
                isShowCamera = true;
                break;
        }
        switch (rg_1.getCheckedRadioButtonId()) {
            case R.id.rb_multi:
                isSingle = false;
                break;
            case R.id.rb_single:
                isSingle = true;
                break;
        }

        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new ImageAdapter(mList, this);
        mAdapter.setCallback(new IDeletePicCallback() {
            @Override
            public void callBack(int position) {
                mList.remove(position);
            }
        });
        mAdapter.setSingle(isSingle);
        mAdapter.setShowCamera(isShowCamera);
        gv_imgs.setAdapter(mAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_IMAGE) {
            List<String> list = MultiImageSelector.getResults(data);
            if (list != null && list.size() >= 0) {
                mList.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_multi:
                isSingle = false;
                break;
            case R.id.rb_single:
                isSingle = true;
                break;
            case R.id.rb_hide_camrea:
                isShowCamera = false;
                break;
            case R.id.rb_show:
                isShowCamera = true;
                break;
        }
        mList.clear();
        initAdapter();
    }
}
