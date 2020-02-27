package drawit;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

public class DoublePointTest {
	
	private DoublePoint doublePoint;
	
	@Before
	public void setUp() {
		doublePoint = new DoublePoint(10, -5);
	}
	
	//@org.junit.Test
	@Test
	public void test() {
		//DoublePoint testPoint1 = new DoublePoint(10, -5);
		assertEquals(10, doublePoint.getX());
		assertEquals(-5, doublePoint.getY());
		//assertEquals(-9, testPoint1.getX());	
	}
}

