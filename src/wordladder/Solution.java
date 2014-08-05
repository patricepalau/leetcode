package wordladder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 *   
 *   THIS VERSION HAS NOT BEEN ACCEPTED - TIME LIMIT EXCEEDED
 *   
 */
public class Solution {
	String start;
	String end;
	int shortest = -1;
	Set<String> dict;
	List<String> words;
	int[][] distances;
	//int indexOfEnd;
	//Set<String> deadends;
	char[] endChars;
	
	private boolean isSolution(List<String> moves) {
		if (moves.size() == 0) return false;
		String last = moves.get(moves.size() - 1);
		return (calcDistance(endChars, last.toCharArray()) <= 1);
	}
	
	private void processSolution(List<String> moves) {
		if (shortest == -1) {
			shortest = moves.size();
		}
		else {
			if (moves.size() < shortest) {
				shortest = moves.size();
			}
		};
	}
	
	Map<String,Integer> distToEnd = new HashMap<String,Integer>();

long timeBuildCandidates = 0, timeSort = 0;
int countb = 0;
long tRemove = 0;
	private List<String> buildCandidates(List<String> moves) {
countb++;
long beg = System.currentTimeMillis();

		int nbMoves = moves.size();
		String currentWord = nbMoves == 0 ? start : moves.get(moves.size() - 1);
		
		int currentDist = distToEnd.get(currentWord);
		
//		List<String> potentialCandidates =  new ArrayList<String>();
//
//		potentialCandidates.addAll(dist.get(currentDist - 1));
//		potentialCandidates.addAll(dist.get(currentDist));
		List<String> potentialCandidates = allPotentialCandidates.get(currentDist);
//System.out.println("# of potentials:" + potentialCandidates.size());
		timeSort += (System.currentTimeMillis() - beg);
		
		List<String> candidates = new ArrayList<String>();
		for (String word: potentialCandidates) {
			if (isOneCharAway(currentWord, word)) {
				candidates.add(word);
			}
		}
		
long e = System.currentTimeMillis();
timeBuildCandidates += (e - beg);
		
		return candidates;
	}
	
	private Map<String, Map<String,Boolean>> cache;
private int total = 0;
private int hit = 0;
	private boolean isOneCharAway(String s1, String s2) {
total++;
		
		Map<String,Boolean> m1 = cache.get(s1);
		if (m1 != null) {
			//Boolean cached = cache[i1][i2];
			Boolean cached = m1.get(s2);
			if (cached != null) {
hit++;
				return cached;
			}
		}
		
		int dist = 0;

		if (!s1.equals(s2)) {
			char[] char1 = s1.toCharArray();
			Arrays.sort(char1);
			char[] char2 = s2.toCharArray();
			
			char[] tmp = Arrays.copyOf(char1, char1.length);
			// how many chars in common?
			for (int i = 0; i < char2.length; i++) {
				int match = Arrays.binarySearch(tmp, char2[i]);
				if (match <  0) {
					dist++;
				}
				else {
					tmp[match] = '_';
				}
				if (dist > 1) {
					break;
				}
			}
		}
		if (m1 == null) {
			m1 = new HashMap<String,Boolean>();
			cache.put(s1, m1);
		}
		
		m1.put(s2, dist <= 1);;
		
		return dist <= 1;
	}
	
	private int calcDistance(char[] char1, char[] char2) {
		int dist = 0;
		char[] tmp = Arrays.copyOf(char1, char1.length);
		// how many chars in common?
		for (int i = 0; i < char2.length; i++) {
			int match = Arrays.binarySearch(tmp, char2[i]);
			if (match <  0) {
				dist++;
			}
			else {
				// remove char at match
				// copy from 0  to match-1 and match+1 to end
				char[] old = tmp;
				tmp = new char[old.length - 1];
				System.arraycopy(old, 0, tmp, 0, match);
				if (match < old.length - 1) {
					System.arraycopy(old, match + 1, tmp, match, old.length - 1 - match);
				}
			}
		}
		
		return dist;
	}
	
	private void makeMove(List<String> moves, String candidate) {
		moves.add(candidate);
		int d = distToEnd.get(candidate);
		List<String> wsd = allPotentialCandidates.get(d);
		wsd.remove(candidate);
		List<String> wsd1 = null;
		if (d + 1 < allPotentialCandidates.size()) {
			wsd1 = allPotentialCandidates.get(d+1);
			wsd1.remove(candidate);
		}
	}
	
	private void unmakeMove(List<String> moves, String candidate) {
		moves.remove(candidate);
		int d = distToEnd.get(candidate);
		List<String> wsd = allPotentialCandidates.get(d);
		if (!deadends.contains(candidate)) {
			wsd.add(candidate);
			if (d + 1 < allPotentialCandidates.size()) {
				List<String> wsd1 = allPotentialCandidates.get(d+1);
				wsd1.add(candidate);
			}
		}
	}
	
	private void prune(List<String> moves) {	
//		System.out.println("no candidates for moves: " + moves);

		String last = moves.get(moves.size() - 1);
		deadends.add(last);
		int d = distToEnd.get(last);
		List<String> ws = null;
		if (d+1 < allPotentialCandidates.size()) {
			ws = allPotentialCandidates.get(d+1);
			ws.remove(last);
		}
		
		ws = allPotentialCandidates.get(d);
		ws.remove(last);
	}
	
	private void backtrackDFS(List<String> moves, int k) {
//for (int i = 0; i < k; i++) System.out.print(" ");
//System.out.println("backtrack " + moves.size() + ": " + moves);
		if (shortest != -1 && moves.size() >= shortest) {
			return;
		}
		
		if (isSolution(moves)) {
			processSolution(moves);
		}
		else {
			List<String> candidates = buildCandidates(moves);
			if (candidates.isEmpty()) {
				prune(moves);
			}
			
//for (String candidate: candidates) {
//	System.out.println("candidate: " + candidate + " -> " + distToEnd.get(candidate));
//}
			for (String candidate: candidates) {
				// one of the backtracks found a solution
				// stop here since we're not going to find a shorter sequence
				if (shortest != -1 && moves.size() >= shortest - 1) {
					break;
				}
				
				makeMove(moves, candidate);
				backtrackDFS(moves, k + 1);
				unmakeMove(moves, candidate);
			}
		}
	}
	
//	
//	private void backtrackBFS(List<List<String>> moves, int k) {
//		//for (int i = 0; i < k; i++) System.out.print(" ");
//		//System.out.println("backtrack " + moves.size() + ": " + moves);
//				if (shortest != -1 && moves.size() >= shortest) {
//					return;
//				}
//				
//				if (isSolution(moves)) {
//					processSolution(moves);
//					System.out.println("*** moves: " + moves);
//				}
//				else {
//					List<String> candidates = buildCandidates(moves);
//					if (candidates.isEmpty()) {
//						prune(moves);
//					}
//					
//		//for (String candidate: candidates) {
////			System.out.println("candidate: " + candidate + " -> " + distToEnd.get(candidate));
//		//}			
//					List<List<String>> nextMoves = new ArrayList<List<String>>();
//					for (String candidate: candidates) {
//						// one of the backtracks found a solution
//						// stop here since we're not going to find a shorter sequence
//						if (shortest != -1 && moves.size() >= shortest - 1) {
//							break;
//						}
//						
//						makeMove(moves, candidate);
//						nextMoves.add(new ArrayList<String>(moves));
//						if (isSolution(moves)) {
//							processSolution(moves);
//						}
//						unmakeMove(moves, candidate);
//					}
//					backtrackBFS(nextMoves, k + 1;)
//				}
//			}
	
	private Map<String,Integer> calculateDistancesTo(Collection<String> words, String word) {
		Map<String,Integer> distToWord = new HashMap<String,Integer>();
		char[]  c = word.toCharArray();
		Arrays.sort(c);
		
		for (String w: words) {
			if (w.equals(word)) {
				continue;
			}
			else {
				int d = calcDistance(c, w.toCharArray());
				distToWord.put(w, d);
			}
		}
		
		return distToWord;
	}
	
	private List<List<String>> getDistances() {
		// abc dist max = 3 = length
		// d=3 d=2 d=1
		Set<String> words = new HashSet<String>(dict);
		words.remove(end);
		Map<String,Integer> distances = calculateDistancesTo(words, end);
		int length = end.length() + 1;
		List<List<String>> counts = new ArrayList<List<String>>(length);
		for (int i = 0; i < length; i++)
			counts.add(new ArrayList<String>());
		
		for (String w: distances.keySet()) {
			int d = distances.get(w);
			List<String> ws = counts.get(d);
			ws.add(w);
		}
		
		return counts;
	}
	
	private int calcDistance(String s1, String s2) {
		char[]  c = s1.toCharArray();
		Arrays.sort(c);
		return calcDistance(c, s2.toCharArray());
	}
	
	private void calculateDistancesToEnd(List<String> words) {
		for (String word: words) {
			if (word.equals(end)) {
				distToEnd.put(word, 0);
			}
			else {
				distToEnd.put(word, calcDistance(endChars, word.toCharArray()));
			}
		}
	}
	
	private Map<String,Integer> indexes;
	private List<String> wordsCloseToEnd;
	List<List<String>> dist;
	List<String> d2;
	List<String> deadends = new ArrayList<String>();
	List<List<String>> allPotentialCandidates = new ArrayList<List<String>>();
	
    public int ladderLength(String start, String end, Set<String> dict) {    
long t = System.currentTimeMillis();
        this.start = start;
        this.end = end;
        this.dict = dict;
        this.words = new ArrayList<String>(dict);
        this.wordsCloseToEnd = new ArrayList<String>();
        this.words.add(start);
        if (!words.contains(end)) this.words.add(end);
        this.indexes = new HashMap<String,Integer>();
        
        cache = new HashMap<String,Map<String,Boolean>>(); //[words.size()][words.size()];
        
        endChars = this.end.toCharArray();
        Arrays.sort(endChars);
        
long t0 = System.currentTimeMillis();
        Collections.sort(words);
        int index = 0;
        for (String word: words) {
        	indexes.put(word, index);
        	index++;
        }
        
long ta = System.currentTimeMillis();
        this.dist = getDistances();
        
        // potentialCandidate[k] = dist[k] + dist[k-1]
        //allPotentialCandidates = new ArrayList<List<String>>());
        allPotentialCandidates.add(Arrays.asList(end));
        for (int d = 1; d < dist.size(); d++) {
        	List<String> potentialCandidates = new ArrayList<String>();
        	potentialCandidates.addAll(dist.get(d-1));
        	potentialCandidates.addAll(dist.get(d));
        	allPotentialCandidates.add(potentialCandidates);
        }
        
        int distStartToEnd =  calcDistance(start, end);
		
        // remove from set of candidates all words that are further away that distStartToEnd
		for (int i = distStartToEnd + 1; i <= start.length(); i++) {
			List<String> wordsToRemove = dist.get(i);
			words.removeAll(wordsToRemove);
		}
		
long t1 = System.currentTimeMillis();
System.out.println("trn: " + (t1-ta));
        // pre-calculate distances from each word to the end word
        
        calculateDistancesToEnd(words);
        wordsCloseToEnd = new ArrayList<String>();
        for (String word: words) {
        	if (distToEnd.get(word) == 1) {
        		wordsCloseToEnd.add(word);
        	}
        }

long t2 = System.currentTimeMillis();
        List<String> moves = new ArrayList<String>();
        backtrackDFS(moves, 0);
long t3 = System.currentTimeMillis();
        if (shortest == -1) {
        	shortest = 0; // no path
        }
        else {
        	shortest += 2; // we include start and end
        }
System.out.println("------------------");
System.out.println("t0:" + (t0-t));
System.out.println("t1:" + (t1-t0));
System.out.println("t2:" + (t2-t1));
System.out.println("dict:" + dict.size());
System.out.println("t3:" + (t3-t2));
System.out.println("tbuild:" + timeBuildCandidates);
System.out.println("tremove:" + tRemove);
System.out.println("countb:" + countb);
System.out.println("tsort :" + timeSort);
System.out.println("cache : " + hit + "/" + total);
System.out.println("t     :" + (t3-t));
System.out.println("------------------");
        
        return shortest;
    }
}
