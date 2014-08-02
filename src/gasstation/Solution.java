package gasstation;

/**
 * https://oj.leetcode.com/problems/gas-station/
 * 
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note: The solution is guaranteed to be unique.
 *
 *
 * This solution got accepted and simply attempts to start from each station in turn,
 * then goes through all N stations, until running out of gas or reaching the starting point.
 * 
 */
public class Solution {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		
		int index = -1; // result: index of starting point
		int N = gas.length;
		
		int start = 0; // current starting point
		
		// try all gas stations as a starting point
		// until finding one that allows a complete circle
		while (start < N ) {
			int j = 0;
			int amount = 0;
			boolean hasEnoughGas = true;
			while (j <= N && hasEnoughGas) {
				// can we go to the next station?
				int station = (j + start) % N;
				// amount of gas = gas at current station + cost to go to next
				amount += gas[station] - cost[station];
				// enough gas left?
				hasEnoughGas = (amount >= 0);
				// continue with next station
				j++;
			}
			
			// if we left the previous loop with gas
			// we found our result
			if (hasEnoughGas) {
				index = start;
				break;
			}
			
			// otherwise try next station as a starting point
			start++;
		}
		
		return index;
    }
}
