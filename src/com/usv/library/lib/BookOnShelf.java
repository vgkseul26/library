package com.usv.library.lib;

import java.io.Serializable;

public class BookOnShelf implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8005726542872967278L;
	private int numOnShelf;
	private boolean issued;
	private final int ISBN;
	private final Book book;

	public BookOnShelf(int numOnShelf, Book book) {
		this.numOnShelf = numOnShelf;
		this.issued = false;
		this.book = book;
		this.ISBN = book.getISBN();
	}

	public int getNumOnShelf() {
		return numOnShelf;
	}

	public void setNumOnShelf(int numOnShelf) {
		this.numOnShelf = numOnShelf;
	}

	public boolean getIssued() {
		return issued;
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
	}

	public Book getBook() {
		return book;
	}

	@Override
	public String toString() {
		return issued + "/" + book;
	}

}
