package haystack;

import java.util.Arrays;

/*
 * https://oj.leetcode.com/problems/implement-strstr/
 * Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
 * 
 * The naive, brute-force algorithm is way too slow O(nm).
 * 
 */
public class Solution {
	public String strStr(String haystack, String needle) {
		String result = null;
		
		if (needle == null || haystack == null) {
			return null;
		}
		
		// got 2 empty strings
		if (needle.length() == 0 && haystack.length() == 0) {
			return "";
		}
		
		// needle is empty - we get a match right away
		if (needle.length() == 0) {
			return haystack;
		}
		
		if (needle.length() > haystack.length())
			return null;
		
		char[] haystackChars = haystack.toCharArray();
		char[] needleChars   = needle.toCharArray();
		
		// brute force
		for (int i = 0; i < haystackChars.length; i++) {
			if (haystackChars[i] == needleChars[0]) {
				boolean match = true;
				for (int k = 0; k < needleChars.length; k++) {
					if (i + k < haystackChars.length) {
						if (needleChars[k] != haystackChars[i + k]) {
							match = false;
							break;
						}
					}
					else {
						match = false;
						break;
					}
				}
				
				if (match) {
					char [] resultChars = Arrays.copyOfRange(haystackChars, i, haystackChars.length);
					result = new String(resultChars);
				}
			}
		}
		
        return result;
    }
}
