package com.wwc2.glbegin.fliter;

import android.opengl.GLSurfaceView;

import com.wwc2.glbegin.fliter.fliterImp.BaseFilter;
import com.wwc2.glbegin.fliter.fliterImp.OriginFilter;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FilterRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "FilterRenderer";

    private int mSurfaceWidth, mSurfaceHeight;

    private BaseFilter mTargetFilter = new OriginFilter();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mTargetFilter.onSurfaceCreated();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mSurfaceWidth = width;
        mSurfaceHeight = height;
        mTargetFilter.onSurfaceChanged(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mTargetFilter.onDrawFrame();
    }

    public void setFilter(BaseFilter baseFilter) {
        if (mTargetFilter != null) {
            mTargetFilter.onDestroy();
        }
        mTargetFilter = baseFilter;
        if (mTargetFilter != null) {
            mTargetFilter.onSurfaceCreated();
            mTargetFilter.onSurfaceChanged(mSurfaceWidth, mSurfaceHeight);
        }
    }
}
