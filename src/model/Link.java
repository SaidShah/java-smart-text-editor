package model;

import java.io.Serializable;

public class Link implements Serializable {
	private Link next;
	private String word;
	private BabyLinkList myList = new BabyLinkList();

	public Link(String word) {
		this.word = word;
		next = null;
	}

	public Link getNext() {
		return next;
	}

	public void setNext(Link next) {
		this.next = next;
	}

	public BabyLinkList getMyList() {
		return myList;
	}

	public void setMyList(BabyLinkList myList) {
		this.myList = myList;
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
