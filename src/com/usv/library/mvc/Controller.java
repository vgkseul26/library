package com.usv.library.mvc;

import java.io.IOException;

public interface Controller {
	boolean add(int numOnShelf, String author, String title, int ISBN) throws IOException;

	boolean del(int numOnShelf) throws IOException;

	boolean edit(int numOnShelf, String author, String title, int ISBN) throws IOException;
	
	boolean edit(int numOnShelf, String author, String title) throws IOException;
	
	boolean edit(int numOnShelf, String author) throws IOException;
	
	boolean edit(int numOnShelf, boolean issued) throws IOException;
	
	boolean view(int numOnShelf) throws ClassNotFoundException, IOException;
	
	void viewAll() throws ClassNotFoundException, IOException;
	
	boolean copy(String fileName) throws ClassNotFoundException, IOException;

}
