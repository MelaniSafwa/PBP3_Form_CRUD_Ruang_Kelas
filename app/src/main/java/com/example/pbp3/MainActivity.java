package com.example.pbp3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    String[] daftar;
    ListView listView;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbHelper;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateRuang.class);
                startActivity(intent);
            }
        });
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }
        ma = this;
        dbHelper = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ruangan", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();
        }

        listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String selection = daftar[i];
                final CharSequence[] dialogItem = {"Lihat Ruangan", "Update Ruangan", "Hapus Ruangan"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, (dialog, item) -> {
                    switch (item) {
                        case 0:
                            Intent intent = new Intent(getApplicationContext(), ReadRuang.class);
                            intent.putExtra("namaRuangan", selection);
                            startActivity(intent);
                            break;
                        case 1:
                            Intent intent1 = new Intent(getApplicationContext(), UpdateRuang.class);
                            intent1.putExtra("namaRuangan", selection);
                            startActivity(intent1);
                            break;
                        case 2:
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            db.execSQL("delete from ruangan where namaRuangan = '" + selection + "'");
                            RefreshList();
                            break;
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter<?>) listView.getAdapter()).notifyDataSetInvalidated();
    }
}