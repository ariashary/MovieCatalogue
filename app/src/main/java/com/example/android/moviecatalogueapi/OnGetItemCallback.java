package com.example.android.moviecatalogueapi;

import android.graphics.Movie;

public interface OnGetItemCallback {
    void onSuccess(Items items);

    void onError();
}
