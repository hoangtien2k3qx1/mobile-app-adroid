package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class DetailPlaceActivity extends Activity {
    public static PlacesActivity placesActivity;
    private DetailPlaceActivity detailPlaceActivity;
    private Place selectedPlace;
    private TextView txtPlaceName;
    private TextView txtDescription;
    private Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        // lấy ra giá trị từ màn hình khác truyền đến
        selectedPlace = (Place) Objects.requireNonNull(getIntent().getExtras()).getSerializable("selectedPlace");
        Toast.makeText(this, selectedPlace.toString(), Toast.LENGTH_SHORT).show();

        txtPlaceName = (TextView) findViewById(R.id.txtPlaceNames);
        txtDescription = (TextView) findViewById(R.id.txtDescriptions);
        btnDelete = findViewById(R.id.btnDelete);

        txtPlaceName.setText(selectedPlace.getPlaceName());
        txtDescription.setText(selectedPlace.getDescription());
        setupAction();

    }

    public void setSelectedPlace(Place selectedPlace) {
        this.selectedPlace = selectedPlace;
        txtPlaceName.setText(selectedPlace.getPlaceName());
        txtDescription.setText(selectedPlace.getDescription());
    }

    private void actionUpdatePlace() {
        // edit dialog
        EditPlaceDialog editPlaceDialog = new EditPlaceDialog(this,
                DetailPlaceActivity.placesActivity,
                selectedPlace,
                this,
                R.layout.activity_detail_place);
        editPlaceDialog.show();
    }
    private void setupAction() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DetailPlaceActivity.this)
                        .setTitle("Delete place")
                        .setMessage("Are you sure you want to delete this?")
                        .setIcon(android.R.drawable.dark_header)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                placesActivity.deletePlace(selectedPlace.getPlaceId());
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            }
        });

        txtPlaceName.setOnClickListener(v -> actionUpdatePlace());
        txtDescription.setOnClickListener(v -> actionUpdatePlace());

    }

}