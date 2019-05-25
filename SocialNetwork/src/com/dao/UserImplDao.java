package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

class UsersDaoImpl implements UserDao{

	  private DaoFactory daoFactory;

	  public UsersDaoImpl(DaoFactory daoFactory) {
		  this.daoFactory = daoFactory;
	  }

	  
	  
	  public User lookForUser(String email) {
	      User u = null;
	      
	      PreparedStatement prepStm = null;
	      String select = "SELECT * FROM users where email = ?";

	        try {
	        	Connection conn = daoFactory.getConnection();
	            prepStm = conn.prepareStatement(select);
	            prepStm.setString(1,email);
	            
	            ResultSet rs = prepStm.executeQuery();
	            if(!rs.next()) {
	            	return null;
	            }
	            u = new User(rs.getInt(1),email,rs.getString(3),rs.getString(4), rs.getString(5));
	            prepStm.close();
	        }catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	            
	        

	      return u;
	  }


	public User lookForUser(String email, String password) {
	      User u = null;
	      
	      PreparedStatement prepStm = null;
	      String select = "SELECT * FROM users where email = ? and password = ?";

	        try {
	        	Connection conn = daoFactory.getConnection();
	            prepStm = conn.prepareStatement(select);
	            prepStm.setString(1,email);
	            prepStm.setString(2,password);
	            ResultSet rs = prepStm.executeQuery();
	            if(!rs.next()) {
	            	return null;
	            }
	            u = new User(rs.getInt(1),email,password,rs.getString(4), rs.getString(5));
            	
	            prepStm.close();
	        }catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	            
	        

	      return u;
	  }


	  public void addUser(User newUser){
	    
	    PreparedStatement prepStm = null;
	    String insertQuery = "INSERT INTO USERS(email,password,fullName,profileImg_url) VALUES (?,?,?,?);";
	    try {
	    	Connection conn = daoFactory.getConnection();
	    	conn.setAutoCommit(false);
	        prepStm = conn.prepareStatement(insertQuery);
	        prepStm.setString(1,newUser.getEmail());
	        prepStm.setString(2,newUser.getPassword());
	        prepStm.setString(3,newUser.getFullName());
	        prepStm.setString(4,newUser.getProfileImg_url());

	        prepStm.executeUpdate();
	        conn.commit();
	        prepStm.close();

	    }catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    

	  }

	  public void changePassword(int id_user, String newPassword) {
	    
	    PreparedStatement prepStm = null;
	    String insertQuery = "UPDATE  USERS SET password := ? WHERE id = ?;";
	    try {
	    	Connection conn = daoFactory.getConnection();
	    	conn.setAutoCommit(false);
	        prepStm = conn.prepareStatement(insertQuery);
	        prepStm.setString(1,newPassword);
	        prepStm.setInt(2,id_user);

	        prepStm.executeUpdate();
	        conn.commit();
	        prepStm.close();

	    }catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }

	  }

	  public void changeProfilImg(int id_user, String newProfilImg) {
	    
	    PreparedStatement prepStm = null;
	    String insertQuery = "UPDATE  USERS SET profileImg_url := ? WHERE id = ?;";
	    try {
	    	Connection conn = daoFactory.getConnection();
	    	conn.setAutoCommit(false);
	        prepStm = conn.prepareStatement(insertQuery);
	        prepStm.setString(1,newProfilImg);
	        prepStm.setInt(2,id_user);

	        prepStm.executeUpdate();
	        conn.commit();
	        prepStm.close();

	    }catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	  }
	  
}
