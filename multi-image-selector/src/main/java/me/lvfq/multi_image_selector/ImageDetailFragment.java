package me.lvfq.multi_image_selector;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import me.lvfq.multi_image_selector.view.bigimage.PhotoViewAttacher;


/**
 * ����ͼƬ��ʾFragment
 */
public class ImageDetailFragment extends Fragment {
    private String mImageUrl;
    private ImageView mImageView;
    private ProgressBar progressBar;
    private PhotoViewAttacher mAttacher;

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url")
                : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.image_detail_fragment,
                container, false);
        mImageView = (ImageView) v.findViewById(R.id.image);
        mAttacher = new PhotoViewAttacher(mImageView);

        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                getActivity().finish();
            }
        });

        progressBar = (ProgressBar) v.findViewById(R.id.loading);
        return v;
    }

    //
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Glide.with(getActivity()).load(mImageUrl).apply(new RequestOptions().placeholder(R.drawable.default_error)).into(new SimpleTarget<Drawable>() {

            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                Log.i("lfq", "onLoadStarted  ");
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "图片加载失败", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                Log.i("lfq", "onResourceReady  ");
                progressBar.setVisibility(View.GONE);
                mImageView.setImageDrawable(resource);
                mAttacher.update();
            }

        });
//        Glide.with(getActivity()).load(mImageUrl).apply(RequestOptions.placeholderOf(R.drawable.default_error)).into(new GlideDrawableImageViewTarget(mImageView) {
//
//            @Override
//            public void onLoadStarted(Drawable placeholder) {
//                Log.i("lfq", "onLoadStarted  ");
//                progressBar.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                Log.i("lfq", "onLoadFailed  ");
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(getActivity(), "图片加载失败", Toast.LENGTH_SHORT).show();
//
//            }
//
//
//            @Override
//            protected void setResource(GlideDrawable resource) {
//                Log.i("lfq", "setResource : " + resource.toString());
//            }
//
//
//            @Override
//            public void onLoadCleared(Drawable placeholder) {
//                Log.i("lfq", "onLoadCleared  ");
//                super.onLoadCleared(placeholder);
//            }
//
//            @Override
//            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                Log.i("lfq", "onResourceReady");
//                progressBar.setVisibility(View.GONE);
//                super.onResourceReady(resource, glideAnimation);
//                mImageView.setImageDrawable(resource);
//                mAttacher.update();
//            }
//        });
//        final ImageLoader imageLoader = ImageLoader.getInstance();
//        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
//        imageLoader.getInstance().displayImage(mImageUrl, mImageView,
//                new SimpleImageLoadingListener() {
//                    @Override
//                    public void onLoadingStarted(String imageUri, View view) {
//                        progressBar.setVisibility(View.VISIBLE);
//                    }
//
//                    @Override
//                    public void onLoadingFailed(String imageUri, View view,
//                                                FailReason failReason) {
//                        String message = null;
//                        switch (failReason.getType()) {
//                            case IO_ERROR:
//                                message = "下载错误";
//                                break;
//                            case DECODING_ERROR:
//                                message = "图片无法显示";
//                                break;
//                            case NETWORK_DENIED:
//                                message = "网络有问题，无法下载";
//                                break;
//                            case OUT_OF_MEMORY:
//                                message = "图片太大无法显示";
//                                break;
//                            case UNKNOWN:
//                                message = "未知的错误";
//                                break;
//                        }
//                        Toast.makeText(getActivity(), message,
//                                Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onLoadingComplete(String imageUri, View view,
//                                                  Bitmap loadedImage) {
//                        progressBar.setVisibility(View.GONE);
//                        mAttacher.update();
//                    }
//                });
    }
}
