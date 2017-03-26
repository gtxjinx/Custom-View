package com.example.administrator.service;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.icu.text.LocaleDisplayNames;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.R.attr.gravity;
import static android.R.attr.height;
import static android.R.attr.name;
import static android.R.attr.value;
import static android.R.attr.width;

/**
 * Created by Jinx on 2017/3/18 11:31.
 */

public class Code extends View {

    private Paint mPaint;
    private int codecolor= Color.GRAY;
    private int textcolor=Color.BLACK;
    private float length;
    private float textsize;
    private String text;
    private RectF rectf;
    private int position;
    private float X=0;
    private float Y=0;
    private static final int LEFT=0;
    private static final int TOP=1;
    private static final int RIGHT=2;
    private static final int BOTTOM=3;
    private static final int CENTER=4;



    public Code(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        rectf=new RectF();
    }

    public Code(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context, attrs);
    }

    private void initParams(Context context, AttributeSet attrs) {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        rectf=new RectF();
        TypedArray typedArray =context.obtainStyledAttributes(attrs,R.styleable.Code);
        if(typedArray!=null)
        {
            codecolor=typedArray.getColor(R.styleable.Code_codecolor,Color.YELLOW);
            text=typedArray.getString(R.styleable.Code_text);
            textsize=typedArray.getDimension(R.styleable.Code_textsize,20);
            length=typedArray.getDimension(R.styleable.Code_size,100);
            textcolor=typedArray.getColor(R.styleable.Code_textcolor,Color.GRAY);
            position=typedArray.getInt(R.styleable.Code_gravity,LEFT);

            typedArray.recycle();
        }
        if (text==null)
            text="";
    }

    public Code(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        //////////设定控件的长宽
        switch (widthMode)
        {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                widthMeasureSpec=MeasureSpec.makeMeasureSpec((int)length,MeasureSpec.EXACTLY);
                break;
            case MeasureSpec.AT_MOST:
                widthMeasureSpec=MeasureSpec.makeMeasureSpec((int)length,MeasureSpec.EXACTLY);
                break;
        }
        switch (heightMode)
        {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                heightMeasureSpec=MeasureSpec.makeMeasureSpec((int)length,MeasureSpec.EXACTLY);
                break;
            case MeasureSpec.AT_MOST:
                heightMeasureSpec=MeasureSpec.makeMeasureSpec((int)length,MeasureSpec.EXACTLY);
                break;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);   //传输长宽数据


        //获取方块的中心点
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        X=width/2;
        Y=height/2;
        Log.i("X1",X+"");
        switch (position)
        {
            case LEFT:
                X=length/2+getPaddingLeft();
                Log.i("X2",X+"");
                break;
            case RIGHT:
                X=width-getPaddingLeft()-length/2;
                break;
            case TOP:
                Y=length/2+getPaddingTop();
                break;
            case BOTTOM:
                Y=height-getPaddingBottom()-length/2;
                break;
            case CENTER:
                break;
        }
        float left=X-length/2;
        float right=X+length/2;
        float top=Y-length/2;
        float bottom=Y+length/2;
        rectf.set(left,top,right,bottom);   //获取绘图区域
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        mPaint.setColor(codecolor);
        canvas.drawRoundRect(rectf,length/10,length/10,mPaint);
        mPaint.setColor(textcolor);
        mPaint.setTextSize(textsize);
        Log.d("Jinx",text);
        canvas.drawText(text,X-length/5*2,Y-length/2+textsize,mPaint);
    }
}
