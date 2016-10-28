package com.humanbooster.basededonnees;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nea on 26/10/2016.
 */

public class DatabaseService {

    private SQLiteDatabase db;

    public DatabaseService(DatabaseHelper helper) {
        db = helper.getWritableDatabase();
    }

    public void addContact(Contact contact){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", contact.getName());
        contentValues.put("firstname", contact.getFirstname());
        contentValues.put("telephone", contact.getPhone());
        db.insert("contact", null, contentValues);
    }

    public void removeContact(Integer contactId){
        db.delete("contact", "_id = ?", new String[]{contactId.toString()});
    }

    public Contact getContact(Integer contactId) {
        Contact contactFound = new Contact();
        Cursor result = db.rawQuery("SELECT * FROM contact WHERE _id = ? ", new String[]{contactId.toString()});

        result.moveToFirst();

        if (contactId == result.getInt(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_ID))) {
            String firstname = result.getString(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_FIRSTNAME));
            String name = result.getString(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_NAME));
            String phone = result.getString(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_PHONE));

            contactFound.setId(contactId);
            contactFound.setName(name);
            contactFound.setFirstname(firstname);
            contactFound.setPhone(phone);

            result.close();;
        }
        return contactFound;
    }

    public Contact getContactByNameAndFirstname(String name, String firstname) {
        Contact contactFound = new Contact();
        Cursor result = db.rawQuery("SELECT * FROM contact WHERE name = ? AND firstname = ?",
                new String[]{name, firstname});
        result.moveToFirst();

        try {
            Integer id = result.getInt(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_ID));
            String phone = result.getString(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_PHONE));

            contactFound.setName(name);
            contactFound.setId(id);
            contactFound.setFirstname(firstname);
            contactFound.setPhone(phone);

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactFound;
    }

    public void updateContact (Contact contact) {
        String name = contact.getName();
        String firstname = contact.getFirstname();
        String phone = contact.getPhone();
        ContentValues updateUser = new ContentValues();
        updateUser.put(DatabaseHelper.CONTACT_COLUMN_PHONE, phone);
        db.update("contact", updateUser, "name = ? AND firstname = ?", new String[]{name, firstname});
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<Contact>();
        Cursor result = db.rawQuery("SELECT * FROM contact", null);
        result.moveToFirst();

        while (result.isAfterLast() == false) {
            contacts.add(new Contact(
                    result.getInt(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_ID)),
                    result.getString(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_NAME)),
                    result.getString(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_FIRSTNAME)),
                    result.getString(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_PHONE))));
            result.moveToNext();
        }
        result.close();
        return contacts;
    }

    public Integer getLastId() {
        Cursor result = db.rawQuery("SELECT * FROM contact ORDER BY _id DESC LIMIT 1", null);
        result.moveToFirst();
        Integer lastId = result.getInt(result.getColumnIndex(DatabaseHelper.CONTACT_COLUMN_ID));
        return lastId;
    }

}
