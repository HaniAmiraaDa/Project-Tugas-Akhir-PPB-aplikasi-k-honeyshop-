package com.example.kpopstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public class Profil extends AppCompatActivity {
    EditText inputText;
    Button btn_simpan, btn_hapus, btn_lihat;
    SessionSp session;
    TextView tampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionSp(this);

        inputText = findViewById(R.id.inputText);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_lihat = findViewById(R.id.btn_lihat);
        btn_hapus = findViewById(R.id.btn_hapus);
        tampil = findViewById(R.id.tampilData);

    btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kalimat = String.valueOf(inputText.getText());
                session.setKalimat(kalimat);
                Toast.makeText(Profil.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
            }
        });

        btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kalimat = session.getKalimat();
                tampil.setText(kalimat);
            }
        });

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setKalimat("");
                Toast.makeText(Profil.this, "Data di hapus!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}