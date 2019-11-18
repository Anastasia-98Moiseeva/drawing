package com.example.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DrawView extends View {

    private Paint mPaint = new Paint();
    private Paint mBackgroundPaint = new Paint();
    private List<Draw> mList = new ArrayList<>();
    private Draw mCurrentDraw;

    private int mColor = getResources().getColor(R.color.colorRed);
    private Instrument mInstrument = Instrument.LINE;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpPaint();
        initPaint();
    }

    public void setColor(int color){
        mColor = color;
    }

    public void setInstrument(Instrument instrument){
        mInstrument = instrument;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for (Draw draw : mList) {
            mPaint.setColor(draw.getColor());
            switch (draw.getInstrument()){
                case POIT:
                    canvas.drawPoint(draw.getOrigin().x, draw.getOrigin().y, mPaint);
                    break;
                case LINE:
                    canvas.drawLine(draw.getCurrent().x, draw.getCurrent().y, draw.getOrigin().x, draw.getOrigin().y, mPaint);
                    break;
                case SQUARE:
                    float left = Math.min(draw.getCurrent().x, draw.getOrigin().x);
                    float right = Math.max(draw.getCurrent().x, draw.getOrigin().x);
                    float top = Math.min(draw.getCurrent().y, draw.getOrigin().y);
                    float bottom = Math.max(draw.getCurrent().y, draw.getOrigin().y);
                    canvas.drawRect(left, top, right, bottom, mPaint);
                    break;
            }
        }
    }

    public void clean(){
        mList.clear();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        PointF current = new PointF(event.getX(), event.getY());

        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                mCurrentDraw = new Draw(current, mColor, mInstrument);
                mList.add(mCurrentDraw);
                break;
            case MotionEvent.ACTION_MOVE:
                if(mCurrentDraw != null){
                    mCurrentDraw.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            default:
                return super.onTouchEvent(event);
        }
        invalidate();
        return true;
    }

    private void initPaint() {
        mPaint.setColor(mColor);
    }

    private void setUpPaint(){
        mBackgroundPaint.setColor(Color.WHITE);
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.STROKE);
    }
}
