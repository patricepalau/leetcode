package countandsay;

public class Solution {
    public String countAndSay(int n) {
    	if (n == 1) return "1";
        int i = 1;
        String current = "1";
        String result = null;
        while (i < n) {
            result = "";
            int count = 0;
            Character prev = null;
            for (int j = 0; j < current.length(); j++) {
            	Character currentChar =  current.charAt(j);
            	if (currentChar == prev || prev == null) {
            		count++;
            	}
            	else {
            		result += String.valueOf(count) + prev;
            		count = 1;
            	}
            	prev = currentChar;
            }
            result += String.valueOf(count) + prev;
            current = result;
            i++;
        }
        
        
        return result;
     }
}
