package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rojo : Button=findViewById(R.id.rojo)
        val verde : Button=findViewById(R.id.verde)
        val amarillo : Button=findViewById(R.id.amarillo)
        val azul : Button=findViewById(R.id.azul)
        val jugar : Button=findViewById(R.id.jugar)
        val contador : TextView=findViewById(R.id.contador)



        jugar.setOnClickListener(){

        }
    }
}