package ysn.com.view.roundrect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @Author yangsanning
 * @ClassName RoundRectView
 * @Description 一句话概括作用
 * @Date 2019/8/24
 * @History 2019/8/24 author: description:
 */
public class RoundRectView extends View implements IView {

    private Context context;

    private int bgColor;
    private int round;
    private int borderColor;
    private int borderWidth;

    private Paint bgPaint;
    private RectF bgRectF;
    private Paint borderPaint;
    private RectF borderRectF;

    private int viewHeight;
    private int viewWidth;
    private float xRadius;
    private float yRadius;

    private boolean isClick;

    public RoundRectView(Context context) {
        this(context, null);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
        initData();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs);
        initPaint();
        initData();
    }

    @Override
    public void initAttrs(Context context, @Nullable AttributeSet attrs) {
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundRectView);

        round = typedArray.getDimensionPixelSize(R.styleable.RoundRectView_rrv_round, 300);
        bgColor = typedArray.getColor(R.styleable.RoundRectView_rrv_bg_color, getColor(R.color.bg_color));
        borderColor = typedArray.getColor(R.styleable.RoundRectView_rrv_border_color, getColor(R.color.border_color));
        borderWidth = typedArray.getDimensionPixelSize(R.styleable.RoundRectView_rrv_border_width, 10);

        typedArray.recycle();
    }

    protected int getColor(@ColorRes int colorRes) {
        return context.getResources().getColor(colorRes);
    }

    @Override
    public void initPaint() {
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.FILL);
        bgRectF = new RectF();

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(borderColor);
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);
        borderRectF = new RectF();
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        xRadius = viewWidth >> 1;
        yRadius = viewWidth >> 1;

        bgRectF.left = -xRadius;
        bgRectF.right = xRadius;
        bgRectF.top = -yRadius;
        bgRectF.bottom = yRadius;

        borderRectF.left = -xRadius + (borderWidth >> 1) - 1;
        borderRectF.right = xRadius - (borderWidth >> 1) + 1;
        borderRectF.top = -yRadius + (borderWidth >> 1) - 1;
        borderRectF.bottom = yRadius - (borderWidth >> 1) + 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(xRadius, yRadius);

        canvas.drawRoundRect(bgRectF, round, round, bgPaint);
        if (isClick) {
            canvas.drawRoundRect(borderRectF, round, round, borderPaint);
        }

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            isClick = !isClick;
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
