package com.wwc2.glbegin.fliter.fliterImp;


import com.wwc2.glbegin.R;
import com.wwc2.glbegin.SharderUtils.ResReadUtils;

public class QuarterMirrorFilter extends BaseFilter {

    public QuarterMirrorFilter() {
        super(ResReadUtils.readResource(R.raw.quarter_mirror_filter_vertex_shader), ResReadUtils.readResource(R.raw.quarter_mirror_filter_fragment_shader));
    }


}
