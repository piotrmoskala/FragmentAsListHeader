package io.inspace.fragmentaslistheader;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * This is the ListView which contains invisible header that we can set.
 * This view intercepts and passes touch events from header through listeners so you can easily
 * manipulate them. Also it passes scroll position that can be used to scroll or manipulate
 * header view.
 */
public class HeaderListView extends ListView implements AbsListView.OnScrollListener {

    private HeaderedListViewListener mCallbacks;
    private Dictionary<Integer, Integer> listViewItemHeights = new Hashtable<Integer, Integer>();
    private View mFakeHeaderView;

    public HeaderListView(Context context) {
        super(context);
        init();
    }

    public HeaderListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setOnScrollListener(this);
        mFakeHeaderView = new View(getContext());
        addHeaderView(mFakeHeaderView);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view.getChildCount() == 0) return;
        if (mCallbacks != null) {
            mCallbacks.onListScroll(-getScroll());
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent){
        Rect rect = new Rect();
        int childCount = getChildCount();
        int[] listViewCoords = new int[2];
        getLocationOnScreen(listViewCoords);
        int x = (int) motionEvent.getRawX() - listViewCoords[0];
        int y = (int) motionEvent.getRawY() - listViewCoords[1];
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            child.getHitRect(rect);
            if (rect.contains(x, y)) {
                if (mFakeHeaderView.equals(child)) {
                    if(mCallbacks!=null) {
                        mCallbacks.onHeaderTouch(motionEvent);
                    }
                    return false;
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setHeaderHeight(int height) {
        mFakeHeaderView.setMinimumHeight(height);
    }

    public void setHeaderedListViewListener(HeaderedListViewListener callbacks) {
        mCallbacks = callbacks;
    }

    private int getScroll() {
        View c = getChildAt(0); //this is the first visible row
        int scrollY = -c.getTop();
        listViewItemHeights.put(getFirstVisiblePosition(), c.getHeight());
        for (int i = 0; i < getFirstVisiblePosition(); ++i) {
            if (listViewItemHeights.get(i) != null) // (this is a sanity check)
                scrollY += listViewItemHeights.get(i); //add all heights of the views that are gone
        }
        return scrollY;
    }

    public interface HeaderedListViewListener {
        public void onListScroll(int firstElementPosition);
        public void onHeaderTouch(MotionEvent motionEvent);
    }

}
