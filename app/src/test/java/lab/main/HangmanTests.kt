package lab.main

import lab.main.hangman.Hangman
import org.junit.Assert.*
import org.junit.Test

class HangmanTests {
	@Test
	fun test1() {
		val hangman = Hangman("test")
		assertEquals(0, hangman.currentTry)
		assertEquals(hangman.MAX_MISTAKES, hangman.possibleMistakesRemaining)
		assertEquals(listOf<Char>(), hangman.usedLetters)
		assertEquals("____", hangman.encryptedWordToGuess)
	}

	@Test
	fun test2() {
		val hangman = Hangman("impeccable")
		assertEquals(0, hangman.currentTry)
		assertEquals(hangman.MAX_MISTAKES, hangman.possibleMistakesRemaining)
		assertEquals(listOf<Char>(), hangman.usedLetters)
		assertEquals("__________", hangman.encryptedWordToGuess)
	}

	@Test
	fun test3() {
		val hangman = Hangman("test")
		assertFalse(hangman.guess('t'))
		assertEquals(1, hangman.currentTry)
		assertEquals(hangman.MAX_MISTAKES, hangman.possibleMistakesRemaining)
		assertEquals(listOf('t'), hangman.usedLetters)
		assertEquals("t__t", hangman.encryptedWordToGuess)
	}

	@Test
	fun test4() {
		val hangman = Hangman("impeccable")
		assertFalse(hangman.guess('a'))
		assertEquals(1, hangman.currentTry)
		assertEquals(hangman.MAX_MISTAKES, hangman.possibleMistakesRemaining)
		assertEquals(listOf('a'), hangman.usedLetters)
		assertEquals("______a___", hangman.encryptedWordToGuess)
	}

	@Test
	fun test5() {
		val hangman = Hangman("impeccable")
		assertFalse(hangman.guess('a'))
		assertFalse(hangman.guess('a'))
		assertFalse(hangman.guess('a'))
		assertEquals(1, hangman.currentTry)
		assertEquals(hangman.MAX_MISTAKES, hangman.possibleMistakesRemaining)
		assertEquals(listOf('a'), hangman.usedLetters)
		assertEquals("______a___", hangman.encryptedWordToGuess)
	}

	@Test
	fun test6() {
		val hangman = Hangman("impeccable")
		assertFalse(hangman.guess('a'))
		assertFalse(hangman.guess('t'))
		assertFalse(hangman.guess('e'))
		assertEquals(3, hangman.currentTry)
		assertEquals(hangman.MAX_MISTAKES - 1, hangman.possibleMistakesRemaining)
		assertEquals(listOf('a', 't', 'e'), hangman.usedLetters)
		assertEquals("___e__a__e", hangman.encryptedWordToGuess)
	}

	@Test
	fun testLosing1() {
		val hangman = Hangman("test")
		assertFalse(hangman.guess('a'))
		assertFalse(hangman.guess('b'))
		assertFalse(hangman.guess('c'))
		assertFalse(hangman.guess('d'))
		assertFalse(hangman.guess('f'))
		assertFalse(hangman.guess('g'))
		assertFalse(hangman.guess('h'))
		assertFalse(hangman.guess('i'))
		assertFalse(hangman.guess('j'))
		assertEquals(9, hangman.currentTry)
		assertEquals(0, hangman.possibleMistakesRemaining)
		assertEquals(
			listOf('a', 'b', 'c', 'd', 'f', 'g', 'h', 'i', 'j'),
			hangman.usedLetters
		)
		assertEquals("test", hangman.encryptedWordToGuess)
	}

	@Test
	fun testWinning1() {
		val hangman = Hangman("test")
		assertFalse(hangman.guess('a'))
		assertFalse(hangman.guess('b'))
		assertFalse(hangman.guess('t'))
		assertFalse(hangman.guess('c'))
		assertFalse(hangman.guess('e'))
		assertTrue(hangman.guess('s'))
		assertEquals(6, hangman.currentTry)
		assertEquals(0, hangman.possibleMistakesRemaining)
		assertEquals(
			listOf('a', 'b', 't', 'c', 'e', 's'),
			hangman.usedLetters
		)
		assertEquals("test", hangman.encryptedWordToGuess)
	}
}