package com.dao;

import java.sql.SQLException;
import java.util.List;

import beans.Comment;

public interface CommentsDao {
	  void ajouterCommentaire(Comment newComment) throws SQLException;
	  List<Comment> getCommentByPostId(int post_id) throws SQLException;
}
