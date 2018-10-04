<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ace's Up</title>
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
    crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>
  	<script src="/js/jquery.ui.touch-punch.min.js"></script>
  	<script src="/js/ajax.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="#"><h4>Aces's Up</h4></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarText">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Game <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Stats</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Preferences</a>
	      </li>
	    </ul>
	    <span class="navbar-text">
	      <c:out value="${user.userName}" /> | <a href="/logout">Logout</a>
	    </span>
	  </div>
	</nav>
	
	<div id="left">
		<p><b><span id="score_title">Score:</span> <span id="score"><c:out value="${score}" /></span></b></p>	
		<p><b><span id="piles_total">Deals:</span> <span id="totalPiles">14</span></b></p>
	</div>
	<div id="right">
		<div class="deal"><input type="image" src="/images/card_blue_back.png" width="120" type="button" value="Deal" id="start" ></div>
	</div>
	<div id="center">
		<div class="container">
		<!-- <div class="menu row">
		    <div class="menu-category list-group ">
	        <div class="menu-category-name list-group-item">Card Pile 1</div>
				<p class='menu-item list-group-item' id="1"/></p>
		    </div>
		    <div class="menu-category list-group ">
	        <div class="menu-category-name list-group-item">Card Pile 2</div>
	       		<p class='menu-item list-group-item' id="2"/></p>
		    </div>
		    <div class="menu-category list-group ">
	        <div class="menu-category-name list-group-item">Card Pile 3</div>
	        	<p class='menu-item list-group-item' id="3"/></p>
		    </div>
		    <div class="menu-category list-group ">
	        <div class="menu-category-name list-group-item ">Card Pile 4</div>
	        	<p class='menu-item list-group-item' id="4"/></p>
		    </div>
		</div> -->
		<div>
			<div class="offsetPile" id="drop1"></div>
	    	<div class="offsetPile" id="drop2"></div>
	    	<div class="offsetPile" id="drop3"></div>
	    	<div class="offsetPile" id="drop4"></div>
	    </div>
	    <div>
		    <div class="pile" id="1"></div>
		    <div class="pile" id="2"></div>
		    <div class="pile" id="3"></div>
		    <div class="pile" id="4"></div>
		</div> 
	</div>
	<form action="/reset">
		<div class="result"></div>
	</form>
	</div>
	
    <!-- Bootstrap -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
      crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
      crossorigin="anonymous"></script>
</body>
</html>