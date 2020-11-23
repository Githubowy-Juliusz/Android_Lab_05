package lab.main.hangman

import java.util.*

class Hangman(private val wordToGuess: String) {
	val MAX_MISTAKES = 9
	private val _usedLetters = mutableListOf<Char>()
	private var _mistakes = 0
	var mistakes: Int
		get() {
			return _mistakes
		}
		private set(value: Int) {
			_mistakes = value
		}

	var currentTry = 0
		private set

	val usedLetters: List<Char>
		get() {
			return Collections.unmodifiableList(_usedLetters)
		}

	val possibleMistakesRemaining: Int
		get() {
			return MAX_MISTAKES - mistakes
		}

	val encryptedWordToGuess: String
		get() {
			if(possibleMistakesRemaining == 0)
				return wordToGuess
			val tmpList = mutableListOf<Char>()
			for(c in wordToGuess) {
				if(c in _usedLetters)
					tmpList.add(c)
				else
					tmpList.add('_')
			}
			return tmpList.joinToString(separator = "")
		}

	fun guess(letter: Char): Boolean {
		if(letter in _usedLetters)
			return false
		if(possibleMistakesRemaining == 0)
			return false

		currentTry++
		_usedLetters.add(letter)
		countAndSetMistakes()

		for(c in wordToGuess) {
			if(c !in _usedLetters)
				return false
		}
		mistakes = MAX_MISTAKES
		return true
	}

	private fun countAndSetMistakes() {
		var tmpMistakes = 0
		for(c in _usedLetters)
			if(c !in wordToGuess)
				tmpMistakes++
		mistakes = tmpMistakes
	}

}