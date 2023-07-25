package com.example.mybill

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            var c = findViewById<TextView>(R.id.ctax)
            var s = findViewById<TextView>(R.id.stax)
            var i = findViewById<TextView>(R.id.itax)
            var t = findViewById<TextView>(R.id.ttax)
            var amount = findViewById<TextView>(R.id.amount)
            var discount = findViewById<TextView>(R.id.disamount)


            var mrps: String = findViewById<TextInputEditText>(R.id.mrp).text.toString()
//            Log.e("check", "${mrp}")
            var cgst = findViewById<TextInputEditText>(R.id.cgst).text.toString()
            var sgst = findViewById<TextInputEditText>(R.id.sgst).text.toString()
            var igst = findViewById<TextInputEditText>(R.id.igst).text.toString()
            var dis = findViewById<TextInputEditText>(R.id.dis).text.toString()



            if (mrps.isEmpty()) {
                Toast.makeText(this, "MRP FIELD IS EMPTY!!", Toast.LENGTH_SHORT).show()
            } else {
                val switchCompat = findViewById<SwitchCompat>(R.id.switch1)
                var add = findViewById<TextInputEditText>(R.id.add).text.toString()
                var ded = findViewById<TextInputEditText>(R.id.deduct).text.toString()
                var mrp = mrps.toDouble()
                var d: String = dis(mrp, dis.toDouble())

                if (switchCompat.isChecked) {
                    c.setText("CGST% : $00.00")
                    s.setText("SGST% : $00.00")
                    var itax = ist(mrp, igst.toDouble())

                    i.setText("IGST% : $${itax}")
                    t.setText("Total GST% : $${itax}")

                    discount.setText("Discount : $${d}")
                    if (add.isEmpty())
                        add = "0"
                    if (ded.isEmpty())
                        ded = "0"

                    val pay = String.format("%.2f",-d.toDouble() + itax.toDouble() + add.toDouble() - ded.toDouble()+mrp)

                    amount.setText("Total Amount : $${pay}")


                } else {
                    var add = findViewById<TextInputEditText>(R.id.add).text.toString()
                    var ded = findViewById<TextInputEditText>(R.id.deduct).text.toString()
                    var d: String = dis(mrp, dis.toDouble())

                    var mrp = mrps.toDouble()
                    if (add.isEmpty())
                        add = "0"
                    if (ded.isEmpty())
                        ded = "0"
                    i.setText("IGST% : $00.00")
                    var cst=cst(mrp,cgst.toDouble())
                    var sst=cst(mrp,sgst.toDouble())
                    c.setText("CGST% : $${cst}")
                    s.setText("SGST% : $${sst}")
                    discount.setText("Discount : $${d}")
                    t.setText("Total GST% : $${(cst.toDouble()+sst.toDouble()).toString()}")
                    val pay = String.format("%.2f",-d.toDouble() + cst.toDouble()+sst.toDouble() + add.toDouble() - ded.toDouble()+mrp)

                    amount.setText("Total Amount : $${pay}")
                }


            }


        }


    }

    fun cst(mrp: Double, c: Double): String {
        var s = String.format("%.2f", (mrp * c) / 100)

        return s
    }

    fun sst(mrp: Double, c: Double): String {
        var s = String.format("%.2f", (mrp * c) / 100)

        return s
    }

    fun ist(mrp: Double, c: Double): String {
        var s = String.format("%.2f", (mrp * c) / 100)

        return s
    }

    fun dis(mrp: Double, c: Double): String {
        var s = String.format("%.2f", (mrp * c) / 100)

        return s
    }

}

