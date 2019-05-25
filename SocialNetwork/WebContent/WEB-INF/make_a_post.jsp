<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Make your post !!</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">



  <link rel="stylesheet" href="css/globalStyle.css"/>

  </head>
  <body>
  <!--Header call*************************************-->
        <jsp:include page="header.jsp">
          <jsp:param name="Post" value="active"/>
        </jsp:include>
        <!--end of header***********************************-->

    <div class="container pt-5">

        <div class="card pb-2 mx-auto px-0" style="width:35rem;">
          <div class="card-header" id="hide">
            <form id = "img_block" method="post" action="post" enctype = "multipart/form-data">
              <div class="form-group">
                <div class="custom-file">
                  <span class="btn btn-default btn-file">
                  <label id = "btn-style"for="imgInp">Upload a picture <i class="fas fa-cloud-upload-alt"></i></label>
                  <input type="file" id="imgInp" name="imgInp" accept=".png, .jpg, .jpeg" onchange="onFileSelected(event)" hidden/>
                  </span>
                </div>
              </div>
              <input type="text" name="dataDescription" id ="dataDescription" value="" hidden/>
            </form>

          </div>
          <div class="card-body">
            <img id='img-upload' class="card-img-top"/>
            <div class="click2edit"></div>
            <hr>
            <button class="btn_color btn btn-primary" onclick="edit()" type="button">Edit</button>
            <button class="btn_color btn btn-primary" onclick="save()" type="button">Post</button>
          </div>
        </div>

    </div>





    <script>



      var edit = function() {
        document.getElementById("hide").style.display = 'block';
        $('.click2edit').summernote({
          placeholder: 'Write your post...',
          disableDragAndDrop: true,
          shortcuts: false,
          toolbar: [
           ['style', ['bold', 'italic', 'underline']],
           ['fontsize', ['fontsize']],
           ['color', ['color']],
           ['para', ['ul', 'ol', 'paragraph']],
           ['height', ['height']]
         ],
          tabsize: 2,
          minHeight: 250,
          maxHeight:250

        });

      };


      var save = function() {
        var markup = $('.click2edit').summernote('code');
        $('.click2edit').summernote('destroy');
        document.getElementById("dataDescription").value = markup;
        //alert(document.getElementById("dataDescription").value);
        document.getElementById("hide").style.display = 'none';
        document.getElementById("img_block").submit();

      };

      // Displaying only image on the card header
      function onFileSelected(event) {
        var fileName = document.getElementById("imgInp").value;
        var idxDot = fileName.lastIndexOf(".") + 1;
        var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
        if (extFile=="jpg" || extFile=="jpeg" || extFile=="png"){
          var selectedFile = event.target.files[0];
          var reader = new FileReader();

          var imgtag = document.getElementById("img-upload");
          imgtag.title = selectedFile.name;

          reader.onload = function(event) {
            imgtag.src = event.target.result;
          };
          reader.readAsDataURL(selectedFile);

        }else{
            alert("Only jpg/jpeg and png files are allowed!");
        }
      }





    </script>

</body>
</html>
