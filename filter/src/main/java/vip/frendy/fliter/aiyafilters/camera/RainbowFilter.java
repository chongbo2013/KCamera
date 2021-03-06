package vip.frendy.fliter.aiyafilters.camera;

import android.content.res.Resources;
import android.opengl.GLES20;

/**
 * Created by Simon on 2017/7/6.
 */

public class RainbowFilter extends LandmarkFilter {

    private int gLandmarkX;
    private int gLandmarkY;
    private int gMouthOpen;
    private int gGlobalTime;
    private float[] uLandmarkX;
    private float[] uLandmarkY;
    private int isMouthOpen = 0;

    public RainbowFilter(Resources mRes) {
        super(mRes);
        uLandmarkX = new float[106];
        uLandmarkY = new float[106];
    }

    @Override
    protected void onCreate() {
        createProgramByAssetsFile("shader/base_vertex.sh",
                "shader/test/rainbow_fragment.frag");

        gLandmarkX = GLES20.glGetUniformLocation(mProgram, "uLandmarkX");
        gLandmarkY = GLES20.glGetUniformLocation(mProgram, "uLandmarkY");
        gMouthOpen = GLES20.glGetUniformLocation(mProgram, "uMouthOpen");
        gGlobalTime = GLES20.glGetUniformLocation(mProgram, "iGlobalTime");
    }

    @Override
    protected void onSizeChanged(int width, int height) {
    }

    public void setLandmarks(float[] landmarkX, float[] landmarkY) {
        uLandmarkX = landmarkX;
        uLandmarkY = landmarkY;
    }

    public void setMouthOpen(int isOpen) {
        isMouthOpen = isOpen;
    }

    @Override
    protected void onSetExpandData() {
        super.onSetExpandData();
        GLES20.glUniform1fv(gLandmarkX, uLandmarkX.length, uLandmarkX, 0);
        GLES20.glUniform1fv(gLandmarkY, uLandmarkY.length, uLandmarkY, 0);
        GLES20.glUniform1i(gMouthOpen, isMouthOpen);

        float time = ((float) (System.currentTimeMillis() - START_TIME)) / 1000.0f;
        GLES20.glUniform1f(gGlobalTime, time);
    }
}
