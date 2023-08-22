package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesItemView> {
    private final ArrayList<Place> placesData;
    private PlacesActivity placesActivity;
    public PlacesAdapter(ArrayList<Place> placesData) {
        this.placesData = placesData;
    }

    public void setPlacesActivity(PlacesActivity placesActivity) {
        this.placesActivity = placesActivity;
    }

    @NonNull
    @Override
    public PlacesItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.place_item_view, viewGroup, false);
        PlacesItemView placesItemView = new PlacesItemView(view);
        placesItemView.setPlacesActivity(this.placesActivity);
        return placesItemView;
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesItemView placesItemView, int i) {
        Place selectedPlace = placesData.get(i);
        String placeName = selectedPlace.getPlaceName();
        String description = selectedPlace.getDescription();
        placesItemView.txtPlaceName.setText(placeName);
        placesItemView.txtDescription.setText(description);
    }

    @Override
    public int getItemCount() {
        return placesData.size();
    }
}
