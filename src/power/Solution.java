package power;

/**
 * https://oj.leetcode.com/problems/powx-n/
 * 
 * Implement pow(x, n).
 * 
 */
public class Solution {
	public double pow(double x, int n) {
        if (n == 0) return 1;
        if (x == 1) return 1;
        if (x == -1) {
            if (n % 2 == 0) {
                return 1;
            }
            else {
                return -1;
            }
        }
        if (n < 0) {
        	return pow(1/x, -n);
        }
        else {
        	double a = this.pow(x, n/2);
            if (n % 2 == 0) { 
            	return a * a;
            }
            else {
            	return x * a * a;
            }
        }
    }
}
