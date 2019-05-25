package com.dao;

import java.sql.SQLException;

import beans.User;


public interface UserDao {
	  User lookForUser(String email)  throws SQLException;
	  User lookForUser(String email, String password) throws SQLException;   
	  void addUser(User newUser) throws SQLException;
	  void changePassword(int id_user, String newPassword) throws SQLException;
	  void changeProfilImg(int id_user, String newProfilImg) throws SQLException;
	
} 

