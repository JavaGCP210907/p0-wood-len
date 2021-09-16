package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

public class Main {

	public static void main(String[] args) {
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("Connection Successful");
		}
		catch(SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
		
		Menu menu = new Menu();
		menu.displayMain();
	}

}
