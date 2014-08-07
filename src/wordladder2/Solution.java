package wordladder2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {
	private LinkedList<Move> queue = new LinkedList<Move>();;
	// dictionary
	private Set<String>		 dict;
	// target word
	private String 			 end;
	// shortest length so far
	private int 			 shortestLength = -1;
	// ladders
	private List<List<String>> ladders = new ArrayList<List<String>>();
	
	// data structure holds a "move"
	// i.e. given a path so far, a word that we want to evaluate
	// obviously we could only keep track on the search depth
	//
	private class Move {
		String       word;
		List<String> path;
		
		public Move(String word, List<String> path) {
			this.word = word;
			this.path = path;
		}
		public String toString() {
			return path + " -> " + word;
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
	
	private Set<String> findWords(String word, List<String> ignore) {
		Set<String> result = new HashSet<String>();; 
		
		// for each word, 
		char[] letters = word.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			// switch each letter and check whether it's in the dictionary
			for (char c = 'a'; c <= 'z'; c++) {
				char tmp = letters[i];
				letters[i] = c;
				String w = String.valueOf(letters);
				if (dict.contains(w) && !ignore.contains(w)) {
					result.add(w);
				}
				letters[i] = tmp;
			}
		}
		
		return result;
	}
	
	
	private void search(String word) {
		List<String> path = new ArrayList<String>();
		path.add(word);
		queue.push(new Move(word, path));
		
		while (!queue.isEmpty()) {
			search();
		}
		
		// never got any match - return 0
		if (shortestLength == -1) shortestLength = 0;
	}
	
	private boolean isSolution(String neighbor, List<String> path) {
		int d = calcDistance(neighbor, end);
		return ( d <= 1 );
	}
	
	private void processSolution(String neighbor, List<String> path) {	
		int length = path.size() + 1; // adding neighbor
		List<String> p = new ArrayList<String>(path);
		p.add(neighbor);
		if (!neighbor.equals(end)) {
			p.add(end);
			length++;
		}
		
		if (length == shortestLength) {
			System.out.println("Adding path: " + p);
			ladders.add(p);
		}
		
		if (length < shortestLength || shortestLength == -1) {
			System.out.println("*** Clearing laddders!!!");
			ladders.clear();
			System.out.println("Adding path: " + p);
			ladders.add(p);
			shortestLength = length;
		}
	}
	
	private void search() {
		Move move = queue.pop();
		List<String> path = move.path;
		String word = move.word;
		
		// find words in dict at 1 letter distance from current or less
		Set<String> neighbors = findWords(word, path);
		
		if (neighbors.isEmpty()) {
			dict.remove(word);
		}
		
		for (int k = 1; k <= path.size(); k++){
			System.out.print(" ");
		}
		System.out.println("depth: " + path.size() + " queue: " + queue.size());
		//System.out.println(path + " --> " + neighbors);
		
		// for each word
		for (String neighbor: neighbors) {
			if (isSolution(neighbor, path)) {
				processSolution(neighbor, path);
			}
			else {
				if (shortestLength == -1 ||
						// current path from start + neighbor + end
						path.size() + 2 < shortestLength) {
					if (!path.contains(neighbor)) {
						List<String> nextPath = new ArrayList<String>(path);
						nextPath.add(neighbor);
						move = new Move(neighbor, nextPath);
						queue.add(move);
						// we keep neighbor around in case there is another path
						// involving this word
						//dict.remove(neighbor);
					}
				}				
			}
		}
		
	}
	
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        this.end = end;
        this.dict = dict;
        
        search(start);
        
        return ladders;
    }
}
