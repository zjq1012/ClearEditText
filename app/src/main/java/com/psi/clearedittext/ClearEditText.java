package com.psi.clearedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dorado on 2017/12/12.
 */

public class ClearEditText extends AppCompatEditText implements TextWatcher, View.OnFocusChangeListener {
    private Drawable clearDrawable;

    public ClearEditText(Context context) {
        super(context);
        init();
    }


    public ClearEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public ClearEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        clearDrawable = getCompoundDrawables()[2];
        if (clearDrawable == null) {
            clearDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_edit_text_clear);
        }
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());
        setClearDrawableVisible(false);
    }


    private void setClearDrawableVisible(boolean clearDrawableVisiable) {
        Drawable drawable = clearDrawableVisiable ? clearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], drawable, getCompoundDrawables()[3]);
    }


    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hasFocus() && s.length() > 0) {
            setClearDrawableVisible(true);
        } else {
            setClearDrawableVisible(false);
        }
    }


    @Override public void afterTextChanged(Editable s) {

    }


    @Override public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus && getText().length() > 0) {
            setClearDrawableVisible(true);
        } else {
            setClearDrawableVisible(false);
        }
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (clearDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();
            boolean isInHorizontalRange = (x > getWidth() - getTotalPaddingRight() && x < getWidth() - getPaddingRight());
            // 图片高度
            int height = clearDrawable.getBounds().height();
            // 图片距离上下边缘
            int distanceY = (getHeight() - height) / 2;
            boolean isInVerticalRange = (y > distanceY  && y < getHeight() - distanceY);
            if (isInHorizontalRange && isInVerticalRange){
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }
}
