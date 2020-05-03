package drawit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FastRoundedPolygonContainsTestStrategyTest {

	@Test
	void test() {
		IntPoint point1 = new IntPoint(0,0);
	    IntPoint point2 = new IntPoint(100,0); 
	    IntPoint point3 = new IntPoint(100,100); 
	    IntPoint point4 = new IntPoint(0,100);
	    RoundedPolygon poly1 = new RoundedPolygon(); 
	    IntPoint[] array1 = new IntPoint[4]; 
	    array1[0] = point1; 
	    array1[1] = point2; 
	    array1[2] = point3;
	    array1[3] = point4; 
	    poly1.setRadius(1);
	    poly1.setVertices(array1); 
	    IntPoint testPoint1 = new IntPoint(10,10);
	    IntPoint testPoint2 = new IntPoint(50,100); 
	    IntPoint testPoint3 = new IntPoint(100,100); 
	    IntPoint testPoint4 = new IntPoint(-10,10); 
	    IntPoint testPoint5 = new IntPoint(50,-100); 
	    IntPoint testPoint6 = new IntPoint(101,100); 
	    IntPoint testPoint7 = new IntPoint(78, 3);
	    IntPoint testPoint8 = new IntPoint(100, 103);
	    
	    FastRoundedPolygonContainsTestStrategy test1 = 
	    		new FastRoundedPolygonContainsTestStrategy();
	    
	    assertEquals(true, test1.contains(poly1,testPoint1)); 
	    assertEquals(true, test1.contains(poly1, testPoint2)); 
	    assertEquals(true, test1.contains(poly1, testPoint3)); 
	    assertEquals(false, test1.contains(poly1, testPoint4)); 
	    assertEquals(false, test1.contains(poly1, testPoint5)); 
	    assertEquals(false, test1.contains(poly1, testPoint6));
	    assertEquals(true, test1.contains(poly1, testPoint7));
	    assertEquals(false, test1.contains(poly1, testPoint8));
	}
	
	@Test
	public void testConcave() {
		/**
		 * Test cases for Rounded Polygon
		 */
		IntPoint punt1 = new IntPoint(0, 0);
		IntPoint punt2 = new IntPoint(30, 0);
		IntPoint punt3 = new IntPoint(20, 5);
		IntPoint punt4 = new IntPoint(30, 10);
		IntPoint punt5 = new IntPoint(0, 10);
		IntPoint punt6 = new IntPoint(5, 5);

		IntPoint[] lijst = new IntPoint[0];
		lijst = PointArrays.insert(lijst, 0, punt1);
		lijst = PointArrays.insert(lijst, 1, punt2);
		lijst = PointArrays.insert(lijst, 2, punt3);
		lijst = PointArrays.insert(lijst, 3, punt4);
		lijst = PointArrays.insert(lijst, 4, punt5);
		lijst = PointArrays.insert(lijst, 5, punt6);


		RoundedPolygon roundpoly = new RoundedPolygon();
		roundpoly.setVertices(lijst);

		assertEquals(null, PointArrays.checkDefinesProperPolygon(lijst));

		IntPoint testPunt1 = new IntPoint(10, 5);
		IntPoint testPunt2 = new IntPoint(3, 5);
		IntPoint testPunt3 = new IntPoint(2, 6);
		IntPoint testPunt3B = new IntPoint(3, 4);
		IntPoint testPunt4 = new IntPoint(10, 6);
		IntPoint testPunt5 = new IntPoint(25, 5);
		IntPoint testPunt6 = new IntPoint(31, 5);
		IntPoint testPunt7 = new IntPoint(15, 20);
		
		FastRoundedPolygonContainsTestStrategy test1 = 
	    		new FastRoundedPolygonContainsTestStrategy();
		assertEquals(true, test1.contains(roundpoly, testPunt1));
		assertEquals(true, test1.contains(roundpoly, testPunt2));
		assertEquals(true, test1.contains(roundpoly, testPunt3));
		assertEquals(true, test1.contains(roundpoly, testPunt3B));
		assertEquals(true, test1.contains(roundpoly, testPunt4));
		assertEquals(true, test1.contains(roundpoly, testPunt5));
		assertEquals(false, test1.contains(roundpoly, testPunt6));
		assertEquals(false, test1.contains(roundpoly, testPunt7));
	}


}
