package com.example.resipeapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.resipeapp.R;
import com.example.resipeapp.model.entities.Recetas;
import com.example.resipeapp.model.entities.Registro;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroActivity extends AppCompatActivity {

    //Declaramos la coleccion de firebase con el nombre que creamos en web(google FireBase)
    public static final String NOMBRE_COLECCION2 = "registro";

    //Declaramos los EditText y el Button de los xml
    private EditText etnombre, etpass, etemail;

    @Override
    public boolean onSupportNavigateUp() {
        //return super.onSupportNavigateUp();
        onBackPressed();
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.setTitle("Registrarse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        referenciarcampos();

    }

    private void referenciarcampos(){
        etnombre = findViewById(R.id.et_nombreuser);
        etemail = findViewById(R.id.et_usuario);
        etpass = findViewById(R.id.et_password);
    }

    //Creamos método publico clickguardar en donde atrapamos los datos
    public void clickguardardo(View view){
        String nombre = etnombre.getText().toString();
        String email = etemail.getText().toString();
        String password = etpass.getText().toString();

        //Hacemos validacion de campos vacios para que la persona no envie campos en blanco
        //Lo realizamos con cada campo
        if("".equals(nombre)){
            etnombre.setError(getString(R.string.error_validacion));
            return;
        }
        if("".equals(email)){
            etemail.setError(getString(R.string.error_validacion));
            return;
        }
        if("".equals(password)){
            etpass.setError(getString(R.string.error_validacion));
            return;
        }

        //constructor
        Registro registro = new Registro(nombre,email,password);

        //Creamos un intent para los datos
        //Intent datos = new Intent();
        //datos.putExtra("datos_de_registro",recetas);

        //setResult(RESULT_OK,datos);

        //Implementamos Base de datos Firebase
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(NOMBRE_COLECCION2).add(registro);

        finish();
    }
}