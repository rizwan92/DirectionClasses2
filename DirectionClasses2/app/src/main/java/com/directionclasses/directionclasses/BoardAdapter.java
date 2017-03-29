package com.directionclasses.directionclasses;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rizwan on 15-Mar-17.
 */

public class BoardAdapter extends ArrayAdapter<Board> {


    public BoardAdapter(Context context, ArrayList<Board> boards) {
        super(context, 0, boards);
    }

    public BoardAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Board> boards) {
        super(context, resource);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

   @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        Board board = getItem(position);
        int board_id = board.getBoard_id();
        String board_name = board.getBoard_name();

        TextView text_name = (TextView) listItemView.findViewById(R.id.tv1);
        text_name.setText(board_name);
      //  TextView text_id = (TextView) listItemView.findViewById(R.id.tv2);
       // text_id.setText(board_id+"");
        return listItemView;


    }
}
