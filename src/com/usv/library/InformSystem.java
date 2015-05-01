package com.usv.library;

import com.usv.library.mvc.ConsoleView;
import com.usv.library.mvc.Controller;
import com.usv.library.mvc.Model;
import com.usv.library.mvc.SerializeModel;
import com.usv.library.mvc.SimpleController;
import com.usv.library.mvc.View;

/**
 * My library application!
 * 
 * @author vgkseul26
 * @version 1.0
 */

public class InformSystem {
	private static View view;
	private static Controller control; 
	private static Model model;

	public static void main(String[] args) throws Exception {
		control = new SimpleController();
		model = new SerializeModel();
	    view = new ConsoleView();
				
		view.start();
	}
	
	/**
	 * @return a {@link Model} instance
	 */
	public static Model getModel() {
		return model;
	}

	/**
	 * @return a {@link View} instance
	 */
	public static View getView() {
		return view;
	}

	/**
	 * @return a {@link Controller} instance
	 */
	public static Controller getController() {
		return control;
	}
}
