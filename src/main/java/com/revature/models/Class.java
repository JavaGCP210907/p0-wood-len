package com.revature.models;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;

public class Class {
	private int class_id;
	private String name;
	private boolean casting;
	private String priority1;
	private String priority2;
	private String save1;
	private String save2;
	private String[] ability2;
	
	public Class(int class_id, String name, boolean casting, String priority1, String priority2, String save1,
			String save2, Array abilities) {
		super();
		this.class_id = class_id;
		this.name = name;
		this.casting = casting;
		this.priority1 = priority1;
		this.priority2 = priority2;
		this.save1 = save1;
		this.save2 = save2;
		try {
			ability2 = (String[])abilities.getArray();
		}
		catch(SQLException e) {
			System.out.println("Error in Class Constructor");
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Class [name=" + name + ", abilities=" + Arrays.toString(ability2) + "]";
	}
	
	public String abilityToString() {
		return Arrays.toString(ability2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ability2 == null) ? 0 : ability2.hashCode());
		result = prime * result + (casting ? 1231 : 1237);
		result = prime * result + class_id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priority1 == null) ? 0 : priority1.hashCode());
		result = prime * result + ((priority2 == null) ? 0 : priority2.hashCode());
		result = prime * result + ((save1 == null) ? 0 : save1.hashCode());
		result = prime * result + ((save2 == null) ? 0 : save2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Class other = (Class) obj;
		if (ability2 == null) {
			if (other.ability2 != null)
				return false;
		} else if (!ability2.equals(other.ability2))
			return false;
		if (casting != other.casting)
			return false;
		if (class_id != other.class_id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority1 == null) {
			if (other.priority1 != null)
				return false;
		} else if (!priority1.equals(other.priority1))
			return false;
		if (priority2 == null) {
			if (other.priority2 != null)
				return false;
		} else if (!priority2.equals(other.priority2))
			return false;
		if (save1 == null) {
			if (other.save1 != null)
				return false;
		} else if (!save1.equals(other.save1))
			return false;
		if (save2 == null) {
			if (other.save2 != null)
				return false;
		} else if (!save2.equals(other.save2))
			return false;
		return true;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCasting() {
		return casting;
	}

	public void setCasting(boolean casting) {
		this.casting = casting;
	}

	public String getPriority1() {
		return priority1;
	}

	public void setPriority1(String priority1) {
		this.priority1 = priority1;
	}

	public String getPriority2() {
		return priority2;
	}

	public void setPriority2(String priority2) {
		this.priority2 = priority2;
	}

	public String getSave1() {
		return save1;
	}

	public void setSave1(String save1) {
		this.save1 = save1;
	}

	public String getSave2() {
		return save2;
	}

	public void setSave2(String save2) {
		this.save2 = save2;
	}

	public String[] getAbility2() {
		return ability2;
	}

	public void setAbility2(String[] ability2) {
		this.ability2 = ability2;
	}
	
	
}
