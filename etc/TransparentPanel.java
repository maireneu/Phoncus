package com.phoncus.app.alpha.etc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by OYM on 2016-08-17.
 */
public class TransparentPanel extends LinearLayout {


    private Paint innerPaint = null;
    private Paint borderPaint = null;


    public TransparentPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 안쪽 사각형
        innerPaint = new Paint();
        innerPaint.setARGB(20, 75, 75, 75); // 투명도
        innerPaint.setAntiAlias(true);

        // 테두리 설정
        borderPaint = new Paint();
        borderPaint.setARGB(255, 255, 255, 255);
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(0);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {

        RectF drawRect = new RectF();
        drawRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRoundRect(drawRect, 5, 5, innerPaint);
        canvas.drawRoundRect(drawRect, 5, 5, borderPaint);
        //부모 생성자 호출
        super.dispatchDraw(canvas);
    }

}

