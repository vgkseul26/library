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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
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
			
		} catch (IOException ex){
			shelfBooks = new TreeMap<Integer, BookOnShelf>();
		} catch (ClassNotFoundException e) {
			
		} 
	}

	public Map<Integer, BookOnShelf> getShelfBooks() {
		return shelfBooks;
	}

	@Override
	public void update() throws IOException {
		try (OutputStream fos = new GZIPOutputStream(new FileOutputStream(LIBRARY_DATE));) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (Entry<?, ?> e : getShelfBooks().entrySet()) {
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
		} catch (IOException|ClassNotFoundException ex) {
			throw ex;
		}
		return map;
	}

	@Override
	public Book createBook(String author, String title, int ISBN) {
		return new Book(author, title, ISBN);
	}

	@Override
	public BookOnShelf createBookOnShelf(int numOnShelf, Book book) {
		return new BookOnShelf(numOnShelf, book);
	}

	@Override
	public boolean addBook(int numOnShelf, BookOnShelf shelfBook) {
		for (Entry<Integer, BookOnShelf> e : getShelfBooks().entrySet()){
			if (e.getKey()==numOnShelf)
				return false;
			else
				if ((e.getValue().getBook().getAuthor().equals(shelfBook.getBook().getAuthor())&& (e.getValue().getBook().getTitle().equals(shelfBook.getBook().getTitle()))))
					return false;
				else
					if (e.getValue().getBook().getIsbn()==shelfBook.getBook().getIsbn())
						return false;
		}
		getShelfBooks().put(numOnShelf, shelfBook);
		return true;
	}

	@Override
	public boolean delBook(int numOnShelf) {
		if (getShelfBooks().containsKey(numOnShelf)) {
			getShelfBooks().remove(numOnShelf);
			return true;
		}
		return false;
	}

	@Override
	public BookOnShelf getBook(int numOnShelf) {
		return getShelfBooks().get(numOnShelf);
	}

	@Override
	public boolean setBook(int numOnShelf, BookOnShelf book) {
		getShelfBooks().remove(numOnShelf);
		getShelfBooks().put(numOnShelf, book);
		return true;
	}

	@Override
	public boolean viewBook(int numOnShelf) {
		BookOnShelf book;
		if ((book = getShelfBooks().get(numOnShelf)) != null) {
			InformSystem.getController().print(new StringBuilder(String.valueOf(numOnShelf)), new StringBuilder(book.getBook().getAuthor()),  new StringBuilder(book.getBook().getTitle()),
					new StringBuilder(book.getBook().getIsbn()), new StringBuilder(String.valueOf(book.getIssued())));
			return true;
		} else
			return false;
	}
	@Override
	public void viewAll() {
		for (Entry<Integer, BookOnShelf> e : getShelfBooks().entrySet()) {
			InformSystem.getController().print(new StringBuilder(String.valueOf(e.getKey())), new StringBuilder(e.getValue().getBook().getAuthor()),  new StringBuilder(e.getValue().getBook().getTitle()), new StringBuilder(String.valueOf(e.getValue().getBook().getIsbn())), new StringBuilder(String.valueOf(e.getValue().getIssued())));
		}
	}
	
	@Override
	public boolean copy(String fileName) throws ClassNotFoundException, IOException{
		for (Entry<Integer, BookOnShelf> e : readAll(fileName).entrySet()) {
			addBook(e.getKey(), e.getValue());
		}
		return true;
	}
	
	@Override
	public void find(String pattern) throws PatternSyntaxException{
		Pattern p = Pattern.compile(pattern); 
		Matcher a,t;
        for (Entry<Integer, BookOnShelf> e : getShelfBooks().entrySet()) {
        	a = p.matcher(String.valueOf(e.getValue().getBook().getAuthor()));
        	t = p.matcher(String.valueOf(e.getValue().getBook().getTitle()));
        	if (a.matches() || t.matches()){
        		InformSystem.getController().print(new StringBuilder(String.valueOf(e.getKey())), new StringBuilder(e.getValue().getBook().getAuthor()),  new StringBuilder(e.getValue().getBook().getTitle()), new StringBuilder(String.valueOf(e.getValue().getBook().getIsbn())), new StringBuilder(String.valueOf(e.getValue().getIssued())));
        	}
        		
        }
	}
}
