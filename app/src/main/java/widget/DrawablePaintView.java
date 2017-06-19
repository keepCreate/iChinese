package widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by VULCNAVSeries on 2017/6/18.
 */

public class DrawablePaintView extends View {
    private Paint mPaint=null;
    private Path mPath=null;
    private Bitmap desBitmap=null;
    private Canvas mCanvas=null;
    private float preX;
    private float preY;
    private int width;
    private int height;
    private static final int MIN_SLOP=5;

    public DrawablePaintView(Context context){
        super(context);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels-2*45;
        init();
    }
    public DrawablePaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
         height=dm.heightPixels-2*45;
        init();
    }

    public DrawablePaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
         width=dm.widthPixels;
         height=dm.heightPixels-2*45;
        init();
    }
    public void init(){
        mPath=new Path();
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(0xff000000);
        desBitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        mCanvas=new Canvas(desBitmap);
        mCanvas.drawColor(Color.parseColor("#FFFFFF"));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(desBitmap,0,0,null);
        mCanvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX=event.getX();
        float currentY=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(currentX,currentY);
                preX=currentX;
                preY=currentY;
                break;
            case MotionEvent.ACTION_MOVE:

                int dx=(int)Math.abs(currentX-preX);
                int dy=(int)Math.abs(currentY-preY);
                if (dx>MIN_SLOP||dy>MIN_SLOP){
                    mPath.quadTo(preX,preY,currentX,currentY);
                    preX=currentX;
                    preY=currentY;
                }
                break;
        }
        invalidate();
        return true;
    }
    public  void ClearAllPaint(){
        init();
        invalidate();
    }
}
