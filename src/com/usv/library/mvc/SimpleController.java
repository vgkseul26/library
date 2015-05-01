package com.usv.library.mvc;

import java.io.IOException;
/**
 * The simple realization of the {@link Controller} component
 * @author vgkseul26
 *
 */
public class SimpleController implements Controller {
	private Model model = new SerializeModel();
	public SimpleController() {
		
	}

	@Override
	public boolean add(int numOnShelf, String author, String title, int ISBN) throws IOException {
		boolean b = model.addBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, title, ISBN)));
		model.update();
		return b;
	}

	@Override
	public boolean del(int numOnShelf) throws IOException{
		boolean b = model.delBook(numOnShelf);
		model.update();
		return b;
	}

	@Override
	public boolean edit(int numOnShelf, String author, String title, int ISBN) throws IOException {
		boolean b = model.setBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, title, ISBN)));
		model.update();
		return b;
	}
	
	public boolean edit(int numOnShelf, String author, String title) throws IOException {
		boolean b = model.setBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, title, model.getBook(numOnShelf).getBook().getIsbn())));
		model.update();
		return b;
	}
	
	public boolean edit(int numOnShelf, String author) throws IOException {
		boolean b = model.setBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, model.getBook(numOnShelf).getBook().gettitle(), model.getBook(numOnShelf).getBook().getIsbn())));
		model.update();
		return b;
	}
	
	public boolean edit(int numOnShelf, boolean issued) throws IOException {
		model.getBook(numOnShelf).setIssued(issued);
		model.update();
		return true;
	}
	
	@Override
	public boolean view(int numOnShelf) {
		boolean b = model.viewBook(numOnShelf);
		return b;
	}
	
	@Override
	public boolean copy(String fileName) throws ClassNotFoundException, IOException {
		boolean b = model.copy(fileName);
		model.update();
		return b;
	}
	
	@Override
	public void viewAll() {
		model.viewAll();
	}
	
	@Override
	public void update() throws IOException{
		model.update();
	}
	
	@Override
	public void print(StringBuilder... args) {
		for (int i = 0; i < args.length; i++) {
			System.out.print(args[i] + " ");
		}
		System.out.println();
	}
}
