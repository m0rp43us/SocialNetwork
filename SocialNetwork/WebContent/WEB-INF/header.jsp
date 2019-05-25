<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
    
<nav class="navbar navbar-expand-lg websiteNav">
      <a class="navbar-brand" href="#"><h2><i class="fab fa-slack"></i></h2></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"><i class="fas fa-bars"></i></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item ${param.Home}">
            <a class="nav-link" href="home">Home</a>
          </li>
          <li class="nav-item ${param.Popular}">
            <a class="nav-link" href="popular">Popular</a>
          </li>
          <li class="nav-item ${param.Post}">
            <a class="nav-link" href="post">Make a post</a>
          </li>
          <li class="nav-item dropdown mr-auto">
            <a class="nav-link dropdown-toggle scrollable-menu" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Notifications <i class="fas fa-bell"></i>
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <c:forEach items = "${sessionScope.notifications}" var="notification">
              <a class="dropdown-item" href="home#${notification.post_id}">
              <span><img src = "ImgUploads/UserProfileImg/${notification.user_profileImg}" class="profil_img_comment"/>
              		${notification.user_fullName} ${notification.status}
              </span>
              </a>
              <hr>
            </c:forEach>
              
            </div>
          </li>
          </ul>
          <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown mr-auto">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Profile <i class="fas fa-user-alt"></i>
              </a>
              <div class="dropdown-menu small_dropdown" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="settings">Setting</a>
                <hr>
                <a class="dropdown-item" href="login">Logout</a>
              </div>
            </li>
          </ul>
      </div>
</nav>
