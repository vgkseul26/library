package com.usv.library.lib;

import java.io.Serializable;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7462401398179612693L;
	private final String author;
	private final String title;
	private final int ISBN;
	
	public Book(String author, String title, int ISBN){
		this.author = author;
		this.title = title;
		this.ISBN = ISBN;
	}

	public String getauthor() {
		return author;
	}

	public String gettitle() {
		return title;
	}

	public int getISBN() {
		return ISBN;
	}

	@Override
	public String toString() {
		return author + "/" + title + "/" + ISBN;
	}
	
}
