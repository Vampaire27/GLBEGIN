package com.wwc2.glbegin;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/test/1")
public class MainActivity extends GLSurfaceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected GLSurfaceView.Renderer bindRenderer() {
        return new TrainRender();
    }
}