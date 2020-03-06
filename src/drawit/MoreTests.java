package drawit;

import static org.junit.Assert.assertEquals;

public class MoreTests {
	@org.junit.Test
	public void test() {
		/**
		 * Test cases for Rounded Polygon
		 */


		IntPoint punt1 = new IntPoint(0, 0);
		IntPoint punt2 = new IntPoint(3, 0);
		IntPoint punt3 = new IntPoint(4, 1);
		IntPoint punt4 = new IntPoint(5, 0);
		IntPoint punt5 = new IntPoint(7, 5);
		IntPoint punt6 = new IntPoint(5, 10);
		IntPoint punt7 = new IntPoint(6, 4);
		IntPoint punt8 = new IntPoint(3, 7);
		IntPoint punt9 = new IntPoint(0, 7);
		IntPoint punt10 = new IntPoint(1, 4);

		IntPoint[] lijst = new IntPoint[0];
		lijst = PointArrays.insert(lijst, 0, punt1);
		lijst = PointArrays.insert(lijst, 1, punt2);
		lijst = PointArrays.insert(lijst, 2, punt3);
		lijst = PointArrays.insert(lijst, 3, punt4);
		lijst = PointArrays.insert(lijst, 4, punt5);
		lijst = PointArrays.insert(lijst, 5, punt6);
		lijst = PointArrays.insert(lijst, 6, punt7);
		lijst = PointArrays.insert(lijst, 7, punt8);
		lijst = PointArrays.insert(lijst, 8, punt9);
		lijst = PointArrays.insert(lijst, 9, punt10);


		RoundedPolygon roundpoly = new RoundedPolygon();
		roundpoly.setVertices(lijst);

		assertEquals(null, PointArrays.checkDefinesProperPolygon(lijst));

		IntPoint testPunt1 = new IntPoint(3, 0);
		IntPoint testPunt2 = new IntPoint(0, 4);
		IntPoint testPunt3 = new IntPoint(4, 10);
		IntPoint testPunt4 = new IntPoint(4, 2);
		IntPoint testPunt5 = new IntPoint(-1, 7);


		assertEquals(true, roundpoly.contains(testPunt1));
		//assertEquals(false, roundpoly.contains(testPunt2));
		assertEquals(false, roundpoly.contains(testPunt3));
		assertEquals(true, roundpoly.contains(testPunt4));
		assertEquals(false, roundpoly.contains(testPunt5));
		roundpoly.contains(testPunt2);
	}

}
