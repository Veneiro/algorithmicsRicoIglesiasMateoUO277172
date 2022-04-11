package algstudent.passwords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class PasswordGenerator {
	private int numberOfTotalCharacters; // size of each of the passwords
	private int numberOfNonLettersEnds; // size of the punctuation marks or
										// numbers the appears at the end of the
										// password
	private int numberOfPasswords; // number of desired passwords

	private String consonantPairsPath; // path of the file with consonant pairs
										// that are allowed
	private List<String> consonantPairs; // consonant pairs once they are read
											// from the text file

	private char[] password; // to save each of the passwords
	private List<String> passwords; // to save all the passwords
	private boolean found; // to indicate a solution is found

	private List<Character> lettersList = Arrays.asList('a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	private List<Character> nonLettersList = Arrays.asList('.', ',', '-', '_',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

	public PasswordGenerator(int numberOfTotalCharacters,
			int numberOfNonLettersEnds, int numberOfPasswords,
			String consonantPairsPath) {
		this.numberOfTotalCharacters = numberOfTotalCharacters;
		this.numberOfNonLettersEnds = numberOfNonLettersEnds;
		this.numberOfPasswords = numberOfPasswords;
		this.consonantPairsPath = consonantPairsPath;

		password = new char[numberOfTotalCharacters];
		passwords = new ArrayList<String>();
		readFromFile(this.consonantPairsPath);
	}

	public void generate() {
		found = false;
		for (int i = 0; i < numberOfPasswords; i++) {
			backtracking(0);
			found = false;
		}
	}

	public List<String> getPasswords() {
		return passwords;
	}

	// SOLUTION WITH BACKTRACKING
	private void backtracking(int level) {

		if (level == numberOfTotalCharacters) {
			passwords.add(String.valueOf(password));
			found = true;
		} else {
			Character[] shuffle;
			// Non letter
			if (level >= numberOfTotalCharacters - numberOfNonLettersEnds) {
				Collections.shuffle(nonLettersList); // O(n)
				shuffle = nonLettersList
						.toArray(new Character[nonLettersList.size()]);

			}

			// Letter
			else {
				Collections.shuffle(lettersList);
				shuffle = lettersList
						.toArray(new Character[lettersList.size()]);

			}

			for (int i = 0; i < shuffle.length; i++) {
				if (!found) {
					char character = shuffle[i];
					if (isValid(level, character)) {
						password[level] = character;
						backtracking(level + 1);
						password[level] = '?';
					}
				}
			}
		}
	}

	/**
	 * Returns true if a character is valid and false if it is not
	 * 
	 * @param level
	 * @param letter
	 * @return
	 */
	private boolean isValid(int level, char letter) {
		// Only two consecutive vowels if they are different
		if (level > 0 && isVowel(letter) && password[level - 1] == (letter)) {
			return false;
		}

		// Only some consonant pairs are allowed
		if (level > 0 && isConsonant(letter)
				&& isConsonant(password[level - 1])) {
			String aux = String
					.valueOf(password[level - 1] + String.valueOf(letter));
			if (!consonantPairs.contains(aux)) {
				return false;
			}
		}

		// Not three consecutive vowels or consonants allowed
		if (level > 1) {
			if (isVowel(letter) && isVowel(password[level - 1])
					&& isVowel(password[level - 2])) {
				return false;
			}
			if (isConsonant(letter) && isConsonant(password[level - 1])
					&& isConsonant(password[level - 2])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return true if the letter is a vowel
	 * 
	 * @param letter
	 * @return
	 */
	private boolean isVowel(char letter) {
		return "aeiou".indexOf(letter) != -1;
	}

	/**
	 * Return true if the letter is a consonant
	 * 
	 * @param letter
	 * @return
	 */
	private boolean isConsonant(char letter) {
		return "qwrtyupsdfghjkl�zxcvbnm".indexOf(letter) != -1;
	}

	private void readFromFile(String fileName) {
		consonantPairs = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while (reader.ready()) {
				consonantPairs.add(reader.readLine());
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
