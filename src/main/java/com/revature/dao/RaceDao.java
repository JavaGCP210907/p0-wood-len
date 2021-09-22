package com.revature.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import com.revature.models.Class;
import com.revature.models.Race;
import com.revature.utils.ConnectionUtil;


public class RaceDao implements RaceDaoI {

	@Override
	public List<Race> getRaces() {
		try(Connection conn = ConnectionUtil.getConnection()){
			ResultSet rs = null;
			String query = 
					"SELECT races.race_id, races.name, traits.trait, adds.str, adds.dex, adds.con, adds.inte, adds.wis, adds.cha FROM races \r\n"
					+ "LEFT OUTER JOIN race_trait \r\n"
					+ "ON races.race_id = race_trait.race_id_fk\r\n"
					+ "JOIN traits\r\n"
					+ "ON traits.trait_id = race_trait.trait_id_fk\r\n"
					+ "JOIN adds\r\n"
					+ "ON adds.race_id_fk = races.race_id";
			Statement s = conn.createStatement();
			rs = s.executeQuery(query);
			List<Race> raceList = new ArrayList<>();
			Race r = new Race();
			while(rs.next()) {
				if(r.getRace_id() == rs.getInt("race_id")){
					r.setTraits(rs.getString("trait"));
				}
				else {
					r = new Race(
							rs.getInt("race_id"),
							rs.getString("name"),
							rs.getInt("str"),
							rs.getInt("dex"),
							rs.getInt("con"),
							rs.getInt("inte"),
							rs.getInt("wis"),
							rs.getInt("cha")
					);
					r.setTraits(rs.getString("trait"));
					raceList.add(r);
				}
			}
			return raceList;
		}
		catch(SQLException e) {
			System.out.println("Error in getRaces");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Race getRaceByName(String name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			ResultSet rs = null;
			String query = 
					"SELECT races.race_id, races.name, traits.trait, adds.str, adds.dex, adds.con, adds.inte, adds.wis, adds.cha FROM races \r\n"
					+ "LEFT OUTER JOIN race_trait \r\n"
					+ "ON races.race_id = race_trait.race_id_fk\r\n"
					+ "JOIN traits\r\n"
					+ "ON traits.trait_id = race_trait.trait_id_fk\r\n"
					+ "JOIN adds\r\n"
					+ "ON adds.race_id_fk = races.race_id\r\n"
					+ "WHERE races.name = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			Race r = new Race();
			while(rs.next()) {
				if(r.getRace_id() == rs.getInt("race_id")){
					r.setTraits(rs.getString("trait"));
				}
				else {
					r = new Race(
							rs.getInt("race_id"),
							rs.getString("name"),
							rs.getInt("str"),
							rs.getInt("dex"),
							rs.getInt("con"),
							rs.getInt("inte"),
							rs.getInt("wis"),
							rs.getInt("cha")
					);
					r.setTraits(rs.getString("trait"));
				}
			}
			return r;
		}
		catch(SQLException e) {
			System.out.println("Error in getRaceByName");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getNameByID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			ResultSet rs = null;
			String query =  "SELECT races.name FROM races WHERE race_id = ?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			String s = "";
			while(rs.next()) {
				s = rs.getString("name");
			}
			return s;
		}
		catch(SQLException e) {
			System.out.println("Error in race getNameByID");
			e.printStackTrace();
		}
		return null;
	}

}
