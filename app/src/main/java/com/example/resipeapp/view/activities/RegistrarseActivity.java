package com.example.resipeapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.resipeapp.R;
import com.example.resipeapp.model.entities.Recetas;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrarseActivity extends AppCompatActivity {

    //Declaramos la coleccion de firebase con el nombre que creamos en web(google FireBase)
    public static final String NOMBRE_COLECCION2 = "registro";

    //Declaramos los EditText y el Button de los xml
    private EditText etnombre, etpass, etusuario;
    private Button btnregistrar;

    //Accion de hacer click y retroceder con Override Methods:onSupportNavigateUp():boolean


    @Override
    public boolean onSupportNavigateUp() {
        //Comentamos lo que trae por defecto
        //return super.onSupportNavigateUp();
        //Creamos la flecha de regresar por medio de onBackPressed
       // onBackPressed();
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        //Cambiamos Titulo de la Activity
       /* this.setTitle("Registrarse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //inicializamos el metodo privado que llamamos referenciardatos
        referenciarcampos();*/
    }

    private void referenciarcampos(){
        etnombre = findViewById(R.id.et_nombreuser);
        etpass = findViewById(R.id.et_password);
        etusuario = findViewById(R.id.et_usuario);
        btnregistrar = findViewById(R.id.btn_registrar);
    }

    //Creamos método publico clickguardar en donde atrapamos los datos
    /*public void clickguardado(View view){
        String nombre = etnombre.getText().toString();
        String password = etpass.getText().toString();
        String usuario = etusuario.getText().toString();

        //Hacemos validacion de campos vacios para que la persona no envie campos en blanco
        //Lo realizamos con cada campo
        if("".equals(nombre)){
            etnombre.setError(getString(R.string.error_validacion));
            return;
        }
        if("".equals(password)){
            etpass.setError(getString(R.string.error_validacion));
            return;
        }
        if("".equals(usuario)){
            etusuario.setError(getString(R.string.error_validacion));
            return;
        }

        //constructor
        Recetas recetas = new Recetas(nombre,password,usuario);

        //Creamos un intent para los datos
        //Intent datos = new Intent();
        //datos.putExtra("datos_de_registro",recetas);

        //setResult(RESULT_OK,datos);

        //Implementamos Base de datos Firebase
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(NOMBRE_COLECCION2).add(recetas);

        finish();
    }*/
}