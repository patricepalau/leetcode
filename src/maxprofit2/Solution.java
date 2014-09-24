package maxprofit2;

public class Solution {
    public int maxProfit(int[] prices) {
		if (prices.length == 0) return 0;
		int n = prices.length;
		int buyAt = -1; // indicates no stock bought
		int profit = 0;
		for (int i = 0; i < n - 1; i++) {
			int today    = prices[i];
			int tomorrow = prices[i + 1];
			// price is going up
			if (tomorrow > today) {
				// if we have no stock, buy today
				if (buyAt == -1) {
					buyAt = today;
				}
				else {
					// wait to sell - price is going up
				}
			}
			else {
				if (buyAt >= 0) {
					// sell if price is going down tomorrow
					profit += (today - buyAt);
					buyAt = -1;
				}
				else {
					// wait to buy - price will be lower tomorrow
				}
			}
				
		}
		
		// on the last day, sell position if profit
		if (buyAt != -1 && buyAt < prices[n - 1]) {
			profit += (prices[n-1] - buyAt);
		}
		
		return profit;
    }
}
