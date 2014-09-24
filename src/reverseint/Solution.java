package reverseint;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int reverse(int x) {
		int result = 0;
		long temp = 0;
		int q = x;
		int r = 0;
		List<Integer> digits = new ArrayList<Integer>();
		
		do {
			q = x / 10;
			r = x % 10;
			
			digits.add(r);
			
			x = q;
		}
		while (q != 0);
		
		for (int i = 0; i < digits.size() ; i++) {
			int digit = digits.get(i);
			temp += digit * Math.pow(10, digits.size() - i - 1);
		}
		
		if (temp > Integer.MAX_VALUE) {
			result = (int)temp;
		}
		else {
			result = (int)temp;
		}
		
		if (x < 0) result = -result;
		
        return result;
    }
}
