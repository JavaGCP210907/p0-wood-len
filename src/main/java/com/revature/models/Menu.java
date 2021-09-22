package com.revature.models;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.ClassDao;
import com.revature.dao.RaceDao;
import com.revature.dao.CharacterDao;

public class Menu {
	
	ClassDao cDao = new ClassDao();
	RaceDao rDao = new RaceDao();
	CharacterDao chDao = new CharacterDao();
	private Scanner scan = new Scanner(System.in);
	
	//Logger object
	Logger log = LogManager.getLogger(Menu.class);
	
	public void displayMain() {
		
		boolean displayMenu = true;
		while(displayMenu) {
			System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
			System.out.println(":  Hail and well met, adventurer. What action would you like to take?  °");
			System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:·°·");
			System.out.println("   *·. character - View and Create Characters .·*");
			System.out.println("   .·* class     - View Classes               *·.");
			System.out.println("   *·. race      - View Race                  .·*");
			System.out.println("   .·* exit      - Exit Application           *·.");
			System.out.println("   °·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.");
			System.out.print(">>> ");
			String input = scan.nextLine();
			switch(input) {
				case "character":
					characterMenu();
					break;
				case "class":
					classMenu();
					break;
				case "race":
					raceMenu();
					break;
				case "exit":
					displayMenu = false;
					System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·");
					System.out.println(":  Farewell, adventurer. 'Til we meet again.  °");
					System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:");
					break;
				default:
					System.out.println(input + " is not a recognized command.");
					break;
			}
		}
	}
	
		private void characterMenu() {
			boolean displayMenu = true;
			boolean alt = true;
			while(displayMenu) {
				System.out.println(".:·°·:.:·°·:.:·°·:.:");
				System.out.println(":  Character Menu  °");
				System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·");
				System.out.println("   *·. all    - View all Characters       .·*");
				System.out.println("   .·* one    - View a Specific Character *·.");
				System.out.println("   *·. create - Create a Character        .·*");
				System.out.println("   .·* update - Update a Character        *·.");
				System.out.println("   *·. delete - Delete a Character        .·*");
				System.out.println("   .·* back   - Back to Main Menu         *·.");
				System.out.println("   °·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·");
				System.out.print(">>> ");
				String input = scan.nextLine();
				switch(input) {
					case "all":
						ArrayList<Character> cl = chDao.getCharacters();
						for(Character c : cl) {
							if(alt) {
								alt = false;
								System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
							}
							else {
								alt = true;
								System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:·°·");
							}
							System.out.println(c.toString());
						}
						break;
					case "one":
						System.out.println(".·* Character's first name?");
						System.out.print(">>> ");
						String fname = scan.nextLine();
						System.out.println("*·. Character's last name?");
						System.out.print(">>> ");
						String lname = scan.nextLine();
						System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
						Character character = chDao.getCharacterByName(fname, lname);
						if(character.getF_name() != null) {
							System.out.println(character);
						}
						else {
							System.out.println("Character " + fname + " " + lname + " not found.");
						}
						break;
					case "create":
						System.out.println(".·* Character's first name?");
						System.out.print(">>> ");
						fname = scan.nextLine();
						System.out.println("*·. Character's last name?");
						System.out.print(">>> ");
						lname = scan.nextLine();
						System.out.println(".·* Character's race?");
						System.out.print(">>> ");
						String race = scan.nextLine();
						System.out.println("*·. Character's class?");
						System.out.print(">>> ");
						String cla = scan.nextLine();
						System.out.println(".·* Character's alignment?");
						System.out.print(">>> ");
						String alignment = scan.nextLine();
						System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
						chDao.createCharacter(fname, lname, race, cla, alignment);
						log.info("User created new character: " + fname + " " + lname);
						break;
					case "update":
						boolean no = true;
						boolean exit = false;
						Character ch = null;
						while(no) {
							System.out.println("*·. Which character would you like to update?");
							System.out.println(".·* Character's first name?");
							System.out.print(">>> ");
							fname = scan.nextLine();
							System.out.println("*·. Character's last name?");
							System.out.print(">>> ");
							lname = scan.nextLine();
							ch = chDao.getCharacterByName(fname, lname);
							if(ch.getF_name() != null) {
								System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
								System.out.println(ch);
								System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:·°·");
								System.out.println(".·* Is this the character you'd like to update? (y/n)");
								System.out.print(">>> ");
								String yn = scan.nextLine();
								yn.toLowerCase();
								if(yn.equals("y") || yn.equals("yes")) {
									no = false;
								}
								else if(yn.equals("back")) {
									no = false;
									exit = true;
								}
								else if(!yn.equals("n") && !yn.equals("no")) {
									System.out.println(yn + " is not a recognized command.");
								}
							}
							else {
								System.out.println("Character not found");
							}
							
						}
						if(!exit) {
							System.out.println("*·. Please input new information, or press enter to keep current information.");
							System.out.println(".·* Character's first name?");
							System.out.print(">>> ");
							fname = scan.nextLine();
							if(fname.equals("")) {
								fname = ch.getF_name();
							}
							System.out.println("*·. Character's last name?");
							System.out.print(">>> ");
							lname = scan.nextLine();
							if(lname.equals("")) {
								lname = ch.getL_name();
							}
							System.out.println("*·. Character's class?");
							System.out.print(">>> ");
							cla = scan.nextLine();
							System.out.println(".·* Character's alignment?");
							System.out.print(">>> ");
							alignment = scan.nextLine();
							if(alignment.equals("")) {
								alignment = ch.getAlignment();
							}
							System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
							if(cla.equals("")) {
								chDao.updateCharacter(ch.getCharacter_id(), fname, lname, ch.getCl(), alignment);
							}
							else {
								chDao.updateCharacter(ch.getCharacter_id(), fname, lname, cla, alignment);
							}
							log.info("User updated character: " + fname + " " + lname);
						}
						break;
					case "delete":
						no = true;
						ch = null;
						exit = false;
						while(no) {
							System.out.println("*·. Which character would you like to delete?");
							System.out.println(".·* Character's first name?");
							System.out.print(">>> ");
							fname = scan.nextLine();
							System.out.println("*·. Character's last name?");
							System.out.print(">>> ");
							lname = scan.nextLine();
							ch = chDao.getCharacterByName(fname, lname);
							if(ch.getF_name() != null) {
								System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
								System.out.println(ch);
								System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:·°·");
								System.out.println(".·* Is this the character you'd like to delete? (y/n)");
								System.out.print(">>> ");
								String yn = scan.nextLine();
								yn.toLowerCase();
								if(yn.equals("y") || yn.equals("yes")) {
									no = false;
								}
								else if(yn.equals("back")) {
									no = false;
									exit = true;
								}
								else if(!yn.equals("n") && !yn.equals("no")) {
									System.out.println(yn + " is not a recognized command.");
								}
							}
							else {
								System.out.println("Character not found");
							}
							
						}
						if(!exit) {
							System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
							chDao.removeCharacter(ch.getCharacter_id());
							System.out.println("Character " + ch.getF_name() + " " + ch.getL_name() + " was removed");
							log.info("User deleted character: " + ch.getF_name() + " " + ch.getL_name());
						}
						break;
					case "back":
						displayMenu = false;
						break;
					default:
						System.out.println(input + " is not a recognized command.");
						break;
				}
			}
			
		}
		
		private void classMenu() {
			boolean displayMenu = true;
			boolean alt = true;
			while(displayMenu) {
				System.out.println(".:·°·:.:·°·:.:·°");
				System.out.println(":  Class Menu  :");
				System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·");
				System.out.println("   *·. all    - View all Classes      .·*");
				System.out.println("   .·* one    - View a Specific Class *·.");
				System.out.println("   *·. back   - Back to Main Menu     .·*");
				System.out.println("   .:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:");
				System.out.print(">>> ");
				String input = scan.nextLine();
				switch(input) {
					case "all":
						List<Class> cl = cDao.getClasses();
						for(Class c : cl) {
							if(alt) {
								alt = false;
								System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
							}
							else {
								alt = true;
								System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:·°·");
							}
							System.out.println(c.toString());
						}
						break;
					case "one":
						System.out.println(".·* Class name?");
						System.out.print(">>> ");
						String name = scan.nextLine();
						System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
						Class cla = cDao.getClassByName(name);
						if(cla.getName() != null) {
							System.out.println(cla);
						}
						else {
							System.out.println("Class " + name + " not found.");
						}
						break;
					case "back":
						displayMenu = false;
						break;
					default:
						System.out.println(input + " is not a recognized command.");
						break;
				}
			}
		}
		
		private void raceMenu() {
			boolean displayMenu = true;
			boolean alt = true;
			while(displayMenu) {
				System.out.println(".:·°·:.:·°·:.:·");
				System.out.println(":  Race Menu  °");
				System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·");
				System.out.println("   *·. all    - View all Races       .·*");
				System.out.println("   .·* one    - View a Specific Race *·.");
				System.out.println("   *·. back   - Back to Main Menu    .·*");
				System.out.println("   .:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.");
				System.out.print(">>> ");
				String input = scan.nextLine();
				switch(input) {
					case "all":
						List<Race> rl = rDao.getRaces();
						for(Race r : rl) {
							if(alt) {
								alt = false;
								System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
							}
							else {
								alt = true;
								System.out.println("°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:·°·");
							}
							System.out.println(r.toString());
						}
						break;
					case "one":
						System.out.println(".·* Race name?");
						System.out.print(">>> ");
						String name = scan.nextLine();
						System.out.println(".:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:.:·°·:");
						Race race = rDao.getRaceByName(name);
						if(race.getName() != null) {
							System.out.println(race);
						}
						else {
							System.out.println("Race " + name + " not found.");
						}
						break;
					case "back":
						displayMenu = false;
						break;
					default:
						System.out.println(input + " is not a recognized command.");
						break;
				}
			}
		}

}
