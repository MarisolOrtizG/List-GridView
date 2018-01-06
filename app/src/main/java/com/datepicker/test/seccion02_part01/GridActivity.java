package com.datepicker.test.seccion02_part01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private List<String> names;
    private GridView gridView;
    private MyAdapter myAdapter;

    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gridView =  findViewById(R.id.gridView);

        //Datos a mostrar
        names = new ArrayList<String>();
        names.add("Marisol");
        names.add("Jorge");
        names.add("Jhesssica");
        names.add("Diego");
        names.add("Nox");
        names.add("Joel");
        names.add("Irinia");
        names.add("Hecdalys");
        names.add("Hector Luis");
        names.add("Heglimar");


        //Elemento listener que nos dice cuando se hace click sobre alguno de los elementos del List View
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Cuando se cliquea sobre uno de los view, vamos a su vista de detalles
                Toast.makeText(GridActivity.this, "Clicked: "+ names.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        //Enlazamos con nuestro adaptador personalizado
        myAdapter = new MyAdapter(this, R.layout.grid_item, names);
        gridView.setAdapter(myAdapter);

        /*Cuando se implementa por primera vez un context menu, este no funciona, no sale el
         Pop-Up Message o ventana emergente. Para solucionarlo: */
        registerForContextMenu(gridView);


    }

    /*** Para CREAR EL MENÚ ***/

    //Inflamos el layout del menú de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Vamos a inflar el toolbar, action bar, que tenemos
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    //Para manejar los eventos cuando se les hagan click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:

                /*Informamos al adaptador, no al list view ni al grid view, que hemos incrementado en uno el valor del array,
                , es decir, añadimos nuevo nombre, por tanto, ha cambiado y se tiene que refrescar.
                Recordar que en el adaptador es donde está el método get view, que es a donde llegan las posiciones,
                y en el que se renderizan las distintas vistas para cada uno de nuestros datos.
                */

                this.names.add("Added n°"+(++counter));

                //Habiendo sumado el nuevo valor notificamos al adaptador el cambio producido y lo refrescamos
                this.myAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*** Para el CONTEXT MENU ***/

    // Inflamos el layout del contex menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        //De esta forma conseguimos inyectarle el item que ha sido clickeado al menú de diálogo, a ese context menu
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.names.get(info.position));

        inflater.inflate(R.menu.context_menu, menu);
    }


    // Manejamos eventos click en el context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //Traemos la información de donde se ha hecho click
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){

            case R.id.delete_item:
                //Eliminamos el item clickeado.iinfo.position nos da la dirección del item que ha sido clickeado
                this.names.remove(info.position);

                //Habiendo sumado el nuevo valor notificamos al adaptador el cambio producido y lo refrescamos.
                this.myAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onContextItemSelected(item);

        }


    }
}
