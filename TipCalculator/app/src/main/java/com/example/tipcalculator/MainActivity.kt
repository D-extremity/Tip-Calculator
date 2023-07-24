package com.example.tipcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val cost:EditText=findViewById(R.id.cost)
        val optioned =findViewById<RadioGroup>(R.id.option)
        //Optionid is int because this is obviously reference
        val switch=findViewById<Switch>(R.id.switch1)
        val button=findViewById<Button>(R.id.calculate)
        val text=findViewById<TextView>(R.id.textView3)

        button.setOnClickListener {

            if (cost.text.toString().isEmpty())
                Toast.makeText(this, "Field is empty", Toast.LENGTH_SHORT).show()
            else {
                val tip: Int = cost.text.toString().toInt()

                val tippercentage = when (optioned.checkedRadioButtonId) {
                    R.id.amazing -> 0.2
                    R.id.good -> 0.1
                    else -> 0.05
                }
                val b = tip * tippercentage + tip
                if (switch.isChecked)
                    text.setText(NumberFormat.getCurrencyInstance().format(kotlin.math.ceil(b)))
                else
                    text.setText(NumberFormat.getCurrencyInstance().format(b))
            }
        }

    }


}







