package cn.changwentao.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.Shape;

/**
 * 
 * @author wentao
 * 
 */
public class FunShape extends Shape {
	private RectF mRect = new RectF();

	public FunShape() {
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRoundRect(mRect, getHeight() / 2, getHeight() / 2, paint);
	}

	@Override
	protected void onResize(float width, float height) {
		mRect.set(1, 1, width-2, height-2);
	}

	@Override
	public Shape clone() throws CloneNotSupportedException {
		final FunShape shape = (FunShape) super.clone();
		shape.mRect = new RectF(mRect);
		return shape;
	}

}
