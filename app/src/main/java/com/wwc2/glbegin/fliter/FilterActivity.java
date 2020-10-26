package com.wwc2.glbegin.fliter;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wwc2.glbegin.R;
import com.wwc2.glbegin.core.table.RouteTable;
import com.wwc2.glbegin.fliter.fliterImp.GrayFilter;
import com.wwc2.glbegin.fliter.fliterImp.OriginFilter;
import com.wwc2.glbegin.fliter.fliterImp.QuarterMirrorFilter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@Route(path = RouteTable.PAGE_FILTER)
public class FilterActivity  extends AppCompatActivity {
    private ViewGroup mRootLayer;
    private OpenGLView mOpenGLView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setupViews();
    }


    private void setupViews(){
        mRootLayer = findViewById(R.id.linear_root_layer);
        mOpenGLView = new OpenGLView(this);
        mRootLayer.addView(mOpenGLView,0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.filter_default) {
            mOpenGLView.setFilter(new OriginFilter());
        } else if (itemId == R.id.filter_gray) {
            mOpenGLView.setFilter(new GrayFilter());
        } else if (itemId == R.id.filter_quarter_mirror) {
            mOpenGLView.setFilter(new QuarterMirrorFilter());
        }
        return super.onOptionsItemSelected(item);
    }


}