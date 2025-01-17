package drawit.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import drawit.IntPoint;
import drawit.PointArrays;
import drawit.RoundedPolygon;

public class RoundedPolygonTest {

	
	@Test 
	public void test() { 
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
	    assertEquals(true, array1[0].equals(point1));
	    assertEquals(true, array1[1].equals(point2)); 
	    assertEquals(true, array1[2].equals(point3)); 
	    assertEquals(true, array1[3].equals(point4));
	    assertEquals(null, PointArrays.checkDefinesProperPolygon(array1));
	    
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
	    
	    assertEquals(true, poly1.contains(testPoint1)); 
	    assertEquals(true, poly1.contains(testPoint2)); 
	    assertEquals(true,poly1.contains(testPoint3)); 
	    assertEquals(false,poly1.contains(testPoint4)); 
	    assertEquals(false, poly1.contains(testPoint5)); 
	    assertEquals(false,poly1.contains(testPoint6));
	    assertEquals(false, testPoint6.isOnLineSegment(point2,point3));
	    assertEquals(false, testPoint6.isOnLineSegment(point3,point4));
	    assertEquals(true,poly1.contains(testPoint7));
	    assertEquals(false,poly1.contains(testPoint8));
	}
	
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
//		RoundedPolygon hardCorePolygon = new RoundedPolygon();
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
//		assertEquals(true, array1[0].equals(point1));
//		assertEquals(true, array1[1].equals(point2));
//		assertEquals(true, array1[2].equals(point3));
//		assertEquals(true, array1[3].equals(point4));
//		assertEquals(null, PointArrays.checkDefinesProperPolygon(array1));
//		
//		hardCorePolygon.setVertices(array1);
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
//		assertEquals(true, testPoint7.isOnLineSegment(point10, point11));
//		
//		IntPoint testPoint9 = new IntPoint(-200,300);
//		IntPoint testPoint10 = new IntPoint(600,600);
//		IntPoint testPoint11 = new IntPoint(-100,-100);
//		IntPoint testPoint12 = new IntPoint(150,50);
//		IntPoint testPoint13 = new IntPoint(250,250);
//		IntPoint testPoint14 = new IntPoint(100,700);
//	
//		assertEquals(true, hardCorePolygon.contains(testPoint1));
//		assertEquals(true, hardCorePolygon.contains(testPoint2)); 
//		assertEquals(true, hardCorePolygon.contains(testPoint3)); 
//		assertEquals(true, hardCorePolygon.contains(testPoint4));
//		assertEquals(true, hardCorePolygon.contains(testPoint5)); 
//		assertEquals(true, hardCorePolygon.contains(testPoint6)); 
//		assertEquals(true, hardCorePolygon.contains(testPoint7));
//		assertEquals(true, hardCorePolygon.contains(testPoint8)); 
//		assertEquals(true, hardCorePolygon.contains(pieterPoint));
//		assertEquals(true, hardCorePolygon.contains(pieterPoint2));
//
//		assertEquals(false, hardCorePolygon.contains(testPoint9)); //!!
//		assertEquals(false, hardCorePolygon.contains(testPoint10)); 
//		assertEquals(false, hardCorePolygon.contains(testPoint11)); 
//		assertEquals(false, hardCorePolygon.contains(testPoint12));
//		assertEquals(false, hardCorePolygon.contains(testPoint13)); 
//		assertEquals(false, hardCorePolygon.contains(testPoint14));
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
//		assertEquals(false, hardCorePolygon.contains(testPointD)); 
//		assertEquals(true, hardCorePolygon.contains(testPointE)); 
//		assertEquals(true, hardCorePolygon.contains(testPointF));
//		assertEquals(false, hardCorePolygon.contains(testPointG)); 
//		assertEquals(false, hardCorePolygon.contains(testPointH));
//		assertEquals(false, hardCorePolygon.contains(testPointI));
//		assertEquals(false, hardCorePolygon.contains(testPointJ));
//		assertEquals(true, hardCorePolygon.contains(testPointK));
//		assertEquals(false, hardCorePolygon.contains(testPointX));
//		assertEquals(false, hardCorePolygon.contains(testPointY));
//		
//		IntPoint exitPoint = new IntPoint(100000,testPoint9.getY());
//		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point16, point17));
//		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point17, point1));
//		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point3, point4));
//		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point4, point5));
//		assertEquals(true, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point10, point11));
//	}
//	
	/*
	@Test
	public void getDrawingCommands() {
		RoundedPolygon poly1 = new RoundedPolygon();
		//System.out.print(poly1.getDrawingCommands());
		assertEquals(
				"line 0 50 0 10 \n" + 
				"arc 10 10 10 3.141592653589793 1.5707963267948966 \n" +
				"line 10 0 50 0 \n" +
				"line 50 0 90 0 \n" +
				"arc 90 10 10 -1.5707963267948966 1.5707963267948966 \n" +
				"line 100 10 100 50 \n" +
				"line 100 50 100 90 \n" +
				"arc 90 90 10 0.0 1.5707963267948966 \n" +
				"line 90 100 50 100 \n" +
				"line 50 100 10 100 \n" +
				"arc 10 90 10 1.5707963267948966 1.5707963267948966 \n" +
				"line 0 90 0 50 \n", poly1.getDrawingCommands());

	} */
	
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


		assertEquals(true, roundpoly.contains(testPunt1));
		assertEquals(false, roundpoly.contains(testPunt2));
		assertEquals(false, roundpoly.contains(testPunt3));
		assertEquals(false, roundpoly.contains(testPunt3B));
		assertEquals(true, roundpoly.contains(testPunt4));
		assertEquals(false, roundpoly.contains(testPunt5));
		roundpoly.contains(testPunt2);
	}
	
	

}
