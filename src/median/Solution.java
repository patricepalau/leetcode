package median;


/**
 * https://oj.leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * There are two sorted arrays A and B of size m and n respectively.
 * Find the median of the two sorted arrays.
 * 
 * The overall run time complexity should be O(log (m+n)).
 * 
 * We're in fact looking for the (m+n)/2 element in A U B.
 * 
 * Given:
 * a1 ... ai ... an
 * b1 ... bj ... bm
 * 
 * ai is the kth smallest element if:
 * - i + j = k
 * - bj < ai < bj+1
 * i.e. we have this situation:
 *    a1 ...     ai      ...    an
 *    b1 ... bj     bj+1 ... bm
 * 
 * if we have:
 *    a1 ...  ai    ai+1  ...    an
 *    b1 ...     bj     bj+1 ... bm
 * then bj is the kth smallest
 * 
 * If instead we get:
 *
 * Case 1:
 * -------
 *    a1     ai  ai+1       ...    an
 *    b1 ...          bj bj+1 ... bm
 * i.e. ai < ai+1 < bj < bj+1
 * 
 * Then it means that there is at least
 *   (i+1) + (j-1) = k items smaller than bj (so it's at least the (k+1)th smaller item - i.e. k+1 or more)
 * Similarly there is at most:
 *   (i-1) + (j-1) = k - 2 items smaller than ai (so it's at most the (k-1)th smaller item - i.e. k-1 or less)
 *   (assuming b1 < ... < bj-1 < ai in the best case)
 * 
 * This also means that:
 * - bj+1 is at least the k+2 smaller, bj+2 the k+3 samller etc.
 * - ai-1 is at most the k-2 smaller, ai-2 the k-3 smaller etc.  
 * 
 * which means we can discard:
 * - bj ... bm
 * - a1 ... ai
 * from our search for the kth smallest element.
 * 
 * 
 * Case 2:
 * ------
 *    a1     ...     ai      ...    an
 *    b1 ... bj bj+1 ... bm
 * i.e. bj < bj+1 < ai
 * 
 * Following a similar reasoning:
 * - ai is at best the k+1 smaller element (a1..ai=1 and b1...bj+1 are smaller)
 * - bj is at most the k-1 smaller element (b1..bj-1 and, in the best case, a1...ai-1) are smaller
 * 
 * so we can discard:
 * - ai ... an
 * - b1 ... bj
 * from our search
 * 
 * Since we're searching for k=(n+m)/2, we can start with i=n/2 and j=m/2 (hence i+j=k)
 * then continue halving the search space by discarding at least half of the elements
 * of A U B at each iteration
 * 
 */
public class Solution {
	private int[] A;
	private int[] B;
	
	public double findMedianSortedArrays(int A[], int B[]) {		
		this.A = A;
		this.B = B;
		
		int n = A.length;
		int m = B.length;
		
		// if n+m is odd, we want the item exactly in the middle spot
		int k = (n + m) / 2 + (n + m) % 2;
				
		return findMedian(1, A.length, 1, B.length, k);
	}

	// indexes are 1-based
	private double findMedian(int startA, int n, int startB, int m, int k) {
		// we pick i and j in the middle of A and B
		int i = (int)Math.ceil((double)n / 2); // middle element in A
 		int j = (int)Math.ceil((double)m / 2); // middle element in B
 		
// 		// if i and j are both odd
// 		if (!(n <= 1 && m <= 1) && i + j == k + 1) {
// 			// decrement the largest of i and j
// 			if (i > j) i--; else j--;
// 		}
 		
 		
 		// ensure i + j = k
 		if (i + j > k) {
 			int delta = i + j - k;
 			if (i - delta >= 1) {
 				i -= delta;
 			}
 			else if (j - delta >= 1) {
 				j -= delta;
 			}
 		}
 		
 		if (i + j < k) {
 			int delta = k - i - j;
			if (j + delta <= m) {
				j += delta;
			}
			else if (i + delta <= n) {
				i += delta;
			}
 		}
 		
 		// adjust i and j according to start indexes
		i = i + startA - 1;
 		j = j + startB - 1;
 		
 		int ai   = i >= 1 ? A[i-1] : Integer.MIN_VALUE; // ai
 		int bj   = j >= 1 ? B[j-1] : Integer.MIN_VALUE; // bj
 		int aip1 = i < A.length ? A[i] : Integer.MAX_VALUE;   // ai+1
 		int bjp1 = j < B.length ? B[j] : Integer.MAX_VALUE;   // bj+1
 		
 		// either ai or bj is the kth smallest element
		if (bj <= ai && ai <= bjp1) {
			if ((A.length + B.length) % 2 == 0) {
				// need the next smaller either ai+1 or bj+1
				if (aip1 == Integer.MAX_VALUE && bjp1 == Integer.MAX_VALUE) {
					return ((double)(ai + bj)) / 2;
				}
				else {
					return ((double)(ai + Math.min(aip1, bjp1))) / 2;
				}
			}
			return ai;
		}
		if (ai <= bj && bj <= aip1) {
			if ((A.length + B.length) % 2 == 0) {
				// need the next smaller either ai+1 or bj+1
				if (aip1 == Integer.MAX_VALUE && bjp1 == Integer.MAX_VALUE) {
					return ((double)(ai + bj)) / 2;
				}
				else {
					return ((double)(bj + Math.min(aip1, bjp1))) / 2;
				}
			}
			return bj;
		}
		
		if (ai < bj) {
			// we're discarding a1 ... ai and bj bj+1 ... bm, which means that:
			// - the new start index for A is i + 1
			// - the new n is the current n minus the # of items we're discarding i.e. a[startA] to a[i]
			return findMedian(i + 1, n - (i - startA + 1), startB, j - startB, k - (i - startA + 1)); 
		}
		else {
			// same - we're discarding ai ai+1 and b1 ... bj
			return findMedian(startA, i - startA, j + 1, m - (j - startB + 1), k - (j - startB + 1));
		}
	}

}
