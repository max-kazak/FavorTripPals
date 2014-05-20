<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<html>
    <head>
        <title>Sign In</title>
        <style type="text/css">
           body{
             margin: auto; 
             background-image: url(http://newsprom.ru/i/n/691/190691/tn_190691_124f23ba1325.jpg);
             background-repeat: no-repeat;
             background-size: cover;
           }
        </style>
    </head>
    <body>
        <div class="button">
            <style type="text/css">
               body{
                 margin-left: 44%;
                 margin-top: 30%;
               }
            </style>
            <div>
                <form action="<c:url value="/signin/facebook" />" method="POST">
                    <button type="submit" style="padding: 0; border: 0; width: 70px; height: 70px;">
                        <img src="<c:url value="/resources/facebook.jpg"/>" style="width: 70px;"/>
                    </button>
                    <input type="hidden" name="scope" value="email,publish_stream,offline_access" />
                </form>
            </div>
            <div style="margin-left: 90px; margin-top: -86px;">
                <form action="<c:url value="/signin/vkontakte" />" method="POST">
                    <button type="submit" style="padding: 0; border: 0; width: 70px; height: 70px;">
                        <img src="<c:url value="/resources/vk.jpg"/>" style="width: 70px;"/>
                    </button>
                    <input type="hidden" name="scope" value="notify,friends,offline" />
                </form>
            </div>
        </div>
    </body>
</html>
