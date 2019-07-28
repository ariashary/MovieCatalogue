package com.example.android.moviecatalogueapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TVDetailActivity extends AppCompatActivity {
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";

    public static String TV_ID = "tv_id";

    ProgressBar progressBar;

    private ImageView TVPhoto;
    private TextView TVTitle;
    private TextView TVReleaseDate;
    private TextView TVRating;
    private TextView TVDescription;

    private TVRepository TVRepository;
    private int tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetail);

        tvId = getIntent().getIntExtra(TV_ID, tvId);

        TVRepository = TVRepository.getInstance();

        initUI();

        getTV();
    }

    private void initUI() {
        TVPhoto = findViewById(R.id.dtl_photo_tv);
        TVTitle = findViewById(R.id.dtl_name_tv);
        TVReleaseDate = findViewById(R.id.dtl_date_tv);
        TVRating = findViewById(R.id.dtl_rating_tv);
        TVDescription = findViewById(R.id.dtl_description_tv);
        progressBar = findViewById(R.id.dtl_progress_bar_tv);
    }


    private void getTV() {
        progressBar.setVisibility(View.VISIBLE);
        TVRepository.getTv(tvId, new OnGetItemCallback() {
            @Override
            public void onSuccess(Items items) {
                progressBar.setVisibility(View.GONE);
                TVTitle.setText(items.getTvName());
                TVReleaseDate.setText(items.getTvReleaseDate());
                TVRating.setText(String.valueOf(items.getRating()));
                TVDescription.setText(items.getDescription());
                if (!isFinishing()) {
                    Glide.with(TVDetailActivity.this)
                            .load(IMAGE_BASE_URL + items.getPhoto())
                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                            .into(TVPhoto);
                }
            }

            @Override
            public void onError() {
                finish();
            }
        });
    }
}
