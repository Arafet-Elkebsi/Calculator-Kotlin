package com.arafet.calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class CalculeScientifique : AppCompatActivity() {

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

    private lateinit var cos : Button
    private lateinit var sin : Button
    private lateinit var pi : Button
    private lateinit var tan : Button
    private lateinit var retour : Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calcule_scientifique)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CalculScience)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        divide = findViewById(R.id.divideButton2)
        multiply = findViewById(R.id.multiplyButton2)
        add = findViewById(R.id.plusButton2)
        minus = findViewById(R.id.minusButton2)
        equal = findViewById(R.id.equalsButton2)
        clear = findViewById(R.id.clearButton2) // Changed 'del' to 'clear'
        one = findViewById(R.id.oneButton2)
        two = findViewById(R.id.twoButton2)
        three = findViewById(R.id.threeButton2)
        four = findViewById(R.id.fourButton2)
        five = findViewById(R.id.fiveButton2)
        six = findViewById(R.id.sixButton2)
        seven = findViewById(R.id.sevenButton2)
        eight = findViewById(R.id.eightButton2)
        nine = findViewById(R.id.nineButton2)
        zero = findViewById(R.id.zeroButton2)
        result = findViewById(R.id.displayTextView2)
        delete = findViewById(R.id.delButton2)

        cos = findViewById(R.id.cos)
        tan = findViewById(R.id.tan)
        sin = findViewById(R.id.sin)
        pi = findViewById(R.id.pi)

        retour = findViewById(R.id.retour)

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


        retour.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        equal.setOnClickListener {
            val input = result.text.toString()
            try {
                val expression = ExpressionBuilder(input).build()
                val calculationResult = expression.evaluate()
                result.text = calculationResult.toString()
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid Expression", Toast.LENGTH_LONG).show()
            }
        }
        clear.setOnClickListener {
            result.text = "0"
        }

        pi.setOnClickListener {
            appendToResult(Math.PI.toString())
        }

        tan.setOnClickListener {
            val input = result.text.toString()
            try {
                val value = input.toDouble() // Convert input to Double
                val tanValue = tan(Math.toRadians(value)) // Calculate tan in degrees
                result.text = tanValue.toString()
            }catch (e: Exception) {
                Toast.makeText(this, "Invalid Expression", Toast.LENGTH_LONG).show()
            }
        }

        cos.setOnClickListener {
            val input = result.text.toString()
            try {
                val value = input.toDouble() // Convert input to Double
                val cosValue = cos(Math.toRadians(value)) // Calculate cos in degrees
                result.text = cosValue.toString()
            }catch (e: Exception) {
                Toast.makeText(this, "Invalid Expression", Toast.LENGTH_LONG).show()
            }
        }

        sin.setOnClickListener {
            val input = result.text.toString()
            try {
                val value = input.toDouble() // Convert input to Double
                val sinValue = sin(Math.toRadians(value)) // Calculate sin in degrees
                result.text = sinValue.toString()
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid Expression", Toast.LENGTH_LONG).show()
            }
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