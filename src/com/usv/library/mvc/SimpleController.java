package com.usv.library.mvc;

import java.io.IOException;

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
		boolean b = model.setBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, title, model.getBook(numOnShelf).getBook().getISBN())));
		model.update();
		return b;
	}
	
	public boolean edit(int numOnShelf, String author) throws IOException {
		boolean b = model.setBook(numOnShelf, model.createBookOnShelf(numOnShelf, model.createBook(author, model.getBook(numOnShelf).getBook().gettitle(), model.getBook(numOnShelf).getBook().getISBN())));
		model.update();
		return b;
	}
	
	public boolean edit(int numOnShelf, boolean issued) throws IOException {
		model.getBook(numOnShelf).setIssued(issued);
		model.update();
		return true;
	}
	
	@Override
	public boolean view(int numOnShelf) throws ClassNotFoundException, IOException{
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
	public void viewAll() throws ClassNotFoundException, IOException{
		model.viewAll();
	}
}
