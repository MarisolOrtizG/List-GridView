package com.datepicker.test.seccion02_part01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        //Datos a mostrar
        names = new ArrayList<String>();
        names.add("Marisol");
        names.add("Jorge");
        names.add("Jhesssica");
        names.add("Diego");
        names.add("Nox");
        names.add("Joel");
        names.add("Luis Ernesto");
        names.add("Alejandro");
        names.add("Gladymar");
        names.add("Neximar");
        names.add("Javier");
        names.add("Alberto");
        names.add("Irinia");
        names.add("Hecdalyz");
        names.add("Hector Luis");
        names.add("Heglimar");

        //Adaptador, la forma visual en que mostraremos nuestros datos
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        //Enlazamos nuestro adapatador con nuestro List View
        //listView.setAdapter(adapter);

        //Elemento listener que nos dice cuando se hace click sobre alguno de los elementos del List View
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Cuando se cliquea sobre uno de los view, vamos a su vista de detalles
                Toast.makeText(ListActivity.this, "Clicked: "+ names.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        //Enlazamos con nuestro adaptador personalizado
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names);
        listView.setAdapter(myAdapter);
    }
}

