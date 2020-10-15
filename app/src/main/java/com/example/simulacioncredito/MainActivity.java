package com.example.simulacioncredito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nombre, documento, fecha, valor;
    TextView total1, total2;
    RadioButton vivienda, libreInversion, vehiculo, educacion, uno, dos, tres;
    CheckBox cuotamanejo;
    Button enviar, limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.etnombre);
        documento = findViewById(R.id.etdocumento);
        fecha = findViewById(R.id.etfechaCredito);
        valor = findViewById(R.id.etvalorcredito);
        vivienda = findViewById(R.id.rbvivienda);
        vehiculo = findViewById(R.id.rbvehiculo);
        libreInversion = findViewById(R.id.rblibreinversion);
        educacion = findViewById(R.id.rbeducacion);
        uno = findViewById(R.id.rbuno);
        dos = findViewById(R.id.rbdos);
        tres = findViewById(R.id.rbtres);
        cuotamanejo = findViewById(R.id.cbcuota);
        total1 = findViewById(R.id.tvdeudatotal);
        total2 = findViewById(R.id.tvmensual);
        enviar = findViewById(R.id.btnenviar);
        limpiar = findViewById(R.id.btnlimpiar);


        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documento.setText("");
                nombre.setText("");
                fecha.setText("");
                valor.setText("");
                total1.setText("");
                total2.setText("");
                vivienda.setChecked(false);
                vehiculo.setChecked(false);
                libreInversion.setChecked(false);
                educacion.setChecked(false);
                uno.setChecked(false);
                dos.setChecked(false);
                tres.setChecked(false);
                cuotamanejo.setChecked(false);
            }
        });
        enviar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        if (!documento.getText().toString().isEmpty()&&!nombre.getText().toString().isEmpty()&&!fecha.getText().toString().isEmpty()&&!valor.getText().toString().isEmpty())
        {
            if (vivienda.isChecked()||educacion.isChecked()||libreInversion.isChecked()||vehiculo.isChecked())
            {

                double mvalor = Double.parseDouble(valor.getText().toString());
                double calculo = 0, totcredito = 0, totcta = 0, mctaman=10000;

                DecimalFormat nro = new DecimalFormat("###,###,###.##");
                if (vivienda.isChecked())
                {
                    calculo = mvalor * 0.01;
                }
                else if (educacion.isChecked())
                {
                    calculo = mvalor * 0.012;
                }
                else if (libreInversion.isChecked())
                {
                    calculo = mvalor * 0.02;
                }
                else
                {
                    calculo = mvalor * 0.015;
                }
                if (uno.isChecked()||dos.isChecked()||tres.isChecked())
                {
                    if (uno.isChecked())
                    {
                        if (cuotamanejo.isChecked())
                        {
                            totcredito = mvalor + (calculo*12);
                            totcta = totcredito / 12 + mctaman;
                        }
                        else
                        {
                            totcredito = mvalor + (calculo*12);
                            totcta = totcredito / 12;
                        }
                    }
                    else if (dos.isChecked())
                    {
                        if (cuotamanejo.isChecked())
                        {
                            totcredito = mvalor + (calculo*24);
                            totcta = totcredito / 24 + mctaman;
                        }
                        else
                        {
                            totcredito = mvalor + (calculo*24);
                            totcta = totcredito / 24;
                        }
                    }
                    else
                    {
                        if (cuotamanejo.isChecked())
                        {
                            totcredito = mvalor + (calculo*36) ;
                            totcta = totcredito / 36 + mctaman;
                        }
                        else
                        {
                            totcredito = mvalor + (calculo*36);
                            totcta = totcredito / 36;
                        }
                    }


                    total1.setText(nro.format(totcredito));
                    total2.setText(nro.format(totcta));
                }
                else
                    {
                    Toast.makeText(getApplicationContext(), "seleccione el numero de cuotas", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    {
                Toast.makeText(getApplicationContext(), "seleccione el tipo de prestamo", Toast.LENGTH_SHORT).show();
                    }
                }
                 else
                    {
                 Toast.makeText(getApplicationContext(), "complete todos los campos", Toast.LENGTH_SHORT).show();
                    }
    }
}