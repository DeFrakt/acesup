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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
     <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>
  	<style>
    
    </style>
    <script>

    $(document).ready(function(){
    	//refresh
	    window.onload = function (e) {
	
	        $.ajax({
	            type: 'GET',
	            url: '/api/refresh',
	            data: {IsRefresh:'Load'},
	            success : function(response) {
	            	var data = $.parseJSON(response);
	            	console.log(data);
	            	//loop through piles
	            	var count = 1;
	            	$.each(data, function(k, v){
	            		console.log(v["pile"]);
	            		$.each(v["pile"], function(k1, v1){
	            			console.log(v1);
	            			 $( "#"+ count ).append("<p class='card' id="+v1["suit"]+"_"+v1["faceValue"]+"_"+count+">"+v1["faceName"]+" of "+ v1["suit"]+"</p>");
	                		
	            		});
	            		count++;  	
             		});
	            },
	            error : function(xhr, status, error) {
	              alert(xhr.responseText);
	            }
	        });
	    };
    	/* Move card function*/
		function moveCard()
		{
			/*  Check & Enable Card Movement */
		 	var p1LastCard = $("#1 > .card").last().attr('id');
			var p2LastCard = $("#2 > .card").last().attr('id');
			var p3LastCard = $("#3 > .card").last().attr('id');
			var p4LastCard = $("#4 > .card").last().attr('id');
			if(typeof p1LastCard != "undefined"){
				$("#"+p1LastCard).draggable({disabled: false});
				console.log("P1 Last: "+p1LastCard);
			}
			if(typeof p2LastCard != "undefined"){
				$("#"+p2LastCard).draggable({disabled: false});
				console.log("P2 Last: "+p2LastCard);
			}
			if(typeof p3LastCard != "undefined"){
				$("#"+p3LastCard).draggable({disabled: false});
				console.log("P3 Last: "+p3LastCard);
			}
			if(typeof p4LastCard != "undefined"){
				$("#"+p4LastCard).draggable({disabled: false});
				console.log("P4 Last: "+p4LastCard);
			}  
			/* move card */
 		     $(".card").draggable({	
		       revert: "invalid"
		    });
		    
	 		if(typeof p1LastCard == "undefined"){
				var cardDrop1 = "#1";
				$(cardDrop1).droppable().droppable( "option", "disabled", false );
			}
			if(typeof p2LastCard == "undefined"){
				var cardDrop2 = "#2";
				$(cardDrop2).droppable().droppable( "option", "disabled", false );
			}
			if(typeof p3LastCard == "undefined"){
				var cardDrop3 = "#3";
				$(cardDrop3).droppable().droppable( "option", "disabled", false );
			}
			if(typeof p4LastCard == "undefined"){
				var cardDrop4 = "#4";
				$(cardDrop4).droppable().droppable( "option", "disabled", false );
			} 
			
		    $(cardDrop1+","+cardDrop2+","+cardDrop3+","+cardDrop4).droppable({
		         drop: function (event, ui) {
		          var offset = $('.menu-item').offset();
		          var top = parseInt($(ui.draggable).css('top')) - offset.top;
		          var left = parseInt($(ui.draggable).css('left')) - offset.left;            
		          $(ui.draggable).appendTo(event.target);
		          $(ui.draggable).css({'top' : top, 'left' : left});
		          $(this).droppable( "option", "disabled", true );
		          
		          $(ui.draggable).animate({
		            top: 0,
		            left: 0           
		          }, 200, function() { 
		            // Animation complete
		            	//Card var
						var currentCard = $(this).attr('id');
		             	var card = currentCard.split("_");
		             	$(this).attr('id', card[0]+'_'+card[1]+'_'+event.target.id);
		             	var data = "emptyPile="+event.target.id+"&movingFromPile="+card[2];
		        	    $.ajax({
		                    url : '/api/move',
		                    data : data,  
		                    type : "GET",
		                   	success : function(response) {
		                    var data = $.parseJSON(response);
		      				console.log(data);
		                    
		                  },
		                  error : function(xhr, status, error) {
		                    alert(xhr.responseText);
		                  }
		                });
		          });                
		    
		        }
		    });
		}
    	/* Dealing Deck */
    	$("#start").click(function(event) {
    		 /*  Disable Card Movement */
          	 $(".card").draggable({disabled: true});
             $.ajax({
                url : '/api/deal',
                /* data : data, */
                type : "GET",
               	success : function(response) {
                var data = $.parseJSON(response);
                var count = 1;
                if(data == true){
                	$(".container").remove();
                	$(".deal").remove();
                	$(".result").append("You win, idiot!<BR>");
                	$(".result").append("<input type='submit' value='Play Again?' id='reset'>");
                	console.log("Win");
                } else if(data == false){
                	$(".container").remove();
                	$(".deal").remove();
                	$(".result").append("Losing is hard, try again.<BR>");
                	$(".result").append("<input type='submit' value='Play Again?' id='reset'>");
                	console.log("Lose");
                } else {
             		$.each(data, function(k3, v3){
                		$( "#" + count ).append("<p class='card' id="+v3["suit"]+"_"+v3["faceValue"]+"_"+count+">"+v3["faceName"]+" of "+ v3["suit"]+"</p>");
                		count++;	
             		});
                }
              },
              error : function(xhr, status, error) {
                alert(xhr.responseText);
              }
            });
            event.preventDefault();
        });
    	/* Discard Card */
    	$('p').click(function(event) {
    		/*  Check that card is last */
    		var currentCard = event.target.id;
		 	var p1LastCard = $("#1 > .card").last().attr('id');
			var p2LastCard = $("#2 > .card").last().attr('id');
			var p3LastCard = $("#3 > .card").last().attr('id');
			var p4LastCard = $("#4 > .card").last().attr('id');
			console.log("Current Card: "+currentCard);
			console.log("P1Last: "+p1LastCard);
			console.log("P2Last: "+p2LastCard);
			console.log("P3Last: "+p3LastCard);
			console.log("P4Last: "+p4LastCard);
			if(currentCard.localeCompare(p1LastCard) === 0){
				var data = "card="+currentCard;
				console.log("Discard Card is Last1: "+data);
			} else if(currentCard.localeCompare(p2LastCard) === 0){
				var data = "card="+currentCard;
				console.log("Discard Card is Last2: "+data);
			} else if(currentCard.localeCompare(p3LastCard) === 0){
				var data = "card="+currentCard;
				console.log("Discard Card is Last3: "+data);
			} else if(currentCard.localeCompare(p4LastCard) === 0){
				var data = "card="+currentCard;
				console.log("Discard Card is Last4: "+data);
			} else {
				var data ="card=Diamonds_15_2";
			}
			
			/*  send over card data  */
                  $.ajax({
                    url : '/api/discard',
                    data : data,
                    type : "GET",
                   	success : function(response) {
                    var data = $.parseJSON(response);
                    console.log(data[1]);
                    //update score
                    if (data[1].localeCompare("o") === -1){
                    	$("#score").html(data[1]);
                    }
                    //delete card
                    $( "#" + data[0] ).remove();
                    //movement check
                    var count1 = $("#1 > .card").length;
                	var count2 = $("#2 > .card").length;
                	var count3 = $("#3 > .card").length;
                	var count4 = $("#4 > .card").length;
                	console.log("===========");
                	console.log("C1: "+count1);
                	console.log("C2: "+count2);
                	console.log("C3: "+count3);
                	console.log("C4: "+count4);
                	if(count1 == 0 || count2 == 0 || count3 == 0 || count4 == 0){       		
                		$(moveCard); 
                	}
                	
                  },
                  error : function(xhr, status, error) {
                    alert(xhr.responseText);
                  }
                });
                event.preventDefault();
            });
    		
    });   

    </script>
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
	      <c:out value="${user.userName}" /> | Score: <span id="score"><c:out value="${score}" /></span> | <a href="/logout">Logout</a>
	    </span>
	  </div>
	</nav>
	<div class="container">
		<div class="menu row">
		    <div class="menu-category list-group ">
	        <div class="menu-category-name list-group-item active">Card Pile 1</div>
				<p class='menu-item list-group-item' id="1"/></p>
		    </div>
		    <div class="menu-category list-group ">
	        <div class="menu-category-name list-group-item active">Card Pile 2</div>
	       		<p class='menu-item list-group-item' id="2"/></p>
		    </div>
		    <div class="menu-category list-group ">
	        <div class="menu-category-name list-group-item active">Card Pile 3</div>
	        	<p class='menu-item list-group-item' id="3"/></p>
		    </div>
		    <div class="menu-category list-group ">
	        <div class="menu-category-name list-group-item active">Card Pile 4</div>
	        	<p class='menu-item list-group-item' id="4"/></p>
		    </div>
		</div>
	</div>
	<div class="deal"><input type="button" value="Deal" id="start"></div>
	<form action="/reset">
		<div class="result"></div>
	</form>
    <!-- Bootstrap -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
      crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
      crossorigin="anonymous"></script>
</body>
</html>