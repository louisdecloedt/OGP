package drawit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;


public class IntPointTest {

	@Test
	public void test() {
		IntPoint testPoint1 = new IntPoint(10,-1221);
		assertEquals(10, testPoint1.getX());
		assertEquals(-1221, testPoint1.getY());
		DoublePoint testPoint2 = new DoublePoint(10, -1221);
		assertEquals(testPoint1.getX(),testPoint2.getX());
		assertEquals(testPoint1.getX(),testPoint2.getX());
		
		
		IntPoint testPoint3 = new IntPoint(2,2);
		IntPoint testPoint4 = new IntPoint(10,10);
		
		IntPoint testPoint5 = new IntPoint(5,5);
		IntPoint testPoint6 = new IntPoint(2,2);
		IntPoint testPoint7 = new IntPoint(5,6);
		IntPoint testPoint8 = new IntPoint(7,-13);
		IntPoint testPoint9 = new IntPoint(10,10);
		
		//Errors because of integer Arithmetic?
		
		assertEquals(true, testPoint5.isOnLineSegment(testPoint3, testPoint4));
		assertEquals(false, testPoint6.isOnLineSegment(testPoint3, testPoint4));
		assertEquals(false, testPoint7.isOnLineSegment(testPoint3, testPoint4));
		assertEquals(false, testPoint8.isOnLineSegment(testPoint3, testPoint4));
		assertEquals(false, testPoint9.isOnLineSegment(testPoint3, testPoint4));
		
		
		IntPoint testPoint10 = new IntPoint(5,5);
		IntPoint testPoint11 = new IntPoint(2,2);
		IntPoint testPoint12 = new IntPoint(5,6);
		IntPoint testPoint13 = new IntPoint(1,-20);
		IntPoint testPoint14 = new IntPoint(10,10);
		IntPoint testPoint15 = new IntPoint(0, 0);
		IntPoint testPoint16 = new IntPoint(10,12);
		IntPoint testPoint17 = new IntPoint(-5,12);
		IntPoint testPoint18 = new IntPoint(10,-3);
		
		
		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint3, testPoint4, testPoint5, testPoint6)); 
		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint3, testPoint4, testPoint6, testPoint5)); 
		assertEquals(true, IntPoint.lineSegmentsIntersect(testPoint11, testPoint10, testPoint12, testPoint13)); 
		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint10, testPoint11, testPoint11, testPoint12)); 
		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint13, testPoint14, testPoint15, testPoint16)); 
		assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint13, testPoint14, testPoint16, testPoint15));
		assertEquals(true, IntPoint.lineSegmentsIntersect(testPoint17, testPoint18, testPoint14, testPoint13));
	}

}
