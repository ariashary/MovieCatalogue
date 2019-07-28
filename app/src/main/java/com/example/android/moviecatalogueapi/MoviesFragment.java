package com.example.android.moviecatalogueapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private MoviesRepository moviesRepository;

    ProgressBar progressBar;



    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movies, container, false);

        recyclerView = view.findViewById(R.id.rv_movies);
        progressBar = view.findViewById(R.id.progress_bar_movies);

        showRecyclerlist();

        return view;
    }

    private void showRecyclerlist() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        moviesRepository = MoviesRepository.getInstance();
        moviesRepository.getMovies(new OnGetItemsCallback() {
            @Override
            public void onSuccess(List<Items> items) {
                progressBar.setVisibility(View.GONE);
                adapter = new MoviesAdapter(items, callback);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError() {
                Toast.makeText(getActivity(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    OnItemsClickCallback callback = new OnItemsClickCallback() {
        @Override
        public void onClick(Items items) {
            Intent intent = new Intent(getActivity(), MoviesDetailActivity.class);
            intent.putExtra(MoviesDetailActivity.MOVIE_ID, items.getId());
            startActivity(intent);
        }
    };
}
