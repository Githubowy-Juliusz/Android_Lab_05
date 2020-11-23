package lab.main.lines

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import kotlin.random.Random

class LineDrafter(
	private val width: Int, private val height: Int, imageView: ImageView
) {
	private val bitmap =
		Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
	private val canvas = Canvas(bitmap)
	private val divisor = Random.nextInt(5, 25)

	init {
		imageView.background = BitmapDrawable(bitmap)
	}

	private fun drawLine(begin: PointF, end: PointF, paint: Paint) {
		paint.strokeWidth = 2f
		canvas.drawLine(begin.x, begin.y, end.x, end.y, paint);
	}

	private fun drawMiddle(heightPart: Float) {
		val paint = Paint()
		paint.color = Color.BLACK
		val colorOffset = 256 / divisor
		for(i in 1..divisor) {
			val begin = PointF(0f, i * heightPart)
			val end = PointF(width.toFloat(), (divisor - i) * heightPart)
			paint.color += colorOffset
			drawLine(begin, end, paint)
		}
	}

	private fun drawLeftSide(heightPart: Float, widthPart: Float) {
		val paint = Paint()
		paint.color = Color.BLACK
		val colorOffset = (256 / divisor) shl 8
		for(i in 0 until divisor) {
			val begin = PointF(0f, i * heightPart)
			val end = PointF((i + 1) * widthPart, height.toFloat())
			paint.color += colorOffset
			drawLine(begin, end, paint)
		}
	}

	private fun drawRightSide(heightPart: Float, widthPart: Float) {
		val paint = Paint()
		paint.color = Color.BLACK
		val colorOffset = (256 / divisor) shl 16
		for(i in 0 until divisor) {
			val begin = PointF(i * widthPart, 0f)
			val end = PointF(width.toFloat(), (i + 1) * heightPart)
			paint.color += colorOffset
			drawLine(begin, end, paint)
		}
	}

	fun draw() {
		val paint = Paint()
		paint.color = Color.WHITE
		canvas.drawPaint(paint)

		val heightPart = (height / divisor).toFloat()
		val widthPart = (width / divisor).toFloat()

		drawMiddle(heightPart)
		drawLeftSide(heightPart, widthPart)
		drawRightSide(heightPart, widthPart)
	}
}