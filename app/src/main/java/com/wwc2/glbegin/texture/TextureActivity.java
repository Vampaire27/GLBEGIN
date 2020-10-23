package com.wwc2.glbegin.texture;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wwc2.glbegin.GLSurfaceActivity;
import com.wwc2.glbegin.core.table.RouteTable;

import androidx.annotation.Nullable;

@Route(path = RouteTable.PAGE_TEXTURE)
public class TextureActivity extends GLSurfaceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected GLSurfaceView.Renderer bindRenderer() {
        return new TextureRender();
    }
}
