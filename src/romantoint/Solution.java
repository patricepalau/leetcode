package romantoint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int romanToInt(String s) {
        int result = 0;
        
        List<Character>  romans   = Arrays.asList('M', 'D', 'C', 'L', 'X', 'V', 'I');
        List<Integer> integers = Arrays.asList(1000, 500, 100,  50,  10,   5,   1);
        
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for (int i = 0; i < romans.size(); i++) {
        	map.put(romans.get(i), integers.get(i));
        }
        
        for (int i = 0; i < s.length(); i++) {
        	char current = s.charAt(i);
        	int value = map.get(current);
        	if (i + 1 < s.length()) {
        		char nextChar = s.charAt(i + 1);
        		int nextValue = map.get(nextChar);
        		if (nextValue > value) {
        			value = nextValue - value;
        			i++;
        		}
        	}
        	result += value;
        }
        
        return result;
    }
}
