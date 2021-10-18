package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declaracion de botones, arrays y el boolean de jugando

//array1=generado, array2=dado por el jugador

        var array1= ArrayList<Int>()
        var array2=ArrayList<Int>()

        val rojo: Button=findViewById(R.id.rojo)
        val azul: Button=findViewById(R.id.azul)
        val amarillo: Button=findViewById(R.id.amarillo)
        val verde: Button=findViewById(R.id.verde)
        val inicio: Button = findViewById(R.id.jugar)
        val siguienteRonda: Button=findViewById(R.id.siguiente)

        var jugando=false


    }
}
