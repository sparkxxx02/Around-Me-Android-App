package com.example.aroundme

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false
    var currentScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart.setOnClickListener { v ->
            currentScore = 0
            text_items.setText("")
            items.setText("")
            startTimer(21000)
        }
        buttonReset.setOnClickListener { v ->
            if(isRunning)
                pauseTimer()
            timer.setText("20")
            currentScore = 0
            text_items.setText("")
            items.setText("")
        }
        buttonSubmit.setOnClickListener { v ->

            currentScore = currentScore + 1
            var initial: String = text_items.text.toString()
            items.append(initial + "\n")
            text_items.setText("")

        }
    }

    private fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                score.setText("Your score is $currentScore")
                text_items.isFocusable = false
            }

            override fun onTick(millisUntilFinished: Long) {
                val time: Long = (millisUntilFinished / 1000) - 1
                timer.setText(time.toString())
            }
        }
        countdown_timer.start()
        isRunning = true
    }

    private fun pauseTimer() {
        countdown_timer.cancel()
        isRunning = false

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.three_dot, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.aboutFragment -> setContentView(R.layout.about)
            R.id.creditsFragment -> setContentView(R.layout.creditials)
        }
                return super.onOptionsItemSelected(item)
        }
    }


