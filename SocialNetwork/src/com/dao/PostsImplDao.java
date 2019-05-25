package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Post;

import java.sql.CallableStatement;
import java.sql.Connection;

class PostsDaoImpl implements PostsDao{

	  private DaoFactory daoFactory;
	  
	  
	  
	  
	  public PostsDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	  }

	public void ajouterPost(Post newPost) {
	    
	    PreparedStatement prepStm = null;
	    String insertQuery = "insert into Posts(img_url, description, user_id, date_publication) values(?,?,?,NOW());";
	    try {
	    	Connection conn = daoFactory.getConnection();
	    	conn.setAutoCommit(false);
	        prepStm = conn.prepareStatement(insertQuery);
	        prepStm.setString(1,newPost.getPost_img_url());
	        prepStm.setString(2,newPost.getDescription());
	        prepStm.setInt(3,newPost.getUser_id());
	        
	        
	        prepStm.executeUpdate();
	        conn.commit();
	        prepStm.close();

	    }catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	  }

	  // la fonction supprimer manque

	 public void likePost(int post_id){
	   
	    PreparedStatement prepStm = null;
	    String insertQuery = "UPDATE  Posts SET nbrUpvotes := nbrUpvotes + 1 WHERE id_post = ?;";
	    try {
	    	Connection conn = daoFactory.getConnection();
	    	conn.setAutoCommit(false);
	        prepStm = conn.prepareStatement(insertQuery);
	      
	        prepStm.setString(1, Integer.toString(post_id));

	        prepStm.executeUpdate();
	        conn.commit();
	        prepStm.close();

	    }catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }


	 }

	  public void dislikePost(int post_id){
	   
	    PreparedStatement prepStm = null;
	    String insertQuery = "UPDATE  Posts SET nbrUpvotes := nbrUpvotes - 1 WHERE id_post = ?;";
	    try {
	    	Connection conn = daoFactory.getConnection();
	    	conn.setAutoCommit(false);
	        prepStm = conn.prepareStatement(insertQuery);
	      
	        prepStm.setString(1, Integer.toString(post_id));

	        prepStm.executeUpdate();
	        conn.commit();
	        prepStm.close();

	    }catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	        
	 }

	@Override
	public void supprimerPost(int oldPost_id) throws SQLException {
		PreparedStatement prepStm = null;
	    String deleteQuery = "DELETE FROM Posts WHERE id_post = ?;";
	    try {
	    	Connection conn = daoFactory.getConnection();
	    	conn.setAutoCommit(false);
	        prepStm = conn.prepareStatement(deleteQuery);
	      
	        prepStm.setString(1, Integer.toString(oldPost_id));

	        prepStm.executeUpdate();
	        conn.commit();
	        prepStm.close();

	    }catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		
	}

	@Override
	public List<Post> getPostsByUserId(int some_user_id) throws SQLException {
		
		Connection conn = daoFactory.getConnection();
	    CallableStatement cStmt = conn.prepareCall("{call getPostsByUserId(?)}");
	    cStmt.setInt(1, some_user_id);
	    List<Post> posts =  new ArrayList<Post>();
	    ResultSet rs = cStmt.executeQuery();
	    
	    while(rs.next()) {
	    		    	
	    	posts.add(new Post(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9)));
	        
	    }
	                  
		return posts;
	}

	@Override
	public List<Post> getPopularPosts() throws SQLException {
		Connection conn = daoFactory.getConnection();
	    CallableStatement cStmt = conn.prepareCall("{call getPopularPosts}");
	    List<Post> posts =  new ArrayList<Post>();
	    ResultSet rs = cStmt.executeQuery();
	    while(rs.next()) {
	    		    	
	    	posts.add(new Post(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9)));
	        
	    }
	                  
		return posts;
	}
}