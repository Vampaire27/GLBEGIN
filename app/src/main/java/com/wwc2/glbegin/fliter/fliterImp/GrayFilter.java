package com.wwc2.glbegin.fliter.fliterImp;

import android.opengl.GLES30;

import com.wwc2.glbegin.R;
import com.wwc2.glbegin.SharderUtils.ResReadUtils;


public class GrayFilter extends BaseFilter {

    private int aFilterLocation;

    private float[] filterValue = new float[]{0.299f, 0.587f, 0.114f};

    public GrayFilter() {
        super(ResReadUtils.readResource(R.raw.gray_filter_vertex_shader), ResReadUtils.readResource(R.raw.gray_filter_fragment_shader));
    }

    @Override
    public void setupProgram() {
        super.setupProgram();
        aFilterLocation = GLES30.glGetUniformLocation(mProgram, "a_Filter");
    }

    @Override
    public void onUpdateDrawFrame() {
        //更新参数
        GLES30.glUniform3fv(aFilterLocation, 1, filterValue, 0);
    }

}
