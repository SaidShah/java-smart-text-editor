package model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkList implements Serializable {

	Link first;
	TimeCalculations timeCalcs = new TimeCalculations();
	Hashtable<String, String> dictionary = new Hashtable<String, String>();

	public LinkList() {
		first = null;

	}

	public Link getFirst() {
		return first;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void setFirst(Link first) {
		this.first = first;
	}

	public String convertDocumentToString(String fileName) {
		File file = new File(fileName);

		try (Scanner input = new Scanner(new BufferedReader(new FileReader(file)))) {
			StringBuilder sb = new StringBuilder();
			input.useDelimiter("\\z");
			while (input.hasNext()) {
				sb.append(input.next());
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void readWords(String text) {

		String previousWord = null;
		Scanner input = new Scanner(new BufferedReader(new StringReader(text)));
		input.useDelimiter("\\s+");
		while (input.hasNext()) {
			String next = input.next().replaceAll("[?\",.();:!]+", "");
			insert(next);
			if (previousWord != null) {
				Link newLink = findWord(previousWord);
				newLink.getMyList().insertBabyLink(next);
			}
			previousWord = next;

		}
		input.close();
	}

	public void saveDictionary() {
		File dataFile = new File("InputData/Dictionary.txt");

		try {
			Scanner input = new Scanner(new BufferedReader(new FileReader(dataFile)));
			while (input.hasNext()) {
				String word = input.next();
				dictionary.put(word, word);

			}
			input.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			FileOutputStream fos = new FileOutputStream("InputData/Dictionary.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dictionary);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void calculateUsingThreeLoop(String word) {
		long startTime = System.nanoTime();

		String syllablesToFind = "[aeiouAEIOU]";
		Pattern syllablePattern = Pattern.compile(syllablesToFind);
		Matcher syllableMatcher = syllablePattern.matcher(word);
		while (syllableMatcher.find()) {
			;
		}

		String sentenceToFind = "[.!?]+";
		Pattern sentencePattern = Pattern.compile(sentenceToFind);
		Matcher sentenceMatch = sentencePattern.matcher(word);
		while (sentenceMatch.find()) {
			;
		}

		String wordCountChecker = "['\\w]+";
		Pattern wordCountPattern = Pattern.compile(wordCountChecker);
		Matcher wordCountMatch = wordCountPattern.matcher(word);
		while (wordCountMatch.find()) {
			;
		}

		timeCalcs.insertThreeLoopHundredThousandWordTime(System.nanoTime() - startTime);
		startTime = System.nanoTime();

	}

	public void calculateUsingOneLoop(String word) {
		long startTime = System.nanoTime();

		String syllablesToFind = "[aeiouAEIOU]";
		Pattern syllablePattern = Pattern.compile(syllablesToFind);
		Matcher syllableMatcher = syllablePattern.matcher(word);

		String sentenceToFind = "[.!?]+";
		Pattern sentencePattern = Pattern.compile(sentenceToFind);
		Matcher sentenceMatch = sentencePattern.matcher(word);

		String wordCountChecker = "['\\w]+";
		Pattern wordCountPattern = Pattern.compile(wordCountChecker);
		Matcher wordCountMatch = wordCountPattern.matcher(word);

		while (wordCountMatch.find() || sentenceMatch.find() || syllableMatcher.find()) {
			;
		}
		timeCalcs.insertOneLoopHundredThousandWordTime(System.nanoTime() - startTime);
		startTime = System.nanoTime();
	}

	public int calculateSyllables(String word) {
		int syllables = 0;
		String patternToCheck = "[aeiouAEIOU]";
		Pattern pattern = Pattern.compile(patternToCheck);
		Matcher match = pattern.matcher(word);
		while (match.find()) {
			syllables++;
		}
		return syllables;
	}

	public int calculateNumberOfSentences(String word) {

		int numOfSentences = 0;
		String patternToCheck = "[.!?]+";
		Pattern pattern = Pattern.compile(patternToCheck);
		Matcher match = pattern.matcher(word);
		while (match.find()) {
			numOfSentences++;
		}
		return numOfSentences;
	}

	public int calculateWordCount(String word) {

		int numOfWords = 0;
		String patternToCheck = "['\\w]+";
		Pattern pattern = Pattern.compile(patternToCheck);
		Matcher match = pattern.matcher(word);
		while (match.find()) {
			numOfWords++;
		}
		return numOfWords;

	}

	public String[] randomSentence(String startingWord, int numOfWords) {

		String[] randomWords = new String[numOfWords];
		for (int i = 0; i < numOfWords; i++) {
			Link currentLink = findWord(startingWord);
			if (currentLink != null && !currentLink.getMyList().isEmpty()) {
				randomWords[i] = currentLink.getMyList().getRandomWord();
				startingWord = randomWords[i];
			} else {
				return null;
			}
		}
		return randomWords;
	}

	@SuppressWarnings("unchecked")
	public void loadDictionary() {
		try {
			FileInputStream fis = new FileInputStream("InputData/Dictionary.dat");
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
			dictionary = (Hashtable<String, String>) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Hashtable<String, String> getDictionary() {
		return dictionary;
	}

	public void displayRandomSentence(String startingWord, int numOfWords) {
		String[] randomSentence = randomSentence(startingWord, numOfWords);
		System.out.print("The Starting Word Is " + startingWord + " ");
		System.out.println();
		for (int i = 0; i < randomSentence.length; i++) {
			System.out.print(randomSentence[i] + " ");
		}
		System.out.println();
	}

	public Link findWord(String currentWord) {
		if (currentWord == null) {
			return null;
		}
		Link current = first;
		while (current != null) {
			if (!current.getWord().equalsIgnoreCase(currentWord)) {
				current = current.getNext();
			} else {
				break;
			}
		}
		return current;
	}

	public void insert(String word) {

		if (isEmpty()) {
			Link newLink = new Link(word);
			first = newLink;

		} else {
			Link newLink = findWord(word);
			if (newLink == null) {
				Link brandNewLink = new Link(word);
				brandNewLink.setNext(first);
				first = brandNewLink;
			}
		}

	}

	public void diplay() {
		Link current = first;
		System.out.print("the word is ");
		while (current != null) {
			current.getMyList().display();
			current = current.getNext();
		}
		System.out.println(" ");
	}

	public void save(String newFileName, String inputText) {

		try {

			File newFile = new File("InputData/" + newFileName);
			FileWriter fw = new FileWriter(newFile, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(inputText);
			bw.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	public int fleschScore(String word) {
		double score;
		int sentenceCount = calculateNumberOfSentences(word);
		if (sentenceCount == 0) {
			return -1;
		} else {
			int wordCount = calculateWordCount(word);
			int syllables = calculateSyllables(word);
			double sentenceLength = wordCount / sentenceCount;
			double averageSyllablesPerWord = syllables / wordCount;
			score = 206.835 - (1.015 * sentenceLength) - (84.6 * averageSyllablesPerWord);
			return (int) score;
		}
	}

}
