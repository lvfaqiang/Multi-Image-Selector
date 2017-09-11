# MultiImage-Selector
项目中常用的多图选择，查看大图整理, 已适配 Android 6.0 权限。

效果图：

![image](https://github.com/lvfaqiang/Multi-Image-Selector/blob/master/multi_select.gif)

依赖地址

    compile 'com.lfq:MultiImageSelector:1.2' 
    
需要在项目中引入 Glide 版本

    compile 'com.github.bumptech.glide:glide:3.7.0'

项目的 manifest 文件中，添加配置 Activity:

    <activity android:name="me.lvfq.multi_image_selector.MultiImageSelectorActivity" /> 
    <activity android:name="me.lvfq.multi_image_selector.ImagePagerActivity" />

### 选择图片用法：

调用：

    Intent intent = new Intent(context, MultiImageSelectorActivity.class);
    //设置剩余可选择数量
    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxCount - list.size());
    // 设置选择模式（SINGLE 单选， MULTI 多选)
    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, isSingle ? MultiImageSelectorActivity.MODE_SINGLE : MultiImageSelectorActivity.MODE_MULTI);
    // 是否显示相机
    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, isShowCamera);
    ((Activity) context).startActivityForResult(intent, MainActivity.REQUEST_IMAGE);
    
接收：

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

### 查看大图用法：
    
    Intent intent = new Intent(context, ImagePagerActivity.class);
    // 设置显示的当前图片下标
    intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, pos);
    // 设置显示的图片地址列表
    intent.putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, (ArrayList<String>) list);
    context.startActivity(intent);