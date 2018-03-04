package com.nelsonbenitez.calculadora;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    /*** Declaración de variables ***/


    EditText ePantalla=null, eResult=null, otroResultado=null;
    String resultado, operador;
    Double operacion;
    int cantidadOperaciones=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ePantalla=(EditText) findViewById(R.id.proceso);//se busca EditText PARA LOS NUMEROS DIGITADOS
        eResult=(EditText) findViewById(R.id.resultado); //EditText para el resultado

        ePantalla.setInputType(InputType.TYPE_NULL);
        eResult.setInputType(InputType.TYPE_NULL);
    }


    public void numPresionado(View view) {
        Button clickedButton= (Button) view;  //se valida cual fue el boton presionado
        ePantalla.append(clickedButton.getText()); // se adiciona el numero asignado al boton
    }


    // Cuando se presiona el símbolo más

    public void equalClicked(View view) {

        if (ePantalla.getText().length()==0){
            Toast.makeText(this, "No Ingresó un Valor", Toast.LENGTH_SHORT).show();
            return;
        }

            resultado=ePantalla.getText().toString();
            // se parte el string en dos, tomando como base el operador para dicha partición
            String [] operation = resultado.split(Pattern.quote(operador));
            operacion= Aritmetica(operation[0], operation[1], operador);//se llama a una función para el resultado
            eResult.setText(String.valueOf(operacion)); //resultado de la operacion

    }

    private Double Aritmetica(String s, String s1, String opt) {

        switch(opt){
            case "+": return(Double.valueOf(s) + Double.valueOf(s1));
            case "-": return(Double.valueOf(s) - Double.valueOf(s1));
            case "*": return(Double.valueOf(s) * Double.valueOf(s1));
            case "/": try{
                      return(Double.valueOf(s) / Double.valueOf(s1));
            } catch(Exception e){
                Log.d("Calc", e.getMessage());
            }
            default: return 1.0;
        }
    }


    public void operaPresionado(View view) {
        Button b = (Button) view;

        if (cantidadOperaciones==0){
            operador=b.getText().toString();
            ePantalla.append(b.getText());
            cantidadOperaciones++;

        }

        else{
            operador=b.getText().toString();
            ePantalla.setText(eResult.getText()+operador);
            cantidadOperaciones++;
        }

    }
}