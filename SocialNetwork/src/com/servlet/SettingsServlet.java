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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.DaoFactory;
import com.dao.UserDao;

import beans.User;

/**
 * Servlet implementation class SettingsServlet
 */
@WebServlet("/settings")
@MultipartConfig(
		fileSizeThreshold=1024*1024, // 1MB
		maxFileSize=1024*1024*5      // 5MB
)

public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE = "/WEB-INF/settings.jsp";
    public static String uploadDir = "ImgUploads/UserProfileImg/";
    private UserDao userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.userDao = daoFactory.getUsersImplDao();
		uploadDir = this.getServletContext().getRealPath(uploadDir);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser != null) this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
		else response.sendRedirect("/SocialNetwork/login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");

		try {
		     		    
		      String password = request.getParameter("password");
		      
		      if(password.equals("")) {
		    	  
		    	  request.setAttribute("errors" , "Missing fields !");
		    	  this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
			      
		      }
		      else {  	
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
		                String fileNameModified = currentUser.getId()  + ft.format(now) + "." + extension;
		                File file = new File(uploads, fileNameModified);
		                
		                
		    			try (InputStream input = part.getInputStream()) {
		    			    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		    			}
		    			userDao.changePassword(currentUser.getId(), password);
		    			userDao.changeProfilImg(currentUser.getId(), fileNameModified);
		    			currentUser.setPassword(password);
		    			currentUser.setProfileImg_url(fileNameModified);
		    			
		    		}
		    		else {
			          
		    			userDao.changePassword(currentUser.getId(), password);
		    			currentUser.setPassword(password);
			          
			        }
			   }

		      
		      		    
		  } catch (SQLException e) { e.printStackTrace(); }
		  	
		doGet(request,response);

	}

}
