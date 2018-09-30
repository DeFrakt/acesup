package com.stevenyaussi.acesup.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.Table;


public class DeckOfCards {
	public List<Card> deck;
	
	
	public DeckOfCards() {
		//suit
		String [] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
		//type
		String [] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
		//deck array
		List<Card> deck = new ArrayList<>();
		
		//img coordinates
//		final int width = 100;
//		final int height = 100;
//		final int col = 13;
//		final int rows = 4;
		//sub image
//		BufferedImage bigImage = ImageIO.read(new File("./src/main/resources/static/images/cardDeck.jpg"));
//		BufferedImage tempCardImage;
		
		//loop suit
		for(int suit = 0; suit < 4; suit++) {
		//loop faces
			for(int face = 0; face < 13; face++) {
//				tempCardImage = bigImage.getSubimage(((face*width)+(face*9)), ((suit*height) + (suit*14)), width, height);
				Card c = new Card(suits[suit],faces[face], face+2);	
				deck.add(c);
//				new ImageIcon(c.getCardImage());
			}
		}
//		//win test
//		Card c1 = new Card("Hearts","Ace", 14);
//		Card c2 = new Card("Diamonds","Ace", 14);
//		Card c3 = new Card("Clubs","Ace", 14);
//		Card c4 = new Card("Spades","Ace", 14);
//		deck.add(c1);
//		deck.add(c2);
//		deck.add(c3);
//		deck.add(c4);
		this.deck = deck;
	}

	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	
	public Card deal(){
		Card d = deck.get(0);
		deck.remove(0);
		return d;
	}
	
	

}


