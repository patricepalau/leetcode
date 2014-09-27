package palindromenb;

/**
 * https://oj.leetcode.com/problems/palindrome-number/
 * 
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 */
public class Solution {
    public boolean isPalindrome(int x) {
    	int n = x;
    	int nbDigits = 1;
    	
    	if (x < 0) {
    		return false;
    	}
    	
		while (x / Math.pow(10, nbDigits) >= 1) {
			nbDigits++;
    	}

    	
    	int current = x;
    	for (int i = nbDigits - 1; i >= 0; i--) {
    		int q = current / (int)Math.pow(10, i);
    		int r = current % (int)Math.pow(10, i);
    		current = r;
    		n -= q * Math.pow(10, nbDigits - 1 - i);
    	}
    	
        return n == 0;
    }
}
