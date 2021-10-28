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
        val siguienteRonda: Button = findViewById(R.id.siguiente)
        val contador: TextView = findViewById(R.id.contador)
        var ronda = 1

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
                        Log.d("color","Destello rojo")
                    }
                    2 -> {
                        azul.setBackgroundColor(Color.parseColor("#047c71"))
                        delay(1000)
                        azul.setBackgroundColor(Color.parseColor("#00ffe8"))
                        delay(500)
                        Log.d("color","Destello azul")
                    }
                    3 ->{
                        amarillo.setBackgroundColor(Color.parseColor("#a9ac04"))
                        delay(1000)
                        amarillo.setBackgroundColor(Color.parseColor("#ffec00"))
                        delay(500)
                        Log.d("color","Destello amarillo")
                    }
                    else ->{
                        verde.setBackgroundColor(Color.parseColor("#075e00"))
                        delay(1000)
                        verde.setBackgroundColor(Color.parseColor("#13ff00"))
                        delay(500)
                        Log.d("color","Destello verde")
                    }
                }
            }
        }
        fun colores(array: ArrayList<Int>) {
            val job = CoroutineScope(Dispatchers.IO).launch {

                mostarColores(array)
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


        }
        //instrucciones para el boton siguiente ronda(comprueba si el usuario esta jugando y que la secuencia de colores introducida por el jugador sea la adecuada, borra el array2, y da otro numero al array1)

        siguienteRonda.setOnClickListener() {
            if (jugando) {
                if (array1 == array2) {
                    ronda++
                    array2.clear()
                    array1.add(Random.nextInt(4) + 1)
                    contador.setText(ronda.toString())
                    colores(array1)


                } else {
                    Toast.makeText(applicationContext, "fallaste", Toast.LENGTH_SHORT).show()
                    jugando = false
                }
            } else {
                Toast.makeText(applicationContext, "No le has dado a iniciar", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        //funcion para añadir los colores en el segundo array
        fun anhadir(array: ArrayList<Int>, color: Int) {
            array.add(color)
        }
        //asignamos valores a los colores -> rojo=1; azul=2; amarillo=3; verde=4
        rojo.setOnClickListener() {
            anhadir(array2, 1)
        }
        azul.setOnClickListener() {
            anhadir(array2, 2)
        }
        amarillo.setOnClickListener() {
            anhadir(array2, 3)
        }
        verde.setOnClickListener() {
            anhadir(array2, 4)
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

