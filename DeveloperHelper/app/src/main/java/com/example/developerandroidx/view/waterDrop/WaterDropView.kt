package com.example.developerandroidx.view.waterDrop

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.developerandroidx.utils.AnimUtil
import com.example.developerandroidx.utils.PixelTransformForAppUtil

/**
 * 作者： zjf 7/25/20 12:36 PM
 * 参考：
 * 描述：
 */
class WaterDropView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var viewWidth: Int = PixelTransformForAppUtil.getDiaplayWidth()
    private var viewHeight: Int = PixelTransformForAppUtil.getDiaplayHeight()
    private var paint: Paint
    private lateinit var path: Path

    //随手指移动的偏移
    private var offSetX = 0f
    private var offSetY = 0f

    //松开手指动画进度,默认是1
    private var animProgress = 1f

    val mCircleRadius = 200f //圆半径

    //画布的中心,作为初始的圆心
    val originX = viewWidth / 2f
    val originY = viewHeight / 2f

    //触摸事件按下位置
    private var downX = 0f
    private var downY = 0f

    //是否在执行动画
    private var isAnimating = false

    private val circlePoints = arrayOfNulls<Point>(4)//顺时针记录绘制圆形的四个数据点,从底部正中的点开始

    private val ctrlPoints = arrayOfNulls<Point>(8)//顺时针记录绘制圆形贝塞尔曲线的控制点

    private val C = 0.552284749831f // 用来计算绘制圆形贝塞尔曲线控制点的位置的常数

    //属性动画,松开手指执行
    private lateinit var animator: ValueAnimator

    private class Point constructor(x: Float, y: Float) {
        var x: Float = x
        var y: Float = y
    }

    init {
        paint = Paint()
        paint.strokeCap = Paint.Cap.ROUND
        //控制空心还是实心
//        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true

        setPoints()
    }

    /**
     * 设置点
     */
    private fun setPoints() {
        path = Path()

//        circlePoints[0] = Point(originX + offSetX * animProgress, originY + mCircleRadius + offSetY * animProgress)
        //控制底部一个点不随手指滑动而移动
        circlePoints[0] = Point(originX, originY + mCircleRadius)

        circlePoints[1] = Point(originX + mCircleRadius + offSetX * animProgress, originY + offSetY * animProgress)
        circlePoints[2] = Point(originX + offSetX * animProgress, originY - mCircleRadius + offSetY * animProgress)
        circlePoints[3] = Point(originX - mCircleRadius + offSetX * animProgress, originY + offSetY * animProgress)

        // 初始化控制点
        val mDifference = mCircleRadius * C //圆形的控制点与数据点的差值
        ctrlPoints[0] = Point(circlePoints[0]!!.x + mDifference, circlePoints[0]!!.y)
        ctrlPoints[1] = Point(circlePoints[1]!!.x, circlePoints[1]!!.y + mDifference)
        ctrlPoints[2] = Point(circlePoints[1]!!.x, circlePoints[1]!!.y - mDifference)
        ctrlPoints[3] = Point(circlePoints[2]!!.x + mDifference, circlePoints[2]!!.y)
        ctrlPoints[4] = Point(circlePoints[2]!!.x - mDifference, circlePoints[2]!!.y)
        ctrlPoints[5] = Point(circlePoints[3]!!.x, circlePoints[3]!!.y - mDifference)
        ctrlPoints[6] = Point(circlePoints[3]!!.x, circlePoints[3]!!.y + mDifference)
        ctrlPoints[7] = Point(circlePoints[0]!!.x - mDifference, circlePoints[0]!!.y)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制贝塞尔曲线
        paint.color = Color.RED
        paint.strokeWidth = 6f
        //移动到起始点
        path.moveTo(circlePoints[0]!!.x, circlePoints[0]!!.y)
        //画四条三阶贝塞尔曲线轨迹,连接圆的四个点
        path.cubicTo(ctrlPoints[0]!!.x, ctrlPoints[0]!!.y, ctrlPoints[1]!!.x, ctrlPoints[1]!!.y, circlePoints[1]!!.x, circlePoints[1]!!.y)
        path.cubicTo(ctrlPoints[2]!!.x, ctrlPoints[2]!!.y, ctrlPoints[3]!!.x, ctrlPoints[3]!!.y, circlePoints[2]!!.x, circlePoints[2]!!.y)
        path.cubicTo(ctrlPoints[4]!!.x, ctrlPoints[4]!!.y, ctrlPoints[5]!!.x, ctrlPoints[5]!!.y, circlePoints[3]!!.x, circlePoints[3]!!.y)
        path.cubicTo(ctrlPoints[6]!!.x, ctrlPoints[6]!!.y, ctrlPoints[7]!!.x, ctrlPoints[7]!!.y, circlePoints[0]!!.x, circlePoints[0]!!.y)

        canvas.drawPath(path, paint)

        drawAuxiliaryLine(canvas)
    }

    // 绘制辅助线
    private fun drawAuxiliaryLine(canvas: Canvas) {
        // 绘制数据点和控制点
        paint.setColor(Color.GRAY)
        for (i in 0 until 8) {
            paint.strokeWidth = 15f

            canvas.drawPoint(ctrlPoints[i]!!.x, ctrlPoints[i]!!.y, paint)
            if (i % 2 == 0) {
                canvas.drawPoint(circlePoints[i / 2]!!.x, circlePoints[i / 2]!!.y, paint)
                paint.strokeWidth = 6f
                canvas.drawLine(circlePoints[i / 2]!!.x, circlePoints[i / 2]!!.y, ctrlPoints[i]!!.x, ctrlPoints[i]!!.y, paint)
                if (i == 0) {
                    canvas.drawLine(circlePoints[i / 2]!!.x, circlePoints[i / 2]!!.y, ctrlPoints[7]!!.x, ctrlPoints[7]!!.y, paint)
                } else {
                    canvas.drawLine(circlePoints[i / 2]!!.x, circlePoints[i / 2]!!.y, ctrlPoints[i - 1]!!.x, ctrlPoints[i - 1]!!.y, paint)
                }
            }

        }
    }

    /**
     * 触摸事件
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //如果在执行动画,什么都不处理,返回false
        if (isAnimating) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                if (event.x < viewWidth / 2f - mCircleRadius || event.x > viewWidth / 2f + mCircleRadius ||
                        event.y < viewHeight / 2f - mCircleRadius || event.y > viewHeight / 2f + mCircleRadius) {
                    return false
                }
            }
            MotionEvent.ACTION_MOVE -> {
                offSetX = event.x - downX
                offSetY = event.y - downY

                setPoints()
//                LogUtils.e("位移", "offSetX:$offSetX--offSetY:$offSetY")
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
//                offSetX = 0f
//                offSetY = 0f
//                setPoints()
//                invalidate()
                startAnimator()
            }

        }
        return true
    }

    private fun startAnimator() {
        isAnimating = true

        animator = ValueAnimator.ofFloat(1f, 0f)
        animator.duration = 1000
        animator.interpolator = AnimUtil.getInstance().getSpringInterpolator(0.3f)
        animator.addUpdateListener {
            animProgress = it.getAnimatedValue() as Float
            setPoints()
            invalidate()
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                isAnimating = false

                animator.removeAllUpdateListeners()
                offSetX = 0f
                offSetY = 0f
                animProgress = 1f
                setPoints()
                invalidate()
            }
        })

        animator.start()
    }
}