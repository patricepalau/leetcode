package longestsubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 * 
 */
public class Solution {
	public int lengthOfLongestSubstring(String s) {
 		int max = 0;
		
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		int startIndex = 0;
		// store characters already scanned, with their index
		Map<Character,Integer> charList = new HashMap<Character,Integer>();
		// scan input string
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			// have we seen this character already
			Integer index = charList.get(c);
			// if we saw it before our current start index, we don't care
			if (index != null && index >= startIndex) {
				// yes - the length of the string is currentIndex - indexOfC + 1
				int length = i - startIndex;
				if (length > max) max = length;
				startIndex = index + 1;
			}
			
			// remember this character's index
			charList.put(c, i);
		}
		
		// finally let's make sure the string between lastIndex and the last char
		// is not the longest
		int length = s.length() - startIndex;
		if (length > max) max = length;
		
		return max;
	}
}
