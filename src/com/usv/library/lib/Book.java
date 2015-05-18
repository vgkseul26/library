package com.usv.library.lib;

import java.io.Serializable;

import javax.xml.bind.annotation.*;

/**
 * Represents a book.
 * 
 * @author vgkseul26
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7462401398179612693L;
	/**
	 * Author of the book
	 */
	@XmlElement(required = true)
	private String author;
	/**
	 * Title of the book
	 */
	@XmlElement(required = true)
	private String title;
	/**
	 * ISBN of the book
	 */
	@XmlElement(required = true)
	private int isbn;

	public Book(String author, String title, int isbn) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
	}

	public Book() {

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
		return author + " " + title + " " + isbn;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if ((author.equals(other.author)) && (title.equals(other.title)))
			return true;
		else if (isbn == other.isbn)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return isbn;
	}
}
