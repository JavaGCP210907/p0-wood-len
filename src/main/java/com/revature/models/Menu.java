package com.revature.models;

import com.revature.dao.ClassDao;

public class Menu {
	
	ClassDao cDao = new ClassDao();
	
	public void displayMain() {
		System.out.println(cDao.getClasses().toString());
		System.out.println(cDao.getClassByName("Wizard"));
	}

}
