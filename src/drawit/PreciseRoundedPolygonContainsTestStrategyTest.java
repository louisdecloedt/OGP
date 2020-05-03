package drawit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PreciseRoundedPolygonContainsTestStrategyTest {

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
	    
	    PreciseRoundedPolygonContainsTestStrategy test1 = 
	    		new PreciseRoundedPolygonContainsTestStrategy();
	    
	    assertEquals(true, test1.contains(poly1,testPoint1)); 
	    assertEquals(true, test1.contains(poly1, testPoint2)); 
	    assertEquals(true, test1.contains(poly1, testPoint3)); 
	    assertEquals(false, test1.contains(poly1, testPoint4)); 
	    assertEquals(false, test1.contains(poly1, testPoint5)); 
	    assertEquals(false, test1.contains(poly1, testPoint6));
	    assertEquals(true, test1.contains(poly1, testPoint7));
	    assertEquals(false, test1.contains(poly1, testPoint8));
	}
	
	
	//NOTE: this test doesn't work because, there Extent has no support
	//for points with nonpositive coordinates. (Throws)
//	@Test
//	public void hardCoreTest() {
//		IntPoint point1 = new IntPoint(0,0);
//		IntPoint point2 = new IntPoint(100,0);
//		IntPoint point3 = new IntPoint(100,300);
//		IntPoint point4 = new IntPoint(300,300);
//		IntPoint point5 = new IntPoint(300,200);
//		IntPoint point6 = new IntPoint(200,200);
//		IntPoint point7 = new IntPoint(200,100);
//		IntPoint point8 = new IntPoint(300,100);
//		IntPoint point9 = new IntPoint(400,0);
//		IntPoint point10= new IntPoint(500,0);
//		IntPoint point11= new IntPoint(500,400);
//		IntPoint point12= new IntPoint(350,400);
//		IntPoint point13= new IntPoint(350,600);
//		IntPoint point14= new IntPoint(0,600);
//		IntPoint point15= new IntPoint(0,900);
//		IntPoint point16= new IntPoint(-100,900);
//		IntPoint point17= new IntPoint(-100,300);
//		
//		RoundedPolygon hardcore = new RoundedPolygon();
//		IntPoint[] array1 = new IntPoint[17];
//		array1[0] = point1;
//		array1[1] = point2;
//		array1[2] = point3;
//		array1[3] = point4;
//		array1[4] = point5;
//		array1[5] = point6;
//		array1[6] = point7;
//		array1[7] = point8;
//		array1[8] = point9;
//		array1[9] = point10;
//		array1[10] = point11;
//		array1[11] = point12;
//		array1[12] = point13;
//		array1[13] = point14;
//		array1[14] = point15;
//		array1[15] = point16;
//		array1[16] = point17;
//		
//		PreciseRoundedPolygonContainsTestStrategy test1 = 
//	    		new PreciseRoundedPolygonContainsTestStrategy();
//	    
//		
//		hardcore.setVertices(array1);
//		IntPoint testPoint1 = new IntPoint(350,50);
//		IntPoint testPoint2 = new IntPoint(101,600);
//		IntPoint testPoint3 = new IntPoint(50,150);
//		IntPoint testPoint4 = new IntPoint(450,150);
//		IntPoint testPoint5 = new IntPoint(-50,750);
//		IntPoint testPoint6 = new IntPoint(150,450);
//		IntPoint testPoint7 = new IntPoint(500,200);
//		IntPoint testPoint8 = new IntPoint(350,600);
//		IntPoint pieterPoint = new IntPoint(250,150);
//		IntPoint pieterPoint2 = new IntPoint(200,150);
//		
//		IntPoint testPoint9 = new IntPoint(-200,300);
//		IntPoint testPoint10 = new IntPoint(600,600);
//		IntPoint testPoint11 = new IntPoint(-100,-100);
//		IntPoint testPoint12 = new IntPoint(150,50);
//		IntPoint testPoint13 = new IntPoint(250,250);
//		IntPoint testPoint14 = new IntPoint(100,700);
//	
//		assertEquals(true, test1.contains(hardcore, testPoint1));
//		assertEquals(true, test1.contains(hardcore, testPoint2)); 
//		assertEquals(true, test1.contains(hardcore, testPoint3)); 
//		assertEquals(true, test1.contains(hardcore, testPoint4));
//		assertEquals(true, test1.contains(hardcore, testPoint5)); 
//		assertEquals(true, test1.contains(hardcore, testPoint6)); 
//		assertEquals(true, test1.contains(hardcore, testPoint7));
//		assertEquals(true, test1.contains(hardcore, testPoint8)); 
//		assertEquals(true, test1.contains(hardcore, pieterPoint));
//		assertEquals(true, test1.contains(hardcore, pieterPoint2));
//
//		assertEquals(false, test1.contains(hardcore, testPoint9)); //!!
//		assertEquals(false, test1.contains(hardcore, testPoint10)); 
//		assertEquals(false, test1.contains(hardcore, testPoint11)); 
//		assertEquals(false, test1.contains(hardcore, testPoint12));
//		assertEquals(false, test1.contains(hardcore, testPoint13)); 
//		assertEquals(false, test1.contains(hardcore, testPoint14));
//		
//		IntPoint testPointD = new IntPoint(-200,900);
//		IntPoint testPointE = new IntPoint(-100,900);
//		IntPoint testPointF = new IntPoint(-50,600);
//		IntPoint testPointG = new IntPoint(150,150);
//		IntPoint testPointH = new IntPoint(-200,100);
//		IntPoint testPointI = new IntPoint(100,700);
//		IntPoint testPointJ = new IntPoint(124,200);
//		IntPoint testPointK = new IntPoint(300,400);
//		IntPoint testPointX = new IntPoint(501,400);
//	    IntPoint testPointY = new IntPoint(1,900);
//		
//		assertEquals(false, test1.contains(hardcore, testPointD)); 
//		assertEquals(true, test1.contains(hardcore, testPointE)); 
//		assertEquals(true, test1.contains(hardcore, testPointF));
//		assertEquals(false, test1.contains(hardcore, testPointG)); 
//		assertEquals(false, test1.contains(hardcore, testPointH));
//		assertEquals(false, test1.contains(hardcore, testPointI));
//		assertEquals(false, test1.contains(hardcore, testPointJ));
//		assertEquals(true, test1.contains(hardcore, testPointK));
//		assertEquals(false, test1.contains(hardcore, testPointX));
//		assertEquals(false, test1.contains(hardcore, testPointY));
//		
//		IntPoint exitPoint = new IntPoint(100000,testPoint9.getY());
//		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point16, point17));
//		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point17, point1));
//		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point3, point4));
//		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point4, point5));
//		assertEquals(true, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point10, point11));
//	}
//	

	
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


		PreciseRoundedPolygonContainsTestStrategy test1 = 
	    		new PreciseRoundedPolygonContainsTestStrategy();
		
		
		assertEquals(true, test1.contains(roundpoly, testPunt1));
		assertEquals(false, test1.contains(roundpoly, testPunt2));
		assertEquals(false, test1.contains(roundpoly, testPunt3));
		assertEquals(false, test1.contains(roundpoly, testPunt3B));
		assertEquals(true, test1.contains(roundpoly, testPunt4));
		assertEquals(false, test1.contains(roundpoly, testPunt5));
	}

	

}
