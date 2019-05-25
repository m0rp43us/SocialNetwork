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
import com.dao.PostsDao;

import beans.Post;
import beans.User;

/**
 * Servlet implementation class MakePostServlet
 */
@WebServlet("/post")
@MultipartConfig(
		fileSizeThreshold=1024*1024, // 1MB
		maxFileSize=1024*1024*5      // 5MB
)
public class MakePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/make_a_post.jsp";
	public static String uploadDir = "/ImgUploads/PostsImg/";
	private PostsDao postsDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakePostServlet() {
        super();
        // 
    }
    
    public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.postsDao = daoFactory.getPostsImplDao();
		uploadDir = this.getServletContext().getRealPath(uploadDir);

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		User currentUser = (User) session.getAttribute("currentUser");
		
		if(currentUser == null) response.sendRedirect("/SocialNetwork/login");
		else
			this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		
		String description = request.getParameter("dataDescription");
		if(description != null) {
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
                String fileNameModified = currentUser.getId() + ft.format(now) + "." + extension;
    	        File file = new File(uploads, fileNameModified);
              
              try {
				postsDao.ajouterPost(new Post(fileNameModified,description, currentUser.getId()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
    			try (InputStream input = part.getInputStream()) {
    			    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    			}
    		}
		
		}
		this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
	}
}
