package wordladder2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
	private LinkedList<List<String>> queue = new LinkedList<List<String>>();;
	// dictionary
	private Set<String>		 dict;
	// start word
	private String 			 start;
	// target word
	private String 			 end;
	// shortest length so far
	private int 			 shortestLength = -1;
	// ladders
	private List<List<String>> ladders = new ArrayList<List<String>>();
	// current search depth
	private int currentDepth = 1;
	
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
    	this.start = start;
    	this.end = end;
    	this.dict = dict;
    	
    	// build word index
    	buildIndex();
    	
    	// start search
    	search(start);
    	
    	return ladders;
    }
	
	// initializes the breadth-first search
	private void search(String word) {
		// initialize queue with start word
		List<String> path = new ArrayList<String>();
		path.add(word);
		
		queue.push(path);
		
		// process queue until it's empty
		while (!queue.isEmpty()) {
			search();
		}
		
		// never got any match - return 0
		if (shortestLength == -1) shortestLength = 0;
	}
	
	// remembers candidates at level k
	Map<Integer,Set<String>> candidatesAtDepth = new HashMap<Integer,Set<String>>();
	
	// the set of words that can be ignored because they're considered at higher levels
	// of the breadth-first search
	Set<String> ignore = new HashSet<String>();
	
	// process a queue item
	private void search() {
		// get item at top of the queue
		List<String> path = queue.pop();
		int depth = path.size();
		// if we're past the shortest length found so far - just stop
		if (shortestLength != -1 && depth > shortestLength) return;
		// if we're exploring a lower level
		if (depth != currentDepth) {
			currentDepth = depth;
			ignore.clear();
			// rebuild the list of words to explore
			for (int i = path.size() - 1; i >= 1; i--) {
				Set<String> cand = candidatesAtDepth.get(i);
				if (cand != null) ignore.addAll(cand);
			}
		}
		
		// find words in dict at 1 letter distance from current or less
		Set<String> neighbors = buildCandidates(path);
		
		// for each candidate
		for (String neighbor: neighbors) {
			// if it's a solution
			if (isSolution(neighbor, path)) {
				// process it
				processSolution(neighbor, path);
			}
			
			// TODO is this condition still necessary?
			if (!path.contains(neighbor)) {
				// queue this candidate, to be explored later
				List<String> nextPath = new ArrayList<String>(path);
				nextPath.add(neighbor);
				queue.add(nextPath);
			}
		}
		
		// now record all those words as candidates at the current depth
		Set<String> candidates = candidatesAtDepth.get(depth);
		if (candidates == null) {
			candidates = new HashSet<String>();
			candidatesAtDepth.put(depth, candidates);
		}
		candidates.addAll(neighbors);
		
	}
	
	// indicates whether this word is a solution, given the current path from the start word
	private boolean isSolution(String word, List<String> path) {
		// calculate distance from word to end
		int d = calcDistance(word, end);
		// distance has to be less than 1
		return ( d <= 1 );
	}
	
	// process the given solution
	private void processSolution(String neighbor, List<String> path) {	
		// calculate path length
		int length = path.size() + 1; // adding last word
		if (!neighbor.equals(end)) {  // adding end word if necessary
			length++;
		}
		
		// stop here if the path is longer than the shortest path
		if (shortestLength != -1 && length > shortestLength) 
			return;
		
		// if we found a shortest path
		if (length < shortestLength || shortestLength == -1) {
			shortestLength = length;
			// remove all solutions found so far
			ladders.clear();
		}
		
		// add the complete path to the list of solutions
		List<String> p = new ArrayList<String>(path);
		p.add(neighbor);
		if (!p.contains(end)) p.add(end);
		if (!ladders.contains(p)) {
			ladders.add(p);
		}
	}
	
	// calculates the distance from s1 to s2
	private int calcDistance(String s1, String s2) {
		int d = 0;
		int len = s1.length();
		// we compare each character holding the same position
		// in both strings
		for (int i = 0; i < len; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				d++;
				if (d > 1) {
					break;
				}
			}
		}
		return d;
	}
	
	// builds the set of candidates for a given word
	private Set<String> buildCandidates(List<String> path) {
		// we're only interested in the last word in the path
		int index = path.size() - 1;
		String word = path.get(index);
		// this set will contain all candidates
		Set<String> candidates = new HashSet<String>();
		// get the words indexed for this word
		Set<String> windex = wordIndex.get(word);
		if (windex != null)
			candidates.addAll(windex);
		// remove all words that are in the 'ignore' list
		candidates.removeAll(ignore);
		// if the words are of length 1
		if (word.length() == 1) {
			// all words in the dict are candidates
			candidates.addAll(dict);
		}
	
		return candidates;
	}
	
	// contains the map of words -> legal words to transition to
    private Map<String,Set<String>> wordIndex = new HashMap<String,Set<String>>();
    
    // builds the word index
    // word -> list of words in the dictionary that are 1 letter away
    private void buildIndex() {
    	Set<String> d = new HashSet<String>(dict);
    	// add start word since it needs to be indexed
    	d.add(start);
    	// this contains the prefix/suffix->word map
    	Map<String,Set<String>> indexPrefixSuffix = new HashMap<String,Set<String>>();
    	// index each word
    	for (String w: d) {
    		index(w, indexPrefixSuffix);
    	}
    	// we can now build the word index
    	for (String word: d) {
    		Set<String> candidates = new HashSet<String>();
    		int n = word.length();
    		for (int i = 0; i < n; i++) {
    			StringBuffer buf = new StringBuffer(word);
    			buf.replace(i, i + 1, ".");
        		Set<String> listPrefix = indexPrefixSuffix.get(buf.toString());
        		
        		if (listPrefix != null) {
        			candidates.addAll(listPrefix);
        		}
    		}
    		// candidates contain all words in the dict which are 1 letter away
    		wordIndex.put(word, candidates);
    	}
    }
    
    // index word against the set of possible prefix/suffix
    // e.g. ".anny", "d.nny", "da.ny", "dan.y", "dann."
    private void index(String word, Map<String,Set<String>> indexPrefixSuffix) {
    	int n = word.length();
    	for (int i = 0; i < n; i++) {
    		StringBuffer buf = new StringBuffer(word);
			buf.replace(i, i + 1, ".");
    		Set<String> index = null;
    		if (buf.length() > 0) {
    			index = indexPrefixSuffix.get(buf.toString());
    			if (index == null) {
    				index = new HashSet<String>();
    				indexPrefixSuffix.put(buf.toString(), index);
    			}
    			index.add(word);
    		}
    	}
    }
}
