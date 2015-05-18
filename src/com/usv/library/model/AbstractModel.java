package com.usv.library.model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.usv.library.lib.Book;
import com.usv.library.lib.BookOnShelf;

public abstract class AbstractModel implements Model {
	@XmlElement(name = "BookOnShelf")
	@XmlElementWrapper
	protected Map<Integer, BookOnShelf> shelfBooks;

	@Override
	public Map<Integer, BookOnShelf> getShelfBooks() {
		return shelfBooks;
	}

	@Override
	public Book createBook(String author, String title, int ISBN) {
		return new Book(author, title, ISBN);
	}

	@Override
	public BookOnShelf createBookOnShelf(int numOnShelf, Book book) {
		return new BookOnShelf(numOnShelf, book);
	}

	@Override
	public boolean addBook(int numOnShelf, BookOnShelf shelfBook) {
		for (Entry<Integer, BookOnShelf> e : getShelfBooks().entrySet()) {
			if ((e.getKey() == numOnShelf) || (e.getValue().getBook().equals(shelfBook.getBook()))) {
				return false;
			}
		}
		getShelfBooks().put(numOnShelf, shelfBook);
		return true;
	}

	@Override
	public boolean delBook(int numOnShelf) {
		if (getShelfBooks().containsKey(numOnShelf)) {
			getShelfBooks().remove(numOnShelf);
			return true;
		}
		return false;
	}

	@Override
	public BookOnShelf getBook(int numOnShelf) {
		return getShelfBooks().get(numOnShelf);
	}

	public abstract Map<Integer, BookOnShelf> readAll(String fileName) throws IOException, ClassNotFoundException;

	@Override
	public boolean setBook(int numOnShelf, BookOnShelf book) {
		getShelfBooks().remove(numOnShelf);
		getShelfBooks().put(numOnShelf, book);
		return true;
	}

	@Override
	public BookOnShelf viewBook(int numOnShelf) throws NullPointerException {
		BookOnShelf book = getShelfBooks().get(numOnShelf);
		return book;
	}

	@Override
	public Set<Map.Entry<Integer, BookOnShelf>> viewAll() {
		return getShelfBooks().entrySet();
	}

	@Override
	public Set<Map.Entry<Integer, BookOnShelf>> find(String pattern) throws PatternSyntaxException {
		Set<Map.Entry<Integer, BookOnShelf>> set = new HashSet<Entry<Integer, BookOnShelf>>();
		Pattern p = Pattern.compile(pattern);
		Matcher a, t;
		for (Entry<Integer, BookOnShelf> e : getShelfBooks().entrySet()) {
			a = p.matcher(String.valueOf(e.getValue().getBook().getAuthor()));
			t = p.matcher(String.valueOf(e.getValue().getBook().getTitle()));
			if (a.matches() || t.matches()) {
				set.add(e);
			}

		}
		return set;
	}
	
	@Override
	public boolean copy(String fileName) throws ClassNotFoundException, IOException {
		for (Entry<Integer, BookOnShelf> e : readAll(fileName).entrySet()) {
			addBook(e.getKey(), e.getValue());
		}
		return true;
	}

}
