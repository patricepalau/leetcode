package addbinary;

/**
 * https://oj.leetcode.com/problems/add-binary/
 * 
 * 
 */
public class Solution {
	public String addBinary(String a, String b) {
        StringBuffer result = new StringBuffer();
        
        if (a.length() < b.length()) {
        	int diff = b.length() - a.length();
        	for (int i = 0; i < diff; i++) {
        		a = "0" + a;
        	}
        }
        else {
        	int diff = a.length() - b.length();
        	for (int i = 0; i < diff; i++) {
        		b = "0" + b;
        	}
        }
        int len = a.length();
        boolean remainder = false;
        for (int i = len - 1; i >= 0; i--) {
        	int da = 0; //'0';
        	int db = 0; //'0';
        	if (i < a.length()) {
        		da = (int)(a.charAt(i) - '0');
        	}
        	if (i < b.length()) {
        		db = (int)(b.charAt(i) - '0');
        	}
        	int res = da + db + (remainder ? 1 : 0);
        	
        	switch(res) {
        	case 3: 
        		res = 1;
        		remainder = true;
        		break;
        	case 2:
        		res = 0;
        		remainder = true;
        		break;
        	default:
        		remainder = false;
        	}
        	
        	result.insert(0, (char)('0' + res));
        }
        
        if (remainder) {
        	result.insert(0, '1');
        }
        
        return result.toString();
    }
}
