package com.stevenyaussi.acesup.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stevenyaussi.acesup.models.Card;
import com.stevenyaussi.acesup.models.CardPile;
import com.stevenyaussi.acesup.models.DeckOfCards;
import com.stevenyaussi.acesup.services.AcesService;

@RestController
@RequestMapping("/api")
public class AcesApi {
	private final AcesService acesService;
	
	public AcesApi(AcesService acesService) {
        this.acesService = acesService;
    }
	//create pile
	@GetMapping("/deal")
	public String cardPile(HttpSession session) {
		//get deck
		DeckOfCards deckID = (DeckOfCards) session.getAttribute("deck");
		//get CardPiles 1-4
		CardPile cp1 = (CardPile) session.getAttribute("currentP1");
		CardPile cp2 = (CardPile) session.getAttribute("currentP2");
		CardPile cp3 = (CardPile) session.getAttribute("currentP3");
		CardPile cp4 = (CardPile) session.getAttribute("currentP4");
		if (deckID.getDeck().size() == 0) {
			//set last piles list
			List<Card> lastPiles = new ArrayList<>();
			//add to last pile array
			lastPiles.add(cp1.getLastCard());
			lastPiles.add(cp2.getLastCard());
			lastPiles.add(cp3.getLastCard());
			lastPiles.add(cp4.getLastCard());
			//loop and check aces
			int count = 0;
			for (Card card : lastPiles) {
				System.out.println(card);
				if(card.getCard().equals("Clubs_14")) {
					count = count + 1;
					System.out.println("You hit Clubs_14");
				}
				if(card.getCard().equals("Spades_14")) {
					count = count + 1;
					System.out.println("You hit Spades_14");
				}
				if(card.getCard().equals("Diamonds_14")) {
					count = count + 1;
					System.out.println("You hit Diamonds_14");
				}
				if(card.getCard().equals("Hearts_14")) {
					count = count + 1;
					System.out.println("You hit Hearts_14");
				}
			}
			//evaluate win
			if(count == 4) {
				Boolean outcome = true;
				String response = new Gson().toJson(outcome);
				return response;
			} else {
				Boolean outcome = false;
				String response = new Gson().toJson(outcome);
				return response;
			}
		}
		//set display list
		List<Card> newPiles = new ArrayList<>();
		//Deal Card to Pile 1-4
		cp1.addToPile(deckID.deal());
		cp2.addToPile(deckID.deal());
		cp3.addToPile(deckID.deal());
		cp4.addToPile(deckID.deal());
		//add to pile array
		newPiles.add(cp1.getLastCard());
		newPiles.add(cp2.getLastCard());
		newPiles.add(cp3.getLastCard());
		newPiles.add(cp4.getLastCard());
		//reset current pile
		session.setAttribute("currentP1", cp1);
		session.setAttribute("currentP2", cp2);
		session.setAttribute("currentP3", cp3);
		session.setAttribute("currentP4", cp4);
		//print out piles
		System.out.println("CP1: "+ cp1.getPile());
		System.out.println("CP2: "+ cp2.getPile());
		System.out.println("CP3: "+ cp3.getPile());
		System.out.println("CP4: "+ cp4.getPile());
		//convert to JSON 
		String response = new Gson().toJson(newPiles);
		return response;
	}
	@GetMapping("/discard")
	public String discard(@RequestParam("card") String card, HttpSession session, Model model) {
		//get discard pile
		CardPile currentDiscard = (CardPile) session.getAttribute("currentDiscard");
		//get last card in deck
		CardPile currentP1 = (CardPile) session.getAttribute("currentP1");
		CardPile currentP2 = (CardPile) session.getAttribute("currentP2");
		CardPile currentP3 = (CardPile) session.getAttribute("currentP3");
		CardPile currentP4 = (CardPile) session.getAttribute("currentP4");
		//get score
		Integer score = (Integer) session.getAttribute("score");
		//print out piles
		System.out.println("CP1: "+ currentP1.getPile());
		System.out.println("CP2: "+ currentP2.getPile());
		System.out.println("CP3: "+ currentP3.getPile());
		System.out.println("CP4: "+ currentP4.getPile());
		//split chosen card
		List<String> result = new ArrayList<>();
		String[] arrOfCard = card.split("_");
		//loop though card pile 1
		if(currentP1.getPile().isEmpty() == false) {
			String[] arrOfP1 = currentP1.getLastCard().getCard().split("_"); 
	        if(arrOfCard[0].equals(arrOfP1[0])) {
	        	//convert string to integer
	        	int pickedCard = Integer.parseInt(arrOfCard[1]); 
	        	int cardPile = Integer.parseInt(arrOfP1[1]);
	        	int pile = Integer.parseInt(arrOfCard[2]);
	        	//check value & remove
	        	System.out.println(pickedCard + cardPile);
	        	
	        	//check if picked card is less than pile 1
	        	if(pickedCard < cardPile) {
	        		//check pile and remove picked card
	        		if(pile == 1) {
		        			currentDiscard.addToPile(currentP1.removeFromPile());
		        			session.setAttribute("currentP1", currentP1);
		        			System.out.println("Remove: "+ currentP1.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 2) {
		        			currentDiscard.addToPile(currentP2.removeFromPile());
		        			session.setAttribute("currentP2", currentP2);
		        			System.out.println("Remove: "+ currentP2.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 3) {
		        			currentDiscard.addToPile(currentP3.removeFromPile());
		        			session.setAttribute("currentP3", currentP3);
		        			System.out.println("Remove: "+ currentP3.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 4) {
	        			currentDiscard.addToPile(currentP4.removeFromPile());
		        			session.setAttribute("currentP4", currentP4);
		        			System.out.println("Remove: "+ currentP4.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else {
	        			System.out.println("Cannot detect card pile");
	        		}
	        	}	
	        }
		}
    	//loop though card pile 2
		if(currentP2.getPile().isEmpty() == false) {
			String[] arrOfP2 = currentP2.getLastCard().getCard().split("_"); 
	        if(arrOfCard[0].equals(arrOfP2[0])) {
	        	//convert string to integer
	        	int pickedCard = Integer.parseInt(arrOfCard[1]); 
	        	int cardPile = Integer.parseInt(arrOfP2[1]);
	        	int pile = Integer.parseInt(arrOfCard[2]);
	        	//check value & remove
	        	System.out.println(pickedCard + cardPile);
	        	//check if picked card is less than pile 1
	        	if(pickedCard < cardPile) {
	        		//check pile and remove picked card
	        		if(pile == 1) {
		        			currentDiscard.addToPile(currentP1.removeFromPile());
		        			session.setAttribute("currentP1", currentP1);
		        			System.out.println("Remove: "+ currentP1.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 2) {
		        			currentDiscard.addToPile(currentP2.removeFromPile());
		        			session.setAttribute("currentP2", currentP2);
		        			System.out.println("Remove: "+ currentP2.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 3) {
		        			currentDiscard.addToPile(currentP3.removeFromPile());
		        			session.setAttribute("currentP3", currentP3);
		        			System.out.println("Remove: "+ currentP3.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 4) {
	        			currentDiscard.addToPile(currentP4.removeFromPile());
		        			session.setAttribute("currentP4", currentP4);
		        			System.out.println("Remove: "+ currentP4.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else {
	        			System.out.println("Cannot detect card pile");
	        		}
	        	}	
	        }
		}
    	//loop though card pile 3
		if(currentP3.getPile().isEmpty() == false) {
			String[] arrOfP3 = currentP3.getLastCard().getCard().split("_");  
	        if(arrOfCard[0].equals(arrOfP3[0])) {
	        	//convert string to integer
	        	int pickedCard = Integer.parseInt(arrOfCard[1]); 
	        	int cardPile = Integer.parseInt(arrOfP3[1]);
	        	int pile = Integer.parseInt(arrOfCard[2]);
	        	//check value & remove
	        	System.out.println(pickedCard + cardPile);
	        	//check if picked card is less than pile 1
	        	if(pickedCard < cardPile) {
	        		//check pile and remove picked card
	        		if(pile == 1) {
		        			currentDiscard.addToPile(currentP1.removeFromPile());
		        			session.setAttribute("currentP1", currentP1);
		        			System.out.println("Remove: "+ currentP1.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 2) {
		        			currentDiscard.addToPile(currentP2.removeFromPile());
		        			session.setAttribute("currentP2", currentP2);
		        			System.out.println("Remove: "+ currentP2.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 3) {
		        			currentDiscard.addToPile(currentP3.removeFromPile());
		        			session.setAttribute("currentP3", currentP3);
		        			System.out.println("Remove: "+ currentP3.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 4) {
	        			currentDiscard.addToPile(currentP4.removeFromPile());
		        			session.setAttribute("currentP4", currentP4);
		        			System.out.println("Remove: "+ currentP4.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else {
	        			System.out.println("Cannot detect card pile");
	        		}
	        	}	
	        }
		}
    	//loop though card pile 4
		if(currentP4.getPile().isEmpty() == false) {
			String[] arrOfP4 = currentP4.getLastCard().getCard().split("_"); 
	        if(arrOfCard[0].equals(arrOfP4[0])) {
	        	//convert string to integer
	        	int pickedCard = Integer.parseInt(arrOfCard[1]); 
	        	int cardPile = Integer.parseInt(arrOfP4[1]);
	        	int pile = Integer.parseInt(arrOfCard[2]);
	        	//check value & remove
	        	System.out.println(pickedCard + cardPile);
	        	//check if picked card is less than pile 1
	        	if(pickedCard < cardPile) {
	        		//check pile and remove picked card
	        		if(pile == 1) {
		        			currentDiscard.addToPile(currentP1.removeFromPile());
		        			session.setAttribute("currentP1", currentP1);
		        			System.out.println("Remove: "+ currentP1.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 2) {
		        			currentDiscard.addToPile(currentP2.removeFromPile());
		        			session.setAttribute("currentP2", currentP2);
		        			System.out.println("Remove: "+ currentP2.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 3) {
		        			currentDiscard.addToPile(currentP3.removeFromPile());
		        			session.setAttribute("currentP3", currentP3);
		        			System.out.println("Remove: "+ currentP3.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else if(pile == 4) {
	        			currentDiscard.addToPile(currentP4.removeFromPile());
		        			session.setAttribute("currentP4", currentP4);
		        			System.out.println("Remove: "+ currentP4.getPile());
		        			//score +5
		        			int scoreAdded = score + 5;
		        			session.setAttribute("score", scoreAdded);
		        			result.add(card);
		        			result.add(Integer.toString(scoreAdded));
		        			String response = new Gson().toJson(result);
	        			return response;
	        		} else {
	        			System.out.println("Cannot detect card pile");
	        		}
	        	}	
	        }
		}
		
        String response = new Gson().toJson("Nothing Matched");
        return response;
	}
	//move card when pile is empty
		@GetMapping("/move")
		public String cardMove(@RequestParam("emptyPile") String emptyPile, @RequestParam("movingFromPile") String movingFromPile, HttpSession session) {
			//get current CardPiles
			CardPile currentP1 = (CardPile) session.getAttribute("currentP1");
			CardPile currentP2 = (CardPile) session.getAttribute("currentP2");
			CardPile currentP3 = (CardPile) session.getAttribute("currentP3");
			CardPile currentP4 = (CardPile) session.getAttribute("currentP4");
			//check pile 1
			if(emptyPile.equals("1")) {
				if(movingFromPile.equals("2")) {
					currentP1.moveCard(currentP2.getLastCard(), currentP2);
					System.out.println("Pile1: "+currentP1.getPile());
					System.out.println("Pile2: "+currentP2.getPile());
				} else if(movingFromPile.equals("3")){
					currentP1.moveCard(currentP3.getLastCard(), currentP3);
					System.out.println("Pile1: "+currentP1.getPile());
					System.out.println("Pile3: "+currentP3.getPile());
				} else if(movingFromPile.equals("4")){
					currentP1.moveCard(currentP4.getLastCard(), currentP4);
					System.out.println("Pile1: "+currentP1.getPile());
					System.out.println("Pile4: "+currentP4.getPile());
				} else {
					String response = new Gson().toJson("P1 Does Not Match");
					return response;
				}
			}
			//check pile 2
			if(emptyPile.equals("2")) {
				if(movingFromPile.equals("1")) {
					currentP2.moveCard(currentP1.getLastCard(), currentP1);
					System.out.println("Pile1: "+currentP1.getPile());
					System.out.println("Pile2: "+currentP2.getPile());
				} else if(movingFromPile.equals("3")){
					currentP2.moveCard(currentP3.getLastCard(), currentP3);
					System.out.println("Pile2: "+currentP2.getPile());
					System.out.println("Pile3: "+currentP3.getPile());
				} else if(movingFromPile.equals("4")){
					currentP2.moveCard(currentP4.getLastCard(), currentP4);
					System.out.println("Pile2: "+currentP2.getPile());
					System.out.println("Pile4: "+currentP4.getPile());
				} else {
					String response = new Gson().toJson("P2 Does Not Match");
					return response;
				}
			}
			//check pile 3
			if(emptyPile.equals("3")) {
				if(movingFromPile.equals("1")) {
					currentP3.moveCard(currentP1.getLastCard(), currentP1);
					System.out.println("Pile1: "+currentP1.getPile());
					System.out.println("Pile3: "+currentP3.getPile());
				} else if(movingFromPile.equals("2")){
					currentP3.moveCard(currentP2.getLastCard(), currentP2);
					System.out.println("Pile2: "+currentP2.getPile());
					System.out.println("Pile3: "+currentP3.getPile());
				} else if(movingFromPile.equals("4")){
					currentP3.moveCard(currentP4.getLastCard(), currentP4);
					System.out.println("Pile3: "+currentP3.getPile());
					System.out.println("Pile4: "+currentP4.getPile());
				} else {
					String response = new Gson().toJson("P3 Does Not Match");
					return response;
				}
			}
			//check pile 4
			if(emptyPile.equals("4")) {
				if(movingFromPile.equals("1")) {
					currentP4.moveCard(currentP1.getLastCard(), currentP1);
					System.out.println("Pile1: "+currentP1.getPile());
					System.out.println("Pile4: "+currentP4.getPile());
				} else if(movingFromPile.equals("2")){
					currentP4.moveCard(currentP2.getLastCard(), currentP2);
					System.out.println("Pile2: "+currentP2.getPile());
					System.out.println("Pile4: "+currentP4.getPile());
				} else if(movingFromPile.equals("3")){
					currentP4.moveCard(currentP3.getLastCard(), currentP3);
					System.out.println("Pile3: "+currentP3.getPile());
					System.out.println("Pile4: "+currentP4.getPile());
				} else {
					String response = new Gson().toJson("P4 Does Not Match");
					return response;
				}
			}
			
			String response = new Gson().toJson("Move Compele - Backend");
			return response;
		}
		@GetMapping("/refresh")
		public String refresh(HttpSession session) {
			//get CardPiles 1-4
			CardPile cp1 = (CardPile) session.getAttribute("currentP1");
			CardPile cp2 = (CardPile) session.getAttribute("currentP2");
			CardPile cp3 = (CardPile) session.getAttribute("currentP3");
			CardPile cp4 = (CardPile) session.getAttribute("currentP4");
			//set display list
			List<CardPile> newPiles = new ArrayList<>();
			//add to pile array
			newPiles.add(cp1);
			newPiles.add(cp2);
			newPiles.add(cp3);
			newPiles.add(cp4);
			//convert to JSON 
			String response = new Gson().toJson(newPiles);
			return response;
		}
}
