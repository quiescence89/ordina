package de.rieckpil.blog;

import java.util.Iterator;
import java.util.List;

public class WordFrequencyProgram {
   public static void main(String[] args) {
      
      WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzerImpl();

      int highestFrequency = analyzer.calculateHighestFrequency("123Test123test");
      System.out.println(highestFrequency);
      
      int frequencyForWord = analyzer.calculateFrequencyForWord("123Test123test", "Test");
      System.out.println(frequencyForWord);
      
      List<WordFrequency> wordFrequencyList = analyzer.calculateMostFrequentNWords("The sun shines over the lake", 3);
      WordFrequency tempWordFrequency;
      Iterator<WordFrequency> it = wordFrequencyList.iterator();
      while (it.hasNext()) {
    	  tempWordFrequency = it.next();
          System.out.println(tempWordFrequency.getWord());
      }
   }
}
