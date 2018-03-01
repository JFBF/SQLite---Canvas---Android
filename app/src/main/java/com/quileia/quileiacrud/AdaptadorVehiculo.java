package com.quileia.quileiacrud;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import Entidades.Vehiculo;

/**
 * Clase recyclerAdapter para mostrar los datos.
 * Se creó el OnClickListener manualmente.
 * @author Felipe Bautista
 * @version 22/02/2018
 * @since 1.0
 */

public class AdaptadorVehiculo extends RecyclerView.Adapter<AdaptadorVehiculo.ViewHolderDatos>
                    implements View.OnClickListener{

    private ArrayList<Vehiculo> vehiculos;
    private View.OnClickListener listener;

    public AdaptadorVehiculo(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fila_vehiculo,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.asignarDatos(vehiculos.get(position));

    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(this.listener!=null){
            listener.onClick(view);
        }
    }

    /**
     * Clase para manejar la vista del RecyclerList.
     * @author Felipe Bautista
     * @version 22/02/2018
     * @since 1.0
     */
    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView placa,marca,modelo,tipo,puertas,colores;

        public ViewHolderDatos(View view) {
            super(view);

            placa = (TextView) view.findViewById(R.id.textViewPlaca);
            marca = (TextView) view.findViewById(R.id.textViewMarca);
            modelo = (TextView) view.findViewById(R.id.textViewModelo);
            tipo = (TextView) view.findViewById(R.id.textViewTipo);
            puertas = (TextView) view.findViewById(R.id.textViewPuertas);
            colores = (TextView) view.findViewById(R.id.textViewColorColor);

        }

        /**
         * Método para asignar un vehículo en la vista creada.
         * @param v
         */
        public void asignarDatos(Vehiculo v){
            placa.setText(v.getPlaca());
            marca.setText(v.getMarca());
            modelo.setText(v.getModelo());
            tipo.setText(v.getTipo());
            puertas.setText(v.getPuertas()+"");
            if(!v.getColor().isEmpty())
                colores.setText(v.getColor());
            else
                colores.setText("No hay");
        }
    }


/*    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fila_vehiculo,parent,false);

        Vehiculo v = getItem(position);
        TextView placa = (TextView) view.findViewById(R.id.textViewPlaca);
        TextView marca = (TextView) view.findViewById(R.id.textViewMarca);
        TextView modelo = (TextView) view.findViewById(R.id.textViewModelo);
        TextView tipo = (TextView) view.findViewById(R.id.textViewTipo);
        TextView puertas = (TextView) view.findViewById(R.id.textViewPuertas);
        TextView colores = (TextView) view.findViewById(R.id.textViewColorColor);

        placa.setText(v.getPlaca());
        marca.setText(v.getMarca());
        modelo.setText(v.getModelo());
        tipo.setText(v.getTipo());
        puertas.setText(v.getPuertas()+"");
        colores.setText(v.getColor());



        return view;

    }
*/

}

