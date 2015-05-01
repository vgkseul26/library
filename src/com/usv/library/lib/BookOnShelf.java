package com.usv.library.lib;

import java.io.Serializable;

/**
 * Represents a book in the library.
 * 
 * @author vgkseul26
 */
public class BookOnShelf implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8005726542872967278L;
	/**
	 * Book's number in library
	 */
	private int numOnShelf;
	/**
	 * Is this book free or no?!
	 */
	private boolean issued;
	/**
	 * Book's ISBN
	 */
	private final int isbn;
	/**
	 *Book
	 */
	private final Book book;

	public BookOnShelf(int numOnShelf, Book book) {
		this.numOnShelf = numOnShelf;
		this.issued = false;
		this.book = book;
		this.isbn = book.getIsbn();
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
