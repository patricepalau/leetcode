package wordladder;

import java.util.*;

/**
 * https://oj.leetcode.com/problems/word-ladder/
 * 
 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence
 * from start to end, such that:
 * - Only one letter can be changed at a time
 * - Each intermediate word must exist in the dictionary
 * 
 * For example, given:
 *   start = "hit"
 *   end = "cog"
 *   dict = ["hot","dot","dog","lot","log"]
 *   As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *   return its length 5.
 *   
 *   Note:
 *   - Return 0 if there is no such transformation sequence.
 *   - All words have the same length.
 *   - All words contain only lowercase alphabetic characters.
 *   
 *  The solution uses a breadth-first search.
 *  
 *  Note that finding the candidates is done by generating all possible words
 *  obtained by changing 1 character on a given word (depends on the size of the word).
 *  The alternative, i.e. evaluating words in the dictionary which are
 *  1 letter away from a given word, depends on the size of the dictionary.
 *   
 */
public class Solution {
	private LinkedList<Move> queue;
	// dictionary
	private Set<String>		 dict;
	// target word
	private String 			 end;
	// shortest length so far
	private int 			 shortestLength = -1;
	
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
	
	private List<String> findWords(String word, int d, List<String> ignore) {
		List<String> result = new ArrayList<String>();; 
		
		// for each word, 
		char[] letters = word.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			// switch each letter and check whether it's in the dictionary
			for (char c = 'a'; c <= 'z'; c++) {
				char tmp = letters[i];
				letters[i] = c;
				String w = String.valueOf(letters);
				if (dict.contains(w)) {
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
		if (!neighbor.equals(end)) {
			length++;
		}
		
		if (length < shortestLength || shortestLength == -1) {
			shortestLength = length;
		}
	}
	
	private void search() {
		Move move = queue.pop();
		List<String> path = move.path;
		String word = move.word;
		
		// find words in dict at 1 letter distance from current or less
		List<String> neighbors = findWords(word, 1, path);
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
						dict.remove(neighbor);
					}
				}				
			}
		}
		
	}
	
	
		
	public int ladderLength(String start, String end, Set<String> dict) { 
		// in case we get dummy inputs
		if (start == null || end == null) return 0; // no match
		if (start.equals(end))			  return 2; // direct path from start to end
		
		// remember problem inputs
		this.end   = end;
		this.dict  = dict;
		
		// we're doing a breadth-first search:
		// this queue will keep track of candidates to evaluate
		this.queue = new LinkedList<Move>();
		
		// add end word to the dictionary, in case it's not there already
		dict.add(end);
		
		// start breadth-first search
		search(start);
		
		// this variable holds the shortest length encountered so far
		return shortestLength;
	}
}
