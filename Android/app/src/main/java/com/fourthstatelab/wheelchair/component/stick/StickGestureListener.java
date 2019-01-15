package com.fourthstatelab.wheelchair.component.stick;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class StickGestureListener extends GestureDetector.SimpleOnGestureListener {

    private View view;

    private int halfWidth;
    private int halfHeight;

    private StickGestureListener(View v){
        this.view = v;
        populateViewDimension();
    }

    /**
     * sets half the dimensions of the view
     */
    private void populateViewDimension(){
        halfHeight = this.view.getHeight() / 2;
        halfWidth = this.view.getWidth() / 2;
    }

    /**
     * Handles panning of the view
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return boolean, if the event is handled or not (always true as handled)
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.leftMargin -= halfWidth;
        params.topMargin -= halfHeight;
        params.leftMargin -= distanceX ;
        params.topMargin -= distanceY;
        view.requestLayout();
        return true;
    }

    /**
     * Creates Touch listener from GestureListener which is to be attached to view
     * @param context
     * @return View.OnTouchListener, interfaces GestureDetector via this attached touched listener
     */
    public static View.OnTouchListener getTouchListener(final Context context){
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                StickGestureListener listener = new StickGestureListener(v);
                GestureDetector detector = new GestureDetector(context, listener);
                return detector.onTouchEvent(event);
            }
        };
        return touchListener;
    }
}
