package com.example.onlinetictactoeappkt

import android.graphics.Color
import android.media.MediaParser
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

var playreTurn = true
class GamePlayActivity : AppCompatActivity() {

    lateinit var player1TV:TextView
    lateinit var player2TV:TextView

    lateinit var BTN1:Button
    lateinit var BTN2:Button
    lateinit var BTN3:Button
    lateinit var BTN4:Button
    lateinit var BTN5:Button
    lateinit var BTN6:Button
    lateinit var BTN7:Button
    lateinit var BTN8:Button
    lateinit var BTN9:Button
    lateinit var resetBTN:Button

    var player1Count = 0
    var player2Count = 0

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var empthyCells = ArrayList<Int>()

    var activeUser = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_play)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        player1TV = findViewById(R.id.player1TV)
        player2TV = findViewById(R.id.player2TV)

        BTN1 = findViewById(R.id.BTN1)
        BTN2 = findViewById(R.id.BTN2)
        BTN3 = findViewById(R.id.BTN3)
        BTN4 = findViewById(R.id.BTN4)
        BTN5 = findViewById(R.id.BTN5)
        BTN5 = findViewById(R.id.BTN6)
        BTN7 = findViewById(R.id.BTN7)
        BTN8 = findViewById(R.id.BTN8)
        BTN9 = findViewById(R.id.BTN9)
        resetBTN = findViewById(R.id.resetBTN)

        resetBTN.setOnClickListener(View.OnClickListener {
            reset()
        })
    }

    private fun reset() {

        player1.clear()
        player2.clear()
        empthyCells.clear()
        activeUser = 1

        for (i in 1..9){
            var buttonSelected :Button?
            buttonSelected = when(i){
                1->BTN1
                2->BTN2
                3->BTN3
                4->BTN4
                5->BTN5
                6->BTN6
                7->BTN7
                8->BTN8
                9->BTN9
                else -> {
                    BTN1
                }
            }
            buttonSelected.isEnabled
            buttonSelected.text
            player1TV.text = "Player1 : $player1Count"
            player2TV.text = "Player2 : $player2Count"

            }
    }

    fun BTNClick1(view: View) {

        if (playreTurn){
            val but = view as Button
            var cellID = 0

            when(but.id){
                R.id.BTN1-> cellID =1
                R.id.BTN2-> cellID =2
                R.id.BTN3-> cellID =3
                R.id.BTN4-> cellID =4
                R.id.BTN5-> cellID =5
                R.id.BTN6-> cellID =6
                R.id.BTN7-> cellID =7
                R.id.BTN8-> cellID =8
                R.id.BTN9-> cellID =9
            }
            playreTurn = false
            Handler().postDelayed(Runnable{ playreTurn = true},600)
            playNow(but,cellID);
        }
    }

    private fun playNow(but: Button, cellID: Int) {
      val audio = MediaPlayer.create(this,R.raw.btn_clicked)

      if (activeUser == 1){
          but.text = "X"
          but.setTextColor(Color.parseColor("#FFFFFFFF"))
          player1.add(cellID)
          empthyCells.add(cellID)
          audio.start()
          but.isEnabled = false
          Handler().postDelayed(Runnable{audio.release()},200)
          val checkWinner = checkWinner()
      }

    }

    private fun checkWinner(): Any {
        return true
    }

}