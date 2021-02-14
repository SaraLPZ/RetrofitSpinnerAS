package com.example.retrofitspinneras.interfaces;

import com.example.retrofitspinneras.raices.ClaseRaizMunicipio;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicioMunicipios {
    @GET("ConsultaMunicipio")
    Call<ClaseRaizMunicipio> pedirMunicipios(@Query("Provincia") String provincia, @Query("Municipio") String municipio);
}
