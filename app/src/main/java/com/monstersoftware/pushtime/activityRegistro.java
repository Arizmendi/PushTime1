package com.monstersoftware.pushtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class activityRegistro extends AppCompatActivity {
    EditText editTextemail1,editTextemail2,editTextpass1,editTextpass2;
    Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextemail1 =(EditText)findViewById(R.id.editText_email1);
        editTextemail2 =(EditText)findViewById(R.id.editText_email2);
        editTextpass1 =(EditText)findViewById(R.id.editTextpass1);
        editTextpass2 =(EditText)findViewById(R.id.editTextpass2);
        boton = (Button)findViewById(R.id.registrarse);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1, email2, pass1, pass2,email = null,pass = null;
                email1 = editTextemail1.getText().toString();
                email2 = editTextemail2.getText().toString();
                pass1 = editTextpass1.getText().toString();
                pass2 = editTextpass2.getText().toString();


                if (email1.contentEquals(email2)){
                    email = email1;

                }else{
                    Toast.makeText(activityRegistro.this,"Los correos no son iguales.",Toast.LENGTH_LONG).show();

                }
                if(pass1.contentEquals(pass2)){
                    pass = pass1;
                }else{
                    Toast.makeText(activityRegistro.this,"Las contraseñas no coinciden.",Toast.LENGTH_LONG).show();

                }
                registrar(email ,pass);
                goProyectos();


            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void goProyectos() {
        Intent intent = new Intent(this,ListadeProyectos.class);
        startActivity(intent);
    }

    private void registrar(String email, String pass) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass);
    }

}
