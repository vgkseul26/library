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

import com.usv.library.InformSystem;
import com.usv.library.lib.Book;
import com.usv.library.lib.BookOnShelf;

/**
 * The serialize realization of {@link Model} component
 * @author vgkseul26
 *
 */
public class SerializeModel implements Model, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5106945804509514055L;
	private static final String LIBRARY_DATE = "library.zip";
	private Map<Integer, BookOnShelf> shelfBooks;

	public SerializeModel(){
		try {
		shelfBooks = readAll(LIBRARY_DATE);
		} catch (ClassNotFoundException ex){
			shelfBooks = new TreeMap<Integer, BookOnShelf>();
		} catch (IOException ex){
			
		}
	}

//	public Map<Integer, BookOnShelf> getShelfBooks() {
//		return shelfBooks;
//	}

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
			}
			finally {
				ois.close();
			}
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

	public boolean viewBook(int numOnShelf) {
		BookOnShelf book;
		if ((book = shelfBooks.get(numOnShelf)) != null) {
			InformSystem.getController().print(new StringBuilder(String.valueOf(numOnShelf)), new StringBuilder(book.getBook().getauthor()),  new StringBuilder(book.getBook().gettitle()),
					new StringBuilder(book.getBook().getIsbn()), new StringBuilder(String.valueOf(book.getIssued())));
			return true;
		} else
			return false;
	}
	public void viewAll() {
		for (Entry<Integer, BookOnShelf> e : shelfBooks.entrySet()) {
			InformSystem.getController().print(new StringBuilder(String.valueOf(e.getKey())), new StringBuilder(e.getValue().getBook().getauthor()),  new StringBuilder(e.getValue().getBook()
					.gettitle()), new StringBuilder(e.getValue().getBook().getIsbn()),
					 new StringBuilder(String.valueOf(e.getValue().getIssued())));
		}
	}
	
	public boolean copy(String fileName) throws ClassNotFoundException, IOException{
		for (Entry<Integer, BookOnShelf> e : readAll(fileName).entrySet()) {
			addBook(e.getKey(), e.getValue());
		}
		return true;
	}
}
