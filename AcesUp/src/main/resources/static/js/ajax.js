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
	            			 $( "#"+ count ).append('<img src="/images/'+v1["faceName"]+"_"+v1["suit"]+'.png" class="card" id='+v1["suit"]+"_"+v1["faceValue"]+"_"+count+'>');
/*	            			 $( "#"+ count ).append('<p class="card" id='+v1["suit"]+"_"+v1["faceValue"]+"_"+count+'><img src="/images/'+v1["faceName"]+"_"+v1["suit"]+'.jpg" width="120"/></p>');
*/
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
				var cardDrop1 = "1";
				$("#drop"+cardDrop1).droppable().droppable( "option", "disabled", false );
			}
			if(typeof p2LastCard == "undefined"){
				var cardDrop2 = "2";
				$("#drop"+cardDrop2).droppable().droppable( "option", "disabled", false );
			}
			if(typeof p3LastCard == "undefined"){
				var cardDrop3 = "3";
				$("#drop"+cardDrop3).droppable().droppable( "option", "disabled", false );
			}
			if(typeof p4LastCard == "undefined"){
				var cardDrop4 = "4";
				$("#drop"+cardDrop4).droppable().droppable( "option", "disabled", false );
			} 
			
		    $("#drop"+cardDrop1+",#drop"+cardDrop2+",#drop"+cardDrop3+",#drop"+cardDrop4).droppable({
		        drop: function (event, ui) {
		        var offset = $('.pile').offset();
		        var top = parseInt($(ui.draggable).css('top')) - offset.top;
		        var left = parseInt($(ui.draggable).css('left')) - offset.left;            
		        $(ui.draggable).appendTo("#"+event.target.id.slice(4, 5));
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
		             	$(this).attr('id', card[0]+'_'+card[1]+'_'+event.target.id.slice(4, 5));
		             	var data = "emptyPile="+event.target.id.slice(4, 5)+"&movingFromPile="+card[2];
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
                	$(".result").append("<h3 id='win_title'>You win, idiot!</h3><BR>");
                	$(".result").append("<input type='image' type='button' img src='/images/dunce.jpg' width='200' id='win'><BR>");
                	$(".result").append("<input type='submit' value='Play Again?' id='reset' class='btn btn-primary'>");
                	$("#totalPiles").html(0);
                } else if(data == false){
                	$(".container").remove();
                	$(".deal").remove();
                	$(".result").append("<h3>Losing is hard, try again.</h3><BR>");
                	$(".result").append("<input type='image' type='button' img src='/images/lose.png' id='lose'><BR>");
                	$(".result").append("<input type='submit' value='Play Again?' id='reset' class='btn btn-primary'>");
                	$("#totalPiles").html(0);
                } else {
             		$.each(data, function(k, v){
             		$("#totalPiles").html(k);
             			$.each(v, function(k3, v3){
             				$( "#"+ count ).append('<img src="/images/'+v3["faceName"]+"_"+v3["suit"]+'.png" class="card" id='+v3["suit"]+"_"+v3["faceValue"]+"_"+count+'>');
             				count++;    			
/* * $( "#" + count ).append('<p class="card" id='+v3["suit"]+"_"+v3["faceValue"]+"_"+count+'><img src="/images/'+v3["faceName"]+"_"+v3["suit"]+'.jpg" width="120"/></p>');   
*/    						});
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
    	$('.pile').click(function(event) {
    		/*  Check that card is last */
    		var currentCard = event.target.id;
    		console.log("pick" + currentCard);
		 	var p1LastCard = $("#1 > .card").last().attr('id');
			var p2LastCard = $("#2 > .card").last().attr('id');
			var p3LastCard = $("#3 > .card").last().attr('id');
			var p4LastCard = $("#4 > .card").last().attr('id');
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
                    console.log(data);
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