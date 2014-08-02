package maxpointsonaline;

/**
 * https://oj.leetcode.com/problems/max-points-on-a-line/
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line
 * 
 * The brute-force solution got accepted i.e. considering each pair of points (thus forming a line)
 * and checking whether any other point is in the line.
 * 
 */
public class Solution {
	
	static public class Point {
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { x = a; y = b; }
	}
	
	private boolean same(Point p1, Point p2) {
		return (p1.x == p2.x && p1.y == p2.y);
	}
	
    public int maxPoints(Point[] points) {
        int max = 0;
        
        // edge cases:
        // - no points
        if (points.length == 0) return 0;
        // - 1 point only
        if (points.length == 1) return 1;
        // - all points identical
        boolean all = true;
        Point prev = points[0];
        for (int i = 1; i < points.length; i++) {
        	if (!same(prev, points[i])) {
        		all = false;
        		break;
        	}
        	prev = points[i];
        }
        
        // all points identical: return nb of items
        if (all) return points.length;
        
        // otherwise take each point in P
        for (int i = 0; i < points.length; i++) {
        	// try to build a line with every other point
        	for (int j = i + 1; j < points.length; j++) {
        		// consider the line Pi-Pj
        		// find any point in P going through that line
        		int xi = points[i].x, xj = points[j].x;
        		int yi = points[i].y, yj = points[j].y;
        		// if Pi and Pj are the same point, stop here
        		if (same(points[i], points[j])) continue;
        		// we have at least 2 points in our line...
        		int count = 2;
        		// try every other point 
        		for (int k = 0; k < points.length; k++) {
        			// except Pi and Pj
        			if (k == i || k == j) continue;
        			int xk = points[k].x, yk = points[k].y;
        			// if we have twice the same point, it still counts towards the total
        			// i.e Pi = Pk or Pj = Pk
        			if (same(points[i], points[k]) || same(points[j], points[k])) {
        				count++;
        			}
        			else {
        				// to avoid any calculation inaccuracies...
        				// ...we calculate the surface of the triangle formed by the 3 points
        				int s = (xj - xi)*(yk - yi) - (xk - xi)*(yj - yi);
        				// if it's zero, we have a line
            			if (s == 0) count++;
        			}
        		}
        		
        		// did we beat our max so far?
        		if (count > max) max = count;
        	}
        }
        
        // return max count
        return max;
    }
}
