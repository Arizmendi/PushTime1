package com.monstersoftware.pushtime;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import OBJETOS.FirebaseReferences;
import OBJETOS.Proyecto;

public class AgregarNuevoProyecto extends AppCompatActivity implements View.OnClickListener {
    EditText editFechaini, editFechafin, editNombre;
    CheckBox checkFechafin;
    EditText editText;
    private  FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private  int diaini, mesini ,anoini,diafin ,mesfin,anofin ;
    Proyecto proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_nuevo_proyecto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        editFechaini = (EditText)findViewById(R.id.eFechaini);
        editFechafin = (EditText)findViewById(R.id.eFechafin);
        editNombre = (EditText)findViewById(R.id.NombreProyecto);
        checkFechafin = (CheckBox) findViewById(R.id.checkBoxFechafin);


        editFechaini.setOnClickListener(this);
        editFechafin.setOnClickListener(this);
        checkFechafin.setOnClickListener(this);








        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarproyecto();



            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }





    private boolean verificarCampos(){
        if(editFechaini.getText().toString().equals(""))
            return  false;
        if(editFechafin.getText().toString().equals(""))
            return  false;
        if(editNombre.getText().toString().equals(""))
            return  false;

        return true;
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v == editFechaini){
            final Calendar c = Calendar.getInstance();

            diaini = c.get(Calendar.DAY_OF_MONTH);
            mesini = c.get(Calendar.MONTH);
            anoini = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    editFechaini.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    diaini = dayOfMonth;
                    mesini = month;
                    anoini = year;

                }
            }
            ,anoini,mesini,diaini);
            datePickerDialog.show();

        }
        if (v == checkFechafin ){
            if (checkFechafin.isChecked()){

                Toast.makeText(AgregarNuevoProyecto.this,"Fecha de termino descartada",Toast.LENGTH_LONG).show();

            }
            if (!checkFechafin.isChecked()){
                Toast.makeText(AgregarNuevoProyecto.this,"Fecha de termino activada",Toast.LENGTH_LONG).show();

            }

        }
        if(v == editFechafin){
            if (!checkFechafin.isChecked()){
                final Calendar c = Calendar.getInstance();
                diafin = c.get(Calendar.DAY_OF_MONTH);
                mesfin = c.get(Calendar.MONTH);
                anofin = c.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editFechafin.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        diafin = dayOfMonth;
                        mesfin = month;
                        anofin = year;

                    }
                }
                ,anofin,mesfin,diafin);
                datePickerDialog.show();

            }else {
                Toast.makeText(AgregarNuevoProyecto.this,"Fecha de termino esta descartada",Toast.LENGTH_LONG).show();

            }

        }




    }
    private void registrarproyecto() {
        if(verificarCampos()) {
            Toast.makeText(this,"Datos correctos",Toast.LENGTH_SHORT).show();
            firebaseAuth = FirebaseAuth.getInstance();
            databaseReference =FirebaseDatabase.getInstance().getReference(FirebaseReferences.USER_REFERENCE);
            String name = editNombre.getText().toString();
            String fechainicio = editFechaini.getText().toString() ;
            String fechafin = editFechafin.getText().toString() ;
            Proyecto proyecto = new Proyecto(name, fechainicio,fechafin);
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).child("Proyectos").child("Final:").setValue(proyecto.getFechafin());
            databaseReference.child(user.getUid()).child("Proyectos").child("Inicio:").setValue(proyecto.getFechafin());
            databaseReference.child(user.getUid()).child("Proyectos").child("Nombre:").setValue(proyecto.getNombre());



        }else{
            Toast.makeText(this,"por favor llena todos los datos",Toast.LENGTH_SHORT).show();
        }

    }




}
