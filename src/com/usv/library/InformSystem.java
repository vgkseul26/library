package com.usv.library;

import com.usv.library.mvc.ConsoleView;
import com.usv.library.mvc.Controller;
import com.usv.library.mvc.Model;
import com.usv.library.mvc.SerializeModel;
import com.usv.library.mvc.SimpleController;
import com.usv.library.mvc.View;


public class InformSystem {
	private static View view;

	public static void main(String[] args) throws Exception {
	    view = new ConsoleView();
				
		view.start();
	}
}
