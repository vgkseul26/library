package com.usv.library.mvc;

import java.io.IOException;
import java.util.Map;

import com.usv.library.lib.Book;
import com.usv.library.lib.BookOnShelf;

public interface Model {
	Map<Integer,BookOnShelf> getShelfBooks();
	 
	Book createBook(String author, String title, int ISBN);
	 
	BookOnShelf createBookOnShelf(int numOnShelf, Book book);
	 
	boolean addBook(int numOnShelf, BookOnShelf shelfBook);
	 
	boolean delBook(int numOnShelf);
	 
	void update() throws IOException;
	 
	BookOnShelf getBook(int numOnShelf);
	 
	boolean setBook(int numOnShelf, BookOnShelf book);
	 
	boolean viewBook(int numOnShelf) throws ClassNotFoundException, IOException;
	 
	void viewAll() throws ClassNotFoundException, IOException;
	
	boolean copy(String fileName) throws ClassNotFoundException, IOException;
}
