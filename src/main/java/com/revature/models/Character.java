package com.revature.models;

import java.util.HashMap;

public class Character {
	private int character_id;
	private Class cl;
	private Race race;
	private String f_name;
	private String l_name;
	private String alignment;
	private HashMap<String,Integer> hm;
	
	public Character() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Character(int character_id, Class cl, Race race, String f_name, String l_name, String alignment) {
		super();
		this.character_id = character_id;
		this.cl = cl;
		this.race = race;
		this.f_name = f_name;
		this.l_name = l_name;
		this.alignment = alignment;
	}

	@Override
	public String toString() {
		return "Character: " + f_name + " " + l_name +
		           "\n Class: " + cl.getName() + ", Race: " + race.getName() + ", Alignment: " + alignment +
		           "\n Stats: " + hm.toString() + 
		           "\n Abilities: " + cl.getAbilities().toString() +
		           "\n Traits: " + race.getTraits();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alignment == null) ? 0 : alignment.hashCode());
		result = prime * result + character_id;
		result = prime * result + ((cl == null) ? 0 : cl.hashCode());
		result = prime * result + ((f_name == null) ? 0 : f_name.hashCode());
		result = prime * result + ((hm == null) ? 0 : hm.hashCode());
		result = prime * result + ((l_name == null) ? 0 : l_name.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
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
		Character other = (Character) obj;
		if (alignment == null) {
			if (other.alignment != null)
				return false;
		} else if (!alignment.equals(other.alignment))
			return false;
		if (character_id != other.character_id)
			return false;
		if (cl == null) {
			if (other.cl != null)
				return false;
		} else if (!cl.equals(other.cl))
			return false;
		if (f_name == null) {
			if (other.f_name != null)
				return false;
		} else if (!f_name.equals(other.f_name))
			return false;
		if (hm == null) {
			if (other.hm != null)
				return false;
		} else if (!hm.equals(other.hm))
			return false;
		if (l_name == null) {
			if (other.l_name != null)
				return false;
		} else if (!l_name.equals(other.l_name))
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		return true;
	}

	public int getCharacter_id() {
		return character_id;
	}

	public void setCharacter_id(int character_id) {
		this.character_id = character_id;
	}

	public Class getCl() {
		return cl;
	}

	public void setCl(Class cl) {
		this.cl = cl;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public HashMap<String, Integer> getHm() {
		return hm;
	}

	public void setHm(HashMap<String, Integer> hm) {
		this.hm = hm;
	}
	
	
	
}
