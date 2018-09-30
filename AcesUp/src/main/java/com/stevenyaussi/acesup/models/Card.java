package com.stevenyaussi.acesup.models;

import java.awt.image.BufferedImage;

public class Card {
	private String faceName, suit;
	private int faceValue;
	private transient BufferedImage cardImage;
	
	public Card(String suit, String face, int value) {
		this.suit = suit;
		this.faceName = face;
		this.faceValue = value;
//		this.cardImage = buffered;
	}
	
	public String toString() {
		return  suit+"_"+faceValue;
	} 
	
	public int getFaceValue() {
		return faceValue;
	}
	
	public String getCard() {
		return  suit+"_"+faceValue;
	}
//	public BufferedImage getCardImage() {
//		return cardImage;
//	}
	
}



