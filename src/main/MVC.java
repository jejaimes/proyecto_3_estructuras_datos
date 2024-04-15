package main;

import controller.Controller;

public class MVC {

	public static Controller controller;

	public static void main(String[] args) {
		
		Controller controler = new Controller();
		try {
			controler.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
