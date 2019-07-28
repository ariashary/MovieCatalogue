package com.example.android.moviecatalogueapi;

import java.util.List;

public interface OnGetItemsCallback {
    void onSuccess(List<Items> items);

    void onError();
}
