package com.example.retrofitspinneras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.retrofitspinneras.interfaces.ServicioMunicipios;
import com.example.retrofitspinneras.interfaces.ServicioProvincias;
import com.example.retrofitspinneras.objets.Municipio;
import com.example.retrofitspinneras.objets.Provincia;
import com.example.retrofitspinneras.raices.ClaseRaizMunicipio;
import com.example.retrofitspinneras.raices.ClaseRaizProvincia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Spinner spnProvincias;
    private Spinner spnMunicipios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnProvincias=findViewById(R.id.spnProvincias);
        spnMunicipios=findViewById(R.id.spnMunicipios);

        cargarProvincias();

        spnProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cargarMunicipios(spnProvincias.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    String URL="http://ovc.catastro.meh.es/ovcservweb/ovcswlocalizacionrc/ovccallejero.asmx/";

    //Cargar Provincias
    private void cargarProvincias() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL).addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        ServicioProvincias servicio = retrofit.create(ServicioProvincias.class);
        Call<ClaseRaizProvincia> llamada = servicio.pedirProvincias();
        llamada.enqueue(new Callback<ClaseRaizProvincia>() {
            @Override
            public void onResponse(Call<ClaseRaizProvincia> call, Response<ClaseRaizProvincia> response) {
                ClaseRaizProvincia crp = response.body();
                if (crp != null){
                    List<Provincia> listaProvincias = crp.getProvinciero();
                    ArrayAdapter provinciaAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listaProvincias);
                    spnProvincias.setAdapter(provinciaAdapter);
                }
            }

            @Override
            public void onFailure(Call<ClaseRaizProvincia> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    private void cargarMunicipios(String nombreProvincia) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL).addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        ServicioMunicipios servicio = retrofit.create(ServicioMunicipios.class);
        Call<ClaseRaizMunicipio> llamada = servicio.pedirMunicipios(nombreProvincia,"");
        llamada.enqueue(new Callback<ClaseRaizMunicipio>() {
            @Override
            public void onResponse(Call<ClaseRaizMunicipio> call, Response<ClaseRaizMunicipio> response) {
                ClaseRaizMunicipio crm = response.body();
                if (crm != null){
                    List<Municipio> listaMunicipios = crm.getMunicipiero();
                    ArrayAdapter municipioAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listaMunicipios);
                    spnMunicipios.setAdapter(municipioAdapter);
                }
            }

            @Override
            public void onFailure(Call<ClaseRaizMunicipio> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}