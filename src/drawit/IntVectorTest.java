package drawit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

public class IntVectorTest {

	@Test
	public void test() {
		//getters
		IntVector testVector1 = new IntVector(2, -3);
		IntVector testVector2 = new IntVector(10, 12);
		IntVector testVector3 = new IntVector(-5, -5);
		IntVector testVector4 = new IntVector(-5, 6);
		assertEquals(2, testVector1.getX());
		assertEquals(-3, testVector1.getY());
		assertEquals(10, testVector2.getX());
		assertEquals(12, testVector2.getY());
		
		//asDouble
		assertEquals(2.0, testVector1.asDoubleVector().getX());
		assertEquals(-3.0, testVector1.asDoubleVector().getY());
		assertEquals(-5.0, testVector4.asDoubleVector().getX());
		assertEquals(6.0, testVector4.asDoubleVector().getY());
		
		//dotProduct
		assertEquals(-16, testVector1.dotProduct(testVector2));
		assertEquals(-16, testVector2.dotProduct(testVector1));
		assertEquals(-5, testVector3.dotProduct(testVector4));
		assertEquals(22, testVector2.dotProduct(testVector4));
		
		//crossProduct
		assertEquals(54, testVector1.crossProduct(testVector2));
		assertEquals(-54, testVector2.crossProduct(testVector1));
		assertEquals(-55, testVector3.crossProduct(testVector4));
		assertEquals(-120, testVector4.crossProduct(testVector2));
		
		//isCollinearWith
		
		//plus
		assertEquals(12, testVector1.plus(testVector2).getX());
		assertEquals(9, testVector1.plus(testVector2).getY());
		assertEquals(12, testVector2.plus(testVector1).getX());
		assertEquals(9, testVector2.plus(testVector1).getY());
	}

}
