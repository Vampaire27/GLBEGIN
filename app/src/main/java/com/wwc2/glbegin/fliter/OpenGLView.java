package com.wwc2.glbegin.fliter;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.wwc2.glbegin.fliter.fliterImp.BaseFilter;

public class OpenGLView extends GLSurfaceView {
    private FilterRenderer mGLRender;

    public OpenGLView(Context context) {
        this(context,null);
    }

    public OpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupSurfaceView();
    }

    private void setupSurfaceView() {
        setEGLContextClientVersion(3);
        mGLRender = new FilterRenderer();
        setRenderer(mGLRender);
        try {
            requestRender();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置滤镜
     * 滤镜由于可能存在多种类型
     * 这里抽象了一个基础的滤镜类
     * queueEvent
     *
     * @param baseFilter
     */
    public void setFilter(final BaseFilter baseFilter) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                if (mGLRender != null) {
                    mGLRender.setFilter(baseFilter);
                }
            }
        });
        try {
            requestRender();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
