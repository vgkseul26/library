package com.usv.library.mvc;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

/**
 * The interface of a Controller component.
 * 
 * 
 * @author vgkseul26
 * @see	SimpleController
 */
public interface Controller {
	/**
	 * Adds book to library
	 * 
	 * @param numOnShelf
	 *          book's number in library
	 * @param author
	 * 			author of the book
	 * @param title
	 * 			title of the book
	 * @param ISBN
	 * 			ISBN
	 * @return true if adding success
	 * 			false if book has been already added
	 * @throws IOException
	 * 				 if there are some problems with serialization
	 */
	boolean add(int numOnShelf, String author, String title, int ISBN) throws IOException;

	/**
	 * Deletes book from library
	 * 
	 * @param numOnShelf
	 *          book's number in library
	 * @return true if delete success
	 * 			false if there is no book with such number
	 * @throws IOException
	 * 				 if there are some problems with serialization
	 */
	boolean del(int numOnShelf) throws IOException;

	/**
	 * Edit book in library using 4 parameters
	 * 
	 * @param numOnShelf
	 *          book's number in library
	 * @param author
	 * 			author of the book
	 * @param title
	 * 			title of the book
	 * @param ISBN
	 * 			ISBN
	 * @return true if edit success
	 * @throws IOException
	 * 				 if there are some problems with serialization
	 */
	boolean edit(int numOnShelf, String author, String title, int ISBN) throws IOException;
	
	/**
	 * Edit book in library using 3 parameters
	 * 
	 * @param numOnShelf
	 *          book's number in library
	 * @param author
	 * 			author of the book
	 * @param title
	 * 			title of the book
	 * @return true if edit success
	 * @throws IOException
	 * 				 if there are some problems with serialization
	 */
	boolean edit(int numOnShelf, String author, String title) throws IOException;
	
	/**
	 * Edit book in library using 2 parameters
	 * 
	 * @param numOnShelf
	 *          book's number in library
	 * @param author
	 * 			author of the book
	 * @return true if edit success
	 * @throws IOException
	 * 				 if there are some problems with serialization
	 */
	boolean edit(int numOnShelf, String author) throws IOException;
	
	/**
	 * Edit book in library using 2 parameters
	 * 
	 * @param numOnShelf
	 *          book's number in library
	 * @param issued
	 * 			free or no
	 * @return true if edit success
	 * @throws IOException
	 * 				 if there are some problems with serialization
	 */
	boolean edit(int numOnShelf, boolean issued) throws IOException;
	
	/**
	 * View book with definite number
	 * 
	 * @param numOnShelf
	 *          book's number in library
	 * @return true if view success
	 * 			false if the book with this number is not exist 
	 */
	boolean view(int numOnShelf);
	
	/**
	 * View all existing books
	 * @throws NullPointerException
	 * 				 if there is no any book in library
	 */
	void viewAll();
	
	/**
	 * Copy books to our file from the other one
	 * 
	 * @param filename
	 *          book's number in library
	 * @return true if copy success
	 * @throws IOException
	 * 				if there are some problems with serialization
	 * @throws ClassNotFoundException
	 * 				if there are some problems with classpath
	 */
	boolean copy(String fileName) throws ClassNotFoundException, IOException;

	/**
	 * Write to file library.zip the library from memory
	 * 
	 * @throws IOException
	 * 				 if there are some problems with serialization
	 */
	void update() throws IOException;

	/**
	 * Print book
	 * 
	 * @param args
	 * 			array of String values that must be printed
	 */
	void print(StringBuilder... args);
	
	/**
	 * Finds data according to during pattern
	 * 
	 * @param pattern
	 * 			template of data
	 * @throws PatternSyntaxException
	 * 				if something wrong with syntax
	 */
	void find(String pattern) throws PatternSyntaxException;
}
