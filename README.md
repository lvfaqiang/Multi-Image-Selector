# MultiImage-Selector
项目中常用的多图选择，查看大图整理, 已适配 Android 6.0 权限。

效果图：

![image](https://github.com/lvfaqiang/Multi-Image-Selector/blob/master/multi_select.gif)

依赖地址

    compile 'com.lfq:MultiImageSelector:1.4' 
    
需要在项目中引入 Glide 版本

    compile 'com.github.bumptech.glide:glide:4.2.0'

项目的 manifest 文件中，添加配置 Activity:

    <activity android:name="me.lvfq.multi_image_selector.MultiImageSelectorActivity" /> 
    <activity android:name="me.lvfq.multi_image_selector.ImagePagerActivity" />

### 选择图片用法：
链式调用( v1.4 新增)：

    MultiImageSelector.create()
                    .maxCount(int)  // default 9 
                    .multi()    // 默认多选 or single() 单选，
                    .selectedImgs(ArrayList<String>)    // 设置已选数据
                    .showCamera(true)   // 是否可选相机   default true
                    .start( Activity / v4.Fragment / Fragment, REQUEST_IMAGE);

    
传统调用：

    Intent intent = new Intent(context, MultiImageSelectorActivity.class);
    //设置剩余可选择数量
    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxCount - list.size());
    // 设置选择模式（SINGLE 单选， MULTI 多选)
    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, isSingle ? MultiImageSelectorActivity.MODE_SINGLE : MultiImageSelectorActivity.MODE_MULTI);
    // 是否显示相机
    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, isShowCamera);
    // 去重复（添加此参数之后，注意调整可选择数量）
    intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelImgs);
    ((Activity) context).startActivityForResult(intent, MainActivity.REQUEST_IMAGE);
    
接收：

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_IMAGE) {
            List<String> list = MultiImageSelector.getResults(data); // - v1.4 新增用法
            // List<String> list = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (list != null && list.size() >= 0) {
                mList.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

### 查看大图用法：

链式调用( v1.4 新增)：

    MultiImageShowBig.create()
                    .index(pos) // 初始显示的下标
                    .imageUrls((ArrayList<String>) list)    // 显示的图片地址列表
                    .show(context);

传统用法：

    Intent intent = new Intent(context, ImagePagerActivity.class);
    // 设置显示的当前图片下标
    intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, pos);
    // 设置显示的图片地址列表
    intent.putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, (ArrayList<String>) list);
    context.startActivity(intent);
    
#   
### Log

 - 2017-10-23
 
    新增图片选择链式调用 <br/>
    新增获取选择图片返回结果方法 <br/>
    新增查看大图链式调用 <br/>
    上传 Jcenter 1.4 版本
    
    添加修改日志
    
 - 2017-10-21
    
    调整 Glide 版本为 4.2.0 <br/>
    上传 Jcenter 1.3 版本
 
 - ...
  