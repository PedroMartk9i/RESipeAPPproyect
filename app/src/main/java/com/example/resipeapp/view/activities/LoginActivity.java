package com.example.resipeapp.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.resipeapp.R;
import com.example.resipeapp.model.entities.Recetas;
import com.example.resipeapp.model.entities.Registro;
import com.example.resipeapp.model.local.BaseDatos;
import com.example.resipeapp.model.local.RecetasDAO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    //Declaramos la coleccion de firebase con el nombre que creamos en web(google FireBase)
    public static final String NOMBRE_COLECCION2 = "registro";

    private EditText etEmail, etPass;
    private SharedPreferences sharedPreferences; // SharedPreferences sirve para guardar las contraseñas y que no nos pida cada vez ingresar los datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // sharedPreferences = getSharedPreferences("preferencias", CTRL + espacio); y le seleccionamos MODE_PRIVATE
        sharedPreferences = getSharedPreferences("preferencias",MODE_PRIVATE);
        //hacemos la declaracion del guardado
        if (sharedPreferences.getBoolean("logueado",false)){ //ponemos el mismo nombre de abajo del editor del shared
            finish();//sirve para finalizar, si le damos atras a la app no se devulva al login sino que se cierre
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        referenciar();
    }

    //referenciamos los campos de texto
    private void referenciar(){
        etEmail = findViewById(R.id.tv_email);
        etPass = findViewById(R.id.tv_pass);
    }

    //creamos el método para el botón
    public void clickIngresar(View view){
        //Hacemos peticion asincronica
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(NOMBRE_COLECCION2).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //task es tarea
                //validamos si la tarea esta completada
                if (task.isSuccessful()){
                    String email = etEmail.getText().toString().trim();
                    String pass = etPass.getText().toString().trim();
                    boolean flag= false;
                    //RecetasDAO
                    //Base
                    //(email.equals()&&pass.equals())
                        //va con shared preferences este editor nos guarda cambios
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logueado",true);
                        editor.apply();//sirve para guardar cambios

                        finish();

                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);//este nos sirve para mandar a la activity que deseemos despues de poner los datos
                        startActivity(intent);
                        finish();

                    }
                    //regresamos documentos
                    for (DocumentSnapshot document : task.getResult()){
                        Registro misRegistros = document.toObject(Registro.class);
                        //etEmail.(misRegistros);
                    }

                //}else{
                    Log.e("No registrado",task.getException().getMessage());
                }
            //}
        });



    }

    //creamos el metodo para el boton
    public void clickRegistroo(View view){
        Intent inte = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(inte);//este nos sirve para mandar a la activity que deseemos despues de poner los datos
    }

}
