package com.husar.customwidget

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var customWidget: CustomWidget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize custom widget
        val customWidget = findViewById<CustomWidget>(R.id.custom_widget)

        customWidget.onItemSelectedListener = { position ->

            val valueRGB = (position * 2.55).toInt()
            val color = toHex( valueRGB, valueRGB, valueRGB)

            setBackgroundColor(color)
        }
    }

    fun toHex(red: Int, green: Int, blue: Int): String {
        return "#" + toBrowserHexValue(red) + toBrowserHexValue(green) + toBrowserHexValue(blue)
    }

    private fun toBrowserHexValue(number: Int): String {
        val builder = StringBuilder(Integer.toHexString(number and 0xff))
        while (builder.length < 2) {
            builder.append("0")
        }
        return builder.toString().toUpperCase()
    }

    fun setBackgroundColor(color: String) {
        getWindow().getDecorView().setBackgroundColor(Color.parseColor(color))
    }
}
