package com.wwc2.glbegin;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class GLSurfaceActivity extends AppCompatActivity {
    private GLSurfaceView mGLSurfaceView;

    protected abstract GLSurfaceView.Renderer bindRenderer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
    }

    private void setupView(){
        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setEGLContextClientVersion(3);
        mGLSurfaceView.setRenderer(bindRenderer());
        setContentView(mGLSurfaceView);
    }
}
