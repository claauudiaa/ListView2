package com.example.listview2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Creamos variables
    private ListView lista;
    private TextView texto;
    private RadioButton radioButton_pulsado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establece el layout principal
        setContentView(R.layout.activity_main);

        // Vinculamos las variables con el layout
        lista = findViewById(R.id.listView);
        texto = findViewById(R.id.texto_cambiante);

        // Crea y rellena el ArrayList usando la clase Encapsulador
        ArrayList<Encapsulador> datos = new ArrayList<Encapsulador>();
        datos.add(new Encapsulador(R.drawable.verduras, "SOPA DE VERDURAS", "10/10", false));
        datos.add(new Encapsulador(R.drawable.cocido, "SOPA DE COCIDO", "10/10", false));
        datos.add(new Encapsulador(R.drawable.pollo, "SOPA DE POLLO", "9/10", false));
        datos.add(new Encapsulador(R.drawable.pescado, "SOPA DE PESCADO", "8/10", false));
        datos.add(new Encapsulador(R.drawable.marisco, "SOPA DE MARISCO", "6/10", false));
        datos.add(new Encapsulador(R.drawable.cebolla, "SOPA DE CEBOLLA", "5/10", false));
        datos.add(new Encapsulador(R.drawable.ajo, "SOPA DE AJO", "5/10", false));

        // Configura el adaptador, sin él no se puede mostrar nada e instancia el Adaptador
        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {

            // Sobrescribe onEntrada() para llenarlo con lso datos
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    // Encuentra las vistas en entrada.xml y las vincula
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen);
                    RadioButton miRadio = (RadioButton) view.findViewById(R.id.boton);

                    // Agrega los datos usando Encapsulador
                    texto_superior_entrada.setText(((Encapsulador) entrada).get_textoTitulo());
                    texto_inferior_entrada.setText(((Encapsulador) entrada).get_textoContenido());
                    imagen_entrada.setImageResource(((Encapsulador) entrada).get_idImagen());

                    // Evento al pulsar cualquier radioButton
                    miRadio.setOnClickListener(new View.OnClickListener() {
                        // Metodo obligatorio que debe sobrescribir
                        @Override
                        public void onClick(View v) {
                            // Comprueba si otro botón se ha pulsado
                            if (radioButton_pulsado != null)
                                // Si habia uno pulsado lo desmarca
                                radioButton_pulsado.setChecked(false);
                            // Actualiza radioButton_pulsado para que apunte a la nueva seleccionada
                            radioButton_pulsado = (RadioButton) v;
                            // Cambia el texto
                            texto.setText("MARCADA UNA OPCIÓN");
                        }
                    });
                }
            }
        });
    }

    // Clase que encapsula los datos
    public static class Encapsulador {
        // Variables
        private int imagen;
        private String titulo;
        private String texto;
        private boolean dato1;

        // COnstructor
        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, boolean favorito) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.texto = textoContenido;
            this.dato1 = favorito;
        }

        // Getters
        public int get_idImagen() {
            return imagen;
        }

        public String get_textoTitulo() {
            return titulo;
        }

        public String get_textoContenido() {
            return texto;
        }

        public boolean get_checkBox1() {
            return dato1;
        }
    }
}
