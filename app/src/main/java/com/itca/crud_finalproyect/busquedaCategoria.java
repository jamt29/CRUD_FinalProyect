package com.itca.crud_finalproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class busquedaCategoria extends AppCompatActivity {

    private Spinner sp_fk_categoria;
    private Button btnBuscar;

    String idcategoria = "";
    String nombrecategoria = "";
    int conta = 0;

    ArrayList<String> lista = null;
    ArrayList<dto_categorias> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_categoria);

        btnBuscar = findViewById(R.id.btnBuscar);
        sp_fk_categoria = findViewById(R.id.sp_fk_categoria);

        sp_fk_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (conta >= 1 && sp_fk_categoria.getSelectedItemPosition() > 0) {
                    String item_spinner = sp_fk_categoria.getSelectedItem().toString();
                    String s[] = item_spinner.split("~");

                    idcategoria = s[0].trim();
                    nombrecategoria = s[1].trim();


                } else {
                    idcategoria = "";
                    nombrecategoria = "";
                }
                conta++;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fk_categorias(getApplicationContext());


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sp_fk_categoria.getSelectedItemPosition() > 0) {
                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    vista vista = new vista();
                    Bundle data = new Bundle();
                    data.putString("id_categoria", idcategoria);
                    data.putString("nombre_categoria", nombrecategoria);


                    //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    //FragmentTransaction ft = getFragmentManager().beginTransaction();
                    //  ft.replace(R.id.nav_host_fragment, vista, "details");
                    //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    //ft.addToBackStack(null);  //opcional, si quieres agregarlo a la pila
                    //ft.commit();

                    Intent i = new Intent(busquedaCategoria.this, vista.class);
                    startActivity(i);

                } else {

                    Toast toast = Toast.makeText(getApplicationContext(), "Debe seleccionar una categoria.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }


            }
        });


    }

    public void fk_categorias(final Context context) {
        listaCategorias = new ArrayList<dto_categorias>();
        lista = new ArrayList<String>();
        lista.add("Seleccione Categoria");

        String url = UrlVar.URL_consultaAllCategorias;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            int totalEncontrados = array.length();
                            //Toast.makeText(context, "Total: "+totalEncontrados, Toast.LENGTH_SHORT).show();

                            dto_categorias obj_categorias = null;
                            //dto_categorias obj_categorias = new dto_categorias();

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject categoriasObject = array.getJSONObject(i);
                                int id_categoria = categoriasObject.getInt("id_categoria");
                                String nombre_categoria = categoriasObject.getString("nom_categoria");
                                int estado_categoria = Integer.parseInt(categoriasObject.getString("estado_categoria"));
                                //Encapsulo registro por registro encontrado dentro del objeto de manera temporal
                                obj_categorias = new dto_categorias(id_categoria, nombre_categoria, estado_categoria);


                                //Agrego todos los registros en el arraylist
                                listaCategorias.add(obj_categorias);

                                lista.add(listaCategorias.get(i).getId_categoria() + " ~ " + listaCategorias.get(i).getNom_categoria());

                                //Creo un adaptador para cargar la lista preparada anteriormente.
                                //ArrayAdapter<String> adaptador = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, obtenerListaCategorias());
                                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lista);
                                //Cargo los datos en el Spinner
                                sp_fk_categoria.setAdapter(adaptador);

                                //Muestro datos en LogCat para verificar la respuesta obtenida desde el servidor.
                                Log.i("Id Categoria", String.valueOf(obj_categorias.getId_categoria()));
                                Log.i("Nombre Categoria", obj_categorias.getNom_categoria());
                                Log.i("Estado Categoria", String.valueOf(obj_categorias.getEstado_categoria()));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error. Compruebe su acceso a Internet.", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}