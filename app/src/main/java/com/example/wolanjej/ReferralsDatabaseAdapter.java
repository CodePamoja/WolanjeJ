package com.example.wolanjej;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReferralsDatabaseAdapter {
    Cursor getNumber;
    ContentResolver resolver ;

    Context context;

    public ReferralsDatabaseAdapter(Context context) {
        this.context = context;
    }

    public List<Contacts> getData() {
        List<Contacts> data = new ArrayList<>();
        Log.e("Found","Troal");
        getNumber = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if (getNumber != null) {
            Log.e("count", "" + getNumber.getCount());
            if (getNumber.getCount() == 0) {
                Toast.makeText(context, "No contacts in your contact list.", Toast.LENGTH_LONG).show();
            }

            while (getNumber.moveToNext()) {
                String id = getNumber.getString(getNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                String name = getNumber.getString(getNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = getNumber.getString(getNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //String EmailAddr = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA2));
                String image_thumb = getNumber.getString(getNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));

                Contacts selectUser = new Contacts();
                selectUser.setImage(image_thumb);
                selectUser.setName(name);
                selectUser.setPhone(phoneNumber);
                data.add(selectUser);
            }
        } else {
            Log.e("Cursor close 1", "----");
        }

        getNumber.close();

        return data;
    }
}
