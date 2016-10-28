package com.humanbooster.basededonnees;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nea on 27/10/2016.
 */

public class ContactAdapter extends ArrayAdapter<Contact>{
    public ContactAdapter(Context context, List<Contact> contacts) {
        super(context, R.layout.my_template, contacts);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.my_template, parent, false);

        Contact contact = getItem(position);
        TextView idView = (TextView) rowView.findViewById(R.id.contact_id);
        TextView nameView = (TextView) rowView.findViewById(R.id.contact_name);
        TextView firstnameView = (TextView) rowView.findViewById(R.id.contact_firstname);
        TextView phoneView = (TextView) rowView.findViewById(R.id.contact_phone);

        idView.setText(contact.getId().toString());
        nameView.setText(contact.getName());
        firstnameView.setText(contact.getFirstname());
        phoneView.setText(contact.getPhone());

        return rowView;
    }
}
