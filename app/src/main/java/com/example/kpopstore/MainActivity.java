package com.example.kpopstore;

import static com.example.kpopstore.R.*;
import static com.example.kpopstore.R.id.txtuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    String Username="admin";
    String password="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        EditText txtuser=findViewById(id.txtpass);
        EditText txtpass=findViewById(R.id.txtpass);
        Button login=findViewById(R.id.Login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtuser.getText().toString().equalsIgnoreCase(Username)&&txtpass.getText().toString().equalsIgnoreCase(password)){
                    startActivity(new Intent(MainActivity.this, MainMenu.class));
                }else{
                    Toast.makeText(MainActivity.this, "Username atau Password Salah",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}