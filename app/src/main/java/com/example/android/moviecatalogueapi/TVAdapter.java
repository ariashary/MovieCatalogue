package com.example.android.moviecatalogueapi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TVAdapter extends  RecyclerView.Adapter<TVAdapter.ItemViewHolder> {
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    private List<Items> mData;
    private OnItemsClickCallback callback;

    public TVAdapter(List<Items> mData, OnItemsClickCallback callback) {
        this.callback = callback;
        this.mData = mData;
    }

    @NonNull
    @Override
    public TVAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tv_row, viewGroup, false);
        return new TVAdapter.ItemViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.bind(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName;
        TextView tvReleaseDate;
        TextView tvRating;
        TextView tvDescription;
        Items items;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo_tv);
            tvName = itemView.findViewById(R.id.txt_name_tv);
            tvReleaseDate = itemView.findViewById(R.id.txt_releaseDate_tv);
            tvRating = itemView.findViewById(R.id.txt_rating_tv);
            tvDescription = itemView.findViewById(R.id.txt_description_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(items);
                }
            });
        }

        public void bind(Items items) {
            this.items = items;
            tvName.setText(items.getTvName());
            tvReleaseDate.setText(items.getTvReleaseDate());
            tvRating.setText(String.valueOf(items.getRating()));
            tvDescription.setText(items.getDescription());

            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + items.getPhoto())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imgPhoto);
        }
    }
}
