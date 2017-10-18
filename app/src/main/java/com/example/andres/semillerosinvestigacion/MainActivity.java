package com.example.andres.semillerosinvestigacion;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.andres.semillerosinvestigacion.api.DatosApi;
import com.example.andres.semillerosinvestigacion.modelos.Adaptador;
import com.example.andres.semillerosinvestigacion.modelos.Semillero;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    public final static String TAG = "DATOS COLOMBIA";
    private RecyclerView view;
    private Adaptador adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(android.R.layout.activity_list_item);


        view=(RecyclerView)findViewById(R.id.rView);

        retrofit=new Retrofit.Builder().baseUrl("https://www.datos.gov.co/resource/").addConverterFactory(GsonConverterFactory.create()).build();


        obtenerDatos();
    }



    private void obtenerDatos() {

        DatosApi service = retrofit.create(DatosApi.class);

        final Call<List<Semillero>> semilleroCall = service.obtenerListaSemilleros();

        semilleroCall.enqueue(new Callback<List<Semillero>>() {
            @Override
            public void onResponse(Call<List<Semillero>> call, Response<List<Semillero>> response) {

                if (response.isSuccessful()) {
                    List miSemillero = response.body();

                    for (int i = 0; i < miSemillero.size(); i++) {

                        Semillero s = (Semillero) miSemillero.get(i);
                        adaptador = new Adaptador(miSemillero);
                        view.setAdapter(adaptador);
                        // Log.i(TAG, "Semilleros: " + s.getSemilleroDeInvestigacion() + " Facultad: " + s.getFacultad());
                    }
                } else {
                    // Log.e(TAG, "OnResponsive " + response.errorBody());
                    Toast notif = Toast.makeText(MainActivity.this, "Error al extraer datos!", Toast.LENGTH_LONG);
                    notif.show();
                }
            }

            @Override
            public void onFailure(Call<List<Semillero>> call, Throwable t) {

                // Log.e(TAG, "OnFailure " + t.getMessage());
                Toast notif = Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG);
                notif.show();
            }
        });


    }
}
