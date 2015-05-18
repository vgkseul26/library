package com.usv.library.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.usv.library.lib.BookOnShelf;

@XmlRootElement(name = "model")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLModel extends AbstractModel {
	private static final String XML_LIB_ZIP = "xml_lib.zip";

	public XMLModel() {
		try {
			shelfBooks = readAll(XML_LIB_ZIP);
		} catch (IOException ex) {
			shelfBooks = new TreeMap<Integer, BookOnShelf>();
		}
	}

	public XMLModel(Map<Integer, BookOnShelf> shelfBooks) {
		this.shelfBooks = shelfBooks;
	}

	@Override
	public void update() throws JAXBException, FileNotFoundException, IOException {
		File file = new File(XML_LIB_ZIP);
		JAXBContext context = JAXBContext.newInstance(XMLModel.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		try (OutputStream fos = new GZIPOutputStream(new FileOutputStream(file));) {
			XMLModel md = new XMLModel(shelfBooks);
			marshaller.marshal(md, fos);
		}
	}

	public Map<Integer, BookOnShelf> readAll(String fileName) throws FileNotFoundException, IOException {
		File file = new File(fileName);
		Map<Integer, BookOnShelf> map = new TreeMap<Integer, BookOnShelf>();
		try (InputStream fis = new GZIPInputStream(new FileInputStream(file));) {
			JAXBContext context = JAXBContext.newInstance(XMLModel.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Model md = (XMLModel) unmarshaller.unmarshal(fis);
			map.putAll(md.getShelfBooks());
		} catch (JAXBException ex) {
		}
		return map;
	}
	
}
