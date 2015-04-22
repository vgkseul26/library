package com.usv.library.mvc;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.usv.library.lib.Book;
import com.usv.library.lib.BookOnShelf;

public class SerializeModel implements Model, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5106945804509514055L;
	private static final String LIBRARY_DATE = "library.zip";
	private Map<Integer, BookOnShelf> shelfBooks;
	private View view = new ConsoleView();

	public SerializeModel(){
		try {
		shelfBooks = readAll(LIBRARY_DATE);
		} catch (ClassNotFoundException ex){
			shelfBooks = new TreeMap<Integer, BookOnShelf>();
		} catch (IOException ex){
			
		}
	}

	public Map<Integer, BookOnShelf> getShelfBooks() {
		return shelfBooks;
	}

	public void update() throws IOException {
		try (OutputStream fos = new GZIPOutputStream(new FileOutputStream(LIBRARY_DATE));) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (Entry e : shelfBooks.entrySet()) {
				oos.writeObject(e.getValue());
				oos.flush();
			}
			oos.close();
		} catch (IOException ex) {
			throw ex;
		}
	}

	public Map<Integer, BookOnShelf> readAll(String fileName) throws IOException, ClassNotFoundException {
		Map<Integer, BookOnShelf> map = new TreeMap<Integer, BookOnShelf>();
		BookOnShelf book;
		try (InputStream fis = new GZIPInputStream(new FileInputStream(fileName));) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				while (true) {
					book = (BookOnShelf) ois.readObject();
					map.put(book.getNumOnShelf(), book);
				}

			} catch (EOFException ex) {
				ois.close();
			}
			ois.close();
		} catch (IOException | ClassNotFoundException ex) {
			throw ex;
		}
		return map;
	}

	public Book createBook(String author, String title, int ISBN) {
		return new Book(author, title, ISBN);
	}

	public BookOnShelf createBookOnShelf(int numOnShelf, Book book) {
		return new BookOnShelf(numOnShelf, book);
	}

	public boolean addBook(int numOnShelf, BookOnShelf shelfBook) {
		shelfBooks.put(numOnShelf, shelfBook);
		return true;
	}

	public boolean delBook(int numOnShelf) {
		if (shelfBooks.containsKey(numOnShelf)) {
			shelfBooks.remove(numOnShelf);
			return true;
		}
		return false;
	}

	public BookOnShelf getBook(int numOnShelf) {
		return shelfBooks.get(numOnShelf);
	}

	public boolean setBook(int numOnShelf, BookOnShelf book) {
		shelfBooks.remove(numOnShelf);
		shelfBooks.put(numOnShelf, book);
		return true;
	}

	public boolean viewBook(int numOnShelf) throws ClassNotFoundException, IOException {
		BookOnShelf book;
		if ((book = readAll(LIBRARY_DATE).get(numOnShelf)) != null) {
			view.print(String.valueOf(numOnShelf), book.getBook().getauthor(), book.getBook().gettitle(),
					String.valueOf(book.getBook().getISBN()), String.valueOf(book.getIssued()));
			return true;
		} else
			return false;
	}

	public void viewAll() throws ClassNotFoundException, IOException {
		for (Entry<Integer, BookOnShelf> e : readAll(LIBRARY_DATE).entrySet()) {
			view.print(String.valueOf(e.getKey()), e.getValue().getBook().getauthor(), e.getValue().getBook()
					.gettitle(), String.valueOf(e.getValue().getBook().getISBN()),
					String.valueOf(e.getValue().getIssued()));
		}
	}
	
	public boolean copy(String fileName) throws ClassNotFoundException, IOException{
		for (Entry<Integer, BookOnShelf> e : readAll(fileName).entrySet()) {
//			System.out.println(e.getKey()+" "+e.getValue());
			addBook(e.getKey(), e.getValue());
		}
		return true;
	}
}
