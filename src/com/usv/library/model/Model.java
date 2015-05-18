package com.usv.library.model;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.PatternSyntaxException;

import javax.xml.bind.JAXBException;

import com.usv.library.lib.Book;
import com.usv.library.lib.BookOnShelf;

/**
 * The interface of a Model component.
 * 
 * 
 * @author vgkseul26
 * @see SerializeModel
 */
public interface Model {

	public Map<Integer, BookOnShelf> getShelfBooks();

	/**
	 * Creates book
	 * 
	 * @param author
	 *            author of the book
	 * @param title
	 *            title of the book
	 * @param ISBN
	 *            ISBN
	 * @return Book book
	 */
	Book createBook(String author, String title, int ISBN);

	/**
	 * Creates book on shelf
	 * 
	 * @param numOnShelf
	 *            book's number on the shelf
	 * @param book
	 * 
	 * @return BookOnShelf shelfBook
	 */
	BookOnShelf createBookOnShelf(int numOnShelf, Book book);

	/**
	 * Adds book
	 * 
	 * @param numOnShelf
	 *            book's number on the shelf
	 * @param shelfBook
	 *            book on shelf
	 * 
	 * @return return true if adding success
	 */
	boolean addBook(int numOnShelf, BookOnShelf shelfBook);

	/**
	 * Deletes book
	 * 
	 * @param numOnShelf
	 *            book's number on the shelf
	 * 
	 * @return return true if adding success false if book is not exist
	 */
	boolean delBook(int numOnShelf);

	/**
	 * Write to file library.zip the library from memory
	 * 
	 * @throws IOException
	 *             if there are some problems with serialization
	 * @throws JAXBException
	 */
	void update() throws IOException, JAXBException;

	/**
	 * Get shelfBook
	 * 
	 * @param numOnShelf
	 *            book's number on the shelf
	 * 
	 * @return return bookOnShelf with number numOnShelf
	 * 
	 */
	BookOnShelf getBook(int numOnShelf);

	/**
	 * Changing parameters of the book
	 * 
	 * @param numOnShelf
	 *            book's number on the shelf
	 * 
	 * @param bookOnShelf
	 * @return true if changing success
	 * 
	 */
	boolean setBook(int numOnShelf, BookOnShelf shelfBook);

	/**
	 * View book with number numOnShelf
	 * 
	 * @param numOnShelf
	 *            book's number on the shelf
	 * 
	 * @return book on shelf
	 * @throws NullPointerException
	 *             if there is no book with such number
	 * 
	 */
	BookOnShelf viewBook(int numOnShelf) throws NullPointerException;

	/**
	 * View all existing books
	 * 
	 * @return set of the books
	 * @throws NullPointerException
	 *             if there is no any book in library
	 */
	Set<Entry<Integer, BookOnShelf>> viewAll();

	/**
	 * Copy books to our file from the other one
	 * 
	 * @param filename
	 *            book's number in library
	 * @return true if copy success
	 * @throws IOException
	 *             if there are some problems with serialization
	 * @throws ClassNotFoundException
	 *             if there are some problems with classpath
	 * @throws JAXBException
	 */
	boolean copy(String fileName) throws ClassNotFoundException, IOException, JAXBException;

	/**
	 * Finds data according to during pattern
	 * 
	 * @param pattern
	 *            template of data
	 * @return set of books
	 * @throws PatternSyntaxException
	 *             if something wrong with syntax
	 */
	Set<Map.Entry<Integer, BookOnShelf>> find(String pattern) throws PatternSyntaxException;
}
