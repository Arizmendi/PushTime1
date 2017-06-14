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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarNuevoProyecto extends AppCompatActivity implements View.OnClickListener {
    EditText editFechaini, editFechafin, editNombre;
    CheckBox checkFechafin;
    Button btninicio,btnfinal;
    private  int diaini, mesini ,anoini,diafin ,mesfin,anofin ;

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
        btninicio = (Button)findViewById(R.id.botonini);
        btnfinal = (Button)findViewById(R.id.botonfin);

        btninicio.setOnClickListener(this);
        btnfinal.setOnClickListener(this);
        checkFechafin.setOnClickListener(this);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v == btninicio){
            final Calendar c = Calendar.getInstance();
            diaini = c.get(Calendar.DAY_OF_MONTH);
            mesini = c.get(Calendar.MONTH);
            anoini = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    editFechaini.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                }
            }
            ,diaini,mesini,anoini);
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
        if(v == btnfinal){
            if (!checkFechafin.isChecked()){
                final Calendar c = Calendar.getInstance();
                diafin = c.get(Calendar.DAY_OF_MONTH);
                mesfin = c.get(Calendar.MONTH);
                anofin = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editFechafin.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                    }
                }
                        ,diafin,mesfin,anofin);
                datePickerDialog.show();

            }else {
                Toast.makeText(AgregarNuevoProyecto.this,"Fecha de termino esta descartada",Toast.LENGTH_LONG).show();

            }

        }



    }
}
