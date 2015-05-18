package com.usv.library.view;

import java.io.IOException;

import javax.xml.bind.JAXBException;

/**
 * The interface of a View component.
 * 
 * 
 * @author vgkseul26
 * @see ConsoleView
 */
public interface View {

	/**
	 * Starts the view
	 * 
	 * @throws Exception
	 */
	void start() throws Exception;

	/**
	 * Ends the view and saves the whole changes
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	void end() throws IOException, JAXBException;

}
