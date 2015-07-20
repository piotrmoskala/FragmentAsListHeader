package io.inspace.listtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import io.inspace.fragmentaslistheader.HeaderListFragment;
import io.inspace.fragmentaslistheader.HeaderListView;

/**
 * Created by Piotr on 2015-07-16.
 */
public class DefaultListFragment extends Fragment implements HeaderListFragment {

    private HeaderListView mListView;
    private HeaderListView.HeaderedListViewListener mCallback;
    private ListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View v = layoutInflater.inflate(R.layout.fragment_list, viewGroup, false);

        // Get ListView object from xml
        mListView = (HeaderListView) v.findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[]{"Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };
        adapter = new ListAdapter(getActivity(), R.layout.view_list_row, Arrays.asList(values));
        mListView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof HeaderListView.HeaderedListViewListener) {
            mCallback = (HeaderListView.HeaderedListViewListener) activity;
        }
    }

    public void setHeaderViewHeight(float height) {
        mListView.setHeaderHeight((int) height);
    }

    @Override
    public void setHeaderTouchCallbacks(HeaderListView.HeaderedListViewListener listener) {
        mListView.setHeaderedListViewListener(listener);
    }


}
