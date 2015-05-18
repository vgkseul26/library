package com.usv.library.model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.usv.library.lib.BookOnShelf;

/**
 * The serialize realization of {@link Model} component
 * 
 * @author vgkseul26
 *
 */
public class SerializeModel extends AbstractModel {
	/**
	 * 
	 */
	private static final String LIB_DATE = "library.zip";

	public SerializeModel() {
		try {
			shelfBooks = readAll(LIB_DATE);

		} catch (IOException ex) {
			shelfBooks = new TreeMap<Integer, BookOnShelf>();
		} catch (ClassNotFoundException e) {

		}
	}

	@Override
	public void update() throws IOException {
		try (OutputStream fos = new GZIPOutputStream(new FileOutputStream(LIB_DATE));) {
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
		try (InputStream fis = new GZIPInputStream(new FileInputStream(fileName));
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			while (true) {
				book = (BookOnShelf) ois.readObject();
				map.put(book.getNumOnShelf(), book);
			}
		} catch (EOFException ex) {
		} catch (IOException | ClassNotFoundException ex) {
			throw ex;
		}
		return map;
	}
	
	@Override
	public boolean copy(String fileName) throws ClassNotFoundException, IOException {
		for (Entry<Integer, BookOnShelf> e : readAll(fileName).entrySet()) {
			addBook(e.getKey(), e.getValue());
		}
		return true;
	}

}
