<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Image Compression</title>
<meta name="viewport" content="width=device-width, initial-scale=1"><link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="css/style.css">

  
</head>
<body>
  <div class="user">
    <header class="user__header">
        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/3219/logo.svg" alt="" />
      <font color="black">  <h1>Online Image Compression</h1></font>
       <h4>-Hosted on Amazon EC2</h4>
    </header>
    
    <form class="form" action="compressImage"  method="POST" id="msform" enctype="multipart/form-data">
        <div class="form__group">
        	 <h4>Set the compressed image's quality:</h4>
            <input type="range" name="c_factor" placeholder="Compression Factor" min="0" max="100" class="form__input" />
        </div>
       <br><br>
        <div class="form__group">
        	<h4>Choose image to compress:</h4>
            <input type="file" name="photo" accept="image/*" class="form__input" />
        </div>
        <br><br>
        
        <input type="submit" value="Compress!" class="btn" />
    </form>
    <div align="center" class="form__group"><h2>Made by - SAGAR PARIKH</h2></div>
    
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>