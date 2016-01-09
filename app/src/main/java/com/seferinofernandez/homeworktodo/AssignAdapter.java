package com.seferinofernandez.homeworktodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Seferino Fernandez
 */
public class AssignAdapter extends ArrayAdapter<Assignment>{

    public AssignAdapter(Context context, ArrayList<Assignment> items) {
        super(context, 0, items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Assignment item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.assign_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvDueDate = (TextView) convertView.findViewById(R.id.tvDueDate);
        TextView tvClassName = (TextView) convertView.findViewById(R.id.tvClassName);
        // Populate the data into the template view using the data object
        tvName.setText(item.getAssignName());
        tvDueDate.setText("Due: " + item.getDueDate());
        tvClassName.setText("Class: " + item.getClassName());
        // Return the completed view to render on screen
        return convertView;
    }
}
