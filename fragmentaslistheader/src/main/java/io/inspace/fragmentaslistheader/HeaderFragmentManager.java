package io.inspace.fragmentaslistheader;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

/**
 * Class that manages two fragments HeaderFragment and ListFragment.
 */
public class HeaderFragmentManager implements HeaderListView.HeaderedListViewListener{

    private HeaderFragment mHeaderFragment;
    private HeaderListFragment mHeaderListFragment;
    private View mHeaderFragmentContainer;
    private Activity mActivity;
    private float mListHeaderHeight;

    public void init(Activity activity, HeaderFragment headerFragment, HeaderListFragment headerListFragment) {
        mHeaderListFragment = headerListFragment;
        mHeaderFragment = headerFragment;
        mActivity = activity;
        mHeaderFragmentContainer = mActivity.findViewById(R.id.fragment_header);
        if(mHeaderFragmentContainer == null)
            throw new NullPointerException("Cannot find container for HeaderFragment with id fragment_header");
        mHeaderListFragment.setHeaderTouchCallbacks(this);
    }

    @Override
    public void onListScroll(int firstElementPosition) {
        mHeaderFragmentContainer.setTop(firstElementPosition);
    }

    @Override
    public void onHeaderTouch(MotionEvent motionEvent) {
        mHeaderFragment.onTouchEvent(motionEvent);
    }

    public void setListHeaderHeight(float height) {
        mListHeaderHeight = height;
        mHeaderListFragment.setHeaderViewHeight(height);
    }

    public float getHeaderHeight() {
        return mHeaderFragment.getHeaderHeight();
    }

    public float getListHeaderHeight() {
        return mListHeaderHeight;
    }

}
