package longestpalindrome;

/**
 * https://oj.leetcode.com/problems/longest-palindromic-substring/
 * 
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * 
 */
public class Solution {
    public String longestPalindrome(String s) {
    	if (s == null) return null;
    	if (s.length() == 0) return "";
    	if (s.length() == 1 || isPalindrome(s)) return s;
    	
        String result = null;
        
        int index = 0;
        int minLength = 0;
        int length = s.length();
        
        while (index < length) {
        	String longestPalindrome = null;
        	
        	// find palyndroms of size 2 and 3
        	if (index + 2 <= length) {
        		String substring = s.substring(index, index + 2);
        		if (isPalindrome(substring)) {
        			longestPalindrome = findLongerPalindrome(s, index, index + 1);    			
        			
        		}
        	}
        	
        	if (index + 3 <= length) {
        		String substring = s.substring(index, index + 3);
        		if (isPalindrome(substring)) {    			
        			longestPalindrome = findLongerPalindrome(s, index, index + 2);
        		}
        	}
        	
			if (longestPalindrome != null && longestPalindrome.length() > minLength) {
				minLength = longestPalindrome.length();
				result = longestPalindrome;
			}
        	
        	index++;
        }
        
        return result;
    }
    
    private String findLongerPalindrome(String s, int start, int end) {
		// how far can we extend this string and still have a palindrome
		int length = s.length();
		boolean hasOneChar = true;
		char c = s.charAt(start);
		for (int i = start + 1; i <= end; i++) {
			if (s.charAt(i) != c) {
				hasOneChar = false;
				break;
			}
		}
		
		String longestPalindrome = null;
		
		if (hasOneChar) {
			while (end < length - 1 && s.charAt(end + 1) == c) {
				end++;
			}
			while(start > 0 && s.charAt(start - 1) == c) {
				start--;
			}
		}
		//else {
			while (end < length - 1 && start > 0 && s.charAt(end + 1) == s.charAt(start - 1)) {
				end++;
				start--;
			}
		//}
		
		longestPalindrome = s.substring(start, end + 1);
		
		return longestPalindrome;
    }
    
    private boolean isPalindrome(String s) {
    	boolean isPalindrome = true;
    	
    	if (s != null) {
	    	for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
	    		if (s.charAt(i) != s.charAt(j)) {
	    			isPalindrome = false;
	    			break;
	    		}
	    	}
    	}
    	
    	return isPalindrome;
    }
}
