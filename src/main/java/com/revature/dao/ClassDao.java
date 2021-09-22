package com.revature.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import com.revature.models.Class;
import com.revature.utils.ConnectionUtil;


public class ClassDao implements ClassDaoI {

	@Override
	public List<Class> getClasses() {
		try(Connection conn = ConnectionUtil.getConnection()){
			ResultSet rs = null;
			String query = 
					"SELECT classes.class_id, classes.name, abilities.ability, classes.priority1, classes.priority2, classes.save1, classes.save2 \r\n"
					+ "FROM classes \r\n"
					+ "LEFT OUTER JOIN class_abilities \r\n"
					+ "ON classes.class_id = class_abilities.class_id_fk\r\n"
					+ "JOIN abilities\r\n"
					+ "ON abilities.ability_id = class_abilities.ability_id_fk";
			Statement s = conn.createStatement();
			rs = s.executeQuery(query);
			List<Class> classList = new ArrayList<>();
			Class c = new Class();
			while(rs.next()) {
				if(c.getClass_id() == rs.getInt("class_id")){
					c.setAbilities(rs.getString("ability"));
				}
				else {
					c = new Class(
							rs.getInt("class_id"),
							rs.getString("name"),
							rs.getString("priority1"),
							rs.getString("priority2"),
							rs.getString("save1"),
							rs.getString("save2")
						);
					c.setAbilities(rs.getString("ability"));
					classList.add(c);
				}
			}
			return classList;
		}
		catch(SQLException e) {
			System.out.println("Error in getClasses");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Class getClassByName(String name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			ResultSet rs = null;
			String query = "SELECT classes.class_id, classes.name, abilities.ability, classes.priority1, classes.priority2, classes.save1, classes.save2 \r\n"
					+ "FROM classes \r\n"
					+ "LEFT OUTER JOIN class_abilities \r\n"
					+ "ON classes.class_id = class_abilities.class_id_fk\r\n"
					+ "JOIN abilities\r\n"
					+ "ON abilities.ability_id = class_abilities.ability_id_fk\r\n"
					+ "where classes.name = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			Class c = new Class();
			while(rs.next()) {
				if(c.getClass_id() == rs.getInt("class_id")){
					c.setAbilities(rs.getString("ability"));
				}
				else {
					c = new Class(
							rs.getInt("class_id"),
							rs.getString("name"),
							rs.getString("priority1"),
							rs.getString("priority2"),
							rs.getString("save1"),
							rs.getString("save2")
						);
					c.setAbilities(rs.getString("ability"));
					//classList.add(c);
				}
			}
			return c;
		}
		catch(SQLException e) {
			System.out.println("Error in getClassByName");
			e.printStackTrace();
		}
		return null;
	}

}
