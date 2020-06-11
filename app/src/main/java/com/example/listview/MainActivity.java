package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView lv;
    List<String> list_phone, list_name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        list_name = new ArrayList<String>();
        list_phone = new ArrayList<String>();
        Cursor c = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, null);
        //获取通讯录的信息

        int phoneIndex = 0, nameIndex = 0;
        if (c.getCount() > 0) {
            phoneIndex = c
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // 获取手机号码的列名
            nameIndex = c
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            // 获取用户名的列名
        }
        while (c.moveToNext()) {
            String phone = c.getString(phoneIndex);
            // 获取手机号码
            list_phone.add(phone);
            String name = c.getString(nameIndex);
            // 获取用户名
            list_name.add(name);

        }

        ListViewAdapter adapter = new ListViewAdapter(this, list_name ,  list_phone);
        lv.setAdapter(adapter);
    }

}
