package drawit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import org.junit.jupiter.api.Test;

public class RoundedPolygonTest {

	
	@org.junit.Test 
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
	
	@Test
	public void hardCoreTest() {
		IntPoint point1 = new IntPoint(0,0);
		IntPoint point2 = new IntPoint(100,0);
		IntPoint point3 = new IntPoint(100,300);
		IntPoint point4 = new IntPoint(300,300);
		IntPoint point5 = new IntPoint(300,200);
		IntPoint point6 = new IntPoint(200,200);
		IntPoint point7 = new IntPoint(200,100);
		IntPoint point8 = new IntPoint(300,100);
		IntPoint point9 = new IntPoint(400,0);
		IntPoint point10= new IntPoint(500,0);
		IntPoint point11= new IntPoint(500,400);
		IntPoint point12= new IntPoint(350,400);
		IntPoint point13= new IntPoint(350,600);
		IntPoint point14= new IntPoint(0,600);
		IntPoint point15= new IntPoint(0,900);
		IntPoint point16= new IntPoint(-100,900);
		IntPoint point17= new IntPoint(-100,300);
		
		RoundedPolygon hardCorePolygon = new RoundedPolygon();
		IntPoint[] array1 = new IntPoint[17];
		array1[0] = point1;
		array1[1] = point2;
		array1[2] = point3;
		array1[3] = point4;
		array1[4] = point5;
		array1[5] = point6;
		array1[6] = point7;
		array1[7] = point8;
		array1[8] = point9;
		array1[9] = point10;
		array1[10] = point11;
		array1[11] = point12;
		array1[12] = point13;
		array1[13] = point14;
		array1[14] = point15;
		array1[15] = point16;
		array1[16] = point17;
		
		assertEquals(true, array1[0].equals(point1));
		assertEquals(true, array1[1].equals(point2));
		assertEquals(true, array1[2].equals(point3));
		assertEquals(true, array1[3].equals(point4));
		assertEquals(null, PointArrays.checkDefinesProperPolygon(array1));
		
		hardCorePolygon.setVertices(array1);
		IntPoint testPoint1 = new IntPoint(350,50);
		IntPoint testPoint2 = new IntPoint(101,600);
		IntPoint testPoint3 = new IntPoint(50,150);
		IntPoint testPoint4 = new IntPoint(450,150);
		IntPoint testPoint5 = new IntPoint(-50,750);
		IntPoint testPoint6 = new IntPoint(150,450);
		IntPoint testPoint7 = new IntPoint(500,200);
		IntPoint testPoint8 = new IntPoint(350,600);
		IntPoint pieterPoint = new IntPoint(250,150);
		IntPoint pieterPoint2 = new IntPoint(200,150);

		assertEquals(true, testPoint7.isOnLineSegment(point10, point11));
		
		IntPoint testPoint9 = new IntPoint(-200,300);
		IntPoint testPoint10 = new IntPoint(600,600);
		IntPoint testPoint11 = new IntPoint(-100,-100);
		IntPoint testPoint12 = new IntPoint(150,50);
		IntPoint testPoint13 = new IntPoint(250,250);
		IntPoint testPoint14 = new IntPoint(100,700);
	
		assertEquals(true, hardCorePolygon.contains(testPoint1));
		assertEquals(true, hardCorePolygon.contains(testPoint2)); 
		assertEquals(true, hardCorePolygon.contains(testPoint3)); 
		assertEquals(true, hardCorePolygon.contains(testPoint4));
		assertEquals(true, hardCorePolygon.contains(testPoint5)); 
		assertEquals(true, hardCorePolygon.contains(testPoint6)); 
		assertEquals(true, hardCorePolygon.contains(testPoint7));
		assertEquals(true, hardCorePolygon.contains(testPoint8)); 
		assertEquals(true, hardCorePolygon.contains(pieterPoint));
		assertEquals(true, hardCorePolygon.contains(pieterPoint2));

		assertEquals(false, hardCorePolygon.contains(testPoint9)); //!!
		assertEquals(false, hardCorePolygon.contains(testPoint10)); 
		assertEquals(false, hardCorePolygon.contains(testPoint11)); 
		assertEquals(false, hardCorePolygon.contains(testPoint12));
		assertEquals(false, hardCorePolygon.contains(testPoint13)); 
		assertEquals(false, hardCorePolygon.contains(testPoint14));
		
		IntPoint testPointD = new IntPoint(-200,900);
		IntPoint testPointE = new IntPoint(-100,900);
		IntPoint testPointF = new IntPoint(-50,600);
		IntPoint testPointG = new IntPoint(150,150);
		IntPoint testPointH = new IntPoint(-200,100);
		IntPoint testPointI = new IntPoint(100,700);
		IntPoint testPointJ = new IntPoint(124,200);
		IntPoint testPointK = new IntPoint(300,400);
		IntPoint testPointX = new IntPoint(501,400);
	    IntPoint testPointY = new IntPoint(1,900);
		
		assertEquals(false, hardCorePolygon.contains(testPointD)); 
		assertEquals(true, hardCorePolygon.contains(testPointE)); 
		assertEquals(true, hardCorePolygon.contains(testPointF));
		assertEquals(false, hardCorePolygon.contains(testPointG)); 
		assertEquals(false, hardCorePolygon.contains(testPointH));
		assertEquals(false, hardCorePolygon.contains(testPointI));
		assertEquals(false, hardCorePolygon.contains(testPointJ));
		assertEquals(true, hardCorePolygon.contains(testPointK));
		assertEquals(false, hardCorePolygon.contains(testPointX));
		assertEquals(false, hardCorePolygon.contains(testPointY));
		
		IntPoint exitPoint = new IntPoint(100000,testPoint9.getY());
		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point16, point17));
		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point17, point1));
		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point3, point4));
		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point4, point5));
		assertEquals(true, IntPoint.lineSegmentsIntersect(testPoint9, exitPoint, point10, point11));
	}

}
