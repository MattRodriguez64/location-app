package fr.mattrodriguez.localisationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import fr.mattrodriguez.localisationapp.api.GeoAPI;
import fr.mattrodriguez.localisationapp.model.LocationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView locationValue;
    TextView locationLabel;
    Button locateButton;
    ArrayList<LocationResponse> locationResponses;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationValue = findViewById(R.id.location_value);
        locationLabel = findViewById(R.id.location_label);
        locateButton = findViewById(R.id.location_button);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationClient.getLastLocation()
                            .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        String responseCoords = "(" + location.getLatitude() + ";" + location.getLongitude() + ")";
                                        Log.d("Log", responseCoords);

                                        Retrofit retrofit = new Retrofit.Builder()
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .baseUrl("https://api-adresse.data.gouv.fr/")
                                                .build();
                                        GeoAPI api = retrofit.create(GeoAPI.class);
                                        Call<LocationResponse> locationCallApi = api.getLocation(location.getLongitude(), location.getLatitude());

                                        locationCallApi.enqueue(new Callback<LocationResponse>() {

                                            @Override
                                            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                                                locationValue.setText(response.body().getFeatures().get(0).getProperties().getCity());
                                            }

                                            @Override
                                            public void onFailure(Call<LocationResponse> call, Throwable t) {
                                                Log.d("Yo", "Errror!");
                                            }

                                        });
                                    }
                                }
                            });

                    return;
                }
            }
        });


    }
}