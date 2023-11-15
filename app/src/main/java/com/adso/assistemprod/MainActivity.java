package com.adso.assistemprod;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.adso.assistemprod.adaptadores.ListaClientesAdapter;
import com.adso.assistemprod.db.DbClientes;
import com.adso.assistemprod.db.DbHelper;
import com.adso.assistemprod.entidades.Clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {




    RecyclerView listaClientes;
    ArrayList<Clientes> listaArrayClientes;
    FloatingActionButton fabNuevo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaClientes = findViewById(R.id.listaClientes);
        fabNuevo = findViewById(R.id.fabNuevo);
        listaClientes.setLayoutManager(new LinearLayoutManager(this));

        DbClientes dbClientes = new DbClientes(MainActivity.this);

        listaArrayClientes = new ArrayList<>();

       ListaClientesAdapter adapter = new ListaClientesAdapter(dbClientes.mostrarClientes());
        listaClientes.setAdapter(adapter);

     fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menuNuevo) {
            nuevoRegistro();
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }


}