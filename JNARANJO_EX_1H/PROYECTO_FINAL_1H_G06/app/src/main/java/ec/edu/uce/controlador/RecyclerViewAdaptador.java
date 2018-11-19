package ec.edu.uce.controlador;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
        private TextView placa;
        private TextView marca;
        private TextView fecha;
        private TextView costo;
        private TextView matriculado;
        private TextView color;
        private Button modificar;
        private Button eliminar;

        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            placa = (TextView) itemView.findViewById(R.id.placaVehiculoText);
            marca = (TextView) itemView.findViewById(R.id.claveRegUsuarioText);
            fecha = (TextView) itemView.findViewById(R.id.fechaFabVehiculoLabel);
            costo = (TextView) itemView.findViewById(R.id.costoVehiculoText);
            matriculado = (TextView) itemView.findViewById(R.id.matriculadoSwitch);
            color = (TextView) itemView.findViewById(R.id.colorVehiculoTxt);
            modificar = (Button) itemView.findViewById(R.id.btn_modificar);
            eliminar = (Button) itemView.findViewById(R.id.btn_eliminar);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Elija la Accion");
            MenuItem editar = menu.add(Menu.NONE, 1, 1, "Modificar");
            MenuItem eliminar = menu.add(Menu.NONE, 2, 2, "Eliminar");
            editar.setOnMenuItemClickListener(onEditMenu);
            eliminar.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent siguiente;
                switch (item.getItemId()) {
                    case 1:
                        RegistrarVehiculo.modificacion = true;
                        siguiente = new Intent(context, RegistrarVehiculo.class);
                        siguiente.putExtra("valorPlaca", placa.getText().toString().substring(7, 15));
                        context.startActivity(siguiente);
                        ((ListaVehiculos) context).finish();
                        break;

                    case 2:
                        List<Vehiculo> listaVehiculo = Login.vehiculos;
                        for (Vehiculo vehiculo : listaVehiculo) {
                            if (vehiculo.getPlaca().equalsIgnoreCase(placa.getText().toString().substring(7, 15))) {
                                listaVehiculo.remove(vehiculo);
                                System.out.println("removido...");
                                break;
                            }
                        }
                        Login.setVehiculos(listaVehiculo);
                        siguiente = new Intent(context, ListaVehiculos.class);
                        context.startActivity(siguiente);
                        ((ListaVehiculos) context).finish();

                        break;
                }
                return true;
            }
        };


        void setOnClickListener() {
            modificar.setOnClickListener(this);
            eliminar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent siguiente;
            switch (v.getId()) {
                case R.id.btn_modificar:
                    RegistrarVehiculo.modificacion = true;
                    siguiente = new Intent(context, RegistrarVehiculo.class);
                    siguiente.putExtra("valorPlaca", placa.getText().toString().substring(7, 15));
                    context.startActivity(siguiente);
                    ((ListaVehiculos) context).finish();
                    break;
                case R.id.btn_eliminar:
                    List<Vehiculo> listaVehiculo = Login.vehiculos;
                    for (Vehiculo vehiculo : listaVehiculo) {
                        if (vehiculo.getPlaca().equalsIgnoreCase(placa.getText().toString().substring(7, 15))) {
                            listaVehiculo.remove(vehiculo);
                            System.out.println("removido...");
                        }
                    }
                    Login.setVehiculos(listaVehiculo);
                    siguiente = new Intent(context, ListaVehiculos.class);
                    context.startActivity(siguiente);
                    ((ListaVehiculos) context).finish();
                    break;
            }
        }


    }

    public static List<Vehiculo> vehiculos;

    public RecyclerViewAdaptador(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_lista_vehiculos, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.placa.setText("Placa: " + vehiculos.get(i).getPlaca());
        viewHolder.marca.setText("Marca: " + vehiculos.get(i).getMarca());
        viewHolder.fecha.setText("Fecha fabricacion: " + new SimpleDateFormat("dd-MM-yyyy").format(vehiculos.get(i).getFechaFabricacion()));
        viewHolder.costo.setText("Costo: " + vehiculos.get(i).getCosto());
        if (vehiculos.get(i).getMatriculado() == true) {
            viewHolder.matriculado.setText("Matriculado: si");
        } else {
            viewHolder.matriculado.setText("Matriculado: no");
        }
        viewHolder.color.setText("Color: " + vehiculos.get(i).getColor());
        viewHolder.setOnClickListener();

    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

}
