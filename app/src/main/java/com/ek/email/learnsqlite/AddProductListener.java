package com.ek.email.learnsqlite;

import java.util.List;

public interface AddProductListener {
    void didDatasSave(List<String> urunadi_list,List<String> uruncinsi_list,List<Integer> fiyat_list,List<String>renk_list,List<String> giristarihi_list);
}
