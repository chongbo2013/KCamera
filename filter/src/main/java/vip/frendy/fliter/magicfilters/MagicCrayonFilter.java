package vip.frendy.fliter.magicfilters;

import android.opengl.GLES20;

import vip.frendy.fliter.R;
import vip.frendy.fliter.base.GPUImageFilter;
import vip.frendy.fliter.utils.OpenGlUtils;

public class MagicCrayonFilter extends GPUImageFilter {
	
	private int mSingleStepOffsetLocation;
	//1.0 - 5.0
	private int mStrengthLocation;
	
	public MagicCrayonFilter(){
		super(NO_FILTER_VERTEX_SHADER, OpenGlUtils.readShaderFromRawResource(R.raw.crayon));
	}
	
	protected void onInit() {
        super.onInit();
        mSingleStepOffsetLocation = GLES20.glGetUniformLocation(getProgram(), "singleStepOffset");
        mStrengthLocation = GLES20.glGetUniformLocation(getProgram(), "strength");
        setFloat(mStrengthLocation, 2.0f);
    }
    
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onInitialized(){
        super.onInitialized();
        setFloat(mStrengthLocation, 0.5f);
    }

    private void setTexelSize(final float w, final float h) {
		setFloatVec2(mSingleStepOffsetLocation, new float[] {1.0f / w, 1.0f / h});
	}
	
	@Override
    public void onOutputSizeChanged(final int width, final int height) {
        super.onOutputSizeChanged(width, height);
        setTexelSize(width, height);
    }
}