package com.usv.library.mvc;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

import com.usv.library.InformSystem;
/**
 * The simple realization of the {@link Controller} component
 * @author vgkseul26
 *
 */
public class SimpleController implements Controller {
	public SimpleController() {
		
	}

	@Override
	public boolean add(int numOnShelf, String author, String title, int ISBN) throws IOException {
		boolean b = InformSystem.getModel().addBook(numOnShelf, InformSystem.getModel().createBookOnShelf(numOnShelf, InformSystem.getModel().createBook(author, title, ISBN)));
		InformSystem.getModel().update();
		return b;
	}

	@Override
	public boolean del(int numOnShelf) throws IOException{
		boolean b = InformSystem.getModel().delBook(numOnShelf);
		InformSystem.getModel().update();
		return b;
	}

	@Override
	public boolean edit(int numOnShelf, String author, String title, int ISBN) throws IOException {
		boolean b = InformSystem.getModel().setBook(numOnShelf, InformSystem.getModel().createBookOnShelf(numOnShelf, InformSystem.getModel().createBook(author, title, ISBN)));
		InformSystem.getModel().update();
		return b;
	}
	
	public boolean edit(int numOnShelf, String author, String title) throws IOException {
		boolean b = InformSystem.getModel().setBook(numOnShelf, InformSystem.getModel().createBookOnShelf(numOnShelf, InformSystem.getModel().createBook(author, title, InformSystem.getModel().getBook(numOnShelf).getBook().getIsbn())));
		InformSystem.getModel().update();
		return b;
	}
	
	public boolean edit(int numOnShelf, String author) throws IOException {
		boolean b = InformSystem.getModel().setBook(numOnShelf, InformSystem.getModel().createBookOnShelf(numOnShelf, InformSystem.getModel().createBook(author, InformSystem.getModel().getBook(numOnShelf).getBook().getTitle(), InformSystem.getModel().getBook(numOnShelf).getBook().getIsbn())));
		InformSystem.getModel().update();
		return b;
	}
	
	public boolean edit(int numOnShelf, boolean issued) throws IOException {
		InformSystem.getModel().getBook(numOnShelf).setIssued(issued);
		InformSystem.getModel().update();
		return true;
	}
	
	@Override
	public boolean view(int numOnShelf) {
		boolean b = InformSystem.getModel().viewBook(numOnShelf);
		return b;
	}
	
	@Override
	public boolean copy(String fileName) throws ClassNotFoundException, IOException {
		boolean b = InformSystem.getModel().copy(fileName);
		InformSystem.getModel().update();
		return b;
	}
	
	@Override
	public void viewAll() {
		InformSystem.getModel().viewAll();
	}
	
	@Override
	public void update() throws IOException{
		InformSystem.getModel().update();
	}
	
	@Override
	public void print(StringBuilder... args) {
		for (int i = 0; i < args.length; i++) {
			System.out.print(args[i] + " ");
		}
		System.out.println();
	}
	
	@Override
	public void find(String pattern) throws PatternSyntaxException{
		InformSystem.getModel().find(pattern);
	}
}
