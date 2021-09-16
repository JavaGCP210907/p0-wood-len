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
			String query = "select * from classes";
			Statement s = conn.createStatement();
			rs = s.executeQuery(query);
			List<Class> classList = new ArrayList<>();
			while(rs.next()) {
				Class c = new Class(
					rs.getInt("class_id"),
					rs.getString("name"),
					rs.getBoolean("casting"),
					rs.getString("priority1"),
					rs.getString("priority2"),
					rs.getString("save1"),
					rs.getString("save2"),
					rs.getArray("abilities")
				);
				
				classList.add(c);
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
			Class c = null;
			String query = "select * from classes where name = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			rs.next();
			c = new Class(
					rs.getInt("class_id"),
					rs.getString("name"),
					rs.getBoolean("casting"),
					rs.getString("priority1"),
					rs.getString("priority2"),
					rs.getString("save1"),
					rs.getString("save2"),
					rs.getArray("abilities")
			);
			return c;
		}
		catch(SQLException e) {
			System.out.println("Error in getClassByName");
			e.printStackTrace();
		}
		return null;
	}

}
