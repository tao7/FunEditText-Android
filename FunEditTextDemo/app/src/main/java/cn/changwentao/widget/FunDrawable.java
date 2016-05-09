
package cn.changwentao.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.text.TextPaint;
import android.util.StateSet;
import android.view.View;
import android.widget.EditText;

/**
 * @author wentao
 */
public class FunDrawable extends Drawable {
    private static final int COLOR_FOCUS_DOWN = 0xffff0000;
    private static final int COLOR_FOCUS_UP = 0xff999999;
    private int mColor = COLOR_FOCUS_UP;

    private Shape mShape;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint mTextPaint;

    private EditText mEditText;

    private int mPaddingTop = 60;
    private int yOffset = 0;
    private int xOffset;
    private int YY;
    private int XX;

    private String hintText;

    private ValueAnimator mValueAnimator;

    private Boolean first = true;

    private final RectF mRectF = new RectF();

    /**
     *
     */
    @SuppressLint("NewApi")
    public FunDrawable(EditText editText) {
        mEditText = editText;
        setCallback(mEditText);

        mShape = new FunShape();

        mPaint.setColor(mColor);
        mTextPaint = new TextPaint(mEditText.getPaint());
        mTextPaint.setTextSize(mEditText.getTextSize());

        xOffset = mEditText.getPaddingLeft();
        yOffset = mEditText.getBaseline();

        mValueAnimator = ObjectAnimator.ofInt(this, "YOffset", 0);
        mValueAnimator.setDuration(200);

        hintText = mEditText.getHint().toString();
        mEditText.setHint("");
    }

    @Override
    public boolean getPadding(Rect padding) {
        padding.set(mEditText.getPaddingStart(), mEditText.getPaddingTop() + mPaddingTop,
                mEditText.getPaddingEnd(), mEditText.getPaddingBottom());
        return true;
    }

    @Override
    public boolean setState(int[] stateSet) {
        return super.setState(stateSet);
    }

    @Override
    protected boolean onStateChange(int[] state) {
        if (StateSet.stateSetMatches(state, View.FOCUS_DOWN)) {
            mColor = COLOR_FOCUS_DOWN;
            mPaint.setColor(mColor);
            mTextPaint.setColor(mColor);
            return true;
        } else if (StateSet.stateSetMatches(state, View.FOCUS_UP)) {
            mColor = COLOR_FOCUS_UP;
            mPaint.setColor(mColor);
            mTextPaint.setColor(mColor);
            return true;
        }
        return false;
    }

    public void invalidateXY() {
        XX = (int) (mPaddingTop + mTextPaint.getTextSize() / 2);
        YY = mEditText.getBaseline();
    }

    @Override
    public void draw(Canvas canvas) {
        if (first) {
            invalidateXY();
            if (!mEditText.isFocused() && mEditText.getText().length() == 0)
                down();
            first = false;
        }

        FontMetrics fm = mTextPaint.getFontMetrics();
        float baseline = 0 - fm.top;
        float mTxtHeight = fm.bottom - fm.top;
        float mTxtWidth = mTextPaint.measureText(hintText);
        mRectF.set(xOffset, yOffset - baseline, xOffset + mTxtWidth, yOffset
                - baseline + mTxtHeight);

        canvas.save();
        canvas.clipRect(mRectF, Region.Op.DIFFERENCE);
        mShape.resize(canvas.getWidth(), canvas.getHeight() - mPaddingTop);
        canvas.translate(0, mPaddingTop);
        mShape.draw(canvas, mPaint);
        canvas.restore();

        mTextPaint.setTextSize(mEditText.getTextSize());
        mTextPaint.setColor(mColor);

        canvas.drawText(hintText, xOffset, yOffset, mTextPaint);
    }

    private void setYOffset(int y) {
        yOffset = y;
        invalidateSelf();
    }

    private int getYOffset() {
        return yOffset;
    }

    @SuppressLint("NewApi")
    public void up() {
        invalidateXY();
        mEditText.setHint("");
        mValueAnimator.cancel();
        mValueAnimator.setIntValues(yOffset, XX);
        mValueAnimator.start();
    }

    @SuppressLint("NewApi")
    public void down() {
        invalidateXY();
        mValueAnimator.cancel();
        mValueAnimator.setIntValues(yOffset, YY);
        mValueAnimator.start();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

}
