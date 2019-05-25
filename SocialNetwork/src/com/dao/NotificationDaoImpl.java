package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Notification;

public class NotificationDaoImpl implements NotificationDao {
	
	private DaoFactory daoFactory;
	
	
	public NotificationDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void likeNotification(int user_id, int post_id) throws SQLException {
		// TODO Auto-generated method stub
		String insertQuery = "Insert into Notifications(user_id, post_id, status) Values(?,?,1);";
		PreparedStatement prepStm = null;
		try {
	    	Connection conn = daoFactory.getConnection();
	    	conn.setAutoCommit(false);
	        prepStm = conn.prepareStatement(insertQuery);
	        prepStm.setInt(1, user_id);
	        prepStm.setInt(2, post_id);
	        	
	        prepStm.executeUpdate();
	        conn.commit();
	        prepStm.close();

		}catch (SQLException e) {
		        System.out.println(e.getMessage());
		}

	}

	@Override
	public List<Notification> getNotificationsByUserId(int user_id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = daoFactory.getConnection();
	    CallableStatement cStmt = conn.prepareCall("{call getNotificationsByUserId(?)}");
	    cStmt.setInt(1, user_id);
	    List<Notification> notifications =  new ArrayList<Notification>();
	    ResultSet rs = cStmt.executeQuery();
	    while(rs.next()) {
	    	if(rs.getInt(4) == 1) notifications.add(new Notification(rs.getString(1), rs.getString(2), rs.getInt(3), "a aimé votre post"));
	    	else notifications.add(new Notification(rs.getString(1), rs.getString(2), rs.getInt(3), "a commenté votre post"));
	    	
	    }
	                 
		return notifications;
	    
		
	}

}
