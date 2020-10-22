package com.wwc2.glbegin;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.wwc2.glbegin.SharderUtils.ResReadUtils;
import com.wwc2.glbegin.SharderUtils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TrainRender implements GLSurfaceView.Renderer {

    private  final FloatBuffer vertexBuffer,colorBuffer;
    private ShortBuffer indicesBuffer;

    private int mProgram;


    private static final int VERTEX_POSITION_SIZE = 3;

    private static final int VERTEX_COLOR_SIZE = 4;


    private float[] vertexPoints = new float[]{
            0.5f,0.0f,0.0f,
            0.0f,0.5f,0.0f,
            -0.5f,0.0f,0.0f,
            0.0f,-0.5f,0.0f,
    };

    private short[] indices= {
            0,1,3,
            1,2,3,
    };

    private float[] color = {
            0.3f,0.4f,0.6f,0.0f,
            0.3f,0.4f,0.6f,0.0f,
            0.3f,0.0f,0.0f,0.0f,
            0.3f,0.4f,0.6f,0.0f,
    };

    public TrainRender() {

        vertexBuffer = ByteBuffer.allocateDirect(vertexPoints.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        //传入指定的坐标数据
        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);


        colorBuffer = ByteBuffer.allocateDirect(color.length*4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        //传入指定的颜色
        colorBuffer.put(color);
        colorBuffer.position(0);



        //分配内存空间,每个浮点型占4字节空间
        indicesBuffer = ByteBuffer.allocateDirect(indices.length * 4)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer();
        //传入指定的数据
        indicesBuffer.put(indices);
        indicesBuffer.position(0);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //GLES30.glClearColor(0.5f,0.5f,0.5f,0.5f);
        GLES30.glClearColor(1.0f,0.0f,0.0f,0.0f);
        final int vertexShaderId = ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_train));
        final int fragmentShaderId = ShaderUtils.compileFragmentShader(ResReadUtils.readResource(R.raw.fragment_colorcuber));
        mProgram = ShaderUtils.linkProgram(vertexShaderId,fragmentShaderId);
        GLES30.glUseProgram(mProgram);

        GLES30.glVertexAttribPointer(0, VERTEX_POSITION_SIZE, GLES30.GL_FLOAT, false, 0, vertexBuffer);

        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(1, VERTEX_COLOR_SIZE, GLES30.GL_FLOAT, false, 0, colorBuffer);
        GLES30.glEnableVertexAttribArray(1);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
      GLES30.glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        GLES30.glDrawElements(GL10.GL_TRIANGLES,indices.length,GL10.GL_UNSIGNED_SHORT,indicesBuffer);
    }
}
