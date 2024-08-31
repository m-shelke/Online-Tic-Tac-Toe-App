package com.example.onlinetictactoeappkt

import android.content.DialogInterface
import android.graphics.Color
import android.media.MediaParser
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

var playreTurn = true
class GamePlayActivity : AppCompatActivity() {

    lateinit var player1TV:TextView
    lateinit var player2TV:TextView

    lateinit var BTN1:Button
    lateinit var BTN2:Button
    lateinit var BTN3:Button
    lateinit var BTN4:Button
    private lateinit var BTN5:Button
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

          if (checkWinner == 1){
              Handler().postDelayed(Runnable{ reset() },2000)
          }else if (checksinglePlayer){
              Handler().postDelayed(Runnable{ robot() },5000)
          }else{
              activeUser = 2
          }
      }else{
          but.text = "O"
          audio.start()
          but.setTextColor(Color.parseColor("#FF000000"))
          activeUser = 1
          player2.add(cellID)
          empthyCells.add(cellID)
          Handler().postDelayed(Runnable{ audio.release() },200)
          but.isEnabled = false

          val checkWinner = checkWinner()
          if (checkWinner  == 1){
              Handler().postDelayed(Runnable{ reset() },4000)
          }
      }

    }

    private fun robot() {
        val random = (1..9).random()

        if (empthyCells.contains(random)){
            val buttonSelected = when(random){
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
            empthyCells.add(random)
            val audio = MediaPlayer.create(this,R.raw.btn_clicked)
            audio.start()

            Handler().postDelayed(Runnable{ audio.release() },500)
            buttonSelected.text = "O"
            buttonSelected.setTextColor(Color.parseColor("#FFFFFFFF"))
            player2.add(random)
            buttonSelected.isEnabled = false

            var checkWinner = checkWinner()
            if (checkWinner == 1){
                Handler().postDelayed(Runnable{ reset() },2000)
            }
        }

    }

    private fun checkWinner(): Int {
        val audio = MediaPlayer.create(this,R.raw.winning_sound)

        if ((player1.contains(1) && player1.contains(2) && player1.contains(3)) ||
            (player1.contains(4) && player1.contains(5) && player1.contains(6)) ||
            (player1.contains(7) && player1.contains(8) && player1.contains(9)) ||
            (player1.contains(1) && player1.contains(4) && player1.contains(7)) ||
            (player1.contains(2) && player1.contains(5) && player1.contains(8)) ||
            (player1.contains(3) && player1.contains(6) && player1.contains(9)) ||
            (player1.contains(1) && player1.contains(5) && player1.contains(9)) ||
            (player1.contains(3) && player1.contains(5) && player1.contains(7))

            ){
            player1Count += 1
            buttonDisable()
            audio.start()
            disableReset()
            Handler().postDelayed(Runnable{ audio.release() },4000)

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 1 Wins \n\n "+"Want To Play Again..?")
            build.setIcon(R.drawable.ic_launcher_foreground)

            build.setPositiveButton("Yeah",DialogInterface.OnClickListener { dialog, which ->
                reset()
                audio.release()
            })

            build.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                audio.release()
                exitProcess(1)
            })

            Handler().postDelayed(Runnable{ build.show()},2000)
            return 1

        }else if ((player2.contains(1) && player2.contains(2) && player2.contains(3)) ||
            (player2.contains(4) && player2.contains(5) && player2.contains(6)) ||
            (player2.contains(7) && player2.contains(8) && player2.contains(9)) ||
            (player2.contains(1) && player2.contains(4) && player2.contains(7)) ||
            (player2.contains(2) && player2.contains(5) && player2.contains(8)) ||
            (player2.contains(3) && player2.contains(6) && player2.contains(9)) ||
            (player2.contains(1) && player2.contains(5) && player2.contains(9)) ||
            (player2.contains(3) && player2.contains(5) && player2.contains(7)))
        {
            player2Count +=1
            buttonDisable()
            disableReset()

            Handler().postDelayed(Runnable{ audio.release() },4000)

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 2 Wins \n\n "+"Want To Play Again..?")
            build.setIcon(R.drawable.ic_launcher_foreground)

            build.setPositiveButton("Yeah",DialogInterface.OnClickListener { dialog, which ->
                reset()
                audio.release()
            })

            build.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                audio.release()
                exitProcess(1)
            })

            Handler().postDelayed(Runnable{ build.show()},2000)
            return 1

        }else if (empthyCells.contains(1) && empthyCells.contains(2) && empthyCells.contains(3)
            && empthyCells.contains(4) && empthyCells.contains(5) && empthyCells.contains(6)
            && empthyCells.contains(7) && empthyCells.contains(8) && empthyCells.contains(9))
        {

        val build = AlertDialog.Builder(this)
        build.setTitle("Game Draw")
        build.setMessage("Game Tie \n\n "+"Want To Play Again..?")
        build.setIcon(R.drawable.ic_launcher_foreground)

        build.setPositiveButton("Yeah",DialogInterface.OnClickListener { dialog, which ->
            reset()
        })

        build.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
            exitProcess(1)
        })
            build.show()
            return 1

        }
        return 0
    }

    private fun buttonDisable() {
        player1.clear()
        player2.clear()
        empthyCells.clear()
        activeUser = 1

        for (i in 1..9){
            var buttonSelected :Button ?
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
            buttonSelected.text = ""
            player1TV.text = "Player 1 : $player1Count"
            player2TV.text = "Player 2 : $player2Count"
        }
    }




    private fun disableReset() {
        resetBTN.isEnabled = false
        Handler().postDelayed(Runnable{ resetBTN.isEnabled = true},2200)
    }

}