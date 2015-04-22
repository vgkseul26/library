package com.usv.library.mvc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ConsoleView implements View {
	private static final String BOOKS_NOT_FOUND = "Books not found";
	private static final String FILE_NOT_FOUND = "File not found";
	private static final String COPY_SUCCESS = "Copy success";
	private static final String COPY = "copy";
	private static final String UNKNOWN_COMMAND = "Unknown command";
	private static final String THIS_BOOK_IS_NOT_EXIST = "This book is not exist";
	private static final String EDIT_SUCCESS = "Edit success";
	private static final String ADD_SUCCESS = "Add success";
	private static final String DELETE_SUCCESS = "Delete success";
	private static final String THIS_NUM_IS_NOT_EXIST = "This num is not exist";
	private static final String HELP = "help";
	private static final String VIEW_ALL = "viewAll";
	private static final String VIEW = "view";
	private static final String DEL = "del";
	private static final String EDIT = "edit";
	private static final String ENTER_CORRECT_PARAMETRES_PLEASE = "Enter correct parametres, please";
	private static final String TOO_MUCH_PARAMETERS = "Too much parameters";
	private static final String ENTER_MORE_PARAMETERS = "Enter more parameters";
	private static final String THANK_YOU_FOR_USING_OUR_SYS_INFO_GOODBYE = "Thank you for using our SysInfo, Goodbye!";
	private static final String ADD = "add";
	private static final String EXIT = "exit";
	private static final String PLEASE_ENTER_COMMAND_YOU_NEED_ENTER_HELP_FOR_HELP = "Please enter command you need (enter 'help' for help.)";
	private static final String WELCOME_TO_OUR_INFORMATION_SYSTEM = "Welcome to our Information System!";
	private Controller control;
	public Scanner sc;

	public ConsoleView() {

	}

	public void start() throws IOException, ClassNotFoundException {
		// view подписывается на события model
		// view добавляет лисненеры из контроллера
		// лисненеры из контроллера манипулируют данными
		// model нотифицирует модель об изменении
		// view отреагировало на event
		// ждем sc.hasNext()
		control = new SimpleController();
		sc = new Scanner(System.in);
		ArrayList<String> pars = new ArrayList<>();
		String command = null;
		System.out.println(WELCOME_TO_OUR_INFORMATION_SYSTEM); // extract
																// constants
		System.out.println(PLEASE_ENTER_COMMAND_YOU_NEED_ENTER_HELP_FOR_HELP);
		while (sc.hasNext()) {
			pars.removeAll(pars);
			StringTokenizer st = new StringTokenizer(sc.nextLine());

			if (st.hasMoreTokens())
				command = st.nextToken();

			switch (command){
				case EXIT: 
					end();
					break;
					
				case ADD: {
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 4) {
						System.out.println(ENTER_MORE_PARAMETERS);
						continue;
					}
					if (pars.size() > 4) {
						System.out.println(TOO_MUCH_PARAMETERS);
						continue;
					}
					if (pars.size() == 4) {
						try {
							if (control.add(Integer.parseInt(pars.get(0)), pars.get(1), pars.get(2),
									Integer.parseInt(pars.get(3))))
								System.out.println(ADD_SUCCESS);
						} catch (NumberFormatException ex) {
							System.out.println(ENTER_CORRECT_PARAMETRES_PLEASE);
							continue;
						}
					}
				}
					break;
				
				case DEL: {
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 1) {
						System.out.println(ENTER_MORE_PARAMETERS);
						continue;
					}
					if (pars.size() > 1) {
						System.out.println(TOO_MUCH_PARAMETERS);
						continue;
					}
					if (pars.size() == 1) {
						try {
							if (control.del(Integer.parseInt(pars.get(0))))
								System.out.println(DELETE_SUCCESS);
							else
								System.out.println(THIS_NUM_IS_NOT_EXIST);
						} catch (NumberFormatException ex) {
							System.out.println(ENTER_CORRECT_PARAMETRES_PLEASE);
							continue;
						}
					}
				}
					break;
				case EDIT: {
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 1) {
						System.out.println(ENTER_MORE_PARAMETERS);
						continue;
					}
					if (pars.size() > 4) {
						System.out.println(TOO_MUCH_PARAMETERS);
						continue;
					}
					try {
						if (pars.size() == 4)
							if(control.edit(Integer.parseInt(pars.get(0)), pars.get(1), pars.get(2),Integer.parseInt(pars.get(3))))
								System.out.println(EDIT_SUCCESS);
						if (pars.size() == 3)
							if(control.edit(Integer.parseInt(pars.get(0)), pars.get(1), pars.get(2)))
								System.out.println(EDIT_SUCCESS);
						if ((pars.size() == 2)&&!Boolean.parseBoolean(pars.get(1)))
							if(control.edit(Integer.parseInt(pars.get(0)), pars.get(1)))
								System.out.println(EDIT_SUCCESS);
						if ((pars.size() == 2)&&Boolean.parseBoolean(pars.get(1)))
								if(control.edit(Integer.parseInt(pars.get(0)), Boolean.parseBoolean(pars.get(1))))
									System.out.println(EDIT_SUCCESS);
					} catch (NumberFormatException ex) {
						System.out.println(ENTER_CORRECT_PARAMETRES_PLEASE);
						continue;
					}
				}
					break;
				case VIEW: {
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 1) {
						System.out.println(ENTER_MORE_PARAMETERS);
						continue;
					}
					if (pars.size() > 1) {
						System.out.println(TOO_MUCH_PARAMETERS);
						continue;
					}
					if (pars.size() == 1) {
						try {
							if(!control.view(Integer.parseInt(pars.get(0))))
								System.out.println(THIS_BOOK_IS_NOT_EXIST);
						} catch (NumberFormatException ex) {
							System.out.println(ENTER_CORRECT_PARAMETRES_PLEASE);
							continue;
						}
					}
				}
					break;
				case VIEW_ALL: 
					try {
						control.viewAll();
					} catch (FileNotFoundException ex){
						System.out.println(BOOKS_NOT_FOUND);
					}
					break;
				
				case COPY: {
					while (st.hasMoreTokens()) {
						pars.add(st.nextToken());
					}
					if (pars.size() < 1) {
						System.out.println(ENTER_MORE_PARAMETERS);
						continue;
					}
					if (pars.size() > 1) {
						System.out.println(TOO_MUCH_PARAMETERS);
						continue;
					}
					if (pars.size() == 1) {
						try {
							if(control.copy(pars.get(0)))
								System.out.println(COPY_SUCCESS);
						} catch (FileNotFoundException ex){
							System.out.println(FILE_NOT_FOUND);
						}
					}
					
				}
					break;
				
				case HELP: {
					System.out.println("add - add new book into library(add Number on shelf, Author, Title, ISBN)");
					System.out.println("del - delete existing book(del Number on shelf)");
					System.out.println("edit - edit existing book in library(edit Number on shelf and Author/Title/ISBN)");
					System.out.println("view - view existing book in library(viewlib Number on shelf)");
					System.out.println("viewAll - view all existing book in library(viewAll)");
					System.out.println("copy - copy all books from some file to our archive(copy fileName)");
					System.out.println("exit - exit from program");
				}
					break;
				default: {
					System.out.println(UNKNOWN_COMMAND);
					break;
				}
			}
		}
	}

	@Override
	public void end() {
		System.out.println(THANK_YOU_FOR_USING_OUR_SYS_INFO_GOODBYE);
		sc.close();
		System.exit(0);
	}

	public void print(String... args) {
		for (int i = 0; i < args.length; i++) {
			System.out.print(args[i] + " ");
		}
		System.out.println();
	}
}
