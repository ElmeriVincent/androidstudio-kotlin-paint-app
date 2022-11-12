package com.example.androidstudio_kotlin_paint_app

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.androidstudio_kotlin_paint_app.PainView.Companion.colorList
import com.example.androidstudio_kotlin_paint_app.PainView.Companion.currentBrush
import com.example.androidstudio_kotlin_paint_app.PainView.Companion.pathList

class MainActivity : AppCompatActivity() {

    // Available throughout the app
    companion object {
        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val redBtn = findViewById<ImageButton>(R.id.colorRed)
        val blueBtn = findViewById<ImageButton>(R.id.colorBlue)
        val grayBtn = findViewById<ImageButton>(R.id.colorGray)
        val clearBtn = findViewById<Button>(R.id.clear)

        // Setting the colors
        redBtn.setOnClickListener{
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
        }

        blueBtn.setOnClickListener{
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
        }

        grayBtn.setOnClickListener{
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)
        }

        clearBtn.setOnClickListener{
            pathList.clear()
            colorList.clear()
            path.reset()
        }
    }

    // Setting the selected color
    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }
}