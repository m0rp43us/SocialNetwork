package com.dao;

import java.sql.SQLException;
import java.util.List;

import beans.Notification;

public interface NotificationDao {
	  void likeNotification(int user_id, int post_id) throws SQLException;
	  List<Notification> getNotificationsByUserId(int user_id) throws SQLException;
}
