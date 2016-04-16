package cn.changwentao.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 
 * @author wentao
 * 
 */
public class FunEditText extends EditText {
	private FunDrawable mBackgroundDrawable = new FunDrawable(this);

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

	@SuppressLint("NewApi")
	private void init() {
		mBackgroundDrawable.setCallback(this);
		this.setBackground(mBackgroundDrawable);
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		if (focused) {
			up();
		} else {
			if (this.getText().length() == 0)
				down();
		}
	}

	private void up() {
		mBackgroundDrawable.upup();
	}

	private void down() {
		mBackgroundDrawable.downdown();
	}

}
