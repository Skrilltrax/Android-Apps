package me.skrilltrax.customviewapp

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    fun next() = when(this) {
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }

    fun prev() = when(this) {
        OFF -> HIGH
        LOW -> OFF
        MEDIUM -> LOW
        HIGH -> MEDIUM
    }
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35


class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    private var radius = 0.0f                   // Radius of the circle.
    private var fanSpeed = FanSpeed.OFF         // The active selection.
    private var flag = 0;
    private var startAngle = 0.0f;
    private var angle = 0.0f;
    private val pointPosition: PointF = PointF(0.0f, 0.0f)
    private val valueAnimator = ValueAnimator()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create( "", Typeface.BOLD)
    }

    init {
        isClickable = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
        flag = 1;
    }

    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float, useAngle: Float?) {
        if (useAngle != null) {
            x = changeX(angle);
            y = changeY(angle);
            return
        }
        startAngle = (Math.PI * (9 / 8.0)).toFloat()
        angle = (startAngle + pos.ordinal * (Math.PI / 4)).toFloat()
        x = changeX(angle);
        y = changeY(angle);
    }

    private fun changeX(angle: Float) : Float {
        return (radius * cos(angle)) + width / 2;
    }

    private fun changeY(angle: Float) : Float {
        return (radius * sin(angle)) + height / 2;
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
        canvas.drawCircle((width / 2.0).toFloat(), (height / 2.0).toFloat(), radius, paint)

        if (flag == 0) {
            computeLabel(canvas)
            val markerRadius = radius + RADIUS_OFFSET_INDICATOR
            fanSpeed = fanSpeed.next()
            pointPosition.computeXYForSpeed(fanSpeed, markerRadius, null)
            flag = 1;
        }

        paint.color = Color.BLACK
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius/12, paint)
    }

    private fun computeKnob() {
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        val oldAngle = angle;
        fanSpeed = fanSpeed.next()
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius, null)
        fanSpeed = fanSpeed.prev()
        ValueAnimator.ofFloat(oldAngle, angle).apply {
            duration = 500
            addUpdateListener {
                pointPosition.computeXYForSpeed(fanSpeed, radius, animatedValue as Float)
                invalidate()
            }
        }
    }

    private fun computeLabel(canvas: Canvas) {
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius, null)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true
        computeKnob()
        contentDescription = resources.getString(fanSpeed.label)

        invalidate()
        return true
    }
}