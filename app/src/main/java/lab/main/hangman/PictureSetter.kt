package lab.main.hangman

import android.widget.ImageView
import lab.main.R

class PictureSetter(private val imageView: ImageView) {
	private val dict = mapOf(
		1 to R.drawable.hangman1,
		2 to R.drawable.hangman2,
		3 to R.drawable.hangman3,
		4 to R.drawable.hangman4,
		5 to R.drawable.hangman5,
		6 to R.drawable.hangman6,
		7 to R.drawable.hangman7,
		8 to R.drawable.hangman8,
		9 to R.drawable.hangman9,
		10 to R.drawable.hangman10
	)

	fun set(value: Int) {
		dict[value]?.let { imageView.setImageResource(it) }
	}
}