package com.example.retrofitspinneras.interfaces;

import com.example.retrofitspinneras.raices.ClaseRaizProvincia;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicioProvincias {
    @GET("ConsultaProvincia")
    Call<ClaseRaizProvincia> pedirProvincias();
}
