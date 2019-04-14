package com.example.android.coroutinesinandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import kotlinx.coroutines.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_startButton.setOnClickListener { v ->
            textView_numbers.text = "0"
            Toast.makeText(this, "Coroutine Launched", Toast.LENGTH_SHORT)
                .show()
            fibonacci()
        }
    }

    private fun fib(n: Long): Long {
        return if (n <= 1) n
        else fib(n - 1) + fib(n - 2)
    }

    private fun fibonacci() = GlobalScope.launch(Dispatchers.IO) {

        Log.v("123456789", "Thread in GlobalScope: ${Thread.currentThread().name}")

        for(i in 1..20) {
            val value = fib(i.toLong())
            delay(1000L)
            withContext(Dispatchers.Main) {
                textView_numbers.text = "$value"
                Log.v("123456789", "$value, Thread in: ${Thread.currentThread().name}")
            }
        }

        Log.v("123456789", "Coroutine just finished")
    }

}
