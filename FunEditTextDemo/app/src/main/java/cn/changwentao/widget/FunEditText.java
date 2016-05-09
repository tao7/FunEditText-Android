package cn.changwentao.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * An EditText with a hint up down animation.
 *
 * @author wentao
 */
public class FunEditText extends AppCompatEditText {
    private FunDrawable mBackgroundDrawable;

    public FunEditText(Context context) {
        super(context);
        init();
    }

    public FunEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FunEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FunEditText(Context context, AttributeSet attrs, int defStyleAttr, FunDrawable mBackgroundDrawable) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBackgroundDrawable = new FunDrawable(this);
        setBackground(mBackgroundDrawable);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            up();
        } else {
            if (getText().length() == 0)
                down();
        }
    }

    private void up() {
        mBackgroundDrawable.up();
    }

    private void down() {
        mBackgroundDrawable.down();
    }

}
