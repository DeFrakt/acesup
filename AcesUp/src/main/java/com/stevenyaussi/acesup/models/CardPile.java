package com.stevenyaussi.acesup.models;

import java.util.ArrayList;
import java.util.List;

public class CardPile {
	public List<Card> pile;
	public Card card;
	
	public CardPile() {
//		this.card = card;
		//array pile
		List<Card> pile = new ArrayList<>();
//		pile.add(this.card);
		this.pile = pile;
	}

	public void addToPile(Card card) {
		pile.add(card);	
	}
	
	public Card removeFromPile() {
		Card d = pile.get(pile.size()-1);
		pile.remove(pile.size()-1);
		return d;
	}
	
	public Card getLastCard() {
		return pile.get(pile.size()-1);
	}
	
	public void moveCard(Card card, CardPile movingPile) {
		if(pile.isEmpty()) {
			addToPile(card);
			movingPile.getPile().remove(movingPile.getPile().size()-1);
		} else {
			System.out.println("Pile is not Empty");
		}
	}

	public List<Card> getPile() {
		return pile;
	}

	public void setPile(List<Card> pile) {
		this.pile = pile;
	}


	
}

