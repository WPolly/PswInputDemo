package com.practice.lishan.pswinputview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * Created by lishan on 2019/4/9.
 * 10:47 AM
 */
public class PswInputView extends AppCompatEditText {

    private Paint mPswInputBorderPaint;
    private Paint mPswDotPaint;
    private Paint mPswInputDividerPaint;
    private RectF mInputRect;
    private int mWidth;
    private int mPswCount;
    private int mCurrentInputLength;
    private float mPswDotRadius;
    private float mBorderCornerRadius;
    private OnPswInputCompletedListener mOnPswInputCompletedListener;

    public PswInputView(Context context) {
        super(context);
        init(context, null);
    }

    public PswInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PswInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PswInputView);
        int pswDotColor = typedArray.getColor(R.styleable.PswInputView_pswDotColor, getColor(R.color.colorRedDot));
        int dividerColor = typedArray.getColor(R.styleable.PswInputView_dividerColor, getColor(R.color.colorLightGray));
        int borderColor = typedArray.getColor(R.styleable.PswInputView_borderColor, getColor(R.color.colorDarkGray));
        float borderStrokeWidth = typedArray.getDimension(R.styleable.PswInputView_borderStrokeWidth, dip2Px(1));
        float dividerStrokeWidth = typedArray.getDimension(R.styleable.PswInputView_dividerStrokeWidth, dip2Px(1));
        mPswCount = typedArray.getInt(R.styleable.PswInputView_pswCount, 6);
        mPswDotRadius = typedArray.getDimension(R.styleable.PswInputView_pswDotRadius, dip2Px(8));
        mBorderCornerRadius = typedArray.getDimension(R.styleable.PswInputView_borderCornerRadius, dip2Px(6));
        typedArray.recycle();

        mPswInputBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPswInputBorderPaint.setDither(true);
        mPswInputBorderPaint.setColor(borderColor);
        mPswInputBorderPaint.setStyle(Paint.Style.STROKE);
        mPswInputBorderPaint.setStrokeWidth(borderStrokeWidth);

        mPswInputDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPswInputDividerPaint.setDither(true);
        mPswInputDividerPaint.setColor(dividerColor);
        mPswInputDividerPaint.setStrokeWidth(dividerStrokeWidth);

        mPswDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPswDotPaint.setColor(pswDotColor);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mPswCount)});
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setLongClickable(false);
        setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        setCursorVisible(false);
        setBackground(null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mInputRect = new RectF(0, 0, w, ((float) (w / mPswCount)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(mInputRect, mBorderCornerRadius, mBorderCornerRadius, mPswInputBorderPaint);
        float stepLength = (float) (mWidth / mPswCount);
        for (int i = 1; i<mPswCount; i++) {
            float x = stepLength *i;
            canvas.drawLine(x, 0, x, stepLength, mPswInputDividerPaint);
        }

        for (int i = 0; i<mCurrentInputLength; i++) {
            float x = stepLength *i;
            canvas.drawCircle(x+stepLength/2, stepLength/2, mPswDotRadius, mPswDotPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        mCurrentInputLength = text.length();
        invalidate();
        if (mCurrentInputLength == mPswCount) {
            if (mOnPswInputCompletedListener != null) mOnPswInputCompletedListener.onPswInputCompleted(text.toString());
        }
    }

    public void setOnPswInputCompletedListener (OnPswInputCompletedListener listener) {
        mOnPswInputCompletedListener = listener;
    }

    public interface OnPswInputCompletedListener {
        void onPswInputCompleted(String psw);
    }
    
    private float dip2Px(int dip) {
        return getContext().getResources().getDisplayMetrics().density*dip;
    }
    
    private int getColor(int colorResId) {
        return getContext().getResources().getColor(colorResId);
    }
}
