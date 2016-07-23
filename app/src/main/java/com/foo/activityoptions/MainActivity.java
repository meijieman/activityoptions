package com.foo.activityoptions;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

// 大部分 api 需要 API 21
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private View mImage;
    private View mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_first).setOnClickListener(this);
        findViewById(R.id.btn_second).setOnClickListener(this);
        findViewById(R.id.iv_third).setOnClickListener(this);
        findViewById(R.id.iv_four).setOnClickListener(this);
        findViewById(R.id.btn_other).setOnClickListener(this);

        mImage = findViewById(R.id.image);
        mImage.setOnClickListener(this);
        mText = findViewById(R.id.text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_first: {
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeCustomAnimation(this, R.anim.slide_bottom_in, R.anim.slide_bottom_out);
                // 需要在 FirstActivity 的 onBackPressed 设置 ActivityCompat.finishAfterTransition();
                Intent intent = new Intent(this, FirstActivity.class);
                ActivityCompat.startActivity(this, intent, compat.toBundle());
            }
            break;
            case R.id.btn_second: {
                // 4.x上有用
                // 效果就是不断的放大一个 view，进而进行 activity 的过度
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                Intent intent = new Intent(this, SecondActivity.class);
                ActivityCompat.startActivity(this, intent, compat.toBundle());
            }
            break;
            case R.id.iv_third: {
                // 通过放大一个图片，最后过度到一个新的activity
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.img_01);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeThumbnailScaleUpAnimation(view, bm, 0, 0);
                Intent intent = new Intent(this, ThirdActivity.class);
                ActivityCompat.startActivity(this, intent, compat.toBundle());
            }
            break;
            case R.id.iv_four: {
                // 场景动画, 两个activity中的某些view协同去完成过度动画
                // 注意在 xml 设置 android:transitionName
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(this, view, getString(R.string.transition));
                Intent intent = new Intent(this, FourActivity.class);
                ActivityCompat.startActivity(this, intent, compat.toBundle());
            }
            break;
            case R.id.image:
            {
                // 多个view的协作 makeSceneTransitionAnimation
                // 注意设置目标 xml 的 android:transitionName
                Pair<View, String> imagePair = Pair.create(mImage, getString(R.string.image));
                Pair<View, String> textPair = Pair.create(mText, getString(R.string.name));
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(this, imagePair, textPair);
                Intent intent = new Intent(this, FiveActivity.class);
                ActivityCompat.startActivity(this, intent, compat.toBundle());
            }
            break;
            case R.id.btn_other:{
                skipIntent(OtherActivity.class);
                // 第一个参数是要进入的 activity 的动画， 第二个参数是要退出的 activity(当前显示的 activity) 的动画
                overridePendingTransition(R.anim.slide_bottom_in, android.R.anim.fade_out);
            }
            break;
        }
    }
}
