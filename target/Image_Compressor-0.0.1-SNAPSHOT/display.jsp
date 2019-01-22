<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Compressed Image</title>
<meta name="viewport" content="width=device-width, initial-scale=1"><link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="css/style.css">

  
</head>
<body>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${sessionScope.path}" />
<c:set var="quality" value="${sessionScope.quality}" />

  <div class="user">
    <header class="user__header">
        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/3219/logo.svg" alt="" />
        <h1 >Compressed Image Quality : ${quality}%</h1>
    </header>
    
    <form class="form"  method="POST" id="msform" enctype="multipart/form-data">
        <div class="form__group">
        
            <img src="${path}" class="form__input" alt="Image Not Found!"/>
        </div>
       
        
        <div class="form__group" align="center">
        <a href = "${path}" class="btn"><h4>Download Compressed Image!</h4></a>
        </div>
        
        <div class="form__group" align="center">
       
        <a href = "index.jsp" class="btn"><h4>Compress Again?</h4></a>
        </div>
        
        
    </form>
        <div align="center" class="form__group"><h2>Made by - SAGAR PARIKH</h2></div>
    
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>