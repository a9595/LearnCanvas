package tieorange.com.learncanvas;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by tieorange on 09/10/2016.
 */

public class MyCanvas extends View {

  private static final String COLOR_HEX = "#E74300";
  private static final String TAG = MyCanvas.class.getCanonicalName();
  private final Paint drawPaint;
  private float size;
  private float mTouchY;
  private float mRadius;

  public MyCanvas(final Context context, final AttributeSet attrs) {
    super(context, attrs);
    drawPaint = new Paint();
    drawPaint.setColor(Color.parseColor(COLOR_HEX));
    //drawPaint.setAntiAlias(true);
    setOnMeasureCallback();
    mTouchY = 500;
  }

  @Override protected void onDraw(final Canvas canvas) {
    super.onDraw(canvas);
    //float radius = mTouchY / 5;
    //float radius = size / 5;
    canvas.drawCircle(size, mTouchY, mRadius, drawPaint);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    //Log.d(TAG, "[" + event.getX() + "]");
    mTouchY = event.getY();
    mRadius = mTouchY / 2;
    int color = Color.rgb(100, (int) (mTouchY/5), 10);
    drawPaint.setColor(color);
    invalidate();
    return true;
  }

  private void setOnMeasureCallback() {
    ViewTreeObserver vto = getViewTreeObserver();
    vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override public void onGlobalLayout() {
        removeOnGlobalLayoutListener(this);
        size = getMeasuredWidth() / 2;
      }
    });
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN) private void removeOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener listener) {
    if (Build.VERSION.SDK_INT < 16) {
      getViewTreeObserver().removeGlobalOnLayoutListener(listener);
    } else {
      getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }
  }
}
