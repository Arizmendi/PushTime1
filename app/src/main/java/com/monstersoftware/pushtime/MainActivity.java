package com.monstersoftware.pushtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button buttonRegister, buttonSingin;
    EditText editTextEmail, editTextPass;
    FirebaseAuth.AuthStateListener mAuthListerner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonRegister = (Button) findViewById(R.id.register);
        buttonSingin = (Button) findViewById(R.id.iniciar);
        editTextEmail = (EditText) findViewById(R.id.inemail);
        editTextPass = (EditText) findViewById(R.id.inpass);

        buttonRegister.setOnClickListener(this);
        buttonSingin.setOnClickListener(this);
        mAuthListerner = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.i("SESION","sesion iniciada con email:"+ user.getEmail());
                    goProyectos();


                }else{
                    Log.i("SESION","Sesion cerrada");

                }




            }
        };



    }

    public void goProyectos() {
        Intent intent = new Intent(this,ListadeProyectos.class);
        startActivity(intent);
    }


    public void iniciar (String email, String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iniciar:
                String emailini = editTextEmail.getText().toString();
                String passini = editTextPass.getText().toString();
                iniciar(emailini ,passini);
                break;
            case R.id.register:
                goRegistrarse();
                break;



        }
    }

    private void goRegistrarse() {
        Intent intent = new Intent(this,activityRegistro.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListerner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListerner != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListerner);
        }
    }
}
