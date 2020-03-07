package drawit;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IntPointTest {

    @Test
    public void test() {
        IntPoint testPoint1 = new IntPoint(10, -1221);
        assertEquals(10, testPoint1.getX());
        assertEquals(-1221, testPoint1.getY());
        IntPoint testPoint2 = new IntPoint(10, -1221);
        assertEquals(true, testPoint1.equals(testPoint2));
    


        IntPoint testPoint3 = new IntPoint(2, 2);
        IntPoint testPoint4 = new IntPoint(10, 10);
        assertEquals(testPoint3.minus(testPoint4).getX(), -8);
        assertEquals(testPoint3.minus(testPoint4).getY(), -8);
        IntVector testvec34 = new IntVector(8,8);
        assertEquals(true, testPoint3.plus(testvec34).equals(testPoint4));
        
        IntPoint testPoint5 = new IntPoint(5, 5);
        IntPoint testPoint6 = new IntPoint(2, 2);
        IntPoint testPoint7 = new IntPoint(5, 6);
        IntPoint testPoint8 = new IntPoint(7, -13);
        IntPoint testPoint9 = new IntPoint(10, 10);

        assertEquals(true, testPoint5.isOnLineSegment(testPoint3, testPoint4));
        assertEquals(false, testPoint6.isOnLineSegment(testPoint3, testPoint4));
        assertEquals(false, testPoint7.isOnLineSegment(testPoint3, testPoint4));
        assertEquals(false, testPoint8.isOnLineSegment(testPoint3, testPoint4));
        assertEquals(false, testPoint9.isOnLineSegment(testPoint3, testPoint4));

        //Error case RoundedPolygon.contains()
        IntPoint testPointVerticaal = new IntPoint(0, 100);
        IntPoint testPointB = new IntPoint(0, 0);
        IntPoint testPointC = new IntPoint(0, 200);
        assertEquals(true, testPointVerticaal.isOnLineSegment(testPointB, testPointC));

        //checking if is collinear with is correct for this case.
        //wrong file I know :)
        IntVector testVectorA = new IntVector(0, 100);
        IntVector testVectorB = new IntVector(0, 200);
        assertEquals(true, testVectorA.isCollinearWith(testVectorB));
        //seems to work
        IntVector BA = testPointVerticaal.minus(testPointB);
        IntVector BC = testPointC.minus(testPointB);
        assertEquals(true, BA.isCollinearWith(BC));

        //false was returned for isOnLineSegment if X coordinate matched x coordinate of one of the end points.


        IntPoint testPoint10 = new IntPoint(5, 5);
        IntPoint testPoint11 = new IntPoint(2, 2);
        IntPoint testPoint12 = new IntPoint(5, 6);
        IntPoint testPoint13 = new IntPoint(1, -20);
        IntPoint testPoint14 = new IntPoint(10, 10);
        IntPoint testPoint15 = new IntPoint(0, 0);
        IntPoint testPoint16 = new IntPoint(10, 12);
        IntPoint testPoint17 = new IntPoint(-5, 12);
        IntPoint testPoint18 = new IntPoint(10, -3);

        IntPoint testPointQ = new IntPoint(0, 0);
        IntPoint testPointR = new IntPoint(100, 100);
        IntPoint testPointS = new IntPoint(-200, 100);
        IntPoint testPointT = new IntPoint(1000, 100);
        assertEquals(false, IntPoint.lineSegmentsIntersect(testPointQ, testPointR, testPointS, testPointT));

        assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint3, testPoint4, testPoint5, testPoint6));
        assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint3, testPoint4, testPoint6, testPoint5));
        assertEquals(true, IntPoint.lineSegmentsIntersect(testPoint11, testPoint10, testPoint12, testPoint13));
        assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint10, testPoint11, testPoint11, testPoint12));
        assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint13, testPoint14, testPoint15, testPoint16));
        assertEquals(false, IntPoint.lineSegmentsIntersect(testPoint13, testPoint14, testPoint16, testPoint15));
        assertEquals(true, IntPoint.lineSegmentsIntersect(testPoint17, testPoint18, testPoint14, testPoint13));
    }

}
