package com.usv.library.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.PatternSyntaxException;

import javax.xml.bind.JAXBException;

import com.usv.library.lib.BookOnShelf;
import com.usv.library.controller.Controller;
/**
 * The console realization of {@link View} component
 * @author vgkseul26
 *
 */
public class ConsoleView implements View {
	private Scanner sc;
	private ArrayList<String> pars;
	private Controller control;
	
	public ConsoleView(Controller conrol) {
		sc = new Scanner(System.in);
		this.control = conrol;
	}

	public void start() throws IOException, ClassNotFoundException, JAXBException {
		pars = new ArrayList<>();
		String command = "";
		System.out.println(Answers.WELCOME_TO_OUR_INFORMATION_SYSTEM);
		System.out.println(Answers.PLEASE_ENTER_COMMAND_YOU_NEED_ENTER_HELP_FOR_HELP);
		while (sc.hasNext()) {
			pars.removeAll(pars);
			StringTokenizer st = new StringTokenizer(sc.nextLine());

			if (st.hasMoreTokens())
				command = st.nextToken();

			switch (command){
				case Answers.EXIT: 
					end();
					break;
					
				case Answers.ADD: {
					add(st, command);
				}
					break;
				
				case Answers.DEL: {
					delete(st, command);
				}
					break;
					
				case Answers.EDIT: {
					edit(st, command);
				}
					break;
					
				case Answers.VIEW: {
					view(st, command);
				}
					break;
				case Answers.VIEW_ALL: {
					viewAll(st, command);
				} 
					break;
				
				case Answers.COPY: {
					copy(st, command);					
				}
					break;
				
				case Answers.HELP: {
	 				System.out.println("Every argument must be without spaces. You can use '_' to change spaces. All arguments must be divided by spases"+"\n");
					System.out.println("add - add new book into library"+"\n"+"Example:"+"\t"+"add NumberOnShelf Author Title ISBN"+"\t"+"(int String String int)"+"\n");
					System.out.println("del - delete existing book"+"\n"+"Example:"+"\t"+"del NumberOnShelf"+"\t"+"\t"+"\t"+"(int)"+"\n");
					System.out.println("edit - edit existing book in library(Select one of the change options)"+"\n"+"Example1:"+"\t"+"edit NumberOnShelf Author Title ISBN"+"\t"+"(int String String int)");
					System.out.println("Example2:"+"\t"+"edit NumberOnShelf Author Title"+"\t"+"\t"+"(int String String)");
					System.out.println("Example3:"+"\t"+"edit NumberOnShelf Author"+"\t"+"\t"+"(int String)");
					System.out.println("Example4:"+"\t"+"edit NumberOnShelf Issued"+"\t"+"\t"+"(int boolean)"+"\n");
					System.out.println("view - view existing book in library"+"\n"+"Example:"+"\t"+"view NumberOnShelf"+"\t"+"\t"+"\t"+"(int)"+"\n");
					System.out.println("viewAll - view all existing book in library(You can use some pattern to look for data)"+"\n"+"Example1:"+"\t"+"viewAll ");
					System.out.println("Example2:"+"\t"+"viewAll Pattern"+"\n");
					System.out.println("copy - copy all books from some file to our archive"+"\n"+"Example:"+"\t"+"copy fileName"+"\t"+"\t"+"\t"+"\t"+"(String)"+"\n");
					System.out.println("helpRegex - a list of avaliable symbols for searching data"+"\n"+"Example:"+"\t"+"helpRegex"+"\n");
					System.out.println("exit - exit from program");
				}
					break;
					
				case Answers.HELPREGEX:{
					System.out.println(Answers.A_LIST_OF_AVALIABLE_SYMBOLS_FOR_SEARCHING_DATA);
					System.out.println(Answers.START_OF_THE_CHECKING_LINE);
					System.out.println(Answers.$_END_OF_THE_CHECKING_LINE);
					System.out.println(Answers.ANY_SYMBOL);
					System.out.println(Answers.SYMBOL_OR);
					System.out.println(Answers.NULL_OR_ONE_EXAMPLES_OF_SYMBOL_BEFORE);
					System.out.println(Answers.ONE_OR_MORE_EXAMPLES_OF_THE_SYMBOL_BEFORE);
					System.out.println(Answers.NULL_OR_MORE_EXAMPLES_OF_THE_SYMBOL_BEFORE);
					System.out.println(Answers.RANGE_OF_LETTERS_OR_NUMBERS);
					System.out.println(Answers.D_NUMBER_SYMBOL);
					System.out.println(Answers.D_THIS_SYMBOL_IS_NOT_A_NUMBER);
					System.out.println(Answers.S_SYMBOL_OF_SPACE);
					System.out.println(Answers.S_THIS_SYMBOL_IS_NOT_A_SPACE);
					System.out.println(Answers.W_THIS_SYMBOL_CAN_BE_LETTER_OR_NUMBER_OR);
					System.out.println(Answers.W_ANY_SYMBOL_EXCEPT_FOR_LETTER_OR_NUMBER_OR);
				}
					break;
					
				default: {
					System.out.println(Answers.UNKNOWN_COMMAND);
					System.out.println(Answers.ENTER_HELP_TO_GET_MORE_INFORMATION);
				}
					break;
			}
		}
	}

	@Override
	public void end() throws IOException, JAXBException {
		control.update();
		System.out.println(Answers.THE_WHOLE_DATA_HAS_BEEN_SAVED);
		System.out.println(Answers.THANK_YOU_FOR_USING_OUR_SYS_INFO_GOODBYE);

		sc.close();
		System.exit(0);
	}
	
	private void printExample(String cmd){
		switch(cmd){
			case Answers.ADD :{
				System.out.println(Answers.ENTER_CORRECT_COMMAND_TO_ADD_BOOK);
				System.out.println("Example:"+"\t"+"add NumberOnShelf Author Title ISBN"+"\t"+"(int String String int)");
			}
				break;
			
			case Answers.DEL:{
				System.out.println(Answers.ENTER_CORRECT_COMMAND_TO_DELETE_BOOK);
				System.out.println("Example:"+"\t"+"del NumberOnShelf"+"\t"+"\t"+"\t"+"(int)");
			}
				break;
			
			case Answers.EDIT:{
				System.out.println(Answers.ENTER_CORRECT_COMMAND_TO_EDIT_BOOK);
				System.out.println("Example1:"+"\t"+"edit NumberOnShelf Author Title ISBN"+"\t"+"(int String String int)");
				System.out.println("Example2:"+"\t"+"edit NumberOnShelf Author Title"+ "\t"+"\t"+"(int String String)");
				System.out.println("Example3:"+"\t"+"edit NumberOnShelf Author"+"\t"+"\t"+"(int String)");
				System.out.println("Example4:"+"\t"+"edit NumberOnShelf Issued"+"\t"+"\t"+"(int boolean)");
			}
			break;
			
			case Answers.VIEW:{
				System.out.println(Answers.ENTER_CORRECT_COMMAND_TO_VIEW_BOOK);
				System.out.println("Example:"+"\t"+"view NumberOnShelf"+"\t"+"\t"+"\t"+"(int)");
			}
			break;
			
			case Answers.COPY:{
				System.out.println(Answers.ENTER_CORRECT_COMMAND_TO_COPY_LIBRARY_FROM_FILE);
				System.out.println("Example:"+"\t"+"copy fileName"+"\t"+"\t"+"\t"+"\t"+"(String)");
			}
			break;
		}
	}
	
	private void delete(StringTokenizer st, String command) throws IOException, JAXBException{
		while (st.hasMoreTokens()) {
			pars.add(st.nextToken());
		}
		if (pars.size() < 1) {
			System.out.println(Answers.ENTER_MORE_PARAMETERS);
			printExample(command);
			return;
		}
		if (pars.size() > 1) {
			System.out.println(Answers.TOO_MUCH_PARAMETERS);
			printExample(command);
			return;
		}
		if (pars.size() == 1) {
			try {
				if (control.del(Integer.parseInt(pars.get(0))))
					System.out.println(Answers.DELETE_SUCCESS);
				else
					System.out.println(Answers.THIS_NUM_IS_NOT_EXIST);
			} catch (NumberFormatException ex) {
				System.out.println(Answers.ENTER_CORRECT_PARAMETRES_PLEASE);
				printExample(command);
				return;
			}
		}
	}
	
	private void add (StringTokenizer st, String command) throws IOException, JAXBException{
		while (st.hasMoreTokens()) {
			pars.add(st.nextToken());
		}
		if (pars.size() < 4) {
			System.out.println(Answers.ENTER_MORE_PARAMETERS);
			printExample(command);
			return;
		}
		if (pars.size() > 4) {
			System.out.println(Answers.TOO_MUCH_PARAMETERS);
			printExample(command);
			return;
		}
		if (pars.size() == 4) {
			try {
				if (control.add(Integer.parseInt(pars.get(0)), pars.get(1), pars.get(2),
						Integer.parseInt(pars.get(3))))
					System.out.println(Answers.ADD_SUCCESS);
	 			else
					System.out.println(Answers.ERROR_THIS_BOOK_HAS_BEEN_ALREADY_ADDED);
			} catch (NumberFormatException ex) {
				System.out.println(Answers.ENTER_CORRECT_PARAMETRES_PLEASE);
				printExample(command);
				return;
			}
		}
	}
	
	private void edit(StringTokenizer st, String command) throws IOException, JAXBException{
		while (st.hasMoreTokens()) {
			pars.add(st.nextToken());
		}
		if (pars.size() < 1) {
			System.out.println(Answers.ENTER_MORE_PARAMETERS);
			printExample(command);
			return;
		}
		if (pars.size() > 4) {
			System.out.println(Answers.TOO_MUCH_PARAMETERS);
			printExample(command);
			return;
		}
		try {
			if (pars.size() == 4)
				if(control.edit(Integer.parseInt(pars.get(0)), pars.get(1), pars.get(2),Integer.parseInt(pars.get(3))))
					System.out.println(Answers.EDIT_SUCCESS);
			if (pars.size() == 3)
				if(control.edit(Integer.parseInt(pars.get(0)), pars.get(1), pars.get(2)))
					System.out.println(Answers.EDIT_SUCCESS);
			if ((pars.size() == 2) && !Boolean.parseBoolean(pars.get(1)))
				if(control.edit(Integer.parseInt(pars.get(0)), pars.get(1)))
					System.out.println(Answers.EDIT_SUCCESS);
			if ((pars.size() == 2) && Boolean.parseBoolean(pars.get(1)))
					if(control.edit(Integer.parseInt(pars.get(0)), Boolean.parseBoolean(pars.get(1))))
						System.out.println(Answers.EDIT_SUCCESS);
		} catch (NumberFormatException ex) {
			System.out.println(Answers.ENTER_CORRECT_PARAMETRES_PLEASE);
			printExample(command);
			return;
		}

	}
	
	private void view(StringTokenizer st, String command){
		while (st.hasMoreTokens()) {
			pars.add(st.nextToken());
		}
		if (pars.size() < 1) {
			System.out.println(Answers.ENTER_MORE_PARAMETERS);
			printExample(command);
			return;
		}
		if (pars.size() > 1) {
			System.out.println(Answers.TOO_MUCH_PARAMETERS);
			printExample(command);
			return;
		}
		if (pars.size() == 1) {
			try {
				if (control.view(Integer.parseInt(pars.get(0)))!=null) {
					System.out.println(control.view(Integer.parseInt(pars.get(0))));
				}
				else {
					System.out.println(Answers.THIS_BOOK_IS_NOT_EXIST);
				}
				
			} catch (NumberFormatException ex) {
				System.out.println(Answers.ENTER_CORRECT_PARAMETRES_PLEASE);
				printExample(command);
				return;
			}
		}
	}
		
	private void viewAll(StringTokenizer st, String command){
		while (st.hasMoreTokens()) {
			pars.add(st.nextToken());
		}
		if (pars.size() == 0) {
			try {
				if (control.viewAll().isEmpty())
					System.out.println(Answers.BOOKS_NOT_FOUND);
				else
					printBooks(control.viewAll());
			} catch (NullPointerException ex){
				System.out.println(Answers.BOOKS_NOT_FOUND);
			}
		}
		if (pars.size() == 1){
			try {
				printBooks(control.find(pars.get(0)));
			} catch (PatternSyntaxException ex){
				System.out.println(Answers.ENTER_CORRECT_PARAMETRES_PLEASE);
				System.out.println(Answers.PRINT_HELP_REGEX_TO_GET_MORE_INFORMATION_ABOUT_REGULAR_EXPRESSIONS);
			}
		}
	}
	
	private void copy(StringTokenizer st, String command) throws ClassNotFoundException, IOException, JAXBException{
		while (st.hasMoreTokens()) {
			pars.add(st.nextToken());
		}
		if (pars.size() < 1) {
			System.out.println(Answers.ENTER_MORE_PARAMETERS);
			printExample(command);
			return;
		}
		if (pars.size() > 1) {
			System.out.println(Answers.TOO_MUCH_PARAMETERS);
			printExample(command);
			return;
		}
		if (pars.size() == 1) {
			try {
				if(control.copy(pars.get(0)))
					System.out.println(Answers.COPY_SUCCESS);
			} catch (FileNotFoundException ex){
				System.out.println(Answers.FILE_NOT_FOUND);
			}
		}

	}
	
	private void printBooks(Set<Map.Entry<Integer, BookOnShelf>> set){
		for (Entry<Integer, BookOnShelf> e : set) {
			System.out.println(e.getKey() + " " + e.getValue().getBook().getAuthor() + " " + e.getValue().getBook().getTitle() + " " + e.getValue().getBook().getIsbn() + " " + e.getValue().getIssued());
		}
	}
}
