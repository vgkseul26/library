package com.usv.library.lib;

import java.io.Serializable;

/**
 * Represents a book.
 * 
 * @author vgkseul26
 */
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7462401398179612693L;
	/**
	 * Author of the book
	 */
	private final String author;
	/**
	 * Title  of the book
	 */
	private final String title;
	/**
	 * ISBN of the book
	 */
	private final int isbn;
	
	public Book(String author, String title, int isbn){
		this.author = author;
		this.title = title;
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public int getIsbn() {
		return isbn;
	}

	@Override
	public String toString() {
		return author + "/" + title + "/" + isbn;
	}
	
}
