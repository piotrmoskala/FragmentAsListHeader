package io.inspace.listtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import io.inspace.fragmentaslistheader.HeaderFragment;

/**
 * Created by Piotr on 2015-07-16.
 */
public class DefaultHeaderFragment extends Fragment implements HeaderFragment {

    private ScrollView mScrollView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View v = layoutInflater.inflate(R.layout.fragment_header, viewGroup, false);
        mScrollView = (ScrollView) v.findViewById(R.id.scroll);
        return v;
    }

    public float getHeaderHeight() {
        return getView().getMeasuredHeight();
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        mScrollView.onTouchEvent(motionEvent);
    }
}

