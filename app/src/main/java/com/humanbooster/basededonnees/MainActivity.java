package com.humanbooster.basededonnees;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper myDatabaseHelper;
    private DatabaseService ds;
    private ListView listView;
    private TextView nameView;
    private TextView firstnameView;
    private TextView phoneView;
    private ContactAdapter adapter;
    private String inputName;
    private String inputFirstname;
    private String inputPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper = new DatabaseHelper(this);
        ds = new DatabaseService(myDatabaseHelper);
        listView = (ListView) findViewById(R.id.my_list);
        nameView = (TextView) findViewById(R.id.name);
        firstnameView = (TextView) findViewById(R.id.firstname);
        phoneView = (TextView) findViewById(R.id.phone);
    }

    public void onAddContact(View view) {
        inputName = nameView.getText().toString();
        inputFirstname = firstnameView.getText().toString();
        inputPhone = phoneView.getText().toString();

        ds.addContact(new Contact(inputName, inputFirstname, inputPhone));
        Toast.makeText(this, "Contact ajouté", Toast.LENGTH_SHORT).show();
        displayChangesToContactsList();
    }

    public void onDisplayContacts(View view) {
        displayChangesToContactsList();
    }

    public void onUpdatePhoneWithName(View view) {
        inputName = nameView.getText().toString();
        inputPhone = phoneView.getText().toString();
        inputFirstname = firstnameView.getText().toString();

        Contact contact = ds.getContactByNameAndFirstname(inputName, inputFirstname);
        contact.setPhone(inputPhone);
        ds.updateContact(contact);
        displayChangesToContactsList();
    }

    public void onRemoveByName(View view) {
        inputName = nameView.getText().toString();
        inputFirstname = firstnameView.getText().toString();
        Contact contact = ds.getContactByNameAndFirstname(inputName, inputFirstname);
        Integer id = contact.getId();
        ds.removeContact(id);
        displayChangesToContactsList();
    }

    public void onRemoveLast(View view) {
        Integer lastId = ds.getLastId();
        ds.removeContact(lastId);
        displayChangesToContactsList();
    }

    public void onFirstId(View view) {
        List<Contact> contacts = new ArrayList<>();
        Contact contact = ds.getContact(1);
        contacts.add(contact);
        ContactAdapter adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);
    }

    public void displayChangesToContactsList() {
        List<Contact> contacts = new ArrayList<Contact>();
        contacts = ds.getAllContacts();
        adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);
    }
}
