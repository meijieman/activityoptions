package com.foo.activityoptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/7/20 23:18
 */
public class BaseActivity extends AppCompatActivity {

    protected BaseActivity mBaseActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseActivity = this;
    }

    protected void skipIntent(Class clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
