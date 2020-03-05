package drawit;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;

public class DoublePointTest {

    @Test
    public void test() {
        DoublePoint testPoint1 = new DoublePoint(9.5, -4.5);
        DoublePoint testPoint2 = new DoublePoint(10, -5);
        assertEquals(9.5, testPoint1.getX());
        assertEquals(-4.5, testPoint1.getY());
        assertEquals(10, testPoint2.getX());
        assertEquals(-5, testPoint2.getY());

        DoubleVector testVector1 = new DoubleVector(0.5, -0.5);
        DoublePoint testPoint3 = new DoublePoint(10, -5.0);
        assertEquals(testPoint3.getX(), testPoint1.plus(testVector1).getX());
        assertEquals(testPoint3.getY(), testPoint1.plus(testVector1).getY());
        assertEquals(testVector1.getX(), testPoint2.minus(testPoint1).getX());
        assertEquals(testVector1.getY(), testPoint2.minus(testPoint1).getY());

        DoublePoint testPoint4 = new DoublePoint(12.2, 0.8);
        IntPoint testPoint5 = testPoint4.round();
        assertEquals(12, testPoint5.getX());
        assertEquals(1, testPoint5.getY());

        DoublePoint testPoint6 = new DoublePoint(-9.9, -3.3);
        IntPoint testPoint7 = testPoint6.round();
        assertEquals(-10, testPoint7.getX());
        assertEquals(-3, testPoint7.getY());
    }
}

