<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!doctype html>
<html lang="en">

<head>
  <title>Email Validation</title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
    crossorigin="anonymous">
  <style>
    b {
      color: red;
    }
  </style>
</head>

<body>
  <div class="container">
    <h2>Login and Registration</h2>
    <div class="container col-lg-12 " style="margin-top: 50px; background-color: rgb(226, 226, 226);">
      <div class="row">
        <div class="col col-lg-6 border 1px">
          <h5>Register here!</h5>
          <b><form:errors path="user.*"/></b>
          <b><c:out value="${errorReg}" /></b>
          <form:form method="POST" action="/registration" modelAttribute="user">
            <table class="table table-dark table-striped">
              <tbody>
              	<tr>
                  <td><form:label path="userName">User Name:</form:label></td>
                  <td>
                    <form:input type="text" path="userName" class="form-control"/> 
                  </td>
                  <td>
                  </td>
                </tr>
                <tr>
                  <td><form:label path="email">Email:</form:label></td>
                  <td>
                    <form:input type="email" path="email" class="form-control"/> 
                  </td>
                  <td>
                  </td>
                </tr>
                <tr>
                  <td><form:label path="password">Password:</form:label></td>
                  <td>
                    <form:password path="password" class="form-control"/>
                  </td>
                  <td>
                  </td>
                </tr>
                <tr>
                  <td><form:label path="passwordConfirmation">Password Confirmation:</form:label></td>
                  <td>
                    <form:password path="passwordConfirmation" class="form-control"/>
                  </td>
                  <td>
                  </td>
                </tr>
                <tr>
                  <td></td>
                  <td>
                    <input  class="btn btn-primary" type="submit" value="Submit">
                  </td>
                  <td>
                  </td>
                </tr>
              </tbody>
            </table>
         </form:form>
        </div>
        <div>
          <div class="container">
            <div class="col col-lg-18 border 1px">
              <h5>Login here!</h5>
              <b><c:out value="${error}" /></b>
               <form method="post" action="/login">
                <table class="table table-dark table-striped">
                  <tbody>
                    <tr>
                      <td><label>Email</label></td>
                      <td>
                        <input type="text" name="e" class="form-control"/>
                      </td>
                    </tr>
                    <tr>
                      <td><label for="password">Password</label></td>
                      <td>
                        <input type="password" name="p" class="form-control"/>
                      </td>
                    </tr>
                    <tr>
                      <td></td>
                      <td>
                        <input  class="btn btn-primary" type="submit" value="Submit">
                      </td>
                    </tr>
                  </tbody>
                </table>
              </form>
            </div>
          </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
          crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
          crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
          crossorigin="anonymous"></script>

</body>

</html>