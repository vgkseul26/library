package com.usv.library.lib;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents a book in the library.
 * 
 * @author vgkseul26
 */

@XmlRootElement(name = "BookOnShelf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "numOnShelf", "book", "issued" })
public class BookOnShelf implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -800572654L;

	/**
	 * Book's number in library
	 */
	@XmlElement(name = "NumberOnShelf", required = true)
	private int numOnShelf;

	/**
	 * Is this book free or no?!
	 */
	@XmlElement(name = "issued", required = true)
	private boolean issued;

	/**
	 * Book
	 */
	@XmlElement(name = "book", required = true)
	private Book book;

	public BookOnShelf(int numOnShelf, Book book) {
		this.numOnShelf = numOnShelf;
		this.issued = false;
		this.book = book;
		// this.isbn = book.getIsbn();
	}

	public BookOnShelf() {

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
		return numOnShelf + " " + book + " " + issued;
	}

	@Override
	public int hashCode() {
		return numOnShelf;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookOnShelf other = (BookOnShelf) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (issued != other.issued)
			return false;
		if (numOnShelf != other.numOnShelf)
			return false;
		return true;
	}
}
