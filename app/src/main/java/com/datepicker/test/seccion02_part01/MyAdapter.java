package com.datepicker.test.seccion02_part01;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

                        /*El adaptador puede ser usado tanto en un list view como en un grid view.
                        * Para probar el list o el grid, debemos modificar el activity que queremos como prinpal.
                        * (en el  manifest)*/

public  class MyAdapter extends BaseAdapter {

    private Context context;            //Donde va a ser cargado el adaptador
    private int layout;
    private List<String> names;

    //Constructor
    public MyAdapter(Context context, int layout, List<String> names){
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    /*Le va a decir al adaptador cuántas veces hay que iterar sobre una colección que le vamos a dar.
      El número que retorna es la cant de items que van a ser dibujados en ese list view*/
    public int getCount() {return this.names.size();}

    @Override
    //Para obtener un item de esa colección
    public Object getItem(int position) {
        return names.get(position);
    }

    //Para obtener el id del elemento de esa colección
    @Override
    public long getItemId(int id) {
        return id;
    }

    //Toma cada elemento, item y se dibuja lo que queremos
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        /*convertView es la vista que se va a transformar.
        * Recordar: Un List View es una lista de views y cada view es un item que se dibuja*/

        //View Holder Pattern. Este patrón nos permite aumentar la productividad
        ViewHolder holder;

        if(convertView == null){
            /*Tomamos, del contexto que nos llega por defecto, un objeto LayoutInflater y con él, inflamos(personalizamos)
              el layout y lo devolvemos en el view */
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);

            /*Tomamos la referencia del text view que tenemos en el list_item.xml
            y que es donde queremos mostrar los diferentes nombres. */
            convertView = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();

            /*Ahora  referenciamos y rellenamos  el elemento, en este caso el text view que tenemos en el list_item.xml,
            y que queremos modificar. No podemos usar el findViewById directamente, para esto: */
            holder.nameTextView = convertView.findViewById(R.id.textView);          /* 0 */
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //Nos traemos el valor actual dependiente de la posición
        String currentName = names.get(position);

        /*Otra forma de hacerlo usando el método de nuestro adaptador getItem. Como devuelve un object,
        * hay que hacerle un casting a String*/
        //currentName = (String) getItem(position);

        /*Ahora  referenciamos y rellenamos  el elemento, en este caso el text view que tenemos en el list_item.xml,
        y que queremos modificar. No podemos usar el findViewById directamente, para esto: */
        holder.nameTextView.setText(currentName);


        //Devolvemos la vista inflada y modificada con nuestros datos
        return convertView;
    }

    //Creamos esta clase aquí porque sólo la vamos a utilizar aquí
    static class ViewHolder{
        private TextView nameTextView;                      /*Fijarnos que solo tenemos un text vio. Ver 0 */


    }
}
