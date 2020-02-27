package drawit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoublePointTest {

	@Test
	void test() {
		DoublePoint testPoint1 = new DoublePoint(10, -5);
		assertEquals(10, testPoint1.getX());
		assertEquals(-5, testPoint1.getY());
		//assertEquals(-9, testPoint1.getX());	
	}

}
