package com.ek.email.learnsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button urun_ekle_btn, urun_listele_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urun_ekle_btn = findViewById(R.id.urun_ekle_button);
        urun_listele_btn = findViewById(R.id.urun_listele_button);


        urun_ekle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddProductActivity.class));
                finish();
            }
        });

        urun_listele_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListProductActivity.class));
                finish();
            }
        });
    }
}
