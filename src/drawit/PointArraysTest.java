package drawit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

public class PointArraysTest {

	@Test
	public void test() {
		IntPoint[] testArray1 = new IntPoint[0];
		IntPoint testPoint1 = new IntPoint(1,1);
		IntPoint testPoint2 = new IntPoint(-1,1);
		IntPoint testPoint3 = new IntPoint(1,-1);
		IntPoint testPoint4 = new IntPoint(-1,-1);
		
		IntPoint testPoint5 = new IntPoint(0,2);
		IntPoint testPoint6 = new IntPoint(0,0);
		IntPoint testPoint7 = new IntPoint(1,1);
		IntPoint testPoint8 = new IntPoint(1,1);
		
		//insert
		IntPoint[] testArray2 = PointArrays.insert(testArray1, 0, testPoint1);
		IntPoint[] testArray3 = PointArrays.insert(testArray2, 1, testPoint2);
		IntPoint[] testArray4 = PointArrays.insert(testArray3, 2, testPoint3);
		IntPoint[] testArray5 = PointArrays.insert(testArray4, 3, testPoint4);
		
		//in
		assertEquals(true,PointArrays.in(testArray5, testPoint1));
		assertEquals(true,PointArrays.in(testArray5, testPoint2));
		assertEquals(true,PointArrays.in(testArray5, testPoint3));
		assertEquals(true,PointArrays.in(testArray5, testPoint4));
		assertEquals(false,PointArrays.in(testArray4, testPoint5));
		assertEquals(false,PointArrays.in(testArray4, testPoint6));
		
		//update
		IntPoint[] testArray6 = PointArrays.update(testArray5, 1, testPoint5);
		IntPoint[] testArray7 = PointArrays.update(testArray5, 2, testPoint6);
		
		assertEquals(0, testArray6[1].getX());
		assertEquals(2, testArray6[1].getY());
		assertEquals(0, testArray7[2].getX());
		assertEquals(0, testArray7[2].getY());
		
		//remove
		//assertEquals(true, PointArrays.in(testArray7, testPoint6));
		//IntPoint[] testArray8 = PointArrays.remove(testArray7, 2);
		//assertEquals(false, PointArrays.in(testArray8, testPoint6));

		
		//checkDefineProperPolygon
		assertEquals("Not enough points to define a proper polygon.\n", PointArrays.checkDefinesProperPolygon(testArray1));
		assertEquals("Not enough points to define a proper polygon.\n", PointArrays.checkDefinesProperPolygon(testArray2));
		assertEquals("Not enough points to define a proper polygon.\n", PointArrays.checkDefinesProperPolygon(testArray3));
		assertEquals(null, PointArrays.checkDefinesProperPolygon(testArray4));
		assertEquals(null, PointArrays.checkDefinesProperPolygon(testArray1));
		
		
		//copy
		
		
	}

}
