package com.example.androidstudio_kotlin_paint_app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.androidstudio_kotlin_paint_app.MainActivity.Companion.paintBrush
import com.example.androidstudio_kotlin_paint_app.MainActivity.Companion.path
import java.text.AttributedCharacterIterator.Attribute

class PainView : View {

    //responsible for what is the height and width of the canvas to the parent layout.
    var params : ViewGroup.LayoutParams? = null

    companion object {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.BLACK
    }

    constructor(context : Context) : this(context, null) {
        init()
    }
    constructor(context : Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context : Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    // Initialize brush
    private fun init() {
        paintBrush.isAntiAlias = true // makes texture smooth
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 8f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    // Getting the drawing finger input
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.x
        var y = event.y

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                pathList.add(path)
                colorList.add(currentBrush)
            }
            else -> return false
        }
        // Inform the non-ui threads that some changes have been done on the ui
        postInvalidate()
        return false;
    }

    // Drawing on the screen
    override fun onDraw(canvas: Canvas) {

        for(i in pathList.indices) {
            paintBrush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paintBrush)
            invalidate() // informing non-ui threads about the changes on the ui
        }

    }
}