package com.ext.smartqrscanner.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class QROverlayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val maskPaint = Paint().apply {
        color = Color.parseColor("#88000000")
    }

    private val framePaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 6f
        isAntiAlias = true
    }

    private val scanLinePaint = Paint().apply {
        color = Color.GREEN
        strokeWidth = 4f
        isAntiAlias = true
    }

    private val frameRect = RectF()
    private var scanLineY = 0f

    init {
        startScanAnimation()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val size = min(w, h) * 0.65f
        val left = (w - size) / 2
        val top = (h - size) / 2
        frameRect.set(left, top, left + size, top + size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dark background
        canvas.drawRect(0f, 0f, width.toFloat(), frameRect.top, maskPaint)
        canvas.drawRect(0f, frameRect.bottom, width.toFloat(), height.toFloat(), maskPaint)
        canvas.drawRect(0f, frameRect.top, frameRect.left, frameRect.bottom, maskPaint)
        canvas.drawRect(frameRect.right, frameRect.top, width.toFloat(), frameRect.bottom, maskPaint)

        // White frame
        canvas.drawRect(frameRect, framePaint)

        // Scan line
        canvas.drawLine(
            frameRect.left,
            scanLineY,
            frameRect.right,
            scanLineY,
            scanLinePaint
        )
    }

    private fun startScanAnimation() {
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 1800
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                scanLineY =
                    frameRect.top + frameRect.height() * it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }
}
