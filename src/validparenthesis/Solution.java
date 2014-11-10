package validparenthesis;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {
	private static List<Character> opening = Arrays.asList('(', '{', '[');
	private static List<Character> closing = Arrays.asList(')', '}', ']');
	public boolean isValid(String s) {
		if (s == null) return true;
		
		boolean isValid = true;
		
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			Character current = s.charAt(i);
			if (opening.contains(current)) {
				stack.push(current);
			}
			else if (closing.contains(current)) {
				if (!stack.isEmpty()) {
					Character last = stack.pop();
					if (closing.indexOf(current) != opening.indexOf(last)) {
						isValid = false;
						break;
					}
				}
				else {
					isValid = false;
				}			
			}
		}
		
		isValid = isValid && stack.isEmpty();
		
		return isValid;
	}
}
