package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CommentsDao;
import com.dao.DaoFactory;
import com.dao.NotificationDao;
import com.dao.PostsDao;

import beans.Comment;
import beans.Notification;
import beans.Post;
import beans.User;

/**
 * Servlet implementation class HomePostServlet
 */
@WebServlet("/home")
public class HomePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/home.jsp";
	
	private PostsDao postsDao;
	private CommentsDao commentsDao;
	private NotificationDao notificationDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.postsDao = daoFactory.getPostsImplDao(); 
		this.commentsDao = daoFactory.getCommentsImplDao();
		this.notificationDao = daoFactory.getNotificationImplDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
        
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser != null) {
			List<Post> posts;
			try {
				List<Notification> notifications = notificationDao.getNotificationsByUserId(currentUser.getId());
		        session.setAttribute("notifications" , notifications);
		        
				posts = postsDao.getPostsByUserId(currentUser.getId());
				
				
				List<List<Comment>> comments = new ArrayList<List<Comment>>();;
				
				
				for(Post post:posts) {
					
					List<Comment> comm = commentsDao.getCommentByPostId(post.getId_post());
					comments.add(comm);
					
				}
				
				
				request.setAttribute("posts",posts);
				request.setAttribute("comments", comments);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
		}else response.sendRedirect("/SocialNetwork/login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  HttpSession session = request.getSession(); 
		  User currentUser = (User) session.getAttribute("currentUser");

		  String formName = request.getParameter("upvoteFormSent");
		  if(formName == null) formName = request.getParameter("getCommentFormSent");
		  if(formName == null) formName = request.getParameter("addCommentFormSent");
		  if(formName == null) formName = request.getParameter("deleteFormSent");
		  

		  try {
			  
		    if(formName.equals("upvoteForm")) {
		    	
		      String upvote_status  = request.getParameter("upvote_status");
		      String upvote_postId  = request.getParameter("upvote_postId");
		      if(Integer.parseInt(upvote_status) == 0){
		         postsDao.dislikePost(Integer.parseInt(upvote_postId));
		         Cookie[] cookies =  request.getCookies();
		         for(Cookie c:cookies) {
		        	 if(c.getName().equals(upvote_postId)) {
		        		 c.setMaxAge(0);
		        		 response.addCookie(c);
		        	 }
		         }
		       }
		      else {
		    	  postsDao.likePost(Integer.parseInt(upvote_postId));
		    	  notificationDao.likeNotification(currentUser.getId(),Integer.parseInt(upvote_postId));
		    	  Cookie cookie =new Cookie(Integer.toString(currentUser.getId()), upvote_postId);
		    	  cookie.setMaxAge(60*60*24*30);
		    	  response.addCookie(cookie);
		      }
		    }
		    else if(formName.equals("deleteForm")) {
		    	
		      String id_post = request.getParameter("deletePostId");
		      
		      postsDao.supprimerPost(Integer.parseInt(id_post));
		    }
		    else if(formName.equals("addCommentForm")) {

		      String content = request.getParameter("addedContent");
		      int post_id = Integer.parseInt(request.getParameter("post_id"));
		      
		      int user_id = currentUser.getId();

		      commentsDao.ajouterCommentaire(new Comment(content, post_id, user_id));
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  }

		  doGet(request,response);
	}

}
