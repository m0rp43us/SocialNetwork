<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Home page</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	<link rel="stylesheet" href="css/globalStyle.css">

  </head>
  <body onload="color()">
        <!--Header call*************************************-->
        <jsp:include page="header.jsp">
          <jsp:param name="Home" value="active"/>
        </jsp:include>
        <!--end of header***********************************-->

    <div class="container">


      <c:forEach items="${ posts }" var="post" varStatus="boucle">
        <div class="card pb-2 mb-1 mx-auto px-0 ${post.id_post}" style="width:35rem;">
          <div class="card-header">
            <nav class="navbar py-0 my-1" style="height:24px ! important">
    <!---->       <img class="navbar-brand profilePic ml-0" src = "ImgUploads/UserProfileImg/${sessionScope.currentUser.profileImg_url}" />
    <!---->       <span class="navbar-nav text-muted mr-auto user_fullName">@${sessionScope.currentUser.fullName}</span>
    <!---->       <span class="navbar-nav text-muted ml-auto date_publication ">${post.date_publication}</span>
            </nav>
          </div>

          <div class="card-body py-1">
            <c:if test="${!empty post.post_img_url}">
              <img class="card-img-top my-3" src = "ImgUploads/PostsImg/${post.post_img_url}" />
            </c:if>
            <div class="content">${post.description}</div>
            <hr>
    <!---->     <button class="btn_color btn btn-primary upvotesBtn"  onclick="upvote('${boucle.index}','${post.id_post}')" type="button"><i class="far fa-heart"></i></button>
    <!---->     <span class="text-muted upvotes_numbre">${post.nbrUpvotes}</span>
                <input type = "hidden" class="idPost" style="display:none" value ='${post.id_post}'/>
    <!---->     <button class="btn_color btn btn-primary commentsBtn" type="button" data-toggle="modal" data-target=".${boucle.index}"><i class="fas fa-comments"></i></button>
    <!---->     <span class="text-muted comments_number" >${post.nbrComments}</span>
                <button class="btn btn-danger deleteBtn" type="button" onclick="delete_post('${post.id_post}')"><i class="fas fa-trash-alt"></i></button>
            <br>

          </div>
        </div>



        <!--********Comments Modal****************************  -->
            <div class="modal fade ${boucle.index}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title">Comments !</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <div class="AddComment"></div>
                    <table class="table table-striped table-hover commentTable">

                      <tbody>
                      	<c:set var="i" value="${boucle.index }"/>
                        <c:forEach items="${comments[i]}" var="comment" varStatus="loop" >
	                        <tr>
	                          <th><img class="profil_img_comment" src="ImgUploads/UserProfileImg/${comment.user_profileImg}" alt=""></th>
	                          <td>${comment.user_fullName}</td>
	                          <td style="width:60%">${comment.content}</td>
	                        </tr>
                        </c:forEach>
                      </tbody>

                    <br>
                  </table>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addComment('${boucle.index}' ,'${post.id_post}')">Add a comment</button>
                  </div>
                </div>
              </div>
            </div>
          </c:forEach>



      <!--Hidden forms**********************************  -->

          <iframe name="noReload" style="display:none;"></iframe>

          <div style="display:none;">

	          <form id="upvoteForm" method="post" action="home" target="noReload">
	            <input type="text" name="upvote_status" id ="upvote_status" value="" />
	            <input type="text" name="upvote_postId" id ="upvote_postId" value="" />
              <input type="text" name="upvoteFormSent" id ="upvoteFormSent" value="upvoteForm"/>
            </form>


	          <form id="addCommentForm" method="post" action="home" target="noReload" >
	            <input type="text" name="addedContent" id ="addedContent" value=""/>
	            <input type="text" name="post_id" id ="post_id" value=""/>
              <input type="text" name="addCommentFormSent" id ="addCommentFormSent" value="addCommentForm"/>

	          </form>

            <form id="deleteFormSent" method="post" action="home">
              <input type="text" name="deletePostId" id ="deletePostId" value="" />
              <input type="text" name="deleteFormSent" id ="deleteFormSent" value="deleteForm"/>
            </form>

          </div>

      <!--End of hidden forms ****************************** -->


    </div>


	<script>var arrayOfCookies = [];</script>

	<c:forEach items="${cookie}" var="currentCookie" varStatus = "loop">
		<c:if test = "${currentCookie.value.name == (sessionScope.currentUser.id).toString()}"> 
		<script>
		arrayOfCookies[${loop.index}] =  ${currentCookie.value.value};
		</script>
		</c:if>
	</c:forEach>    

    <script>
	
       
    var i = document.getElementsByClassName("upvotesBtn").length
    var red = [];
	function color() {
		
	      var j;
	      for(j = 0; j < i; j++) {
	    	  
	    	  if(arrayOfCookies.indexOf(parseInt(document.getElementsByClassName("idPost")[j].value)) > -1){
	    		  red[j] = 1;
	    		  document.getElementsByClassName("upvotesBtn")[j].style.background = "#FF0000";
	    	  } 
	    	  else {
	    		  red[j] = 0;
	    	  }
	      }
	}  

      function upvote(i , j) {
        if(red[i] == 0) {
          red[i] = 1;
          document.getElementsByClassName("upvotesBtn")[i].style.background = "#FF0000";
          document.getElementsByClassName("upvotes_numbre")[i].innerHTML = parseInt(document.getElementsByClassName("upvotes_numbre")[i].innerHTML) + 1;
          document.getElementById("upvote_status").value = "1" ;
          document.getElementById("upvote_postId").value = j;
          
        }
        else {
          red[i] = 0;
          document.getElementsByClassName("upvotesBtn")[i].style.background = "#008080";
          document.getElementsByClassName("upvotes_numbre")[i].innerHTML = parseInt(document.getElementsByClassName("upvotes_numbre")[i].innerHTML) - 1;
          document.getElementById("upvote_status").value = "0" ;
          document.getElementById("upvote_postId").value = j;
        }
        // sending [0 -> -1 upvote && 0 -> +1 upvote ] with the id_post

        document.getElementById("upvoteForm").submit();
      }

      function delete_post(i) {
        document.getElementById("deletePostId").value = i;
        document.getElementById("deleteFormSent").submit();
      }




    </script>


    <script>

          $('.AddComment').summernote({
              placeholder: 'Write your comment here...',
              disableDragAndDrop: true,
              shortcuts: false,
              toolbar: [], //Empty toolbar
              tabsize: 2,
              height: 100,
              maxHeight: 80,
              minHeight: 80
            });


            //params -> i= index , j= post_id
            function addComment(i , j) {

              var tr = document.createElement("tr");
              var th = document.createElement("th");
              var img = document.createElement("img");
              var td_name = document.createElement("td");
              var td_comment = document.createElement("td");
              img.className = "profil_img_comment";
              img.src = "ImgUploads/UserProfileImg/${sessionScope.currentUser.profileImg_url}";

              td_name.innerHTML = "${sessionScope.currentUser.fullName}";
              var AddCommentI = ".AddComment:eq("+ i.toString() + ")";
              var markup = $(AddCommentI).summernote('code');


              td_comment.innerHTML = markup;

              th.appendChild(img);

              tr.appendChild(th);
              tr.appendChild(td_name);
              tr.appendChild(td_comment);


              document.getElementsByClassName("commentTable")[i].appendChild(tr);

              document.getElementsByClassName("comments_number")[i].innerHTML = parseInt(document.getElementsByClassName("comments_number")[i].innerHTML) + 1;

              $(AddCommentI).summernote('reset');

              document.getElementById("addedContent").value = markup;
              document.getElementById("post_id").value = j;

              document.getElementById("addCommentForm").submit();

            }

    </script>






  </body>
