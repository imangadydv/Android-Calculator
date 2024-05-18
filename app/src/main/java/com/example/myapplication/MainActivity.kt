package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var workTextView: TextView
    private lateinit var resultTextView: TextView
    private val workStringBuilder = StringBuilder()
    private var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        workTextView = findViewById(R.id.work)
        resultTextView = findViewById(R.id.result)
    }

    fun numberAction(view: View ){
        val button = view as Button
        workStringBuilder.append(button.text.toString())
        workTextView.text = workStringBuilder.toString()
    }
    fun operationAction(view: View){
        val button = view as Button
        workStringBuilder.append(" ").append(button.text.toString()).append(" ")
        workTextView.text = workStringBuilder.toString()
    }
    fun allClearAction(view: View)
    {
        workStringBuilder.clear()
        result = 0.0
        workTextView.text=""
        resultTextView.text=""
    }
    fun backSpaceAction(view: View)
    {
        val length = workStringBuilder.length
        if (length > 0) {
            workStringBuilder.deleteCharAt(length - 1)
            workTextView.text = workStringBuilder.toString()
        }
    }
    fun equalsAction(view: View){
        val expression = workStringBuilder.toString()
        result = evaluateExpression(expression)
        resultTextView.text = result.toString()
    }
    private fun evaluateExpression(expression: String): Double {
        return try {
            val tokens = expression.split(" ")
            var result = tokens[0].toDouble()
            for (i in 1 until tokens.size step 2) {
                val operator = tokens[i]
                val value = tokens[i + 1].toDouble()
                result = when (operator) {
                    "+" -> result + value
                    "-" -> result - value
                    "*" -> result * value
                    "/" -> result / value
                    else -> result
                }
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            0.0
        }
    }
}