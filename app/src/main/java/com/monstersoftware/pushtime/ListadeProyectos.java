package com.monstersoftware.pushtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListadeProyectos extends AppCompatActivity {
    TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listade_proyectos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAgregarproyecto();
            }
        });

        database();




    }

    private void gotoAgregarproyecto() {
        Intent intent = new Intent(this,AgregarNuevoProyecto.class);
        startActivity(intent);
    }

    private void database() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            prueba = (TextView)findViewById(R.id.prueba);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference usuarioref = database.getReference("Usuarios");
            String email= user.getEmail().toString();
            prueba.setText("" + email);
            usuarioref.child(user.getUid()).setValue(email);


        } else {
                // No user is signed in
        }
        //System.out.println("Successfully fetched user data: " );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cerrar_sesion) {
            cerrarsesion();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void cerrarsesion() {
        FirebaseAuth.getInstance().signOut();
        gotoMainactivity();

    }

    private void gotoMainactivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
