package com.webandcrafts.vstream.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Bino on 12/1/2017.
 */

public class SKtetViewBold extends androidx.appcompat.widget.AppCompatTextView {


    public SKtetViewBold(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Roboto_Bold.ttf");
        this.setTypeface(face);
    }

    public SKtetViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Roboto_Bold.ttf");
        this.setTypeface(face);
    }

    public SKtetViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Roboto_Bold.ttf");
        this.setTypeface(face);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
