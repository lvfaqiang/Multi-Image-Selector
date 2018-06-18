# MultiImage-Selector
项目中常用的多图选择，查看大图整理, 已适配 Android 7.0 权限。

效果图：

![image](https://github.com/lvfaqiang/Multi-Image-Selector/blob/master/multi_select.gif)

##### 依赖地址

    implementation 'com.lfq:MultiImageSelector:1.6'
    
##### Module 中默认引入 Glide 版本

    implementation 'com.github.bumptech.glide:glide:4.7.1'

##### 项目的 manifest 文件中，添加配置 Activity: `(从 1.5 版本开始，不用配置此项)`

    <activity android:name="me.lvfq.multi_image_selector.MultiImageSelectorActivity" /> 
    <activity android:name="me.lvfq.multi_image_selector.ImagePagerActivity" />

##### （可选）在 manifest 中给调用选择图片的 Activity 添加以下属性（ Fragment ，则配置其所在 Activity） `（解决部分机型拍照回调图片丢失问题）`
 
    android:configChanges="orientation|keyboardHidden|keyboard|screenSize"


### 配置加载图片方式 (默认 Glide)
这个配置通常也可以不用去管它，看个人心情。

在 Application 中调用一下代码 ：

        MultiImageLoader.getInstance().init(new IImageLoad() {
            @Override
            public void loadImg(Context context, File file, int placeholderResId, int targetWidth, int targetHeight, ImageView imageView) {

            }

            @Override
            public void loadImg(Context context, File file, int targetWidth, int targetHeight, ImageView imageView, int errorId) {

            }

            @Override
            public void resumeTag(Context context) {

            }

            @Override
            public void pauseTag(Context context) {

            }
        });

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
            List<String> list = MultiImageSelector.getResults(data); // getSingleResult(data) 获取单张图片结果   // - v1.4 新增用法
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

 - 适配 Android 7.0， targetVersion = 24.

 - 新增 MultiImageLoader 配置加载图片方式。

    调用方式：在 Application 中 调用 MultiImageLoader.getInstance().init(IImageload)

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
  