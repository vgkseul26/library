package com.usv.library.controller;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

import javax.xml.bind.JAXBException;

import com.usv.library.lib.BookOnShelf;

/**
 * The interface of a Controller component.
 * 
 * 
 * @author vgkseul26
 * @see SimpleController
 */
public interface Controller {
	/**
	 * Adds book to library
	 * 
	 * @param numOnShelf
	 *            book's number in library
	 * @param author
	 *            author of the book
	 * @param title
	 *            title of the book
	 * @param ISBN
	 *            ISBN
	 * @return true if adding success false if book has been already added
	 * @throws IOException
	 *             if there are some problems with serialization
	 * @throws JAXBException
	 */
	boolean add(int numOnShelf, String author, String title, int ISBN) throws IOException, JAXBException;

	/**
	 * Deletes book from library
	 * 
	 * @param numOnShelf
	 *            book's number in library
	 * @return true if delete success false if there is no book with such number
	 * @throws IOException
	 *             if there are some problems with serialization
	 * @throws JAXBException
	 */
	boolean del(int numOnShelf) throws IOException, JAXBException;

	/**
	 * Edit book in library using 4 parameters
	 * 
	 * @param numOnShelf
	 *            book's number in library
	 * @param author
	 *            author of the book
	 * @param title
	 *            title of the book
	 * @param ISBN
	 *            ISBN
	 * @return true if edit success
	 * @throws IOException
	 *             if there are some problems with serialization
	 * @throws JAXBException
	 */
	boolean edit(int numOnShelf, String author, String title, int ISBN) throws IOException, JAXBException;

	/**
	 * Edit book in library using 3 parameters
	 * 
	 * @param numOnShelf
	 *            book's number in library
	 * @param author
	 *            author of the book
	 * @param title
	 *            title of the book
	 * @return true if edit success
	 * @throws IOException
	 *             if there are some problems with serialization
	 * @throws JAXBException
	 */
	boolean edit(int numOnShelf, String author, String title) throws IOException, JAXBException;

	/**
	 * Edit book in library using 2 parameters
	 * 
	 * @param numOnShelf
	 *            book's number in library
	 * @param author
	 *            author of the book
	 * @return true if edit success
	 * @throws IOException
	 *             if there are some problems with serialization
	 * @throws JAXBException
	 */
	boolean edit(int numOnShelf, String author) throws IOException, JAXBException;

	/**
	 * Edit book in library using 2 parameters
	 * 
	 * @param numOnShelf
	 *            book's number in library
	 * @param issued
	 *            free or no
	 * @return true if edit success
	 * @throws IOException
	 *             if there are some problems with serialization
	 * @throws JAXBException
	 */
	boolean edit(int numOnShelf, boolean issued) throws IOException, JAXBException;

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
	BookOnShelf view(int numOnShelf) throws NullPointerException;

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
	 * Write to file library.zip the library from memory
	 * 
	 * @throws IOException
	 *             if there are some problems with serialization
	 * @throws JAXBException
	 */
	void update() throws IOException, JAXBException;

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
