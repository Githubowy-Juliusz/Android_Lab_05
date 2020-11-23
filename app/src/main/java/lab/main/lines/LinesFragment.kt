package lab.main.lines

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import lab.main.R

class LinesFragment :
	Fragment(R.layout.lines_fragment) {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val imageView = view.findViewById<ImageView>(R.id.linesImageView)
		val button = view.findViewById<Button>(R.id.linesButton)

		button.setOnClickListener {
			print("click")
		}
	}
}