package zigzag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/zigzag-conversion/
 * 
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * Write the code that will take a string and make this conversion given a number of rows
 */
public class Solution {
    public String convert(String s, int nbRows) {
    	String result = null;
    	
    	List<String> rows = new ArrayList<String>(nbRows);
    	for (int i = 1; i <= nbRows; i++) {
    		rows.add("");
    	}
    	
    	int row = 1;
    	int col = 1;
    	boolean walkDiagonally = false;
    	
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		String currentStringAtRow = rows.get(row - 1);
    		rows.set(row - 1, currentStringAtRow + c);
    		
    		if (walkDiagonally) {
    			row--;
    			col++;
    		}
    		else {
    			row++;
    		}
    		
    		if (row > nbRows) {
    			// we know need to go diagonally until reaching 1 again
    			walkDiagonally = true;
    			row = Math.max(1, nbRows - 1);
    			col++;
    		}
    		
    		if (row == 0) {
    			// we reached up - go vertically again
    			walkDiagonally = false;
    			row = Math.min(2, nbRows);
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for (String current: rows) {
    		sb.append(current);
    	}
    	
    	result = sb.toString();
        return result;
    }
}
