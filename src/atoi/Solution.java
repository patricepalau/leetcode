package atoi;

public class Solution {
    public int atoi(String str) {
        if (str == null || str.length() == 0) return 0;
        
        long result = 0;
        str = str.trim();
        int n = str.length();
        boolean sign = true;
        boolean isInvalid = false;
        for (int i = 0; i < n; i++) {
        	char c = str.charAt(i);
        
        	int digit = c - '0';
        	if (digit < 0 || digit > 9) {
        		if (i > 0) {
        			// discard all characters starting at this one
	        		int nbCharToDiscard = n - i;
	        		result /= Math.pow(10, nbCharToDiscard);
	        		break;
	        	}
        		else {
        			if (c == '-') {
        				sign = false;
        			}
        			else if (c == '+') {
        				//
        			}
        			else {
        				isInvalid = true;
        				break;
        			}
        		}
        	}
        	else {
        		result += digit * Math.pow(10, n - i - 1);
        	}
        }
        
        if (isInvalid) result = 0;
        
        if (!sign) result = -result;
        if (result <= Integer.MIN_VALUE)
        	result = Integer.MIN_VALUE;
        if (result >= Integer.MAX_VALUE)
        	result = Integer.MAX_VALUE;
        
        return (int)result;
    }
}
