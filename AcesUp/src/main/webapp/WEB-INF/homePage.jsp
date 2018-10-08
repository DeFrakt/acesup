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
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
 	<script src="/js/jquery-3.3.1.min.js"></script>
 	<script src="/js/jquery-1.12.1-ui.min.js"></script>
	<script src="/js/jquery.ui.touch-punch.min.js"></script>
  	<script src="/js/ajax.js"></script>
</head>
<body>
	<div id="outer">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#"><h4><script>document.write(random_title());</script></h4></a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarText">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="#">Game <span class="sr-only">(current)</span></a>
		      </li>
		 <!-- v2 preferences  
		 		<li class="nav-item">
		        <a class="nav-link" href="#">Stats</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">Preferences</a>
		      </li> -->
		      <li class="nav-item">
		        <a class="nav-link" href="/reset">Reset Game</a>
		      </li>
		    </ul>
		    <span class="navbar-text">
		      <c:out value="${user.userName}" /> | <a href="/logout">Logout</a>
		    </span>
		  </div>
		</nav>
		<div id="left">
			<p><b><span id="score_title">Score:</span> <span id="score"><c:out value="${score}" /></span></b></p>	
			<p><b><span id="piles_total">Deals:</span> <span id="totalPiles">13</span></b></p>
		</div>
		<div id="right">
			<div class="deal"><input type="image" src="/images/card_blue_back.png" width="120" type="button" value="Deal" id="start" ></div>
		</div>
		<div id="center">
			<div class="container">
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
	</div>	
    <!-- Bootstrap -->
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>