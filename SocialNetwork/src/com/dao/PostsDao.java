package com.dao;

import java.sql.SQLException;
import java.util.List;

import beans.Post;

public interface PostsDao {

	  void ajouterPost(Post newPost) throws SQLException;
	  void supprimerPost(int oldPost_id) throws SQLException;
	  void likePost(int post_id) throws SQLException;;
	  void dislikePost(int post_id) throws SQLException;
	  List<Post> getPostsByUserId(int some_user_id) throws SQLException;
	  List<Post> getPopularPosts() throws SQLException;

}
