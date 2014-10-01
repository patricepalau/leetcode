package inttoroman;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        
        List<String>  romans   = Arrays.asList("M", "D",  "C",  "L", "X", "V", "I");
        List<Integer> integers = Arrays.asList(1000, 500, 100,  50,  10,   5,   1);
        
        for (int i = 0; i < romans.size(); i++) {
        	int current = integers.get(i);
        	String roman = romans.get(i);
        
            int q = num / current;
            if (q > 0) {
            	StringBuilder temp = new StringBuilder();
            	for (int j = 1; j <= q; j++) {
            		temp.append(roman);
            	}
            	String simplifiedString = simplify(temp.toString(), result);
            	result.append(simplifiedString);
            	num -= current * q;
            }
        }
        
        return result.toString();
    }
    
    private String simplify(String s, StringBuilder current) {
    	if ("IIII".equals(s)) {
    		s = "IV";
    	}
    	else if ("XXXX".equals(s)) {
    		s = "XL";
    	}
    	else if ("CCCC".equals(s)) {
    		s = "CD";
    	}
    	
    	String previous = null;
    	
    	if (current.length() >= 1) previous = current.substring(current.length() - 1, current.length());
    	// V + IV = IX
    	if ("V".equals(previous) && "IV".equals(s)) {
    		current.deleteCharAt(current.length() - 1);
    		s = "IX";
    	}
    	// L + XL = XC
    	if ("L".equals(previous) && "XL".equals(s)) {
    		current.deleteCharAt(current.length() - 1);
    		s = "XC";
    	}
    	// D + CD = CM
    	if ("D".equals(previous) && "CD".equals(s)) {
    		current.deleteCharAt(current.length() - 1);
    		s = "CM";
    	}
    	
    	return s;
    }
}
