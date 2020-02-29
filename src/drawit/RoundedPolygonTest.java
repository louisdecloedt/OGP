package drawit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import org.junit.jupiter.api.Test;

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
		
		poly1.setVertices(array1);
		IntPoint testPoint1 = new IntPoint(10,10);
		IntPoint testPoint2 = new IntPoint(50,100);
		IntPoint testPoint3 = new IntPoint(100,100);
		IntPoint testPoint4 = new IntPoint(-10,10);
		IntPoint testPoint5 = new IntPoint(50,-100);
		IntPoint testPoint6 = new IntPoint(101,100);
		IntPoint testPoint7 = new IntPoint(78, 3);
		//assertEquals(true, poly1.contains(testPoint1));
		assertEquals(true, poly1.contains(testPoint2));
		assertEquals(true, poly1.contains(testPoint3));
		assertEquals(false, poly1.contains(testPoint4));
		assertEquals(false, poly1.contains(testPoint5));
		assertEquals(false, poly1.contains(testPoint6));
		//assertEquals(false, poly1.contains(testPoint7));
	}

}
