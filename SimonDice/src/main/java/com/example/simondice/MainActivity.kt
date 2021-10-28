package com.example.simondice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlinx.coroutines.*


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
        val contador: TextView = findViewById(R.id.contador)
        var ronda = 1
        var nPusaciones: Int = 0

        var jugando = false

        //muestra el array 1 en los botones

        suspend fun mostarColores(array: ArrayList<Int>) {
            for (color in array) {
                when (color) {
                    1 -> {
                        rojo.setBackgroundColor(Color.parseColor("#710000"))
                        delay(1000)
                        rojo.setBackgroundColor(Color.parseColor("#ff0000"))
                        delay(500)
                        Log.d("color", "Destello rojo")
                    }
                    2 -> {
                        azul.setBackgroundColor(Color.parseColor("#047c71"))
                        delay(1000)
                        azul.setBackgroundColor(Color.parseColor("#00ffe8"))
                        delay(500)
                        Log.d("color", "Destello azul")
                    }
                    3 -> {
                        amarillo.setBackgroundColor(Color.parseColor("#a9ac04"))
                        delay(1000)
                        amarillo.setBackgroundColor(Color.parseColor("#ffec00"))
                        delay(500)
                        Log.d("color", "Destello amarillo")
                    }
                    else -> {
                        verde.setBackgroundColor(Color.parseColor("#075e00"))
                        delay(1000)
                        verde.setBackgroundColor(Color.parseColor("#13ff00"))
                        delay(500)
                        Log.d("color", "Destello verde")
                    }
                }
            }
        }

        fun colores(array: ArrayList<Int>) {
            val job = CoroutineScope(Dispatchers.Main).launch {
                mostarColores(array)
            }
        }

        //Comprobamos ambas secuencias
        suspend fun comprobacion() {
            if (array1 == array2) {
                ronda++
                array2.clear()
                array1.add(Random.nextInt(4) + 1)
                contador.setText(ronda.toString())
                delay(500)
                colores(array1)
                nPusaciones = 0
            } else {
                Toast.makeText(applicationContext, "fallaste", Toast.LENGTH_SHORT).show()
                jugando = false
            }
        }
        //instrucciones para el boton de inicio (cambiar "jugando" a true, borrar los arrays que pudieran estar molestando con cosas dentro, y añadir el primer color al array 1)

        inicio.setOnClickListener() {
            ronda = 1
            contador.setText(ronda.toString())
            Toast.makeText(applicationContext, "Inicio", Toast.LENGTH_SHORT).show()
            array1.clear()
            array2.clear()
            jugando = true
            array1.add(Random.nextInt(4) + 1)
            colores(array1)
            nPusaciones = 0

        }
        //funcion para añadir los colores en el segundo array
        fun anhadir(array: ArrayList<Int>, color: Int) {
            array.add(color)
        }

        fun resultado(color: Int) {
            anhadir(array2, color)
            nPusaciones++
            if (ronda == nPusaciones) {
                val job = CoroutineScope(Dispatchers.Main).launch {
                    comprobacion()
                }
                Log.d("color", "Funciona comprobacion")

            }
        }
        //asignamos valores a los colores -> rojo=1; azul=2; amarillo=3; verde=4
        rojo.setOnClickListener() {
            resultado(1)
        }
        azul.setOnClickListener() {
            resultado(2)
        }
        amarillo.setOnClickListener() {
            resultado(3)
        }
        verde.setOnClickListener() {
            resultado(4)
        }


    }

    //muestra el array 1 con toasts
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

