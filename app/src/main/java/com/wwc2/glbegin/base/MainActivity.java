package com.wwc2.glbegin.base;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wwc2.glbegin.GLSurfaceActivity;
import com.wwc2.glbegin.base.TrainRender;
import com.wwc2.glbegin.core.table.RouteTable;

@Route(path = RouteTable.PAGE_BASIS)
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