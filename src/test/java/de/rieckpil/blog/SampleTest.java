package de.rieckpil.blog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SampleTest {
	
	@Test
	public void calculateHighestFrequencyTest() {
		WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzerImpl();
		int highestFrequency = analyzer.calculateHighestFrequency("123Test123test");
		assertEquals(2, highestFrequency);
	}
	 
	@Test
	public void calculateFrequencyForWordTest() {
		WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzerImpl();
		int frequencyForWord = analyzer.calculateFrequencyForWord("123Test123test", "Test");
		assertEquals(2, frequencyForWord);
	}
     
	@Test
	public void calculateMostFrequentNWordsTest() {
		WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzerImpl();
		List<WordFrequency> wordFrequencyList = analyzer.calculateMostFrequentNWords("The sun shines over the lake", 3);
	    WordFrequency tempWordFrequency;
	    Iterator<WordFrequency> it = wordFrequencyList.iterator();
	    int i = 0;
	    ArrayList<String> str = new ArrayList<String>();
        str.add("the");
        str.add("lake");
        str.add("over");
	    while (it.hasNext()) {
	    	tempWordFrequency = it.next();
	    	assertEquals(str.get(i),tempWordFrequency.getWord());
	    	i++;
	    }
	}
	
}
