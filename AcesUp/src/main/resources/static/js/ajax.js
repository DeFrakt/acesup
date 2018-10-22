$(document).ready(function(){
	//refresh
    window.onload = function (e) {
        $.ajax({
            type: 'GET',
            url: '/api/refresh',
            data: {IsRefresh:'Load'},
            success : function(response) {
            	var data = $.parseJSON(response);
            	//loop through piles
            	var count = 1;
            	$.each(data, function(k, v){
            		$.each(v["pile"], function(k1, v1){
            			 $( "#"+ count ).append('<img src="/images/'+v1["faceName"]+"_"+v1["suit"]+'.png" class="c" id='+v1["suit"]+"_"+v1["faceValue"]+"_"+count+'>');
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
	function moveCard(){
		/*  Check & Enable Card Movement */
	 	var p1LastCard = $("#1 > .c").last().attr('id');
		var p2LastCard = $("#2 > .c").last().attr('id');
		var p3LastCard = $("#3 > .c").last().attr('id');
		var p4LastCard = $("#4 > .c").last().attr('id');
		if(typeof p1LastCard != "undefined"){
			$("#"+p1LastCard).draggable({disabled: false});
		}
		if(typeof p2LastCard != "undefined"){
			$("#"+p2LastCard).draggable({disabled: false});
		}
		if(typeof p3LastCard != "undefined"){
			$("#"+p3LastCard).draggable({disabled: false});
		}
		if(typeof p4LastCard != "undefined"){
			$("#"+p4LastCard).draggable({disabled: false});
		}  
		/* move card */
	     $(".c").draggable({	
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
	        //start animation  
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
      	 $(".c").draggable({disabled: true});
      	 $(".offsetPile").droppable().droppable( "option", "disabled", true );
         $.ajax({
            url : '/api/deal',
            type : "GET",
           	success : function(response) {
            var data = $.parseJSON(response);
            var count = 1;
            if(data == true){
            	$(".pile_container").remove();
            	$(".deal").remove();
            	$(".result").append("<h3 id='win_title'>You win, idiot!</h3><BR>");
            	$(".result").append("<input type='image' type='button' img src='/images/dunce.jpg' width='200' id='win'><BR>");
            	$(".result").append("<input type='submit' value='Play Again?' id='reset' class='btn btn-primary'>");
            	$("#totalPiles").html(0);
            } else if(data == false){
            	$(".pile_container").remove();
            	$(".deal").remove();
            	$(".result").append("<h3>Losing is hard, try again.</h3><BR>");
            	$(".result").append("<input type='image' type='button' img src='/images/lose.png' id='lose'><BR>");
            	$(".result").append("<input type='submit' value='Play Again?' id='reset' class='btn btn-primary'>");
            	$("#totalPiles").html(0);
            } else {
         		$.each(data, function(k, v){
         			$("#totalPiles").html(k);
         			if(k === "0"){
         				$("#start").css({'opacity': 0.3});
         				$(".deal").append("<div class='reshuffle'>Reshuffle</div>");
         			}
         			$.each(v, function(k3, v3){
         				$( "#"+ count ).append('<img src="/images/'+v3["faceName"]+"_"+v3["suit"]+'.png" class="c" id='+v3["suit"]+"_"+v3["faceValue"]+"_"+count+'>');
         				count++;    			
						});
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
	 	var p1LastCard = $("#1 > .c").last().attr('id');
		var p2LastCard = $("#2 > .c").last().attr('id');
		var p3LastCard = $("#3 > .c").last().attr('id');
		var p4LastCard = $("#4 > .c").last().attr('id');
		if(currentCard.localeCompare(p1LastCard) === 0){
			var data = "card="+currentCard;
		} else if(currentCard.localeCompare(p2LastCard) === 0){
			var data = "card="+currentCard;
		} else if(currentCard.localeCompare(p3LastCard) === 0){
			var data = "card="+currentCard;
		} else if(currentCard.localeCompare(p4LastCard) === 0){
			var data = "card="+currentCard;
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
                //update score
                if (data[1].localeCompare("o") === -1){
                	$("#score").html(data[1]);
                }
                //delete card
                console.log(data[0]);
                $("#"+data[0]).remove();
                //movement check
                var count1 = $("#1 > .c").length;
            	var count2 = $("#2 > .c").length;
            	var count3 = $("#3 > .c").length;
            	var count4 = $("#4 > .c").length;
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

//random title
function random_title(){
	var title = ["Ace's Up", "Once in a Lifetime", "Ace of the Pile", "Rocket to the Top", "Firing Squad", "Loser Solitaire", "Aces High", "Drivel", "Leggen", "Idiot's Delight"];
	var number = Math.floor(Math.random() * 10);
	return title[number];
}