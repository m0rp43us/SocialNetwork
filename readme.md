# Project JAVA EE

Construction d'un site web en restant fidèle au modèle MVC

## SocialNetwork

A website similar to twitter where you could create a post or upvote ,comment and save posts .

## Modèle

**Package Beans**

- User.java        
- Post.java        
- Comment.java     
- Notification

**Package Dao**

Charger de la connection et des travaux sur la base de donnée

- DaoFactory <- Chargement et connection à la bdd

- UsersDao    <- *Interface* definitions des fcts principal relatif au User
- PostsDao    <-  " "                                                Post
- CommentsDao <-  " "                                                Comment
- NotificationsDao <- " "                                            Notification

- UsersDaoImpl    <- *Implementation* de l'interface UsersDao
- PostsDaoImpl    <-    " "                          PostsDao
- CommentsDaoImpl <-    " "                          CommentsDao
- NotificationsDaoImpl <- " "                        NotificationsDao    


## VUES

Pages jsp :
- header.jsp          
- login.jsp           
- register.jsp        
- home.jsp            
- popular.jsp         
- make_a_post.jsp     
- settings.jsp        

## Servlets

- ConnectionServlet controles  (login.jsp)    
- RegisterServlet       "     (register.jsp)
- HomePostServlet       "     (home).jsp     
- PopularPostServlet    "     (popular.jsp)  
- MakePostServlet       "     (Make_a_post.jsp)
- SettingsServlet       "     (settings.jsp)  


## Base de donnée


```
-- Users---------------------------------------------------

CREATE TABLE Users (
  id int  NOT NULL AUTO_INCREMENT,
  email  varchar(30) NOT NULL UNIQUE,
  password varchar(20) NOT NULL,
  fullName varchar(20) NOT NULL ,
  profileImg_url varchar(40) DEFAULT 'face.jpg',
  PRIMARY KEY(id)
) ENGINE=InnoDB ;


-- Posts--------------------------------------------------

CREATE TABLE Posts (
  id_post int  NOT NULL AUTO_INCREMENT,
  img_url varchar(40),
  description varchar(200) NOT NULL,
  nbrUpvotes int DEFAULT 0,
  nbrComments int DEFAULT 0,
  user_id int NOT NULL,
  date_publication DATE,
  PRIMARY KEY(id_post),
  CONSTRAINT FK_user_id FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE
) ENGINE=InnoDB ;



-- Comments -----------------------------------------------

CREATE TABLE Comments (
  id_comment int  NOT NULL AUTO_INCREMENT,
  content varchar(150) NOT NULL,
  user_id int NOT NULL,
  post_id int NOT NULL,
  date_publication DATE,
  PRIMARY KEY(id_comment),
  CONSTRAINT FK_user_comment_id FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE,
  CONSTRAINT FK_post_id FOREIGN KEY(post_id) REFERENCES Posts(id_post) ON DELETE CASCADE
) ENGINE=InnoDB ;

-- Notifications ---------------------------------------------

CREATE TABLE Notifications(
  id_notif int NOT NULL AUTO_INCREMENT,
  post_id int NOT NULL,
  user_id int NOT NULL,
  status int NOT NULL,
  PRIMARY KEY(id_notif),
  CONSTRAINT FK_post_id_notif FOREIGN KEY(post_id) REFERENCES Posts(id_post) ON DELETE CASCADE,
  CONSTRAINT FK_user_id_notif FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE
)ENGINE=InnoDB ;



-- Trigger updates comment number in Post and insert the action as notification

  CREATE TRIGGER update_newComment AFTER INSERT
  ON Comments FOR EACH row
  BEGIN
        UPDATE Posts
        SET nbrComments := nbrComments + 1
        WHERE id_post = NEW.post_id;

        INSERT INTO Notifications(post_id, user_id, status)
        VALUES(NEW.post_id, NEW.user_id, 0);
  END
  /


  -- ---------------------------------------------------------------------

  CREATE PROCEDURE getNotificationsByUserId(IN some_user_id int)
    BEGIN
      SELECT Users.profileImg_url, Users.fullName, Notifications.post_id, Notifications.status
      FROM Users , Notifications, (SELECT * FROM posts where user_id = some_user_id) as Posts
      WHERE   Notifications.post_id = Posts.id_post
      AND Notifications.user_id = Users.id;
    END
    /

-- Returns all the comments for a certain post given his id -------------

    -- result: (profilePicture @fullName: comment...)*

  CREATE PROCEDURE getCommentByPostId(IN some_id_post int)
    BEGIN
      SELECT Comments.id_comment, Comments.content,
             Posts.id_post, DATE_FORMAT(Comments.date_publication,"%D %M"),
             Users.id, Users.profileImg_url, Users.fullName

      FROM Comments, Users, Posts
      WHERE Comments.post_id = some_id_post
      AND   Comments.post_id = Posts.id_post
      AND   Comments.user_id = Users.id;
    END
    /


-- ----------------------------------------------------------
-- profileImg_url, Fullname, date_publication, img_url, description
-- nbrUpvotes, nbrComments
-------------------------------------------------------------

CREATE PROCEDURE getPostsByUserId(IN some_id_user int)
  BEGIN
  SELECT  Posts.id_post,Posts.img_url, Posts.description,
          Posts.nbrUpvotes, Posts.nbrComments,
          DATE_FORMAT(Posts.date_publication,"%D %M"),
          Posts.user_id,
          Users.profileImg_url, Users.fullname
    FROM  Users, Posts
    WHERE Users.id = some_id_user
    AND   Posts.user_id = Users.id;
  END
  /

-----------------------------------------------------------

CREATE PROCEDURE getPopularPosts()
  BEGIN
    SELECT  Posts.id_post,Posts.img_url, Posts.description,
            Posts.nbrUpvotes, Posts.nbrComments,
            DATE_FORMAT(Posts.date_publication,"%D %M"),
            Posts.user_id,
            Users.profileImg_url, Users.fullname
    FROM  Users, Posts
    WHERE Posts.user_id = Users.id
    ORDER BY Posts.nbrUpvotes DESC
    LIMIT 10;
  END
  /

```

### API

* [Bootstrap](https://getbootstrap.com/)
* [ScrollReveal](https://scrollrevealjs.org/)
* [Summernote](https://summernote.org/)

