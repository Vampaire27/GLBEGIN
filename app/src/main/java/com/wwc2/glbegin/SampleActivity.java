package com.wwc2.glbegin;

import android.os.Bundle;

import android.view.View;


import com.wwc2.glbegin.core.router.PageRouter;
import com.wwc2.glbegin.core.table.RouteTable;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @anchor: andy
 * @date: 2019-03-27
 * @description:
 */
public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opengles_sample);
    }

    public void onBasisClick(View view) {
        PageRouter.getInstance().routeBasisPage();
    }

    public void onTextureClick(View view) {
        PageRouter.getInstance().routePage(RouteTable.PAGE_TEXTURE);
    }


    /**
     * 黑白相机实现
     *
     * @param view
     */
    public void onCameraClick(View view) {
//        PageRouter.getInstance().routePage(RouteTable.PAGE_CAMERA);
    }

    /**
     * 滤镜实现
     *
     * @param view
     */
    public void onFilterClick(View view) {
//        PageRouter.getInstance().routePage(RouteTable.PAGE_FILTER);
    }
}
