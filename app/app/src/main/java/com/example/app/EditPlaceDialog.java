package com.example.app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EditPlaceDialog extends Dialog {
    private PlacesActivity placesActivity;
    private DetailPlaceActivity detailPlaceActivity;
    private Place selectedPlace;
    private EditText txtPlaceName;
    private EditText txtDescription;
    private Button btnSave;

    public EditPlaceDialog(Context context, PlacesActivity placesActivity, Place selectedPlace, DetailPlaceActivity detailPlaceActivity,int layoutResId) {
        super(context);
        this.placesActivity = placesActivity;
        this.selectedPlace = selectedPlace;
        this.detailPlaceActivity = detailPlaceActivity;
        this.setContentView(layoutResId);
        setupUI();
    }

    private void setupUI() {
        txtPlaceName = (EditText) findViewById(R.id.txtPlaceName);
        txtDescription = (EditText) findViewById(R.id.txtDescription);

        btnSave = (Button)findViewById(R.id.btnSave);
        txtPlaceName.setText(selectedPlace.getPlaceName());
        txtDescription.setText(selectedPlace.getDescription());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPlace.setPlaceName(txtPlaceName.getText().toString().trim());
                selectedPlace.setDescription(txtDescription.getText().toString().trim());
                EditPlaceDialog.this.placesActivity.updatePlace(selectedPlace);
                EditPlaceDialog.this.detailPlaceActivity.setSelectedPlace(selectedPlace);
                EditPlaceDialog.this.dismiss();
            }
        });
    }

}
