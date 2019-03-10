package com.fourthstatelab.wheelchair.component.stick;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class StickGestureListener extends GestureDetector.SimpleOnGestureListener {

    private View view;
    private View boundView;

    private int halfWidth;
    private int halfHeight;


    private int centerX;
    private int centerY;
    private int boundRadius;

    private StickGestureListener(View view, View boundView) {
        this.view = view;
        this.boundView = boundView;
        populateViewDimension();
    }

    /**
     * sets half the dimensions of the view
     */
    private void populateViewDimension() {
        halfHeight = this.view.getHeight() / 2;
        halfWidth = this.view.getWidth() / 2;

        centerX = (int) this.view.getX() + halfWidth;
        centerY = (int) this.view.getY() + halfHeight;

        boundRadius = boundView.getWidth() / 2;
    }

    /**
     * Handles panning of the view
     *
     * @param e1        Events
     * @param e2        Events
     * @param distanceX delta in X Axis
     * @param distanceY delta in Y Axis
     * @return boolean, if the event is handled or not (always true as handled)
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        float newCenterX = view.getX() - distanceX + halfWidth;
        float newCenterY = view.getY() - distanceY + halfHeight;
        float distanceFromCenter = getDistance(centerX, centerY, newCenterX, newCenterY);

        if (distanceFromCenter < boundRadius) {
            params.leftMargin -= distanceX;
            params.topMargin -= distanceY;
        }
        view.requestLayout();
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    /**
     * Creates Touch listener from GestureListener which is to be attached to view
     *
     * @param context
     * @return View.OnTouchListener, interfaces GestureDetector via this attached touched listener
     */
    public static View.OnTouchListener getTouchListener(final Context context, final View view, final View boundView) {
        StickGestureListener listener = new StickGestureListener(view, boundView);
        final GestureDetector detector = new GestureDetector(context, listener);
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        };
        return touchListener;
    }

    /**
     * Gets distance between 2 points (x1, y1) and (x2, y2)
     *
     * @param x1 x coordinate of point 1
     * @param y1 y coordinate of point 1
     * @param x2 x coordinate of point 2
     * @param y2 y coordinate of point 2
     * @return
     */
    private float getDistance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
