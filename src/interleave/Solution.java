package interleave;

/**
 * https://oj.leetcode.com/problems/interleaving-string/
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example, given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */
public class Solution {
	private String s1;
	private String s2;
	private String s3; 
	
	// we're using dynamic programming
	// this array contains the result of previous evaluations
	// of recursiveSearch(int, int, int)
	private Boolean[][][] cache;
	
	//
	//
	// Given:
	// - a substring of s1, starting at index i1
	// - a substring of s2, starting at index i2
	// - a substring of s3, starting at index i3
	// 
	// determine recursively whether the substring of s3 is the result of the
	// interleaving of the substring of s1 and the substring of s2
	// 
	private boolean recursiveSearch(int i1, int i2, int i3) {
		boolean match = false; // result of this method
		
		int i = i1; // current index in s1
		int j = i2; // current index in s2
		
		// get the length of s1, s2, s3
		int len1 = s1.length();
		int len2 = s2.length();
		int len3 = s3.length();
		
		// end recursion if we have reached the end of s3
		if (i3 == len3) {
			// return true if we have used all of s1 and s2
			return (len1 == i1 && len2 == i2);
		}
		
		// end recursion if we have reached the end of s1 AND s2
		if (len1 == i1 && len2 == i2) {
			// we didn't reach the end of s3, but no more characters left in s1 and s2
			return false;
		}
		
		// return result if we already calculated it
		if (cache[i1][i2][i3] != null) return cache[i1][i2][i3];
		
		// end recursion if there is not enough characters in s1 and s2
		// to build the remaining part of s3
		int remainingLength1 = s1.length() - i1;
		int remainingLength2 = s2.length() - i2;
		int remainingLength3 = s3.length() - i3;
		if (remainingLength1 + remainingLength2 < remainingLength3) {
			// not enough characters remaining in s1 and s2 to build s3
			// stop right here
			return false;
		}
		
		// we're looking for s3's character at position i3
		char currentChar = s3.charAt(i3);
		
		// let's try to find it in either s1 or s2
		// while there are still characters left to explore in either s1 or s2
		while (i < len1 || j < len2) {
			// this flag set to true if we got a match for the current searched character
			boolean characterMatch = false;
			// is there a match in s1?
			if (len1 > 0 && i < len1 && s1.charAt(i) == currentChar) {
				characterMatch = true;
				// yes - continue search with a slightly simpler problem
				// i.e. the substring of s1 starting at the next position, same s2,
				// and s3 starting at the next position
				match = recursiveSearch(i + 1, j, i3 + 1);
				// if we got a match - no need to search further
				if (match) break;
			}
			
			// is there a match in s2?
			if (len2 > 0 && j < len2 && s2.charAt(j) == currentChar) {
				characterMatch = true;
				// yes - continue search with a slightly simpler problem
				// i.e. same s1, the substring of s2 starting at the next position,
				// and s3 starting at the next position
				match = recursiveSearch(i, j + 1, i3 + 1);
				// if we got a match - no need to search further
				if (match) break;
			}
			
			if (!characterMatch) {
				// no match in either s1 or s2 at the current positions
				// advance to next position, if we haven't reached the end of the strings yet
				i = Math.min(i+1, len1);
				j = Math.min(j+1, len2);
			}
			else if (characterMatch && !match) {
				// we got a match but recursive search did not return a match
				// stop right here
				break;
			}
		}
		
		// record result of evaluation for this set of indexes
		cache[i1][i2][i3] = match;
		
		// return match
		return match;
	}
	
	public boolean isInterleave(String s1, String s2, String s3) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		
		// caches evaluations of recursive search
		this.cache = new Boolean[s1.length() + 1][s2.length() + 1][s3.length()];
		
		// initialize recursive search
		return recursiveSearch(0, 0, 0);
	}
}
