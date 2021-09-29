package com.webandcrafts.vstream.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by wac46 on 2/17/2018.
 */

public class SKtextViewRobotoRegular extends androidx.appcompat.widget.AppCompatTextView{

    public SKtextViewRobotoRegular(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "Roboto_Regular.ttf");
        this.setTypeface(face);
    }

    public SKtextViewRobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "Roboto_Regular.ttf");
        this.setTypeface(face);
    }

    public SKtextViewRobotoRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "Roboto_Regular.ttf");
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }

}



