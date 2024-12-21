package com.arafet.calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arafet.calculator.R.id
import com.arafet.calculator.R.layout
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var divide: Button
    private lateinit var multiply: Button
    private lateinit var add: Button
    private lateinit var minus: Button
    private lateinit var equal: Button
    private lateinit var clear: Button // Changed 'del' to 'clear' for better clarity
    private lateinit var delete: Button // delete only last character
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button
    private lateinit var result: TextView
    private lateinit var color : Button
    private lateinit var calculSc : Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize all the buttons and the TextView
        divide = findViewById(id.divideButton)
        multiply = findViewById(id.multiplyButton)
        add = findViewById(id.plusButton)
        minus = findViewById(id.minusButton)
        equal = findViewById(id.equalsButton)
        clear = findViewById(id.clearButton) // Changed 'del' to 'clear'
        one = findViewById(id.oneButton)
        two = findViewById(id.twoButton)
        three = findViewById(id.threeButton)
        four = findViewById(id.fourButton)
        five = findViewById(id.fiveButton)
        six = findViewById(id.sixButton)
        seven = findViewById(id.sevenButton)
        eight = findViewById(id.eightButton)
        nine = findViewById(id.nineButton)
        zero = findViewById(id.zeroButton)
        result = findViewById(id.displayTextView)
        delete = findViewById(id.delButton)
        color = findViewById(id.changeColor)
        calculSc = findViewById(id.calculSc)

        // You can now add onClickListeners to your buttons here
        // Example:
        one.setOnClickListener {
            appendToResult("1")
        }
        two.setOnClickListener {
            appendToResult("2")
        }
        three.setOnClickListener {
            appendToResult("3")
        }
        four.setOnClickListener {
            appendToResult("4")
        }
        five.setOnClickListener {
            appendToResult("5")
        }
        six.setOnClickListener {
            appendToResult("6")
        }
        seven.setOnClickListener {
            appendToResult("7")
        }
        eight.setOnClickListener {
            appendToResult("8")
        }
        nine.setOnClickListener {
            appendToResult("9")
        }
        zero.setOnClickListener {
            appendToResult("0")
        }
        add.setOnClickListener {
            appendToResult("+")
        }
        minus.setOnClickListener {
            appendToResult("-")
        }
        multiply.setOnClickListener {
            appendToResult("*")
        }
        divide.setOnClickListener {
            appendToResult("/")
        }
        delete.setOnClickListener {
            val currentText = result.text.toString() // Convert CharSequence to String
            if (currentText.isNotEmpty()) {
                result.text = currentText.dropLast(1) // Remove the last character
            }
        }
        equal.setOnClickListener {
            val input = result.text.toString()
            try {
                val expression = ExpressionBuilder(input).build()
                val calculationResult = expression.evaluate()
                result.text = calculationResult.toString()
            }catch (e: Exception) {
                Toast.makeText(this, "Invalid Expression", Toast.LENGTH_LONG).show()
            }
        }
        clear.setOnClickListener {
            result.text = "0"
        }
        //JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ
        color.setOnClickListener {
            // List of color resource IDs
            val colorList = listOf(
                R.color.black,
                R.color.white,
                R.color.red,
                R.color.green,
                R.color.blue,
                R.color.yellow,
                R.color.cyan,
                R.color.magenta,
                R.color.orange,
                R.color.pink,
                R.color.purple,
                R.color.brown,
                R.color.lime,
                R.color.teal,
                R.color.indigo,
                R.color.gold
            )

            // Pick a random color from the list
            val randomColor = colorList.random()

            // Apply the color to the background
            result.setBackgroundColor(ContextCompat.getColor(this, randomColor))
        }

        calculSc.setOnClickListener{
            val intent= Intent(this,CalculeScientifique::class.java)
            startActivity(intent)

        }
    }

    @SuppressLint("SetTextI18n")
    private fun appendToResult(value: String) {
        if (result.text == "0") {
            result.text = value
        } else {
            result.text = "${result.text}$value"
        }
    }

}