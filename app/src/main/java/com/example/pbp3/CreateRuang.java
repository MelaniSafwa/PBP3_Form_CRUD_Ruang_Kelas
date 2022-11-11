package com.example.pbp3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateRuang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan;
    EditText namaRuangan, kapasitas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ruang);
        dbHelper = new DataHelper(this);
        namaRuangan = findViewById(R.id.etNoRuangan);
        kapasitas = findViewById(R.id.etKapasitas);
        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into ruangan(namaRuangan, kapasitas) values('" +
                        namaRuangan.getText().toString() + "','" +
                        kapasitas.getText().toString() + "')");
                Toast.makeText(CreateRuang.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
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