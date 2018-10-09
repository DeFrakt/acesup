//random title
	function random_title(){
	  	var title = ["Idiot's Delight", "Once in a Lifetime", "Ace of the Pile", "Rocket to the Top", "Firing Squad", "Loser Solitaire", "Aces High", "Drivel", "Leggen", "Ace's Up"];
	  	var number = Math.floor(Math.random() * 10);
	  	console.log(number);
	  	return title[number];
	}