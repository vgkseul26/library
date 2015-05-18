package com.usv.library.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

import javax.xml.bind.JAXBException;

import com.usv.library.lib.BookOnShelf; 
import com.usv.library.model.Model;
/**
 * The simple realization of the {@link Controller} component
 * @author vgkseul26
 *
 */
public class SimpleController implements Controller {
	private Model model;
	
	public SimpleController(Model model) {
		this.model = model;
	}

	@Override
	public boolean add(int numOnShelf, String author, String title, int ISBN) throws IOException, JAXBException {
		boolean b = model.addBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, title, ISBN)));
		model.update();
		return b;
	}

	@Override
	public boolean del(int numOnShelf) throws IOException, JAXBException{
		boolean b = model.delBook(numOnShelf);
		model.update();
		return b;
	}

	@Override
	public boolean edit(int numOnShelf, String author, String title, int ISBN) throws IOException, JAXBException {
		boolean b = model.setBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, title, ISBN)));
		model.update();
		return b;
	}
	
	public boolean edit(int numOnShelf, String author, String title) throws IOException, JAXBException {
		boolean b = model.setBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, title, model.getBook(numOnShelf).getBook().getIsbn())));
		model.update();
		return b;
	}
	
	public boolean edit(int numOnShelf, String author) throws IOException, JAXBException {
		boolean b = model.setBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, model.getBook(numOnShelf).getBook().getTitle(), model.getBook(numOnShelf).getBook().getIsbn())));
		model.update();
		return b;
	}
	
	public boolean edit(int numOnShelf, boolean issued) throws IOException, JAXBException {
		model.getBook(numOnShelf).setIssued(issued);
		model.update();
		return true;
	}
	
	@Override
	public BookOnShelf view(int numOnShelf) throws NullPointerException{
		return model.viewBook(numOnShelf);
	}
	
	@Override
	public boolean copy(String fileName) throws ClassNotFoundException, IOException, JAXBException {
		boolean b = model.copy(fileName);
		model.update();
		return b;
	}
	
	@Override
	public Set<Map.Entry<Integer, BookOnShelf>> viewAll() {
		return model.viewAll();
	}
	
	@Override
	public void update() throws IOException, JAXBException{
		model.update();
	}
	
	@Override
	public Set<Map.Entry<Integer, BookOnShelf>> find(String pattern) throws PatternSyntaxException{
		return model.find(pattern);
	}
}
