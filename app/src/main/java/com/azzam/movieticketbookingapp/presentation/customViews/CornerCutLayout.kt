package com.azzam.movieticketbookingapp.presentation.customViews


import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.azzam.movieticketbookingapp.R

class CornerCutLayout(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private var radiusBottomEnd: Float = 0f
    private var radiusBottomStart: Float = 0f
    private var radiusTopStart: Float = 0f
    private var radiusTopEnd: Float = 0f

    private var radiusSide: Float = 0f
    private var cutHeight: Float = 200f

    private lateinit var rectTopStart: RectF
    private lateinit var rectTopEnd: RectF
    private lateinit var rectBottomStart: RectF
    private lateinit var rectBottomEnd: RectF

    init {
        setWillNotDraw(false)
        attrs?.let {
            val ta = context.obtainStyledAttributes(
                it, R.styleable.CornerCutLayout, 0, 0
            )

            radiusTopStart = ta.getDimension(R.styleable.CornerCutLayout_radiusTopStart, 0f)
            radiusTopEnd = ta.getDimension(R.styleable.CornerCutLayout_radiusTopEnd, 0f)
            radiusBottomStart = ta.getDimension(R.styleable.CornerCutLayout_radiusBottomStart, 0f)
            radiusBottomEnd = ta.getDimension(R.styleable.CornerCutLayout_radiusBottomEnd, 0f)

            radiusSide = ta.getDimension(R.styleable.CornerCutLayout_radiusSide, 0f)
            cutHeight = ta.getDimension(R.styleable.CornerCutLayout_cutHeight, 0f)
        }
    }

    override fun draw(canvas: Canvas?) {
        rectTopStart = RectF(
            -radiusTopStart,
            -radiusTopStart,
            radiusTopStart,
            radiusTopStart
        )

        rectTopEnd = RectF(
            width - radiusTopEnd,
            -radiusTopEnd,
            width + radiusTopEnd,
            radiusTopEnd
        )

        rectBottomStart = RectF(
            -radiusBottomStart,
            height.toFloat() - radiusBottomStart,
            radiusBottomStart,
            height.toFloat() + radiusBottomStart
        )

        rectBottomEnd = RectF(
            width.toFloat() - radiusBottomEnd,
            height.toFloat() - radiusBottomEnd,
            width + radiusBottomEnd,
            height.toFloat() + radiusBottomEnd
        )

        val path = Path()

        path.moveTo(radiusTopStart, 0f)

        if (radiusTopStart != 0f) {
            path.arcTo(
                rectTopStart,
                0f,
                90f
            )
        }

        if (cutHeight != 0f && radiusSide != 0f) {
            path.lineTo(0f, height.toFloat() - (cutHeight + radiusSide))

            val rightRect = RectF(
                -radiusSide,
                height.toFloat() - (cutHeight + radiusSide),
                radiusSide,
                height.toFloat() - (cutHeight - radiusSide)
            )

            path.arcTo(
                rightRect,
                -90f,
                180f
            )
        }


        path.lineTo(0f, height.toFloat() - radiusBottomStart)

        if (radiusBottomStart != 0f) {
            path.arcTo(
                rectBottomStart,
                -90f,
                90f
            )
        }

        path.lineTo(width.toFloat() - radiusBottomEnd, height.toFloat())

        if (radiusBottomEnd != 0f) {
            path.arcTo(
                rectBottomEnd,
                180f,
                90f
            )
        }

        if (cutHeight != 0f && radiusSide != 0f) {
            path.lineTo(width.toFloat(), height.toFloat() - (cutHeight - radiusSide))

            val leftRect = RectF(
                width.toFloat() - radiusSide,
                height.toFloat() - (cutHeight + radiusSide),
                width.toFloat() + radiusSide,
                height.toFloat() - (cutHeight - radiusSide)
            )

            path.arcTo(
                leftRect,
                90f,
                180f
            )
        }

        path.lineTo(width.toFloat(), radiusTopEnd)

        if (radiusTopEnd != 0f) {
            path.arcTo(
                rectTopEnd,
                90f,
                90f
            )
        }

        path.lineTo(radiusTopStart, 0f)

        canvas?.clipPath(path)
        super.draw(canvas)
    }
}