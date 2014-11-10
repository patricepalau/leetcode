package lastword;

/**
 * https://oj.leetcode.com/problems/length-of-last-word/
 * 
 * 
 */
public class Solution {
	public int lengthOfLastWord(String s) {
        
		if (s == null) return 0;
		s = s.trim();
		if (s.length() == 0) return 0;
		
		int length = 0;
        
        int start = -1;
        boolean foundWord = false;
        for (int i = 0; i < s.length(); i++) {
        	if (s.charAt(i) == ' ') {
        		if (start >= 0) {
        			length = i - start;
        			foundWord = true;
        		}
        		start = -1;
        	}
        	else {
        		if (start == -1) {
        			start = i;
        			foundWord = false;
        		}
        	}
        }
        
        if (!foundWord) {
        	length = s.length() - start;
        }
        
        return length;
    }
}
