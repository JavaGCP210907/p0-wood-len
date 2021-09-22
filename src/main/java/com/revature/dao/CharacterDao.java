package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.revature.utils.Dice;
import com.revature.models.Character;
import com.revature.models.Class;
import com.revature.models.Race;
import com.revature.utils.ConnectionUtil;

public class CharacterDao implements CharacterDaoI {

	private ClassDao cDao = new ClassDao();
	private RaceDao rDao = new RaceDao();
	
	@Override
	public ArrayList<Character> getCharacters(){
		try(Connection conn = ConnectionUtil.getConnection()){
			ResultSet rs = null;
			String query = 
					"SELECT character_id, classes.name as c_name, races.name as r_name, f_name, l_name, alignment,\r\n"
					+ "abilityscores.str, abilityscores.dex, abilityscores.con,\r\n"
					+ "abilityscores.inte, abilityscores.wis, abilityscores.cha\r\n"
					+ "FROM CHARACTERS JOIN classes ON class_id = class_id_fk\r\n"
					+ "JOIN races ON race_id = race_id_fk JOIN abilityscores\r\n"
					+ "ON character_id_fk = character_id";
			Statement s = conn.createStatement();
			rs = s.executeQuery(query);
			ArrayList<Character> characterList = new ArrayList<>();
			Character c = new Character();
			HashMap<String, Integer> hm = new HashMap<>();
			while(rs.next()) {
					c = new Character(
							rs.getInt("character_id"),
							cDao.getClassByName(rs.getString("c_name")),
							rDao.getRaceByName(rs.getString("r_name")),
							rs.getString("f_name"),
							rs.getString("l_name"),
							rs.getString("alignment")
						);
					hm.put("str", rs.getInt("str")); 
					hm.put("dex", rs.getInt("dex"));
					hm.put("con", rs.getInt("con"));
					hm.put("inte", rs.getInt("inte"));
					hm.put("wis", rs.getInt("wis"));
					hm.put("cha", rs.getInt("cha"));
					c.setHm(hm);
					characterList.add(c);
			}
			return characterList;
		}
		catch(SQLException e) {
			System.out.println("Error in getCharacters");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Character getCharacterByName(String fname, String lname) {
		try(Connection conn = ConnectionUtil.getConnection()){
			ResultSet rs = null;
			String query = 
					"SELECT character_id, classes.name as c_name, races.name as r_name, f_name, l_name, alignment,\r\n"
					+ "abilityscores.str, abilityscores.dex, abilityscores.con,\r\n"
					+ "abilityscores.inte, abilityscores.wis, abilityscores.cha\r\n"
					+ "FROM CHARACTERS JOIN classes ON class_id = class_id_fk\r\n"
					+ "JOIN races ON race_id = race_id_fk JOIN abilityscores\r\n"
					+ "ON character_id_fk = character_id\r\n"
					+ "WHERE f_name = ? AND l_name = ?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, fname);
			ps.setString(2, lname);
			rs = ps.executeQuery();
			Character c = new Character();
			HashMap<String, Integer> hm = new HashMap<>();
			while(rs.next()) {
				c = new Character(
						rs.getInt("character_id"),
						cDao.getClassByName(rs.getString("c_name")),
						rDao.getRaceByName(rs.getString("r_name")),
						rs.getString("f_name"),
						rs.getString("l_name"),
						rs.getString("alignment")
					);
				hm.put("str", rs.getInt("str")); 
				hm.put("dex", rs.getInt("dex"));
				hm.put("con", rs.getInt("con"));
				hm.put("inte", rs.getInt("inte"));
				hm.put("wis", rs.getInt("wis"));
				hm.put("cha", rs.getInt("cha"));
				c.setHm(hm);
			}
			return c;
		}
		catch(SQLException e) {
			System.out.println("Error in getCharacterByName");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void updateCharacter(int character_id, String fname, String lname, String race, String cl, String alignment) {
		Race r = rDao.getRaceByName(race);
		Class c = cDao.getClassByName(cl);
		this.updateCharacter(character_id, fname, lname, r, c, alignment);
	}
	
	@Override
	public void updateCharacter(int character_id, String fname, String lname, String race, Class cl, String alignment) {
		Race r = rDao.getRaceByName(race);
		this.updateCharacter(character_id, fname, lname, r, cl, alignment);
	}
	
	@Override
	public void updateCharacter(int character_id, String fname, String lname, Race race, String cl, String alignment) {
		Class c = cDao.getClassByName(cl);
		this.updateCharacter(character_id, fname, lname, race, c, alignment);
	}
	
	@Override
	public void updateCharacter(int character_id, String fname, String lname, Race r, Class c, String alignment) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "UPDATE CHARACTERS SET f_name = ?, l_name = ?, race_id_fk = ?, class_id_fk = ?, alignment = ? WHERE character_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setInt(3, r.getRace_id());
			ps.setInt(4,c.getClass_id());
			ps.setString(5, alignment);
			ps.setInt(6, character_id);
			ps.executeUpdate();
			
			query = "update abilityscores set str = ?, dex = ?, con = ?, inte = ?, wis = ?, cha = ? where character_id_fk = ?";
			ps = conn.prepareStatement(query);
			HashMap<String, Integer> hm = genStats(r, c);
			ps.setInt(1, hm.get("str"));
			ps.setInt(2, hm.get("dex"));
			ps.setInt(3, hm.get("con"));
			ps.setInt(4, hm.get("inte"));
			ps.setInt(5, hm.get("wis"));
			ps.setInt(6, hm.get("cha"));
			ps.setInt(7, character_id);
			ps.execute();
			System.out.println("Character updated:");
			System.out.println(getCharacterByName(fname, lname).toString());
		}
		catch(SQLException e) {
			System.out.println("Error in updateCharacter");
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeCharacter(int character_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "DELETE FROM abilityscores WHERE character_id_fk = ?;\r\n"
					+ "DELETE FROM CHARACTERS WHERE character_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, character_id);
			ps.setInt(2, character_id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error in removeCharacter");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void createCharacter(String fname, String lname, String race, String cl, String alignment) {
		Class chClass = cDao.getClassByName(cl);
		Race chRace = rDao.getRaceByName(race);
		HashMap<String, Integer> hm = genStats(chRace, chClass);
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "insert into characters (class_id_fk, race_id_fk, f_name, l_name, alignment)" +
				 	   "values (?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, chClass.getClass_id());
			ps.setInt(2, chRace.getRace_id());
			ps.setString(3, fname);
			ps.setString(4, lname);
			ps.setString(5, alignment);
			ps.executeUpdate();
			
			query = "select character_id from characters where f_name = ? and l_name = ?";
			ps = conn.prepareStatement(query);
			ResultSet rs = null;
			ps.setString(1, fname);
			ps.setString(2, lname);
			rs = ps.executeQuery();
			
			int id = -1;
			while(rs.next()) {
				id = rs.getInt("character_id");
			}
			
			
			query = "insert into abilityscores (character_id_fk, str, dex, con, inte, wis, cha)" +
				 	   "values (?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, hm.get("str"));
			ps.setInt(3, hm.get("dex"));
			ps.setInt(4, hm.get("con"));
			ps.setInt(5, hm.get("inte"));
			ps.setInt(6, hm.get("wis"));
			ps.setInt(7, hm.get("cha"));
			ps.executeUpdate();
			System.out.println("Character " + fname + " " + lname + " created." + 
					           "\n Class: " + cl + ", Race: " + race + ", Alignment: " + alignment +
					           "\n " + hm.toString());
		}
		catch(SQLException e) {
			System.out.println("Error in createCharacter");
			e.printStackTrace();
		}
	}
	
	private HashMap<String, Integer> genStats(Race chRace, Class chClass){
		ArrayList<Integer> abilityScores = rollAbilities();
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		hm.put("str", chRace.getStr()); 
		hm.put("dex", chRace.getDex());
		hm.put("con", chRace.getCon());
		hm.put("inte", chRace.getInte());
		hm.put("wis", chRace.getWis());
		hm.put("cha", chRace.getCha());
		hm.put(chClass.getPriority1(), hm.get(chClass.getPriority1()) + abilityScores.get(0));
		abilityScores.remove(0);
		hm.put(chClass.getPriority2(), hm.get(chClass.getPriority2()) + abilityScores.get(0));
		abilityScores.remove(0);
		//Iterator<Map.Entry<String,Integer>> hmI = hm.entrySet().iterator();
		ArrayList<String> keys = new ArrayList<>(hm.keySet());
		Collections.shuffle(keys);
		for(String s: keys) {
			//Map.Entry<String,Integer> mapElement = (Map.Entry<String,Integer>)hmI.next();
			if(!(s.equals(chClass.getPriority1())) && !(s.equals(chClass.getPriority2()))) {
				hm.put(s, hm.get(s) + abilityScores.get(0));
				abilityScores.remove(0);
			}
		}
		return hm;
	}
	
	private ArrayList<Integer> rollAbilities(){
		Dice dice = new Dice();
		ArrayList<Integer> abilityScores = new ArrayList<>();
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			temp = dice.rollMultiple(4,6);
			Collections.sort(temp);
			temp.remove(0);
			int total = 0;
			for(int t : temp) {
				total += t;
			}
			abilityScores.add(total);
		}
		Collections.sort(abilityScores);
		Collections.reverse(abilityScores);
		return abilityScores;
	}


}
