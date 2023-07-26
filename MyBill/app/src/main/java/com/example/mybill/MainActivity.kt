package com.example.mybill

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val c = findViewById<TextView>(R.id.ctax)
            val s = findViewById<TextView>(R.id.stax)
            val i = findViewById<TextView>(R.id.itax)
            var t = findViewById<TextView>(R.id.ttax)
            val amount = findViewById<TextView>(R.id.amount)
            val discount = findViewById<TextView>(R.id.disamount)


            var mrps: String = findViewById<TextInputEditText>(R.id.mrp).text.toString()
//            Log.e("check", "${mrp}")
            val cgst = findViewById<TextInputEditText>(R.id.cgst).text.toString()
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
                    c.setText("CGST% :q     - - -")
                    s.setText("SGST% :      - - -")
                    var itax = ist(mrp, igst.toDouble())

                    i.setText("IGST% : ${NumberFormat.getCurrencyInstance().format(itax.toDouble())}")
                    t.setText("Net GST% : ${NumberFormat.getCurrencyInstance().format(itax.toDouble())}")

                    discount.setText("Discount : ${NumberFormat.getCurrencyInstance().format(d.toDouble())}")
                    if (add.isEmpty())
                        add = "0"
                    if (ded.isEmpty())
                        ded = "0"

                    val pay = String.format("%.2f",-d.toDouble() + itax.toDouble() + add.toDouble() - ded.toDouble()+mrp)
                    val p=NumberFormat.getCurrencyInstance().format(pay.toDouble())

                    amount.setText("Total Amount : $p")


                } else {
                    var add = findViewById<TextInputEditText>(R.id.add).text.toString()
                    var ded = findViewById<TextInputEditText>(R.id.deduct).text.toString()
                    var d: String = dis(mrp, dis.toDouble())

                    var mrp = mrps.toDouble()
                    if (add.isEmpty())
                        add = "0"
                    if (ded.isEmpty())
                        ded = "0"
                    i.setText("IGST% :      - - -")
                    var cst=cst(mrp,cgst.toDouble())
                    var sst=sst(mrp,sgst.toDouble())
                    val total=NumberFormat.getCurrencyInstance().format(cst.toDouble()+sst.toDouble())
                    c.setText("CGST% : ${NumberFormat.getCurrencyInstance().format(cst.toDouble())}")
                    s.setText("SGST% : ${NumberFormat.getCurrencyInstance().format(sst.toDouble())}")
                    discount.setText("Discount : ${NumberFormat.getCurrencyInstance().format(d.toDouble())}")
                    t.setText("Net GST% : ${total}")
                    val pay=String.format("%.2f",-d.toDouble() + cst.toDouble()+sst.toDouble() + add.toDouble() - ded.toDouble()+mrp).toDouble()
                    val p = NumberFormat.getCurrencyInstance().format(pay)

                    amount.setText("Total Amount : ${p}")
                }


            }


        }


    }

    private fun cst(mrp: Double, c: Double): String {
        var s = String.format("%.2f", (mrp * c) / 100)

        return s
    }

    private fun sst(mrp: Double, c: Double): String {
        var s = String.format("%.2f", (mrp * c) / 100)

        return s
    }

    private fun ist(mrp: Double, c: Double): String {
        var s = String.format("%.2f", (mrp * c) / 100)

        return s
    }

    private fun dis(mrp: Double, c: Double): String {
        var s = String.format("%.2f", (mrp * c) / 100)

        return s
    }

}

