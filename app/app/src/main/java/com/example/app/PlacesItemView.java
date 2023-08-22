package com.example.app;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlacesItemView extends RecyclerView.ViewHolder {
    public TextView txtPlaceName;
    public TextView txtDescription;
    private PlacesActivity placesActivity;

    public void setPlacesActivity(PlacesActivity placesActivity) {
        this.placesActivity = placesActivity;
    }

    public PlacesItemView(@NonNull View itemView) {
        super(itemView);
        txtPlaceName = (TextView) itemView.findViewById(R.id.txtPlaceName);
        txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy ra vị trí mình chạm đến
                PlacesItemView.this.placesActivity.navigateToDetailPlace(getLayoutPosition());

            }
        });
    }
}
