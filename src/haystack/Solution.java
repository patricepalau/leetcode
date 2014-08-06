package haystack;

import java.math.BigInteger;
import java.util.Random;

/*
 * https://oj.leetcode.com/problems/implement-strstr/
 * Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
 * 
 * The naive, brute-force algorithm is quadratic on large strings and patterns.
 * This implementation uses a hash function (Rabin-Karp).
 * 
 */
public class Solution {
	
	private long M; // large prime for hash
	private int B;  // base for hash
	private long Bpowerm; // pre-calculate for Rabin-Karp
	
	private void initHash(int m) {
		BigInteger largePrime = BigInteger.probablePrime(31, new Random());
		M = largePrime.longValue();
		B = 256;
		Bpowerm = 1;
		for (int k = 1; k <= m ; k++) Bpowerm = (Bpowerm * B) % M;
	}
	
	//
	// Given a string s of length n:
	// the hash of the substring of length m starting at j is given by:
	// H(s, j)     = B^m-1 x s[j] + B^m-2 x s[j + 1] + ...         + B^0 x s[j + m - 1]
	// H(s, j + 1) =                B^m-1 x s[j + 1] + B^m-2 x s[j + 2] + ... + B^0 x s[j + m]
	//             
	// B x H(s, j)  = B^m x s[j] + B^m-1 x s[j + 1] + ...         + B x s[j + m - 1]
	// B x H(s, j)  = H(s, j + 1) - s[j + m] + B^m x s[j]
	// i.e.
	// H(s, j + 1) = B x H(s,j) - B^m x s[j]  + s[j + m]
	// 
	
	// hash of H(s, 0)
	private long hash(String s, int m) {
		return hash(s, m, 0);
	}
	
	// hash of substring of s of length m, starting at j
	private long hash(String s, int m, int j) {
		long hash = 0;
		
		for (int i = 0; i < m; i++) {
			hash = (B * hash + s.charAt(i + j));
			hash = hash % M;
		}
		// at this point:
		// hash = B^m-1 x s[j] + B^m-2 x s[j+1] + ... + B^0 x s[j+m-1]
		
		return hash;
	}
	
	// is the substring of s1 starting at index i, of length s2.length
	// identical to s2?
	private boolean same(String s1, int i, String s2) {
		boolean match = false;
		for (int j = 0; j < s2.length(); j++) {
			match = (s1.charAt(j + i) == s2.charAt(j));
			if (!match) break;
		}
		return match;
	}
	
	public String strStr(String haystack, String needle) {
		String result = null;
		
		// if one of the string is null, there won't be any match
		if (needle == null || haystack == null) {
			return null;
		}
		
		// same if needle is larger than haystack
		if (needle.length() > haystack.length())
			return null;
		
		// 2 empty strings
		if (needle.length() == 0 && haystack.length() == 0) {
			return "";
		}
		
		// needle is empty - we get a match right away
		if (needle.length() == 0) {
			return haystack;
		}
		
		// calculate hash on needle
		int m = needle.length();
		int n = haystack.length();
		
		// Rabin-Karp
		initHash(m);
				
		long hNeedle   = hash(needle, m);
		long hHaystack = hash(haystack, m);
		
		// handle equality at index 0
		if (hNeedle == hHaystack) {
			if (same(haystack, 0, needle)) {
				return haystack;
			}
		}
		
		// we advance the window forward without extending beyond the
		// end of the haystack:
		// needle = foo
		// haystack = barbarfo
		// i = 1, 2, 3, 4, 5 = 8 - 3
		int i;
		boolean match = false;
		
		for (i = 1; i < n - m; i++) {
			// H(s, j + 1) = B x H(s,j) - B^m x s[j]  + s[j + m]
			hHaystack = (B * hHaystack - Bpowerm * haystack.charAt(i-1) + haystack.charAt(i + m - 1));
			// we want a positive hash mod m
			hHaystack = hHaystack % M;
			if (hHaystack < 0) hHaystack += M;
			
			if (hHaystack == hNeedle) {
				// make sure both strings are indeed identical
				match = true;
				for (int j = 0; j < m; j++) {
					match = (haystack.charAt(j + i) == needle.charAt(j));
					if (!match) break;
				}
				if (match) {
					// return the substring of haystack starting at i
					
					break;
				}	
			}
		}
		
		if (match) {
			result = haystack.substring(i);
		}
		else {
			result = null;
		}
        return result;
    }
}
