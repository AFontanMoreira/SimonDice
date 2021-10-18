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

        var array1 = ArrayList<Int>()
        var array2 = ArrayList<Int>()

        val rojo: Button = findViewById(R.id.rojo)
        val azul: Button = findViewById(R.id.azul)
        val amarillo: Button = findViewById(R.id.amarillo)
        val verde: Button = findViewById(R.id.verde)
        val inicio: Button = findViewById(R.id.jugar)
        val siguienteRonda: Button = findViewById(R.id.siguiente)

        var jugando = false

        //instrucciones para el boton de inicio (cambiar "jugando" a true, borrar los arrays que pudieran estar molestando con cosas dentro, y añadir el primer color al array 1)

        inicio.setOnClickListener() {
            Toast.makeText(applicationContext, "Inicio", Toast.LENGTH_SHORT).show()
            array1.clear()
            array2.clear()
            jugando = true
            array1.add(Random.nextInt(4) + 1)
            mensageUsuario(array1)
        }

        //muestra el array 1 con toasts


    }

    private fun mensageUsuario(array: ArrayList<Int>) {

        for (color in array) {
            when (color) {
                1 ->
                    Toast.makeText(applicationContext, "Rojo", Toast.LENGTH_SHORT).show()
                2 ->
                    Toast.makeText(applicationContext, "Azul", Toast.LENGTH_SHORT).show()
                3 ->
                    Toast.makeText(applicationContext, "Amarillo", Toast.LENGTH_SHORT).show()
                else ->
                    Toast.makeText(applicationContext, "Verde", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

