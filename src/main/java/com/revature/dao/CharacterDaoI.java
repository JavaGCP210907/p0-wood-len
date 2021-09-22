package com.revature.dao;

import java.util.ArrayList;

import com.revature.models.Character;
import com.revature.models.Class;
import com.revature.models.Race;

public interface CharacterDaoI {

	public ArrayList<Character> getCharacters();
	public Character getCharacterByName(String fname, String lname);
	public void removeCharacter(int character_id);
	public void updateCharacter(int character_id, String fname, String lname, String race, String cl, String alignment);
	public void updateCharacter(int character_id, String fname, String lname, String race, Class cl, String alignment);
	public void updateCharacter(int character_id, String fname, String lname, Race race, String cl, String alignment);
	public void updateCharacter(int character_id, String fname, String lname, Race r, Class c, String alignment);
	public void createCharacter(String fname, String lname, String race, String cl, String alignment);
	public void createRandomCharacter();
}
