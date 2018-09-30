package com.stevenyaussi.acesup.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CardRun {
	public static void main(String[] args) throws IOException {
	
	//Make Deck
	DeckOfCards d = new DeckOfCards();
	System.out.println(d.getDeck());
	//Shuffle Deck
	d.shuffle();
	System.out.println(d.getDeck());
	//Create and Deal Card to Pile 1-4
	CardPile cp1 = new CardPile();
	CardPile cp2 = new CardPile();
	CardPile cp3 = new CardPile();
	CardPile cp4 = new CardPile();
	//Deal Card to Pile 1-4
	cp1.addToPile(d.deal());
	cp2.addToPile(d.deal());
	cp3.addToPile(d.deal());
	cp4.addToPile(d.deal());
	//Create Discard Pile
	CardPile cpDiscard = new CardPile();
	//Remove Card
	cpDiscard.addToPile(cp1.removeFromPile());
//	cpDiscard.addToPile(cp2.removeFromPile());
	cp1.moveCard(cp3.getLastCard(), cp3);
	cp1.moveCard(cp3.getLastCard(), cp3);
	
	
	System.out.println("Pile1: "+cp1.getPile());
	System.out.println("Pile2: "+cp2.getPile());
	System.out.println("Pile3: "+cp3.getPile());
	System.out.println("Pile4: "+cp4.getPile());
//	System.out.println("Discard: "+cpDiscard.getPile());
	System.out.println(d.getDeck());
//	System.out.println(cp1.getPile());
//	System.out.println(p1.getPile());
	}


}
