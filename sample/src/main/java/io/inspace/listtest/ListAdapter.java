package io.inspace.listtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Piotr on 2015-07-20.
 */
public class ListAdapter extends ArrayAdapter<String> {

    public List<String> mItems;

    public ListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        mItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.view_list_row, null);
        } else {
            v = convertView;
        }
        TextView textView = (TextView) v.findViewById(R.id.list_row_textview);
        textView.setText(mItems.get(position));
        return v;
    }
}
