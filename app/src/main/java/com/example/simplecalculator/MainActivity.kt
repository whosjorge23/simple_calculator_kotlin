package com.example.simplecalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val operand1Input = findViewById<EditText>(R.id.operand1Input)
        val operand2Input = findViewById<EditText>(R.id.operand2Input)
        val operationInput = findViewById<Spinner>(R.id.operationInput)
        val resultText = findViewById<TextView>(R.id.resultText)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener {
            val operand1Text = operand1Input.text.toString()
            val operand2Text = operand2Input.text.toString()

            if (operand1Text.isEmpty() || operand2Text.isEmpty()) {
                Toast.makeText(this, "Please enter values for both operands", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val operand1 = operand1Text.toDoubleOrNull()
            val operand2 = operand2Text.toDoubleOrNull()

            if (operand1 == null || operand2 == null) {
                Toast.makeText(this, "Invalid input. Please enter valid numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val operation = Operation.values()[operationInput.selectedItemPosition]

            val calculator = Calculator(operand1, operand2)
            val result = calculator.performOperation(operation)

            resultText.text = "Result: %.2f".format(result)
        }
    }
}

// Operation Enum Class
enum class Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
}

// Calculator Class
class Calculator(private val operand1: Double, private val operand2: Double) {

    fun performOperation(operation: Operation): Double {
        return when (operation) {
            Operation.ADD -> operand1 + operand2
            Operation.SUBTRACT -> operand1 - operand2
            Operation.MULTIPLY -> operand1 * operand2
            Operation.DIVIDE -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
        }
    }
}