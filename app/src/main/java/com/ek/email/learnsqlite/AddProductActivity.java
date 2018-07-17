package com.ek.email.learnsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddProductActivity extends AppCompatActivity implements AddProductListener {

    private AddProductPresenter addProductPresenter;
    EditText urunadi_edt, urunCinsi_edt, renk_edt, fiyat_edt, girisTarihi_edt;
    String urunadi, urunCinsi, renk, girisTarihi;
    int fiyat;
    Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        save_btn = findViewById(R.id.activity_addproduct_save_btn);
        urunadi_edt = findViewById(R.id.activity_addproduct_urunadi_edt);
        urunCinsi_edt = findViewById(R.id.activity_addproduct_uruncinsi_edt);
        renk_edt = findViewById(R.id.activity_addproduct_renk_edt);
        fiyat_edt = findViewById(R.id.activity_addproduct_fiyat_edt);
        girisTarihi_edt = findViewById(R.id.activity_addproduct_girisTarihi_edt);
        addProductPresenter = new AddProductPresenter(this,this);
        addProductPresenter.openDB();


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    fiyat = Integer.valueOf(fiyat_edt.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(AddProductActivity.this, "Fiyat Girmek Zorunludur", Toast.LENGTH_SHORT).show();
                }
                urunadi = urunadi_edt.getText().toString().trim();
                urunCinsi = urunCinsi_edt.getText().toString().trim();
                renk = renk_edt.getText().toString().trim();
                girisTarihi = girisTarihi_edt.getText().toString().trim();

                addProductPresenter = new AddProductPresenter(AddProductActivity.this, urunadi, urunCinsi, renk, girisTarihi, fiyat, AddProductActivity.this);
                addProductPresenter.openDB();
                addProductPresenter.addProduct();


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddProductActivity.this, MainActivity.class));
        finish();
    }


    @Override
    public void didDatasSave(List<String> urunadi_list, List<String> uruncinsi_list, List<Integer> fiyat_list, List<String> renk_list, List<String> giristarihi_list) {
       startActivity(new Intent(AddProductActivity.this,ListProductActivity.class));
       finish();
        Toast.makeText(this, "Veriler Eklendi", Toast.LENGTH_SHORT).show();



    }
}
