package maxpointsonaline;

import maxpointsonaline.Solution.Point;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	
	
	// * (0,0), (0,3), (1,2), (2,1), (4,2), (5,5)

	@Test
	public void test1() {
		Solution solution = new Solution();
		Point[] points = new Point[] {
			new Point(0,0),
			new Point(0,3),
			new Point(1,2),
			new Point(2,1),
			new Point(5,5),
			new Point(4,2)
		};
		
		Assert.assertEquals(3, solution.maxPoints(points));
	}
	
	// (4,0),(4,-1),(4,5)
	@Test
	public void test2() {
		Solution solution = new Solution();
		Point[] points = new Point[] {
			new Point(4,0),
			new Point(4,-1),
			new Point(4,5)
		};
		
		Assert.assertEquals(3, solution.maxPoints(points));
	}
	
	// (0,0),(1,1),(1,-1)
	@Test
	public void test3() {
		Solution solution = new Solution();
		Point[] points = new Point[] {
			new Point(0,0),
			new Point(1,1),
			new Point(1,-1)
		};
		
		Assert.assertEquals(2, solution.maxPoints(points));
	}
	
	// (3,1),(12,3),(3,1),(-6,-1)
	@Test
	public void test4() {
		Solution solution = new Solution();
		Point[] points = new Point[] {
			new Point(3,1),
			new Point(12,3),
			new Point(3,1),
			new Point(-6,-1)
		};
		
		Assert.assertEquals(4, solution.maxPoints(points));
	}
	
	// (84,250),(0,0),(1,0),(0,-70),(0,-70),(1,-1),(21,10),(42,90),(-42,-230)
	@Test
	public void test5() {
		Solution solution = new Solution();
		Point[] points = new Point[] {
			new Point(84,250),
			new Point(0,0),
			new Point(1,0),
			new Point(0,-70),
			new Point(0,-70),
			new Point(1,-1),
			new Point(21,10),
			new Point(42,90),
			new Point(-42,-230)
		};
		
		Assert.assertEquals(6, solution.maxPoints(points));
	}
	
	@Test
	public void test6() {
		Solution solution = new Solution();
		Point[] points = new Point[] {
			new Point(1,1),
			new Point(1,1),
			new Point(1,1)
		};
		
		Assert.assertEquals(3, solution.maxPoints(points));
	}
	
}
