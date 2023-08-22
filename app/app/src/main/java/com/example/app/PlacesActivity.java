package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class PlacesActivity extends Activity {
    private RecyclerView placesRecyclerView;
    private RecyclerView.Adapter placesAdapter;
    private ArrayList<Place> placesData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String email = extras.getString("email");
        }

        placesRecyclerView = findViewById(R.id.placesRecyclerView);

        // fake data
        placesData.add(new Place("Ho Hoan Kiem Lake", "This is a beautifull place", 11));
        placesData.add(new Place("ABC Lake", "An exciting park", 22));
        placesData.add(new Place("XYZ Lake", "An good place",33));
        placesData.add(new Place("Ho Hoan Kiem Lake", "This is a beautifull place",44));
        placesData.add(new Place("ABC Lake", "An exciting park",55));
        placesData.add(new Place("XYZ Lake", "An good place",66));
        placesData.add(new Place("Ho Hoan Kiem Lake", "This is a beautifull place",77));
        placesData.add(new Place("ABC Lake", "An exciting park",88));
        placesData.add(new Place("XYZ Lake", "An good place",99));
        placesData.add(new Place("Ho Hoan Kiem Lake", "This is a beautifull place", 111));
        placesData.add(new Place("ABC Lake", "An exciting park", 222));
        placesData.add(new Place("XYZ Lake", "An good place",333));
        placesData.add(new Place("Ho Hoan Kiem Lake", "This is a beautifull place",444));
        placesData.add(new Place("ABC Lake", "An exciting park",555));
        placesData.add(new Place("XYZ Lake", "An good place", 666));
        placesData.add(new Place("Ho Hoan Kiem Lake", "This is a beautifull place",777));
        placesData.add(new Place("ABC Lake", "An exciting park",888));
        placesData.add(new Place("XYZ Lake", "An good place",999));
        placesData.add(new Place("Ho Hoan Kiem Lake", "This is a beautifull place", 1000));
        placesData.add(new Place("ABC Lake", "An exciting park", 1111));
        placesData.add(new Place("XYZ Lake", "An good place", 2222));
        placesData.add(new Place("Ho Hoan Kiem Lake", "This is a beautifull place",3333));
        placesData.add(new Place("ABC Lake", "An exciting park",4444));
        placesData.add(new Place("XYZ Lake", "An good place",5555));


        placesAdapter = new PlacesAdapter(placesData);
        ((PlacesAdapter) placesAdapter).setPlacesActivity(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);
        placesRecyclerView.setAdapter(placesAdapter);
        placesRecyclerView.setLayoutManager(layoutManager);

    }

    public void navigateToDetailPlace(Integer position) {
        Intent intent = new Intent(PlacesActivity.this, DetailPlaceActivity.class);
        Place selectedPlace = placesData.get(position);
        intent.putExtra("selectedPlace", selectedPlace);
        startActivity(intent);
        DetailPlaceActivity.placesActivity = this;
    }


    public void deletePlace(final Integer placeId) {
        placesData.removeIf(place -> {
           return place.getPlaceId().equals(placeId);
        });
        placesAdapter.notifyDataSetChanged(); // dữ liệu sẽ được reload lại
    }

    public void updatePlace(Place updatePlace) {
        placesData.forEach(place -> {
            if (place.getPlaceId().equals(updatePlace.getPlaceId())) {
                place.setPlaceName(updatePlace.getPlaceName());
                place.setDescription(updatePlace.getDescription());
            }
        });
    }


}