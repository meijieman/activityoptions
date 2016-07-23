package com.foo.activityoptions;

import android.os.Bundle;

/**
 * @Desc: TODO
 * @Author: Major
 * @Since: 2016/7/22 1:14
 */
public class OtherActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_bottom_out);
    }
}
