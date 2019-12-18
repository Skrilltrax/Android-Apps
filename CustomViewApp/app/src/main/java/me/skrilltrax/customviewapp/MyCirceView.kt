package me.skrilltrax.customviewapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyCirceView @JvmOverloads constructor(
    context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attr, defStyleAttr) {

    private val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    // Face border width in pixels
    private var borderWidth = 4.0f
    // View size in pixels
    private var size = 320

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        paint.color = faceColor
        val radius = size / 2f
        canvas.drawCircle(size/2f, size/2f, radius, paint)

        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {
    }

    private fun drawMouth(canvas: Canvas) {
    }
}