package com.example.andres.semillerosinvestigacion.api;

import com.example.andres.semillerosinvestigacion.modelos.Semillero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by andres on 10/13/17.
 */

public interface DatosApi {

    @GET("xmcd-xh4d.json")
    Call<List<Semillero>> obtenerListaSemilleros();
}
