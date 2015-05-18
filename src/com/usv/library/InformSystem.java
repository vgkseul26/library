package com.usv.library;

import com.usv.library.controller.Controller;
import com.usv.library.controller.SimpleController;
import com.usv.library.model.Model;
import com.usv.library.model.XMLModel;
import com.usv.library.view.ConsoleView;
import com.usv.library.view.View;

/**
 * My library application!
 * 
 * @author vgkseul26
 * @version 1.0
 */

public class InformSystem {

	public static void main(String[] args) throws Exception {
		Model model = new XMLModel();
		Controller control = new SimpleController(model);
		View view = new ConsoleView(control);

		view.start();
	}

}
