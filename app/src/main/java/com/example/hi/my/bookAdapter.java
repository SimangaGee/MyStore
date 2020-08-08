package com.example.hi.my;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class bookAdapter extends ArrayAdapter<item> {

    private static final String LOG_TAG = bookAdapter.class.getSimpleName();


    public bookAdapter(Activity context, ArrayList<item> desserts) {

        super(context, 0, desserts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Dessert} object located at this position in the list
        item currentDesert = getItem(position);


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.product_namee);

        nameTextView.setText(currentDesert.getProductName());


        TextView numberTextView = (TextView) listItemView.findViewById(R.id.product_numbere);

        numberTextView.setText(String.valueOf(currentDesert.getProductPrice()));


        TextView number = (TextView) listItemView.findViewById(R.id.product_number);

        number.setText(String.valueOf(currentDesert.getProductbuy()));


        TextView date = (TextView) listItemView.findViewById(R.id.product_date);

        date.setText(String.valueOf(currentDesert.getProductdate()));

        return listItemView;
    }
}
