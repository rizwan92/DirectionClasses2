package com.directionclasses.directionclasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rizwan on 18-Mar-17.
 */

public class SubjectAdapter extends ArrayAdapter<Subject> {


    public SubjectAdapter(Context context, ArrayList<Subject> subjects) {
        super(context, 0, subjects);
    }

   /* @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }*/
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        Subject subject = getItem(position);
        int subject_id = subject.getSubject_id();
        String subject_name = subject.getSubject_name();

        TextView text_name = (TextView) listItemView.findViewById(R.id.tv1);
        text_name.setText(subject_name);
        //  TextView text_id = (TextView) listItemView.findViewById(R.id.tv2);
        // text_id.setText(board_id+"");
        return listItemView;


    }
}
