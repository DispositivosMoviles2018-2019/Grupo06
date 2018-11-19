package ec.edu.uce.controlador;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.vista.ListaVehiculos;
import ec.edu.uce.vista.Login;
import ec.edu.uce.vista.R;
import ec.edu.uce.vista.RegistrarVehiculo;

public class AdapterVehiculos extends RecyclerView.Adapter<AdapterVehiculos.ViewHolderVehiculos> {

    private List<Vehiculo> vehiculos;

    public AdapterVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @NonNull
    @Override
    public ViewHolderVehiculos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vehiculo, viewGroup, false);
        ViewHolderVehiculos vhv = new ViewHolderVehiculos(view);
        return vhv;
    }

    //Aqui seteamos el item con los respectivos valores
    @Override
    public void onBindViewHolder(@NonNull ViewHolderVehiculos viewHolderVehiculos, int i) {
        viewHolderVehiculos.placa.setText("Placa:                " + vehiculos.get(i).getPlaca());
        viewHolderVehiculos.marca.setText("Marca:                " + vehiculos.get(i).getMarca());
        String fecha = new SimpleDateFormat("dd-MM-yyyy").format(vehiculos.get(i).getFechaFabricacion());
        viewHolderVehiculos.fecha.setText("Fecha de fabricacion: " + fecha);
        viewHolderVehiculos.costo.setText("Costo:                " + vehiculos.get(i).getCosto());

        if (vehiculos.get(i).getMatriculado() == true){
            viewHolderVehiculos.matriculado.setText("Matriculado:          " + "SI");
        }else{
            viewHolderVehiculos.matriculado.setText("Matriculado:          " + "NO");
        }
        viewHolderVehiculos.color.setText("Color:                " + vehiculos.get(i).getColor());
        viewHolderVehiculos.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

    //Verificar si es necesario
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolderVehiculos extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

        TextView placa;
        TextView marca;
        TextView fecha;
        TextView costo;
        TextView matriculado;
        TextView color;
        Button editar;
        Button eliminar;

        Context context;

        public ViewHolderVehiculos(@NonNull View itemView) {

            super(itemView);
            context = itemView.getContext();
            placa = (TextView) itemView.findViewById(R.id.placaItem);
            marca = (TextView) itemView.findViewById(R.id.marcaItem);
            fecha = (TextView) itemView.findViewById(R.id.fechaItem);
            costo = (TextView) itemView.findViewById(R.id.costoItem);
            matriculado = (TextView) itemView.findViewById(R.id.matriculadoItem);
            color = (TextView) itemView.findViewById(R.id.colorItem);
            editar = (Button) itemView.findViewById(R.id.editarVehiculoButton);
            eliminar = (Button) itemView.findViewById(R.id.eliminarVehiculoButton);
            itemView.setOnCreateContextMenuListener(this);


        }

        void setOnClickListener(){
            editar.setOnClickListener(this);
            eliminar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.editarVehiculoButton:
                    RegistrarVehiculo.editar = true;
                    Intent editarVehiculo = new Intent(context, RegistrarVehiculo.class);
                    editarVehiculo.putExtra("valorPlaca", placa.getText().toString().substring(7,15));
                    context.startActivity(editarVehiculo);
                    ((ListaVehiculos) context).finish();
                    break;
                case R.id.eliminarVehiculoButton:
                    List<Vehiculo> listaVehiculo = Login.vehiculos;
                    for (Vehiculo vehiculo : listaVehiculo){
                        if(vehiculo.getPlaca().equals(placa.getText().toString().substring(7,15))){
                            listaVehiculo.remove(vehiculo);
                            //Toast.makeText(Login.this,"Credenciales erroneas", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Login.setVehiculos(listaVehiculo);
                    Intent eliminarVehiculo = new Intent(context, ListaVehiculos.class);
                    context.startActivity(eliminarVehiculo);
                    ((ListaVehiculos) context).finish();
                    break;
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
