package com.example.android.moviecatalogueapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MoviesDetailActivity extends AppCompatActivity {
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";

    public static String MOVIE_ID = "movie_id";

    ProgressBar progressBar;

    private ImageView moviePhoto;
    private TextView movieTitle;
    private TextView movieReleaseDate;
    private TextView movieRating;
    private TextView movieDescription;

    private MoviesRepository moviesRepository;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        movieId = getIntent().getIntExtra(MOVIE_ID, movieId);

        moviesRepository = MoviesRepository.getInstance();

        initUI();

        getMovie();

    }

    private void initUI() {
        moviePhoto = findViewById(R.id.dtl_photo_movies);
        movieTitle = findViewById(R.id.dtl_name_movies);
        movieReleaseDate = findViewById(R.id.dtl_date_movies);
        movieRating = findViewById(R.id.dtl_rating_movies);
        movieDescription = findViewById(R.id.dtl_description_movies);
        progressBar = findViewById(R.id.dtl_progress_bar_movies);
    }


    private void getMovie() {
        progressBar.setVisibility(View.VISIBLE);
        moviesRepository.getMovie(movieId, new OnGetItemCallback() {
            @Override
            public void onSuccess(Items items) {
                progressBar.setVisibility(View.GONE);
                movieTitle.setText(items.getName());
                movieReleaseDate.setText(items.getReleaseDate());
                movieRating.setText(String.valueOf(items.getRating()));
                movieDescription.setText(items.getDescription());
                if (!isFinishing()) {
                    Glide.with(MoviesDetailActivity.this)
                            .load(IMAGE_BASE_URL + items.getPhoto())
                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                            .into(moviePhoto);
                }
            }

            @Override
            public void onError() {
                finish();
            }
        });
    }

}
