package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
        val contador : TextView = findViewById(R.id.contador)
        var ronda = 1

        var jugando = false




        //instrucciones para el boton de inicio (cambiar "jugando" a true, borrar los arrays que pudieran estar molestando con cosas dentro, y añadir el primer color al array 1)

        inicio.setOnClickListener() {
            contador.setText(ronda.toString())
            Toast.makeText(applicationContext, "Inicio", Toast.LENGTH_SHORT).show()
            array1.clear()
            array2.clear()
            jugando = true
            array1.add(Random.nextInt(4) + 1)
            mensageUsuario(array1)
        }
        //instrucciones para el boton siguiente ronda(comprueba si el usuario esta jugando y que la secuencia de colores introducida por el jugador sea la adecuada, borra el array2, y da otro numero al array1)

        siguienteRonda.setOnClickListener(){
            if (jugando){
                if (array1==array2){
                    ronda++
                    array2.clear()
                    array1.add(Random.nextInt(4)+1)
                    mensageUsuario(array1)
                    contador.setText(ronda.toString())


                }else{
                    Toast.makeText(applicationContext, "fallaste", Toast.LENGTH_SHORT).show()
                    jugando=false
                }
            }else{
                Toast.makeText(applicationContext,"No le has dado a iniciar",Toast.LENGTH_SHORT).show()
            }
        }
        //funcion para añadir los colores en el segundo array
        fun anhadir(array: ArrayList<Int>, color:Int){
            array.add(color)
        }
        //asignamos valores a los colores -> rojo=1; azul=2; amarillo=3; verde=4
        rojo.setOnClickListener(){
            anhadir(array2,1)
        }
        azul.setOnClickListener(){
            anhadir(array2,2)
        }
        amarillo.setOnClickListener(){
            anhadir(array2,3)
        }
        verde.setOnClickListener(){
            anhadir(array2,4)
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

