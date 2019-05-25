package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Comment;


public class CommentsDaoImpl implements CommentsDao{
	
	private DaoFactory daoFactory;
	
	

	public CommentsDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouterCommentaire(Comment newComment) throws SQLException {
		PreparedStatement prepStm = null;
		String insertQuery = "INSERT INTO COMMENTS(content, user_id, post_id, date_publication) VALUES (?,?,?,NOW());";
		try {
		    	Connection conn = daoFactory.getConnection();
		    	conn.setAutoCommit(false);
		        prepStm = conn.prepareStatement(insertQuery);
		        prepStm.setString(1,newComment.getContent());
		        prepStm.setInt(2,newComment.getUser_id());
		        prepStm.setInt(3,newComment.getPost_id());
		        
		        
		        prepStm.executeUpdate();
		        conn.commit();
		        prepStm.close();

		}catch (SQLException e) {
		        System.out.println(e.getMessage());
		}
		
	}

	@Override
	public List<Comment> getCommentByPostId(int post_id) throws SQLException {
		Connection conn = daoFactory.getConnection();
	    CallableStatement cStmt = conn.prepareCall("{call getCommentByPostId(?)}");
	    cStmt.setInt(1, post_id);
	    List<Comment> comments =  new ArrayList<Comment>();
	    ResultSet rs = cStmt.executeQuery();
	    while(rs.next()) {
	    	
	    	comments.add(new Comment(rs.getInt(1), rs.getString(2), post_id, rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7)));
	    	
	    	
	    }
	                 
		return comments;
		
	}
	
}
