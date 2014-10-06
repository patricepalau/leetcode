package lettercombination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://oj.leetcode.com/problems/letter-combinations-of-a-phone-number/
 * 
 * Given a digit string, return all possible letter combinations that the number could represent.
 * 
 */
public class Solution {
    public List<String> letterCombinations(String digits) {
    	List<String> result = new ArrayList<String>();
    	
    	if (digits == null || digits.length() == 0) {
    		result.add("");
    		return result;
    	}
    	
    	Map<Integer,String> map = new HashMap<Integer,String>();
    	map.put(2, "abc");
    	map.put(3, "def");
    	map.put(4, "ghi");
    	map.put(5, "jkl");
    	map.put(6, "mno");
    	map.put(7, "pqrs");
    	map.put(8, "tuv");
    	map.put(9, "wxyz");
    	
    	// initialize list
    	Integer firstDigit = Integer.valueOf(digits.substring(0, 1));
    	String letters = map.get(firstDigit);
    	for (int k = 0; k < letters.length(); k++) {
			String letter = letters.substring(k, k + 1);
			result.add(letter);
		}
    	
    	// go through every digit
    	for (int i = 1; i < digits.length(); i++) {
    		Integer digit = Integer.valueOf(digits.substring(i, i + 1));
    		// get corresponding letters
    		letters = map.get(digit);
    		if (letters != null) {
    			// for every letter
    			List<String> combinations = new ArrayList<String>();
    			for (int j = 0; j < result.size(); j++) {
    				String current = result.get(j);
    				for (int k = 0; k < letters.length(); k++) {
        				String letter = letters.substring(k, k + 1);
        				combinations.add(current + letter);
        			}
    			}
    			result.clear();
    			result.addAll(combinations);
    		}
    	}
    	
    	return result;
    }
}
