package lab.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import lab.main.hangman.HangmanFragment
import lab.main.lines.LinesFragment

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val linesFragment = LinesFragment()
		val hangmanFragment = HangmanFragment()
		val buttonToLines = findViewById<Button>(R.id.mainToLinesButton)
		val buttonToHangman = findViewById<Button>(R.id.mainToHangmanButton)

		supportFragmentManager.beginTransaction().apply {
			replace(R.id.frameLayout, linesFragment)
			commit()
		}
		buttonToLines.visibility = View.GONE

		buttonToLines.setOnClickListener {
			buttonToLines.visibility = View.GONE
			buttonToHangman.visibility = View.VISIBLE
			supportFragmentManager.beginTransaction().apply {
				replace(R.id.frameLayout, linesFragment)
				commit()
			}
		}
		buttonToHangman.setOnClickListener {
			buttonToLines.visibility = View.VISIBLE
			buttonToHangman.visibility = View.GONE
			supportFragmentManager.beginTransaction().apply {
				replace(R.id.frameLayout, hangmanFragment)
				commit()
			}
		}
	}
}