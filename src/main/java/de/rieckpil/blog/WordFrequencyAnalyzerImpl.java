package de.rieckpil.blog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;

@Path("analyze")
public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {
	
	private Map<String, Integer> wordsFound; 
	private int highestFrequency = 0;
	private String previousTextAnalyzed = "";
	
	private void findWords(String text){
		String wordFound;
		wordsFound = new HashMap<>();
		Matcher matcher = Pattern.compile("[A-z]+").matcher(text);
		while(matcher.find()){
			wordFound = matcher.group().toLowerCase();
			wordsFound.put(wordFound, wordsFound.getOrDefault(wordFound, 0) + 1);
		}
	}
	
	public int calculateHighestFrequency(String text){
		if (text != previousTextAnalyzed) findWords(text);
		for (Map.Entry<String, Integer> frequency : wordsFound.entrySet()) {
			//branchless bithack for extra performance, result is equivalent to max(x,y) 
			highestFrequency = highestFrequency - ((highestFrequency - frequency.getValue()) 
				& ((highestFrequency - frequency.getValue()) >> (31)));
		}
		return highestFrequency;
	}
	
	public int calculateFrequencyForWord (String text, String word){
		if (text != previousTextAnalyzed) findWords(text);
		return wordsFound.get(word.toLowerCase());
	}
	
	public List<WordFrequency> calculateMostFrequentNWords (String text, int n){
		if (text != previousTextAnalyzed) findWords(text);
		Set<Map.Entry<String,Integer> > wordsFoundSet = wordsFound.entrySet();
		List<Map.Entry<String,Integer>> wordsFoundList=new ArrayList<>(wordsFoundSet);
		List<WordFrequency> wordFrequencyList = new ArrayList<WordFrequency>();
		wordsFoundList.sort(new Comparator<Map.Entry<String,Integer>>() {
			public int compare(Map.Entry<String,Integer> entry1, Map.Entry<String,Integer> entry2) {
				return entry1.getKey().compareTo(entry2.getKey());
			}});
		wordsFoundList.sort(new Comparator<Map.Entry<String,Integer>>() {
			public int compare(Map.Entry<String,Integer> entry1, Map.Entry<String,Integer> entry2) {
				return entry2.getValue().compareTo(entry1.getValue());
			}});
		for (Map.Entry<String, Integer> wordFound : wordsFoundList) {
			WordFrequency tempWordFrequency = new WordFrequencyImpl(wordFound.getKey(), wordFound.getValue());
			wordFrequencyList.add(tempWordFrequency);
			n--;
			if (n <= 0) break;
		}
		return wordFrequencyList;
	}
	
	@GET
	@Path("calculateHighestFrequency/{text}")
	public Response returnCalculateHighestFrequency(@PathParam("text") String textString) {
		String returnString = Integer.toString(calculateHighestFrequency(textString));
		return Response.ok(returnString).build();
	}
	
	@GET
	@Path("calculateFrequencyForWord/{text}/{word}")
	public Response returnCalculateFrequencyForWord(@PathParam("text") String textString, @PathParam("word") String wordString) {
		String returnString = Integer.toString(calculateFrequencyForWord(textString, wordString));
		return Response.ok(returnString).build();
	}
	
	@GET
	@Path("calculateMostFrequentNWords/{text}/{n}")
	public Response returnCalculateHighestFrequency(@PathParam("text") String textString, @PathParam("n") int n) {
		List<WordFrequency> wordFrequencyList = calculateMostFrequentNWords(textString, n);
	    WordFrequency tempWordFrequency;
	    String returnString = "<br>";
	    Iterator<WordFrequency> it = wordFrequencyList.iterator();
	    while (it.hasNext()) {
			tempWordFrequency = it.next();
			returnString = tempWordFrequency.getWord() + returnString;
	    }
		return Response.ok(returnString).build();
	}
		
}
