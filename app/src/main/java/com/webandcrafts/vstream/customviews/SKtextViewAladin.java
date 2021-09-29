package com.webandcrafts.vstream.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Bino on 1/2/2018.
 */

public class SKtextViewAladin extends androidx.appcompat.widget.AppCompatTextView {


    public SKtextViewAladin(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Roboto_Medium.ttf");
        this.setTypeface(face);
    }

    public SKtextViewAladin(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Roboto_Medium.ttf");
        this.setTypeface(face);
    }

    public SKtextViewAladin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Roboto_Medium.ttf");
        this.setTypeface(face);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
