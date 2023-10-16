package com.example.interfaces_ejercicio1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fun lanzarNewPLayer(){
            val i = Intent(this, NewPlayer::class.java)
            startActivity(i)
        }
        val jugador1 = findViewById(R.id.button1) as Button
        jugador1.setOnClickListener{lanzarNewPLayer()}


    }
}