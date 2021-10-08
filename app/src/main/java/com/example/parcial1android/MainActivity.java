package com.example.parcial1android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private View verdeBtn;
    private View amarilloBtn;
    private View rojoBtn;
    private EditText posXt;
    private EditText posYt;
    private EditText recordatorio;
    private Button vistaBtn;
    private Button confirmarBtn;
    private String posX;
    private String posY;
    private String record;
    private String importancia;
    private String mensajeRecordatorio;
    private String importanciaText;

    //comunicar con TCP
    private TCP com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        com = new TCP(this);
        com.conection();

        verdeBtn = findViewById(R.id.verdeBtn);
        amarilloBtn = findViewById(R.id.amarilloBtn);
        rojoBtn = findViewById(R.id.rojoBtn);
        posXt = findViewById(R.id.posXt);
        posYt = findViewById(R.id.posYt);
        recordatorio = findViewById(R.id.recordatorio);
        vistaBtn = findViewById(R.id.vistaBtn);
        confirmarBtn = findViewById(R.id.confirmarBtn);


        verdeBtn.setOnClickListener(
                (v)-> {
                    importanciaText = "1";
                }
        );

        rojoBtn.setOnClickListener(
                (v)-> {
                    importanciaText = "3";
                }
        );

        amarilloBtn.setOnClickListener(
                (v)-> {
                    importanciaText = "2";
                }

        );

        setColor();


        confirmarBtn.setOnClickListener(
                (v) -> {
                    importancia = importanciaText;
                    posX = posXt.getText().toString();
                    posY = posYt.getText().toString();
                    record = recordatorio.getText().toString();

                    mensajeRecordatorio = posX +"," + posY +"," + record +"," + importancia;

                    if(!posX.equals("") && !posY.equals("") && !record.equals("") && !importancia.equals("")) {
                        com.sendMessage(mensajeRecordatorio);

                    } else {
                        runOnUiThread(
                                () -> {
                                    Toast.makeText(this, "No deje ningún campo vacío",
                                            Toast.LENGTH_SHORT).show();
                                }
                        );
                    }
                }
        );

    }

    public void setColor() {

        new Thread(() -> {

            while (true) {

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(
                        () -> {
                            if (importanciaText == "1") {
                                verdeBtn.setBackgroundResource(R.drawable.seleccion1);
                            } else if (importanciaText != "1") {
                                verdeBtn.setBackgroundResource(R.drawable.verde_btn);
                            }
                            if (importanciaText == "2") {
                                amarilloBtn.setBackgroundResource(R.drawable.seleccion2);
                            } else if (importanciaText != "2") {
                                amarilloBtn.setBackgroundResource(R.drawable.amarillo_btn);
                            }
                            if (importanciaText == "3") {
                                rojoBtn.setBackgroundResource(R.drawable.seleccion3);
                            } else if (importanciaText != "3") {
                                rojoBtn.setBackgroundResource(R.drawable.rojo_btn);
                            }
                        });
            }
        }).start();

    }
}









