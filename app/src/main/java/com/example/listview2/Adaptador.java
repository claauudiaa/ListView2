package com.example.listview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

// Esta clase se encarga de conectar los datos con la interfaz gráfica
public abstract class Adaptador extends BaseAdapter {

    // Lista de los objetos
    private ArrayList<?> entradas;
    // Diseño de cada entrada del layout entrada.xml
    private int R_layout_IdView;
    // Contexto de la actividad para acceder a recursos
    private Context contexto;

    // Constructor
    public Adaptador(Context contexto, int r_layout_IdView, ArrayList<?> entradas) {
        this.contexto = contexto;
        R_layout_IdView = r_layout_IdView;
        this.entradas = entradas;
    }

    // Getters
    public abstract void onEntrada(Object entrada, View view);

    @Override
    public int getCount() {
        return entradas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return entradas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    // Inflar la vista significa transformar el XML en un View que Android puede usar y mostrar
    public View getView(int posicion, View view, ViewGroup parent) {
        // Esto indica que si no hay ninguna vista reciclada se debe usar una nueva
        if (view == null) {
            // Aquí se hace un cast del XML a un View y se obtiene el servicio que se encarga de inflar vistas
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /*
            Infla el XML
            R_layout_IdView: Es nuestro layout (así lo definimos arriba)
            parent: Es el ListView al que pertenece esta vista
            false: Indica que no queremos agregar esta vista al contenedor ListView automáticamente
             */
            view = vi.inflate(R_layout_IdView, parent, false);
        }
        // Llama al metodo abstracto y configura los datos personalizados
        onEntrada(entradas.get(posicion), view);
        // Devuelve la vista personalizada
        return view;
    }
}
