package wordladder2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
	
	private void search(String word) {
		List<String> path = new ArrayList<String>();
		path.add(word);
		queue.push(new Move(word, path));
		
		while (!queue.isEmpty()) {
			//System.out.println(queue);
			search();
		}
		
		// never got any match - return 0
		if (shortestLength == -1) shortestLength = 0;
	}
	
	//private List<String> deadends = new ArrayList<String>();
	private void search() {
		Move move = queue.pop();
		List<String> path = move.path;
		String word = move.word;
		
		// find words in dict at 1 letter distance from current or less
		Set<String> neighbors = findWords(word);
		
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
						//updatePermutations(neighbor);
					}
				}				
			}
		}
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
		List<String> p = new ArrayList<String>(path);
		p.add(neighbor);
		if (!p.contains(end)) p.add(end);
		ladders.add(p);
	}
	
	long tfindw = 0;
	long tgetperm = 0;
	private Set<String> findWords(String word) {
		long t0 = System.currentTimeMillis();
		Set<String> result = new TreeSet<String>();
		List<String> perms = getPermutations(word);
		long t2 = System.currentTimeMillis();
		for (String w: perms) {
			if (dict.contains(w) && !w.equals(word)) {
				result.add(w);
			}
		}
//		long t1 =  System.currentTimeMillis();
//		tfindw += (t2-t0);
//		tgetperm += (t2-t0);
		return result;
	}
	
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
			return path.toString();
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

	private boolean isSolution(List<String> path) {
		String last = path.get(path.size() - 1);
		int d = calcDistance(last, end);
		return ( d <= 1 );
	}
	
	private void processSolution(List<String> path) {	
		int length = path.size();
		
		if (length == shortestLength) {
//			System.out.println("Adding path: " + path);
			ladders.add(new ArrayList<String>(path));
		}
		
		if (length < shortestLength || shortestLength == -1) {
//			System.out.println("*** found shorter path! clearing laddders!!!");
//			System.out.println("Adding path: " + path);
			ladders.clear();
			ladders.add(new ArrayList<String>(path));
			shortestLength = length;
		}
	}
	
	//Map<String,Map<Integer,Set<String>>> cacheCandidates = new HashMap<String,Map<Integer,Set<String>>>();
	Map<String,List<String>> cache = new HashMap<String,List<String>>();
	
	void updatePermutations(String word) {
		for (String key: cache.keySet()) {
			List<String> list = cache.get(key);
			list.remove(word);
		}
	}
	
	int countperm = 0;
	int hitperm = 0;
	long tloop2 = 0;
	List<String> getPermutations(String word) {
		countperm++;
		List<String> permutations = cache.get(word);
		if (permutations != null) {
			hitperm++;
			return permutations;
		}
			
		permutations = new ArrayList<String>();
		cache.put(word, permutations);
//		long t0 = System.currentTimeMillis();
		char[] letters = word.toCharArray();
		
		for (int i = 0; i < word.length(); i++) {
			// switch each letter and check whether it's in the dictionary
			for (char c = 'a'; c <= 'z'; c++) {
				char tmp = letters[i];
				letters[i] = c;
				String w = String.valueOf(letters);
				if (dict.contains(w)) {
					permutations.add(w);
				}
				//permutations.add(w);
//				if (dict.contains(w) && !path.contains(w)) {
//					if (deadends == null || !deadends.contains(w)) {
//						candidates.add(w);
//					}
//				}
				letters[i] = tmp;
			}
		}
//		long t1 = System.currentTimeMillis();
//		tloop2 += (t1-t0);
		
		return permutations;
	}
	
	int count = 0;
	int hit = 0;
	long tloop = 0;
	long tperm = 0;
	long tcont = 0;
//	Map<String,Set<String>> cachec = new HashMap<String,Set<String>>();
	private Set<String> buildCandidates(List<String> path) {
//		count++;
		int index = path.size() - 1;
		String word = path.get(index);
		Set<String> candidates = null; // cachec.get(word+index); //null;

//		if (candidates != null) {
//			hit++;
//			return candidates;
//		}
		
		//candidates = new TreeSet<String>();
		//candidates = new HashSet<String>();
		List<String> temp = new ArrayList<String>();
		if (path.size() < shortestLength) {
			Set<String> deadends = deadendsAtDepth.get(path.size());
			
//			long t0 = System.currentTimeMillis();
			List<String> permutations = getPermutations(word);
			long t1 = System.currentTimeMillis();
			
			//System.out.println("# of permutations: " + permutations.size());
			for (String w: permutations) {
				if ( dict.contains(w) ) {
					if ( !path.contains(w) ) {
						if (deadends == null || !deadends.contains(w)) {
							//candidates.add(w);
							temp.add(w);
						}
					}
				}
			}
			
			long t2 = System.currentTimeMillis();
//			tperm += (t1-t0);
			tloop += (t2-t1);
		}
		
		if (temp.isEmpty()) {
			prune(path);
		}
		Collections.sort(temp);
		candidates = new LinkedHashSet<String>(temp);
		
//		cachec.put(word+index, candidates);
		
		return candidates;
	}
	
	private void prune(List<String> path) {
		//String word = path.get(path.size() - 1);
		//System.out.println("--> no candidates for " + word);
		//dict.remove(word);
	}
	
	private void makeMove(List<String> path, String candidate) {
		path.add(candidate);
	}
	
	private void unmakeMove(List<String> path, String candidate) {
		path.remove(candidate);
	}
	
	long totalps = 0;
	long totalbc = 0;
	
	private void backtrackDFS(List<String> path) {
//		long t0 = System.currentTimeMillis();
//int depth = path.size();
//String spaces = "";
//for (int k = 1; k < depth; k++) spaces+=" ";		
//System.out.println(spaces + path);

 		if (isSolution(path)) {
// 			long t1 = System.currentTimeMillis();
			processSolution(path);
//			long t2 = System.currentTimeMillis();
//			totalps += (t2-t1);
		}
		else {
			long t1 = System.currentTimeMillis();
			Set<String> candidates = buildCandidates(path);
			long t2 = System.currentTimeMillis();
			totalbc += (t2-t1);
//System.out.println("candidates: " + candidates);
			String last = path.get(path.size() - 1);
			if (candidates.isEmpty()) {
				// add candidate as a deadend of depth path.size()
				int d = path.size() - 1;
				Set<Integer> keys = new TreeSet<Integer>(deadendsAtDepth.keySet());
				
				for (Integer i: keys) {
					if (i >= d) {
						Set<String> dends = deadendsAtDepth.get(i);
						dends.add(last);
					}
				}
			}
			else {
				for (String candidate: candidates) {
					makeMove(path, candidate);
					backtrackDFS(path);
					unmakeMove(path, candidate);
				}
			}
		}
	}
	
	Map<Integer,Set<String>> deadendsAtDepth = new HashMap<Integer,Set<String>>();
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
    	long t0 = System.currentTimeMillis();
        this.end = end;
        this.dict = new HashSet<String>(dict);
        
        //Set<String> test = new HashSet<String>(dict);
        for (String w: dict) {
        	List<String> ps = getPermutations(w);
        	List<String> tmp = new ArrayList<String>();
        	for (String p: ps) {
        		if (!dict.contains(p)) tmp.add(p);
        	}
        	ps.removeAll(tmp);
        }
        
        long t5 = System.currentTimeMillis();
        
        // 1) establish min path
        search(start);
        long t1 = System.currentTimeMillis();
        //System.out.println("found deadends: " + deadends.size());
        
        this.dict = dict; //new TreeSet<String>(dict);
        
        Map<Integer,Set<String>> hitsAtDepth = new HashMap<Integer,Set<String>>();
        for (List<String> path: ladders) {
        	for (int i = 1; i < path.size() - 1; i++) {
        		Set<String> hits = hitsAtDepth.get(i);
        		if (hits == null) {
        			hits = new HashSet<String>();
        			hitsAtDepth.put(i, hits);
        		}
        		hits.add(path.get(i));
        	}
        }
        long t2 = System.currentTimeMillis();
        for (List<String> path: ladders) {
        	for (int i = 0; i < path.size() - 1; i++) {
        		String word = path.get(i);
        		String firstSuccessfulCandidate = path.get(i + 1);
        		if (firstSuccessfulCandidate.equals(end)) continue;
        		int depth = i + 1;
        		Set<String> dends = deadendsAtDepth.get(depth);
        		if (dends == null) {
        			dends = new TreeSet<String>();
        			deadendsAtDepth.put(depth, dends);
        		}
        		// eliminate any candidate before this one
        		Set<String> hits = hitsAtDepth.get(depth);
        		Set<String> candidates = findWords(word);
        		for (String candidate: candidates) {
        			
        			if (!hits.contains(candidate) 
        				 && candidate.compareTo(firstSuccessfulCandidate) < 0) {
        				
//        				System.out.println("pruning: " + candidate);
        				dends.add(candidate);
        				//dict.remove(candidate);
        			}
        		}
        	}
        }
        long t3 = System.currentTimeMillis();
        for (int depth = 1; depth <= shortestLength - 2; depth++) {
        	Set<String> dends = deadendsAtDepth.get(depth);
        	if (dends != null) {
        		for (int d = depth + 1; d < shortestLength - 1; d++) {
            		Set<String> current = deadendsAtDepth.get(d);
            		if (current != null)
            			current.addAll(dends);
            	}
        	}
        }
        long t4 = System.currentTimeMillis();
        // 2) run dfs
        
        List<String> moves = new ArrayList<String>();
        moves.add(start);
        backtrackDFS(moves);
        long t6 = System.currentTimeMillis();
        
        System.out.println("t5 = " + (t5-t0));
        System.out.println("t1 = " + (t1-t5));
        System.out.println("  findWords: " + tfindw);
        System.out.println("    getperm: " + tgetperm);
        System.out.println("      loop2: " + tloop2);
        System.out.println("    count  : " + hitperm +"/" + countperm);
        System.out.println("t2 = " + (t2-t1));
        System.out.println("t3 = " + (t3-t2));
        System.out.println("t4 = " + (t4-t3));
        System.out.println("t5 = " + (t6-t4));
        System.out.println("  processSolution: " + totalps);
        System.out.println("  buildCandidates: " + totalbc);
        System.out.println("    perm         : " + tperm);
        System.out.println("    loop         : " + tloop);
        System.out.println("    cont         : " + tcont);
        System.out.println("    cache        : " + hit + "/" + count);
        return ladders;
    }
}
