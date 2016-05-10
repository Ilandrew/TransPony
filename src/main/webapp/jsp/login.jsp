<%--
  Created by IntelliJ IDEA.
  User: vadim
  Date: 11.04.16
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Log In</title>
  <link rel="stylesheet" href="css/login.css">
  <link rel="stylesheet" href="webjars/bootstrap/3.3.4/css/bootstrap.min.css">
  <link rel="stylesheet" href="webjars/font-awesome/4.6.2/css/font-awesome.css">
</head>
<body class="body">

<div class="container">

  <form action="login" method="post" class="form-login">

    <h2 class="form-login-heading">User authorisation</h2>
    <label for="login" class="sr-only">Login</label>
    <input id="login" type="text" name="login" class="form-control" placeholder="Login"
           required autofocus>

    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="password" class="form-control" placeholder="Password"
           required>

    <button class="btn btn-lg btn-primary btn-block" type="submit" name="button" value="login">
      Login
    </button>

  </form>

</div>

</body>
</html>