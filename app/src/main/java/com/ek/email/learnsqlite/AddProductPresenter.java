package com.ek.email.learnsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ek.email.learnsqlite.AddProductListener;
import com.ek.email.learnsqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AddProductPresenter {
    String urunAdi, urunCinsi, renk, girisTarihi;
    int fiyat;
    public static final String CREATE_DATABASE = " create table PRODUCT(ID integer primary key autoincrement,URUNADI text,URUNCINSI text,FIYAT integer,RENK text,GIRISTARIHI text) ";
    Context context;
    DatabaseHelper dbh;
    SQLiteDatabase db;
    List<String> urunadi_list = new ArrayList<>();
    List<String> uruncinsi_list = new ArrayList<>();
    List<Integer> fiyat_list = new ArrayList<>();
    List<String> renk_list = new ArrayList<>();
    List<String> giristarihi_list = new ArrayList<>();
    AddProductListener addProductListener;

    public AddProductPresenter(Context context, AddProductListener addProductListener){
        this.context=context;
        this.addProductListener=addProductListener;
        dbh = new DatabaseHelper(context);
    }

    public AddProductPresenter(Context context, String urunAdi, String urunCinsi, String renk, String girisTarihi, int fiyat, AddProductListener addProductListener) {
        this.urunAdi = urunAdi;
        this.urunCinsi = urunCinsi;
        this.renk = renk;
        this.girisTarihi = girisTarihi;
        this.fiyat = fiyat;
        this.context = context;
        this.addProductListener = addProductListener;
        dbh = new DatabaseHelper(context);

    }

    public void openDB() {
        db = dbh.getWritableDatabase();
    }

    public void closeDB() {
        dbh.close();
    }

    public void addProduct() {
        ContentValues con = new ContentValues();
        con.put("URUNADI", urunAdi);
        con.put("URUNCINSI", urunCinsi);
        con.put("FIYAT", fiyat);
        con.put("RENK", renk);
        con.put("GIRISTARIHI", girisTarihi);
        db.insert("PRODUCT", null, con);


    }

    public void getProductList() {
        String[] table_list = {"URUNADI", "URUNCINSI", "FIYAT", "RENK", "GIRISTARIHI"};
        Cursor cursor = db.query("PRODUCT", table_list, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            urunadi_list.add(cursor.getString(0));
            uruncinsi_list.add(cursor.getString(1));
            fiyat_list.add(cursor.getInt(2));
            renk_list.add(cursor.getString(3));
            giristarihi_list.add(cursor.getString(4));
            cursor.moveToNext();
        }
        cursor.close();
        addProductListener.didDatasSave(urunadi_list,uruncinsi_list,fiyat_list,renk_list,giristarihi_list);
    }


}
