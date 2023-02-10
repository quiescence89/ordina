package de.rieckpil.blog;

public class WordFrequencyImpl implements WordFrequency {
	
	private int frequency;
	private String word;
	
	WordFrequencyImpl(String word, int frequency){
		this.word = word;
		this.frequency = frequency;
	}
	
	public String getWord(){
		return this.word;
	}
	
	public int getFrequency(){
		return this.frequency;
	}
	
}
