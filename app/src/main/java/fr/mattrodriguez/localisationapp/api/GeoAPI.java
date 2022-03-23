package fr.mattrodriguez.localisationapp.api;

import java.util.List;

import fr.mattrodriguez.localisationapp.model.LocationResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoAPI {
    @GET("reverse/")
    Call<LocationResponse> getLocation(
            @Query("lon") double lon,
            @Query("lat") double lat
    );
}
