package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private String url;
	private String username;
	private String password;

	private DaoFactory(String url, String username,String password) {
		this.url=url;
		this.username=username;
		this.password=password;
	}
	//Chargement du driver et connection à la bdd
	public static DaoFactory getInstance() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {e.printStackTrace();}
		DaoFactory instance = new DaoFactory(
			"jdbc:mysql://localhost/projet",
			"root",
			"marouane"
			);
		return instance;
	}

	//Recup la connection 
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,username,password);
	}
	
	//Récup du DAO
	public UserDao getUsersImplDao() {
		return new UsersDaoImpl(this);
	}
	public PostsDao getPostsImplDao() {
		return new PostsDaoImpl(this);
	}
	public CommentsDao getCommentsImplDao() {
		return new CommentsDaoImpl(this);
	}
	public NotificationDao getNotificationImplDao() {
		return new NotificationDaoImpl(this);
	}
}
