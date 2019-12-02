package com.example.roomdatabaseexample.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;



public class NoteEditText extends AppCompatEditText {

    private Rect mRect;
    private Paint mPaint, mVerticalPaint;

    public NoteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mVerticalPaint = new Paint();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLUE);

        mVerticalPaint.setStyle(Paint.Style.STROKE);
        mVerticalPaint.setStrokeWidth(2);
        mVerticalPaint.setColor(Color.RED);

    }/////// End of constructor

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = mRect;
        Paint paint = mPaint;
        Paint vPaint = mVerticalPaint;

        int paperHeight = ((View)this.getParent()).getHeight();
        int lineHeight = getLineHeight();
        int noOfLines = paperHeight / lineHeight;


        int paperWidth = ((View)this.getParent()).getWidth();
        int baseLine = getLineBounds(0, rect);
        int lineWidth = getWidth();
//        int marginLine = getLineBounds(0, rect);

//       For drawing the Vertical lines
//        for(int i = 0; i < noOfLines; i++){
//            canvas.drawLine(marginLine + 1, rect.top, marginLine + 1, rect.bottom, vPaint);
//            marginLine += lineWidth;
//        }

//       For drawing the Horizontal lines
        for(int i = 0; i < noOfLines; i++){
            canvas.drawLine(rect.left, baseLine + 1, rect.right, baseLine + 1, paint);

            baseLine += lineHeight;
        }
        super.onDraw(canvas);
    }/////// End of onDraw()
}
