package com.fourthstatelab.wheelchair.component.stick;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fourthstatelab.wheelchair.R;

public class Stick extends FrameLayout {

    private View rootView;
    private ImageView stickView;

    public Stick(Context context) {
        super(context);
        init();
    }

    public Stick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Stick(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Stick(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * View initialization
     */
    private void init() {
        rootView = View.inflate(getContext(), R.layout.pan_view, null);
        stickView = (ImageView) rootView.findViewById(R.id.stick_view);
        addView(rootView);
        this.post(new Runnable() {
            @Override
            public void run() {
                stickView.setOnTouchListener(StickGestureListener.getTouchListener(getContext(), stickView, rootView));
            }
        });
    }
}
