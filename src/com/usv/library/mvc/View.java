package com.usv.library.mvc;

import java.io.IOException;

/**
 * The interface of a View component.
 * 
 * 
 * @author vgkseul26
 * @see	ConsoleView
 */
public interface View {

	/**
	 * Starts the view
	 * @throws Exception
	 */
	void start() throws Exception;

	/**
	 * Ends the view and saves the whole changes
	 * @throws IOException
	 */
	void end() throws IOException;
	
	/**
	 * Print book
	 * 
	 * @param args
	 * 			array of String values that must be printed
	 */
	void print(StringBuilder... args);

}
