package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.DaoFactory;
import com.dao.UserDao;

import beans.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")

@MultipartConfig(
		fileSizeThreshold=1024*1024, // 1MB
		maxFileSize=1024*1024*5      // 5MB
)
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public static final String VUE = "/WEB-INF/register.jsp";
    public static String uploadDir = "ImgUploads/UserProfileImg/";
    private UserDao userDao;
    
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.userDao = daoFactory.getUsersImplDao();
		uploadDir = this.getServletContext().getRealPath(uploadDir);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  
		  
		  
		  try {

		     
		    
			  String email = request.getParameter("email");
		      String fullName = request.getParameter("fullName");
		      String password = request.getParameter("password");
		      
		      
		      if(fullName.equals("") || password.equals("") || email.equals("")) {
		    	  
		    	  request.setAttribute("errors" , "Missing fields !");
		    	  this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
			      
		      }
		      else {
		    	  Boolean userExists = userDao.lookForUser(email) != null ? true: false; 
		    	  
		    	  if(userExists){
				        request.setAttribute("errors" , "account already exists !");
				        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
				  }
			      else if(!userExists) {
			    	  	
			    	  	File uploads = new File(uploadDir);
			    		//recup le champ du fich
			    		Part part = request.getPart("imgInp"); // Nom du champ fichier dans la jsp
			    		//Verif qu'on a bien un fich

			    		String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

			    		if(fileName != null && !fileName.isEmpty()) {
			    			int i = fileName.lastIndexOf('.');
			    			String extension = fileName.substring(i+1);
			    			Date now = new Date();
			    	        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
			                String fileNameModified = fullName  + ft.format(now) + "." + extension;
			                File file = new File(uploads, fileNameModified);
			                User newUser = new User(email, password, fullName,fileNameModified);
			                userDao.addUser(newUser);
			    			try (InputStream input = part.getInputStream()) {
			    			    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			    			}
			    		}
			    		else {
				          User newUser = new User(email, password, fullName);
				          userDao.addUser(newUser);
				          response.sendRedirect("/SocialNetwork/login");
				          
				        }
			      }

		      }
		      		    
		  } catch (SQLException e) { e.printStackTrace(); }
		  	
		  

	}

}
