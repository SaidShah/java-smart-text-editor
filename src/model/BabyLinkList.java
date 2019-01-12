package model;

import java.io.Serializable;

public class BabyLinkList implements Serializable {

	private BabyLink first;
	int counter;

	public BabyLinkList() {
		first = null;
		counter = 0;
	}

	public BabyLink getFirst() {
		return first;
	}

	public void setFirst(BabyLink first) {
		this.first = first;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void insertBabyLink(String newWord) {

		BabyLink newLink = new BabyLink(newWord);
		if (!isEmpty()) {
			newLink.setNext(first);
		}
		first = newLink;
		counter++;
	}

	public BabyLink findBabyLink(String word) {
		BabyLink current = first;
		while (current != null) {
			if (!current.getWord().equals(word)) {
				current = current.getNext();
			} else {
				break;
			}
		}
		return current;
	}

	public String getRandomWord() {
		BabyLink current = first;
		int randomNumber = (int) (Math.random() * counter);
		for (int i = 0; i < randomNumber; i++) {
			current = current.getNext();
		}
		return current.getWord();
	}

	public void display() {
		BabyLink current = first;

		while (current != null) {
			System.out.print(current.getWord() + " ");
			current = current.getNext();
		}
		System.out.println();
		System.out.println("Counter is " + counter);
		System.out.println();
	}

	public String[] getWordsFromBabyLink() {
		int wordCounter = 0;
		String babyLinkWords[] = new String[counter];
		BabyLink current = first;
		while (current != null) {
			babyLinkWords[wordCounter++] = current.getWord();
			// System.out.println(wordCounter+"---------");
			current = current.getNext();
		}
		return babyLinkWords;
	}

}
