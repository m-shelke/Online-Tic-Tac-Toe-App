package com.example.onlinetictactoeappkt

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GamePlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_play)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun BTNClick1(view: View) {

    }

    fun BTNClick2(view: View) {

    }

    fun BTNClick3(view: View) {

    }
    fun BTNClick4(view: View) {

    }
    fun BTNClick5(view: View) {

    }
    fun BTNClick6(view: View) {

    }
    fun BTNClick7(view: View) {

    }

    fun BTNClick8(view: View) {

    }
    fun BTNClick9(view: View) {

    }
}