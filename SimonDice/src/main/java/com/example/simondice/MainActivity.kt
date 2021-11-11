package com.example.simondice

import android.graphics.Color
import android.media.AsyncPlayer
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
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
        suspend fun reproducir(sonido:Int){
            when (sonido) {
                1 -> {
                    mediaPlayer = MediaPlayer.create(this, R.raw.sonido1)
                    delay(200)
                    mediaPlayer.start()
                    delay(250)
                    mediaPlayer.stop()
                }
                2 ->{
                    mediaPlayer = MediaPlayer.create(this, R.raw.sonido2)
                    mediaPlayer.start()
                    delay(250)
                    mediaPlayer.stop()
                }
                3 ->{
                    mediaPlayer = MediaPlayer.create(this, R.raw.sonido3)
                    delay(200)
                    mediaPlayer.start()
                    delay(250)
                    mediaPlayer.stop()
                }
                else ->{
                    mediaPlayer = MediaPlayer.create(this, R.raw.sonido4)
                    delay(200)
                    mediaPlayer.start()
                    delay(250)
                    mediaPlayer.stop()
                }
            }
        }

        //muestra el array 1 en los botones

        suspend fun mostarColores(array: ArrayList<Int>) {
            for (color in array) {
                when (color) {
                    1 -> {
                        rojo.setBackgroundColor(Color.parseColor("#710000"))
                        val job = CoroutineScope(Dispatchers.Main).launch {
                            reproducir(1)
                        }
                        delay(500)
                        rojo.setBackgroundColor(Color.parseColor("#ff0000"))
                        delay(250)
                        Log.d("color", "Destello rojo")
                    }
                    2 -> {
                        azul.setBackgroundColor(Color.parseColor("#047c71"))
                        val job = CoroutineScope(Dispatchers.Main).launch {
                            reproducir(2)
                        }
                        delay(500)
                        azul.setBackgroundColor(Color.parseColor("#00ffe8"))
                        delay(250)
                        Log.d("color", "Destello azul")
                    }
                    3 -> {
                        amarillo.setBackgroundColor(Color.parseColor("#a9ac04"))
                        val job = CoroutineScope(Dispatchers.Main).launch {
                            reproducir(3)
                        }
                        delay(500)
                        amarillo.setBackgroundColor(Color.parseColor("#ffec00"))
                        delay(250)
                        Log.d("color", "Destello amarillo")
                    }
                    else -> {
                        verde.setBackgroundColor(Color.parseColor("#075e00"))
                        val job = CoroutineScope(Dispatchers.Main).launch {
                            reproducir(4)
                        }
                        delay(500)
                        verde.setBackgroundColor(Color.parseColor("#13ff00"))
                        delay(250)
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
                if (ronda%5 == 0){
                    mensageUsuario()
                }
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
            val job = CoroutineScope(Dispatchers.Main).launch {
                reproducir(1)
            }
        }
        azul.setOnClickListener() {
            resultado(2)

            val job = CoroutineScope(Dispatchers.Main).launch {
                reproducir(2)
            }
        }
        amarillo.setOnClickListener() {
            resultado(3)

            val job = CoroutineScope(Dispatchers.Main).launch {
                reproducir(3)
            }
        }
        verde.setOnClickListener() {
            resultado(4)

            val job = CoroutineScope(Dispatchers.Main).launch {
                reproducir(4)
            }
        }


    }

    //muestra el array 1 con toasts
    private fun mensageUsuario() {
            when (Random.nextInt(4) + 1) {
                1 ->
                    Toast.makeText(applicationContext, "Bien hecho", Toast.LENGTH_SHORT).show()
                2 ->
                    Toast.makeText(applicationContext, "Buena", Toast.LENGTH_SHORT).show()
                3 ->
                    Toast.makeText(applicationContext, "Estas en racha", Toast.LENGTH_SHORT).show()
                else ->
                    Toast.makeText(applicationContext, "Sigue asi", Toast.LENGTH_SHORT).show()
            }

    }
}

