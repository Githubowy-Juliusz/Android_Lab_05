package lab.main.hangman

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import lab.main.R

class HangmanFragment :
	Fragment(R.layout.hangman_fragment) {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val tryNumberText =
			view.findViewById<TextView>(R.id.hangmanTryNumberTextView)
		val wordToGuessText =
			view.findViewById<TextView>(R.id.hangmanWordToGuessTextView)
		val usedLettersText =
			view.findViewById<TextView>(R.id.hangmanUsedLettersTextView)
		val remainingTriesText =
			view.findViewById<TextView>(R.id.hangmanRemainingTriesTextView)
		val letterInput = view.findViewById<EditText>(R.id.hangmanLetterEditText)
		val confirmButton = view.findViewById<Button>(R.id.hangmanConfirmButton)
		val imageView = view.findViewById<ImageView>(R.id.hangmanImageView)

		val dictionary =
			listOf("apple", "noun", "ice", "pear", "pineapple", "hammer", "water")

		var hangman = Hangman(dictionary.random())
		val pictureSetter = PictureSetter(imageView)

		fun updateFields() {
			letterInput.text.clear()
			tryNumberText.text = "Tries: " + hangman.currentTry.toString()
			wordToGuessText.text = "Word to guess: " + hangman.encryptedWordToGuess
			usedLettersText.text =
				"Used letters: " + hangman.usedLetters.joinToString(separator = ", ")
			remainingTriesText.text =
				"Remaining mistakes: " + hangman.possibleMistakesRemaining.toString()
			pictureSetter.set(hangman.mistakes + 1)
		}

		updateFields()

		confirmButton.setOnClickListener() {
			if(hangman.possibleMistakesRemaining == 0) {
				hangman = Hangman(dictionary.random())
				confirmButton.text = "Confirm"
				updateFields()
				return@setOnClickListener
			}
			val character: Char
			try {
				character = letterInput.text[0]
			} catch(e: IndexOutOfBoundsException) {
				return@setOnClickListener
			}
			val win = hangman.guess(character)
			updateFields()
			if(win) {
				confirmButton.text = "Try again"
				tryNumberText.text = "You won!"
			} else if(hangman.possibleMistakesRemaining == 0) {
				tryNumberText.text = "You lost."
				confirmButton.text = "Try again"
			}
		}
	}
}