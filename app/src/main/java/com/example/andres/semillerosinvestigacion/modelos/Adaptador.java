package com.example.andres.semillerosinvestigacion.modelos;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.andres.semillerosinvestigacion.R;

import java.util.List;

/**
 * Created by andres on 10/16/17.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{


        public static class ViewHolder extends RecyclerView.ViewHolder{
            private TextView facultad, lineaDeInvestigacion;
            private CardView card;

            public ViewHolder(View itemView){
                super(itemView);

                facultad=(TextView)itemView.findViewById(R.id.tVFac);
                lineaDeInvestigacion=(TextView)itemView.findViewById(R.id.tVLin);
                card=(CardView)itemView.findViewById(R.id.card);
            }
        }

        public List<Semillero> lista;

    public Adaptador(List<Semillero>lista){this.lista=lista;}

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        holder.facultad.setText(lista.get(position).getFacultad());
        holder.lineaDeInvestigacion.setText(lista.get(position).getLineaDeInvestigacion());

        YoYo.with(Techniques.ZoomIn).playOn(holder.card);
    }

    public int getItemCount(){return lista.size();}
}
