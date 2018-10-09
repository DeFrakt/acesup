<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Idiot's Delight</title>
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
 	<script src="/js/jquery-3.3.1.min.js"></script>
	<script src="/js/jquery.ui.touch-punch.min.js"></script>
	<style>
	#left > p{
		font-weight: bold;
	}
	</style>
</head>
<body>
	<div id="outer">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#"><h4>Idiot's Delight</h4></a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarText">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item">
		        <a class="nav-link" href="/reset">Game</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/rules">Rules<span class="sr-only">(current)</span></a>
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
			<P>1</P>
			<P>2</P>
			<BR>
			<P>3</P>
			<P>4</P>
			<BR>
			<P>5</P>
			<BR>
			<P>6</P>
			<P>7</P>
		</div>
		<div id="right">
			<div class="deal"><input type="image" src="/images/card_blue_back.png" width="120" type="button" value="Deal" id="start" ></div>
			<P>Push button, (blue card), to <b>deal</b></P>
		</div>
		<div id="center_rules">
			<P><b>Deal</b> cards by pushing blue card deck.</P>
			<P>If there are two or more cards of the same suit, <b>discard</b> all but the highest-ranked card of that suit. Aces rank high.</P>
			<P>Repeat step 2 until there are no more pairs of cards with the same suit.</P>
			<P>Whenever there are any empty spaces, you may choose the top card of another pile to <b>move</b> to the empty space. After you do this, go to Step 2.</P>
			<P>When there are no more cards to move or remove, <b>deal</b> out the next cards.</P>
			<P>Repeat Step 2, using only the visible, or top, cards on each of the four piles.</P>
			<P>When the last four cards have been dealt out and any moves made, the game is over. The fewer cards left in the tableau, the better. To win is to have only the four aces left.
			When the game ends, the number of discarded cards is your score. </P>
			<hr>
			<P><B>Deal</B>: push blue card (top-right corner)</P>
			<P><B>Discard</B>: push card you would like to remove</P>
			<P><B>Move</B>: hold and drag to empty pile</P>
		</div>	
    <!-- Bootstrap -->
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>

