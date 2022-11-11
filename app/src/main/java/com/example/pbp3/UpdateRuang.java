package com.example.pbp3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateRuang extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan;
    EditText namaRuangan, kapasitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ruang);

        dbHelper = new DataHelper(this);
        namaRuangan = findViewById(R.id.etNoRuangan);
        kapasitas = findViewById(R.id.etKapasitas);
        btnSimpan = findViewById(R.id.btnSimpan);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ruangan WHERE namaRuangan = '" +
                getIntent().getStringExtra("namaRuangan") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            namaRuangan.setText(cursor.getString(0).toString());
            kapasitas.setText(cursor.getString(1).toString());
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update ruangan set kapasitas='" +
                        kapasitas.getText().toString() + "', namaRuangan='" +
                        namaRuangan.getText().toString() + "' where namaRuangan='" +
                        getIntent().getStringExtra("namaRuangan") + "'");
                Toast.makeText(UpdateRuang.this, "Berhasil Mengubah Data", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }
    }
}