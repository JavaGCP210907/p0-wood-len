package com.revature.dao;

import java.util.List;

import com.revature.models.Class;

public interface ClassDaoI {
	public List<Class> getClasses();
	public Class getClassByName(String name);
}
