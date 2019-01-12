package model;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {

		LinkList myList = new LinkList();
		TimeCalculations tcalcs = new TimeCalculations();

		// check(word);
		myList.loadDictionary();
		boolean isThere = myList.getDictionary().containsKey("sdf");
		System.out.println(isThere);
		// System.out.println(myList.convertDocumentToString("InputData/Dictionary.txt"));
		// myList.saveDictionary();
		// myList.loadDictionary();
		/*
		 * myList.loadDictionary();
		 * System.out.println(myList.getDictionary().get("AOL's"));
		 * System.out.println(myList.getDictionary().containsKey("AOL's"));
		 * System.out.println(myList.getDictionary().size());
		 */
		// System.out.println(myList.getDictionary().containsKey("AOL's"));
		// System.out.println(myList.getDictionary().get("AOL"));
		/*
		 * String text =
		 * myList.convertDocumentToString("InputData/OneHundredThousandWords.txt");
		 * 
		 * String[] words = text.split(" "); for(int i =0;i<words.length;i++) {
		 * myList.calculateUsingThreeLoop(words[i]);
		 * myList.calculateUsingOneLoop(words[i]); System.out.println(
		 * "------------------------------------------------------------------");
		 * 
		 * }
		 */
		/*
		 * tcalcs.loadHundredThousandWordOneLoopArray();
		 * System.out.println("The One loop array size is "+tcalcs.
		 * getOneLoopHundredThousandWordArrayCounter());
		 * tcalcs.loadHundredThousandWordThreeLoopArray();
		 * System.out.println("the 3 loop array size is "+tcalcs.
		 * getThreeLoopHundredThousandWordArrayCounter());
		 * tcalcs.loadTenThousandWordOneLoopArray();
		 * System.out.println("the 1 loop array size is " +
		 * tcalcs.getOneLoopTenThousandWordArrayCounter());
		 */
		/*
		 * tcalcs.loadHundredWordOneLoopArray();
		 * tcalcs.displayHundredWordOneLoopTimes();
		 * System.out.println(tcalcs.getOneLoopCounterHundredWords());
		 * System.out.println(
		 * "----------------------------------------------------------------");
		 * tcalcs.loadHundredWordThreeLoopArray();
		 * tcalcs.displayHundredWordThreeLoopTimes();
		 * System.out.println(tcalcs.getThreeLoopHundredWordCounter());
		 */
		// myList.save("theFileIWaouhnt",text);
		// myList.save();
		// myList.readWords(text);
		// myList.displayRandomSentence("the", 125);
		// myList.calculateUsingOneLoop(text);
		// tcalcs.displayOneLoopTimes();
		//
		// String myFile = "Input/WarAndPeace.txt";
		//
		// System.out.println(myList.calculateNumberOfSentences(text));
		// System.out.println(myList.calculateSyllables(text));
		// System.out.println(myList.calculateWordCount(text));

		// System.out.println(text);
		// System.out.println(text);
		// myList.calculateWordCount(text);
		// myList.calculateNumberOfSentences(text);
		// myList.calculateSyllables(text);
		// System.out.println(myList.schwartzScore(text));
		// myList.getWords(fileName);
		// myList.readWords(text);
		// myList.diplay();
		// System.out.println();
		// myList.displayRandomSentence("the", 15);

	}

	public static void check(String word) {

		Scanner input = new Scanner((Readable) new BufferedReader(new StringReader(word)));
		input.useDelimiter("\\s+");
		while (input.hasNext()) {
			String next = input.next().replaceAll("[ ?\",.();:!]", " ");
			// next.replaceAll("[ ?\",.();:!]", "");
			System.out.println(next);
		}
		input.close();
	}

}
