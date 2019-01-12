package model;

import java.io.Serializable;

public class BabyLink implements Serializable {
	private BabyLink next;
	private String word;

	public BabyLink(String word) {
		this.word = word;
		next = null;
	}

	public BabyLink getNext() {
		return next;
	}

	public void setNext(BabyLink next) {
		this.next = next;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return word + " ";
	}

	public void display() {
		System.out.print(word + " ");
	}

}
