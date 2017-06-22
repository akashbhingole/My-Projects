package com.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;



/**
 * This class is used to create database queries 
 */
public class LoginDAO {

		DataSource dataSource;

		public DataSource getDataSource() {
			return this.dataSource;
		}

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		
		public boolean isValidUser(String username, String password) throws Exception {
			String query = "Select * from user_details where username = ? and password = ?";
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next())
				return (resultSet.getInt(1) > 0);
			else
				return false;
		}

}