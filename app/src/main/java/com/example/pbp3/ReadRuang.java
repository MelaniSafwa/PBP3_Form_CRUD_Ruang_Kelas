package com.example.pbp3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReadRuang extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    TextView namaRuangan, kapasitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_ruang);

        dbHelper = new DataHelper(this);
        namaRuangan = findViewById(R.id.NamaRuangan);
        kapasitas = findViewById(R.id.Kapasitas);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ruangan WHERE namaRuangan = '" +
                getIntent().getStringExtra("namaRuangan") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            namaRuangan.setText(cursor.getString(0).toString());
            kapasitas.setText(cursor.getString(1).toString());
        }

        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

    }
}