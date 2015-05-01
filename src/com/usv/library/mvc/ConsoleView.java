package com.usv.library.mvc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.usv.library.InformSystem;

/**
 * The console realization of {@link View} component
 * @author vgkseul26
 *
 */
public class ConsoleView implements View {
	private Scanner sc;

	public ConsoleView() {
		sc = new Scanner(System.in);
	}

	public void start() throws IOException, ClassNotFoundException {
		ArrayList<String> pars = new ArrayList<>();
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
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 4) {
						System.out.println(Answers.ENTER_MORE_PARAMETERS);
						printExample(command);
						continue;
					}
					if (pars.size() > 4) {
						System.out.println(Answers.TOO_MUCH_PARAMETERS);
						printExample(command);
						continue;
					}
					if (pars.size() == 4) {
						try {
							if (InformSystem.getController().add(Integer.parseInt(pars.get(0)), pars.get(1), pars.get(2),
									Integer.parseInt(pars.get(3))))
								System.out.println(Answers.ADD_SUCCESS);
						} catch (NumberFormatException ex) {
							System.out.println(Answers.ENTER_CORRECT_PARAMETRES_PLEASE);
							printExample(command);
							continue;
						}
					}
				}
					break;
				
				case Answers.DEL: {
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 1) {
						System.out.println(Answers.ENTER_MORE_PARAMETERS);
						printExample(command);
						continue;
					}
					if (pars.size() > 1) {
						System.out.println(Answers.TOO_MUCH_PARAMETERS);
						printExample(command);
						continue;
					}
					if (pars.size() == 1) {
						try {
							if (InformSystem.getController().del(Integer.parseInt(pars.get(0))))
								System.out.println(Answers.DELETE_SUCCESS);
							else
								System.out.println(Answers.THIS_NUM_IS_NOT_EXIST);
						} catch (NumberFormatException ex) {
							System.out.println(Answers.ENTER_CORRECT_PARAMETRES_PLEASE);
							printExample(command);
							continue;
						}
					}
				}
					break;
				case Answers.EDIT: {
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 1) {
						System.out.println(Answers.ENTER_MORE_PARAMETERS);
						printExample(command);
						continue;
					}
					if (pars.size() > 4) {
						System.out.println(Answers.TOO_MUCH_PARAMETERS);
						printExample(command);
						continue;
					}
					try {
						if (pars.size() == 4)
							if(InformSystem.getController().edit(Integer.parseInt(pars.get(0)), pars.get(1), pars.get(2),Integer.parseInt(pars.get(3))))
								System.out.println(Answers.EDIT_SUCCESS);
						if (pars.size() == 3)
							if(InformSystem.getController().edit(Integer.parseInt(pars.get(0)), pars.get(1), pars.get(2)))
								System.out.println(Answers.EDIT_SUCCESS);
						if ((pars.size() == 2) && !Boolean.parseBoolean(pars.get(1)))
							if(InformSystem.getController().edit(Integer.parseInt(pars.get(0)), pars.get(1)))
								System.out.println(Answers.EDIT_SUCCESS);
						if ((pars.size() == 2) && Boolean.parseBoolean(pars.get(1)))
								if(InformSystem.getController().edit(Integer.parseInt(pars.get(0)), Boolean.parseBoolean(pars.get(1))))
									System.out.println(Answers.EDIT_SUCCESS);
					} catch (NumberFormatException ex) {
						System.out.println(Answers.ENTER_CORRECT_PARAMETRES_PLEASE);
						printExample(command);
						continue;
					}
				}
					break;
				case Answers.VIEW: {
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 1) {
						System.out.println(Answers.ENTER_MORE_PARAMETERS);
						printExample(command);
						continue;
					}
					if (pars.size() > 1) {
						System.out.println(Answers.TOO_MUCH_PARAMETERS);
						printExample(command);
						continue;
					}
					if (pars.size() == 1) {
						try {
							if(!InformSystem.getController().view(Integer.parseInt(pars.get(0))))
								System.out.println(Answers.THIS_BOOK_IS_NOT_EXIST);
						} catch (NumberFormatException ex) {
							System.out.println(Answers.ENTER_CORRECT_PARAMETRES_PLEASE);
							printExample(command);
							continue;
						}
					}
				}
					break;
				case Answers.VIEW_ALL: 
					try {
						InformSystem.getController().viewAll();
					} catch (NullPointerException ex){
						System.out.println(Answers.BOOKS_NOT_FOUND);
					}
					break;
				
				case Answers.COPY: {
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 1) {
						System.out.println(Answers.ENTER_MORE_PARAMETERS);
						printExample(command);
						continue;
					}
					if (pars.size() > 1) {
						System.out.println(Answers.TOO_MUCH_PARAMETERS);
						printExample(command);
						continue;
					}
					if (pars.size() == 1) {
						try {
							if(InformSystem.getController().copy(pars.get(0)))
								System.out.println(Answers.COPY_SUCCESS);
						} catch (FileNotFoundException ex){
							System.out.println(Answers.FILE_NOT_FOUND);
						}
					}
					
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
					System.out.println("viewAll - view all existing book in library"+"\n"+"Example:"+"\t"+"viewAll"+"\n");
					System.out.println("copy - copy all books from some file to our archive"+"\n"+"Example:"+"\t"+"copy fileName"+"\t"+"\t"+"\t"+"\t"+"(String)"+"\n");
					System.out.println("exit - exit from program");
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
	public void end() throws IOException {
		InformSystem.getController().update();
		System.out.println(Answers.THE_WHOLE_DATA_HAS_BEEN_SAVED);
		System.out.println(Answers.THANK_YOU_FOR_USING_OUR_SYS_INFO_GOODBYE);

		sc.close();
		System.exit(0);
	}
	
	public void printExample(String cmd){
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

}
