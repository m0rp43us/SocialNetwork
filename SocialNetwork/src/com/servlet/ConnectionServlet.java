package com.servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.dao.DaoFactory;
import com.dao.NotificationDao;
import com.dao.UserDao;

import beans.Notification;
import beans.User;


@WebServlet("/login")
@MultipartConfig(
		fileSizeThreshold=1024*1024, // 1MB
		maxFileSize=1024*1024*5      // 5MB
)
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private NotificationDao notificationDao;
    public static final String VUE = "/WEB-INF/login.jsp";
	
	//Ajout de la fct init 
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.userDao = daoFactory.getUsersImplDao(); 
		this.notificationDao = daoFactory.getNotificationImplDao();
	}
       

    public ConnectionServlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Coming from logout link---Cleaning the session
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
		//-----------------------------------------------
		this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Detect which form was submitted register or connect
		  
		  String email = request.getParameter("email");
		  String password = request.getParameter("password");
		  try {

		    User currentUser = userDao.lookForUser(email, password);
		    

	        if(currentUser != null){

	          HttpSession session = request.getSession();
	          //get all the data of the user and stored as a session var
	          session.setAttribute("currentUser" , currentUser);
	          
	          List<Notification> notifications = notificationDao.getNotificationsByUserId(currentUser.getId());
	          session.setAttribute("notifications" , notifications);
	          
	          response.sendRedirect("/SocialNetwork/home");
	          
	        }
	        else {
	          request.setAttribute("wrong_password" , "Wrong email or password");
	          this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
	        }
		    

		  } catch (SQLException e) { e.printStackTrace(); }


	}

}
