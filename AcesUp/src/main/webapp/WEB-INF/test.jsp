<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ace's Up</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
    crossorigin="anonymous">
    <script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
	<script>
	$(document).ready(function() {
	    $('#sampleForm').click(
	        function(event) {
	        	
	            var firstname = $('#firstname').val();
	            var lastname = $('#lastname').val();                
	            var data = 'firstname='
	                    + encodeURIComponent(firstname)
	                    + '&lastname='
	                    + encodeURIComponent(lastname);
	           	
	             $.ajax({
	                url : '/profile',
	                data : data,
	                type : "GET",
	 
	                success : function(response) {
	                    alert( response );
	                },
	                error : function(xhr, status, error) {
	                    alert(xhr.responseText);
	                }
	            });
	            event.preventDefault();
	           /*  return false; */
	        });
	    });
	</script>


	
      
 
<div>
         <input type="text" name="firstname" id="firstname">
     </div>
 
 
      
 
<div>
         <input type="text" name="lastname" id="lastname">
     </div>
 
 
      
 
<div>
         <button name="submit" id="sampleForm">Submit</button>
     </div>
 
 

	
	
     <!-- Optional JavaScript -->
     <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
       crossorigin="anonymous"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
       crossorigin="anonymous"></script>
</body>
</html>