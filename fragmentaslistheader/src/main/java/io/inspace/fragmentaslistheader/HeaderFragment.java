package io.inspace.fragmentaslistheader;

import android.view.MotionEvent;

/**
 * HeaderFragment interface.
 */
public interface HeaderFragment {
    public float getHeaderHeight();
    public void onTouchEvent(MotionEvent motionEvent);
}
