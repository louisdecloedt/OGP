package drawit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

//import org.junit.jupiter.api.Test;

public class DoubleVectorTest {

	@Test
	public void test() {
		DoubleVector testVector1 = new DoubleVector(6.1,-7.2);
		DoubleVector testVector2 = new DoubleVector(4,5);
		assertEquals(6.1,testVector1.getX());
		assertEquals(4,testVector2.getX());
		assertEquals(-7.2,testVector1.getY());
		assertEquals(5,testVector2.getY());
		
		DoubleVector testVector3 = new DoubleVector(2, 0);
		DoubleVector testVector4 = new DoubleVector(0,2);
		DoubleVector testVector5 = new DoubleVector(24, 24);
		DoubleVector testVector6 = new DoubleVector(-1, -1);
		DoubleVector testVector7 = new DoubleVector(-1,1);
		
		assertEquals(0, testVector3.asAngle());
		assertEquals(Math.PI/2, testVector4.asAngle());
		assertEquals(Math.PI/4, testVector5.asAngle());
		assertEquals(-3*Math.PI/4, testVector6.asAngle()); 
		assertEquals(3*Math.PI/4, testVector7.asAngle());
		
		//crossProduct
		assertEquals(4, testVector3.crossProduct(testVector4));
		assertEquals(-4, testVector4.crossProduct(testVector3));
		assertEquals(48, testVector3.crossProduct(testVector5));
		assertEquals(-2, testVector6.crossProduct(testVector7));
		assertEquals(-48, testVector7.crossProduct(testVector5));

		
		//dotProduct
		assertEquals(0, testVector3.dotProduct(testVector4));
		assertEquals(0, testVector4.dotProduct(testVector3));
		assertEquals(48, testVector3.dotProduct(testVector5));
		assertEquals(0, testVector7.dotProduct(testVector5));
		assertEquals(2, testVector4.dotProduct(testVector7));
		assertEquals(0, testVector5.dotProduct(testVector7));
		
		//getSize
		assertEquals(2, testVector3.getSize());
		assertEquals(2, testVector4.getSize());
		assertEquals(Math.sqrt(1152), testVector5.getSize());
		assertEquals(Math.sqrt(2), testVector6.getSize());
		assertEquals(Math.sqrt(2), testVector7.getSize());
		
		//plus
		assertEquals(2, testVector3.plus(testVector4).getX());
		assertEquals(2, testVector3.plus(testVector4).getY());
		assertEquals(23, testVector5.plus(testVector7).getX());
		assertEquals(25, testVector5.plus(testVector7).getY());
		assertEquals(23, testVector7.plus(testVector5).getX());
		assertEquals(25, testVector7.plus(testVector5).getY());
		
		//scale
		assertEquals(3, testVector3.scale(1.5).getX());
		assertEquals(0, testVector3.scale(1.5).getY());
		assertEquals(24, testVector5.scale(1).getX());
		assertEquals(24, testVector5.scale(1).getY());
		assertEquals(1, testVector3.scale(1/testVector3.getSize()).getX());
		assertEquals(0, testVector3.scale(1/1/testVector3.getSize()).getY());
		assertEquals(5, testVector7.scale(-5).getX());
		assertEquals(-5, testVector7.scale(-5).getY());	
	}
	
}
