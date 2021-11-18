package com.itca.crud_finalproyect;



import static java.security.AccessController.getContext;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class productos extends Activity {
    private ViewGroup viewGroup;

    private EditText et_id, et_nombre_prod, et_descripcion, et_stock, et_precio, et_unidadmedida;
    private Spinner sp_estadoProductos, sp_fk_categoria;
    private TextView tv_fechahora;
    private Button btnSave, btnNew;

    ProgressDialog progressDialog;
    ArrayList<String> lista = null;
    ArrayList<dto_categorias> listaCategorias;              

    //Arreglos para efectuar pruebas de carga de opciones en spinner.
    String elementos[] = {"Uno", "Dos", "Tres", "Cuatro", "Cinco"};
    final String[] elementos1 =new String[]{
            "Seleccione",
            "1",
            "2",
            "3",
            "4",
            "5"
    };

    String idcategoria = "";
    String nombrecategoria = "";
    int conta = 0;

    String datoStatusProduct = "";

    //Instancia DTO
    dto_productos dto = new dto_productos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        viewGroup = (ViewGroup) findViewById(R.id.content);

        et_id = findViewById(R.id.et_id);
        et_nombre_prod = findViewById(R.id.et_nombre_prod);
        et_descripcion = findViewById(R.id.et_descripcion);
        et_stock = findViewById(R.id.et_stock);
        et_precio = findViewById(R.id.et_precio);
        et_unidadmedida = findViewById(R.id.et_unidadmedida);
        sp_estadoProductos = findViewById(R.id.sp_estadoProductos);
        sp_fk_categoria = findViewById(R.id.sp_fk_categoria);
        tv_fechahora = findViewById(R.id.tv_fechahora);
        tv_fechahora.setText(timedate());
        btnSave = findViewById(R.id.btnSave);
        btnNew = findViewById(R.id.btnNew);

        sp_estadoProductos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_estadoProductos.getSelectedItemPosition()>0) {
                    datoStatusProduct = sp_estadoProductos.getSelectedItem().toString();
                }else{
                    datoStatusProduct = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fk_categorias(getApplicationContext());


        sp_fk_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(conta>=1 && sp_fk_categoria.getSelectedItemPosition()>0){
                    String item_spinner= sp_fk_categoria.getSelectedItem().toString();
                  
                    String s[] = item_spinner.split("~");
                    //Dato ID CATEGORIA
                    idcategoria = s[0].trim();        
                    //Dato NOMBRE DE LA CATEGORIA
                    nombrecategoria = s[1];

                    Toast toast = Toast.makeText(getApplicationContext(), "Id cat: " + idcategoria + "\n" + "Nombre Categoria: "+nombrecategoria, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }else{
                    idcategoria = "";
                    nombrecategoria = "";
                }
                conta++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = et_id.getText().toString();
                String nombre = et_nombre_prod.getText().toString();
                String descripcion = et_descripcion.getText().toString();
                String stock = et_stock.getText().toString();
                String precio = et_precio.getText().toString();
                String unidad = et_unidadmedida.getText().toString();

                if(id.length() == 0){
                    et_id.setError("Campo obligatorio.");
                }else if(nombre.length() == 0){
                    et_nombre_prod.setError("Campo obligatorio.");
                }else if(descripcion.length() == 0){
                    et_descripcion.setError("Campo obligatorio.");
                }else if(stock.length() == 0){
                    et_stock.setError("Campo obligatorio.");
                }else if(precio.length() == 0){
                    et_precio.setError("Campo obligatorio.");
                }else if(unidad.length() == 0){
                    et_unidadmedida.setError("Campo obligatorio.");
                }else if(sp_estadoProductos.getSelectedItemPosition() == 0){
                    Toast.makeText(getApplicationContext(), "Debe seleccionar el estado del producto.", Toast.LENGTH_SHORT).show();
                }else if(sp_fk_categoria.getSelectedItemPosition() > 0){
                    //Toast.makeText(getContext(), "Good...", Toast.LENGTH_SHORT).show();
                    save_productos(getApplicationContext(), id, nombre, descripcion, stock, precio, unidad, datoStatusProduct, idcategoria);
                }else{
                    Toast.makeText(getApplicationContext(), "Debe seleccionar la categoria.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_product();
            }
        });

    }
    private String timedate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String fecha = sdf.format(cal.getTime());
        return fecha;
    }


    //public ArrayList<dto_categorias> fk_categorias(final Context context){
    public void fk_categorias(final Context context){
        listaCategorias = new ArrayList<dto_categorias>();
        lista = new ArrayList<String>();
        lista.add("Seleccione Categoria");

        String url  = UrlVar.URL_consultaAllCategorias;
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

                             
                                lista.add(listaCategorias.get(i).getId_categoria()+" ~ "+listaCategorias.get(i).getNom_categoria());

                                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, lista);

                                sp_fk_categoria.setAdapter(adaptador);


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



    private void save_productos(final Context context, final String id_producto, final String nom_producto, final String des_producto,
                                final String stock, final String precio, final String unidad_medida, final String estado_producto,
                                final String categoria){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlVar.URL_registrar_productos, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject requestJSON = null;
                try {
                    requestJSON = new JSONObject(response.toString());
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");

                    if(estado.equals("1")){
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context, "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                    }else if(estado.equals("2")){
                        Toast.makeText(getApplicationContext(), ""+mensaje, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se puedo guardar. \n" +
                        "Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id_producto", id_producto);
                map.put("nom_producto", nom_producto);
                map.put("des_producto", des_producto);
                map.put("stock", stock);
                map.put("precio", precio);
                map.put("unidad_medida", unidad_medida);
                map.put("estado_producto", estado_producto);
                map.put("categoria", categoria);
                return map;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    private void new_product() {
        et_id.setText(null);
        et_nombre_prod.setText(null);
        et_descripcion.setText(null);
        et_stock.setText(null);
        et_precio.setText(null);
        et_unidadmedida.setText(null);
        sp_estadoProductos.setSelection(0);
        sp_fk_categoria.setSelection(0);
    }


    //No utilizo este método en nada por el momento
    public ArrayList<String> obtenerListaCategorias() {
        //ArrayList<String> lista = new ArrayList<String>();
        lista = new ArrayList<String>();
        lista.add("Seleccione Categoria");
        for(int i=0;i<=listaCategorias.size();i++){
            lista.add(listaCategorias.get(i).getId_categoria()+" ~ "+listaCategorias.get(i).getNom_categoria());
        }
        return  lista;
    }

}
