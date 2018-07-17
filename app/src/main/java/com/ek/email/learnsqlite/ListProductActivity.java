package com.ek.email.learnsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity implements AddProductListener {

    private ListView listView;
    AddProductPresenter addProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        listView = findViewById(R.id.list_view);
        addProductPresenter = new AddProductPresenter(this, this);
        addProductPresenter.openDB();
        addProductPresenter.getProductList();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ListProductActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void didDatasSave(List<String> urunadi_list, List<String> uruncinsi_list, List<Integer> fiyat_list, List<String> renk_list, List<String> giristarihi_list) {

        List<String> urunler = new ArrayList<>();
        int sayi = 0;
        for (int i = urunadi_list.size() - 1; i > -1; i--) {
            sayi += 1;
            urunler.add(
                    "Ürün " + sayi +
                            "\nÜrün adı: " + urunadi_list.get(i) + "\n" +
                            "Ürün cinsi: " + uruncinsi_list.get(i) + "\n" +
                            "Fiyat: " + fiyat_list.get(i) + "\n" +
                            "Renk: " + renk_list.get(i) + "\n" +
                            "Giriş Tarihi: " + giristarihi_list.get(i) + "\n"

            );
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, urunler);
        listView.setAdapter(adapter);
    }
}
