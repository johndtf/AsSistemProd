package com.adso.assistemprod.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adso.assistemprod.R;
import com.adso.assistemprod.VerActivity;
import com.adso.assistemprod.entidades.Clientes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder> {
  ArrayList<Clientes> listaClientes;
  ArrayList<Clientes> listaOriginal;

  public ListaClientesAdapter(ArrayList<Clientes> listaClientes){
      this.listaClientes = listaClientes;
      listaOriginal = new ArrayList<>();
      listaOriginal.addAll(listaClientes);
  }


    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cliente, null,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {

      holder.viewNombre.setText(listaClientes.get(position).getNombre());
       holder.viewTelefono.setText(listaClientes.get(position).getTelefono());
        holder.viewCorreo.setText(listaClientes.get(position).getCorreo_electornico());
    }


    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaClientes.clear();
            listaClientes.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Clientes> collecion = listaClientes.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaClientes.clear();
                listaClientes.addAll(collecion);
            } else for (Clientes c : listaOriginal) {
                if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                    listaClientes.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

      TextView viewNombre, viewTelefono, viewCorreo;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
