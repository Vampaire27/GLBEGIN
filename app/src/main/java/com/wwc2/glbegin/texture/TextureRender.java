package com.wwc2.glbegin.texture;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.wwc2.glbegin.R;
import com.wwc2.glbegin.SharderUtils.ResReadUtils;
import com.wwc2.glbegin.SharderUtils.ShaderUtils;
import com.wwc2.glbegin.SharderUtils.TextureUtils;
import com.wwc2.glbegin.core.AppCore;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

public class TextureRender implements GLSurfaceView.Renderer {


    private static final String TAG = "TextureRenderer";

    private  FloatBuffer vertexBuffer, mTexVertexBuffer;

    private  ShortBuffer mVertexIndexBuffer;

    private int mProgram;

    private int textureId;

    /**
     * 顶点坐标
     * (x,y,z)
     */
    private float[] POSITION_VERTEX = new float[]{
            0f, 0f, 0f,     //顶点坐标V0
            1f, 1f, 0f,     //顶点坐标V1
            -1f, 1f, 0f,    //顶点坐标V2
            -1f, -1f, 0f,   //顶点坐标V3
            1f, -1f, 0f     //顶点坐标V4
    };

    /**
     * 纹理坐标
     * (s,t)
     */
    private static final float[] TEX_VERTEX = {
            0.5f, 0.5f, //纹理坐标V0
            1f, 0f,     //纹理坐标V1
            0f, 0f,     //纹理坐标V2
            0f, 1.0f,   //纹理坐标V3
            1f, 1.0f    //纹理坐标V4
    };

    /**
     * 索引
     */
    private static final short[] VERTEX_INDEX = {
            0, 1, 2,  //V0,V1,V2 三个顶点组成一个三角形
            0, 2, 3,  //V0,V2,V3 三个顶点组成一个三角形
            0, 3, 4,  //V0,V3,V4 三个顶点组成一个三角形
            0, 4, 1   //V0,V4,V1 三个顶点组成一个三角形
    };

    private int uMatrixLocation;
    private float[] mMatrix = new float[16];


    public TextureRender() {

        vertexBuffer = ByteBuffer.allocateDirect(POSITION_VERTEX.length*4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(POSITION_VERTEX);
        vertexBuffer.position(0);

        mTexVertexBuffer = ByteBuffer.allocateDirect(TEX_VERTEX.length*4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        mTexVertexBuffer.put(TEX_VERTEX);
        mTexVertexBuffer.position(0);

        mVertexIndexBuffer = ByteBuffer.allocateDirect(VERTEX_INDEX.length * 2)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer()
                .put(VERTEX_INDEX);
        mVertexIndexBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        GLES30.glClearColor(0.5f,0.5f,0.5f,0.5f);
        final int vertexShardId = ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_texture_shader));
        final int fragmentShardId = ShaderUtils.compileFragmentShader(ResReadUtils.readResource(R.raw.fragment_texture_shader));
        mProgram = ShaderUtils.linkProgram(vertexShardId,fragmentShardId);
        uMatrixLocation =GLES30.glGetUniformLocation(mProgram,"u_Matrix");
        textureId = TextureUtils.loadTexture(AppCore.getInstance().getContext(),R.drawable.main);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0,0,width,height);
        final float aspectRatio = width > height ?
                (float) width/ (float) height :
                (float) height/(float)width;
        if (width > height) {
            //横屏
            Matrix.orthoM(mMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
        } else {
            //竖屏
            Matrix.orthoM(mMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        }

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        GLES30.glUseProgram(mProgram);

        GLES30.glUniformMatrix4fv(uMatrixLocation,1,false,mMatrix,0);

        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(0,3,GLES30.GL_FLOAT,false,0,vertexBuffer);

        GLES30.glEnableVertexAttribArray(1);
        GLES30.glVertexAttribPointer(1,2,GLES30.GL_FLOAT,false,0,mTexVertexBuffer);

        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D,textureId);

        GLES30.glDrawElements(GLES30.GL_TRIANGLES,VERTEX_INDEX.length,GLES30.GL_UNSIGNED_SHORT,mVertexIndexBuffer);

    }
}
