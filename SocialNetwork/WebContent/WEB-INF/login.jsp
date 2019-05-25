<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


  <link rel="stylesheet" href="css/bootstrap.min.css">
  
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

  <script src="https://unpkg.com/scrollreveal"></script>
  <script>
        ScrollReveal({ reset: true });
  </script>

  <title>Login Page</title>

  <style>
  body {
    background-image: url("backImg/wall1.png");
    background-attachment: fixed;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }

  .btn_color {
    background: #008080;
  }

  </style>

</head>
<body>

  <div class="container">


      <div class="card pb-2 mx-auto px-0" style="width:25rem; margin-top: 100px; border:#008080 solid 4px;">
        <h1 class="card-header text-center"><i class="fab fa-slack"></i><br><small class='hd'>Welcome !</small></h1>
        <div class="card-body">
          <form  action = "login" method="post">
            <div class="form-group">
              <label for="email">Email address</label>
              <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email">
            </div>
            <div class="form-group">
              <label for="password" >Password</label>
              <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>

            <c:if test ="${!empty wrong_password}">
                <em style = "color: rgb(250,0,0); display: block; margin-bottom: 10px;">${wrong_password}</em>
            </c:if>
            <input type="hidden"  id="formName" name ="formName" value="connectionForm"/>

            <button type="submit" class="btn btn-primary">Submit</button>
            
            <button type="button" class="btn btn-success" onclick="register()">New here ? Register</button>
            
            
          </form>
        </div>
    </div>
    
    

  </div>

  <script>
    ScrollReveal().reveal('.hd' ,{
      delay: 250,
      duration:1500,
      distance: '100px'
    });
    ScrollReveal().reveal('.fab', {
      delay: 250 ,
      rotate: { x: 180, y: 180, z: 5 },
      duration: 1500,
      origin:'top',
      distance:'100px',
      viewFactor: 0.2
    });

    function register() {
    	window.location.href = "register";
    }

  </script>

  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
  <script src="js/bootstrap.min.js"></script>
</body>

</html>
