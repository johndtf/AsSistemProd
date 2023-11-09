package com.adso.assistemprod.db;

import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

// import com.adso.assistemprod.entidades.Clientes;

import com.adso.assistemprod.entidades.Clientes;

import java.util.ArrayList;
public class DbClientes extends DbHelper {
    Context context;

    public DbClientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarCliente(String nombre, String telefono, String correo_electronico) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);

            id = db.insert(TABLE_CLIENTES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Clientes> mostrarClientes() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Clientes cliente = null;
        Cursor cursorClientes = null;


        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " ORDER BY nombre ASC", null);

        if (cursorClientes.moveToFirst()) {
            do {
                cliente = new Clientes();
                cliente.setId(cursorClientes.getInt(0));
                cliente.setNombre(cursorClientes.getString(1));
                cliente.setTelefono(cursorClientes.getString(2));
                cliente.setCorreo_electornico(cursorClientes.getString(3));
                listaClientes.add(cliente);
            } while (cursorClientes.moveToNext());
        }

        cursorClientes.close();

        return listaClientes;
    }

    public Clientes verCliente(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Clientes cliente = null;
        Cursor cursorClientes = null;


        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorClientes.moveToFirst()) {

            cliente = new Clientes();
            cliente.setId(cursorClientes.getInt(0));
            cliente.setNombre(cursorClientes.getString(1));
            cliente.setTelefono(cursorClientes.getString(2));
            cliente.setCorreo_electornico(cursorClientes.getString(3));

        }

        cursorClientes.close();

        return cliente;
    }

    public boolean editarCliente(int id, String nombre, String telefono, String correo_electronico) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

db.execSQL("UPDATE " + TABLE_CLIENTES + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', correo_electronico = '" + correo_electronico + "' WHERE id='" + id + "' ");
    correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }


    public boolean eliminarCliente(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CLIENTES + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}