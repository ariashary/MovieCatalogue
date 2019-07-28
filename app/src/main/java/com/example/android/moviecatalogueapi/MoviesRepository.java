package com.example.android.moviecatalogueapi;

import android.graphics.Movie;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en-US";

    private static MoviesRepository repository;

    private MoviesTMDbApi api;

    private MoviesRepository(MoviesTMDbApi api) {
        this.api = api;
    }

    public static MoviesRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new MoviesRepository(retrofit.create(MoviesTMDbApi.class));
        }

        return repository;
    }

    public void getMovies(final OnGetItemsCallback callback) {
        api.getMovies("9f32de4c8b0e56f0204c318e7b0d59fb", LANGUAGE)
                .enqueue(new Callback<ItemsResponse>() {
                    @Override
                    public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                        if (response.isSuccessful()) {
                            ItemsResponse itemsResponse = response.body();
                            if (itemsResponse != null && itemsResponse.getItems() != null) {
                                callback.onSuccess(itemsResponse.getItems());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ItemsResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getMovie(int movieId, final OnGetItemCallback callback) {
        api.getMovie(movieId, "9f32de4c8b0e56f0204c318e7b0d59fb", LANGUAGE)
                .enqueue(new Callback<Items>() {
                    @Override
                    public void onResponse(Call<Items> call, Response<Items> response) {
                        if (response.isSuccessful()) {
                            Items items = response.body();
                            if (items != null) {
                                callback.onSuccess(items);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Items> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}
