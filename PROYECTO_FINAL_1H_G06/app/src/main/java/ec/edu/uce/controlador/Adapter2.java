package ec.edu.uce.controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.vista.R;

public class Adapter2 extends BaseAdapter {

    private Context context;
    private ArrayList<Vehiculo> arrayList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    public Adapter2(Context context, ArrayList<Vehiculo> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.item_vehiculo,null);
        }
        TextView placa = (TextView) view.findViewById(R.id.placaItem);
        TextView marca = (TextView) view.findViewById(R.id.marcaItem);
        TextView fecha = (TextView) view.findViewById(R.id.fechaItem);
        TextView costo = (TextView) view.findViewById(R.id.costoItem);
        TextView matriculado = (TextView) view.findViewById(R.id.matriculadoItem);
        TextView color = (TextView) view.findViewById(R.id.colorItem);
        Button editar = (Button) view.findViewById(R.id.editarVehiculoButton);
        Button eliminar = (Button) view.findViewById(R.id.eliminarVehiculoButton);


        placa.setText(arrayList.get(i).getPlaca());
        marca.setText(arrayList.get(i).getMarca());
        fecha.setText(sdf.format(arrayList.get(i).getFechaFabricacion()));  // uso de format para recuperar la fecha en formato dd/MM/aaaa
        Double costoD = arrayList.get(i).getCosto();
        costo.setText(costoD.toString());
        matriculado.setText(((arrayList.get(i).getMatriculado())?"Matriculado: SI":"Matriculado: NO")); // uso condicional ? : (condicion)?valor1:valor2;
        /*switch (arrayList.get(i).getColores().size()) {
            case 1:
                color.setText(arrayList.get(i).getColores().get(0));
                break;
        }*/
        //matriculado.setText((  arrayList.get(i).getMatriculado()?"Si":"No" )); //(condicion)?"":"";
        return view;
    }

}