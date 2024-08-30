package com.example.onlinetictactoeappkt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

var checksinglePlayer = false

class MainActivity : AppCompatActivity() {

    lateinit var singlePlayerBTN : Button
    lateinit var multiPlayerBTN : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        singlePlayerBTN = findViewById(R.id.singlePlayerBTN)
        multiPlayerBTN = findViewById(R.id.multiPlayerBTN)

        singlePlayerBTN.setOnClickListener(View.OnClickListener {
            checksinglePlayer = true
           startActivity(Intent(this,GamePlayActivity::class.java))
        })

        multiPlayerBTN.setOnClickListener(View.OnClickListener {
            checksinglePlayer = false
            startActivity(Intent(this,GamePlayActivity::class.java))
        })
    }
}