package drawit.shapegroups1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class ExtentTest {

	@Test
	void test() {
		Extent extent1 = Extent.ofLeftTopRightBottom(10, 10, 10, 10);
		Extent extent2 = Extent.ofLeftTopRightBottom(10, 10, 10, 10);
		Extent extent3 = Extent.ofLeftTopRightBottom(100, 100, 100, 100);
		
		assertEquals(true, extent1.equals(extent2));
		assertEquals(true, extent2.equals(extent1));
		assertEquals(false, extent3.equals(extent1));
		assertEquals(false, extent3.equals(extent2));
		
		assertEquals(true, extent1.hashCode() == extent2.hashCode());
		assertEquals(false, extent3.hashCode() == extent1.hashCode());
		assertEquals(false, extent3.hashCode() == extent2.hashCode());
		
		assertEquals(extent1.toString(), extent2.toString());
		assertNotEquals(extent3.toString(), extent1.toString());
		assertNotEquals(extent3.toString(), extent2.toString());
	}
	
	
	@Test
	void advancedTest() {
		Extent extent1 = Extent.ofLeftTopRightBottom(10, 10, 10, 10);
		Extent extent2 = Extent.ofLeftTopRightBottom(10, 10, 10, 10);
		Extent extent3 = Extent.ofLeftTopRightBottom(100, 100, 100, 100);
		Extent extent4 = Extent.ofLeftTopRightBottom(210, 210, 310, 310);
		Extent extent5 = Extent.ofLeftTopRightBottom(200, 200, 200, 200);
		
		HashSet<Extent> testSet = new HashSet<Extent>();
		testSet.add(extent1);
		testSet.add(extent2);
		testSet.add(extent3);
		assertEquals(true, testSet.contains(extent1));
		assertEquals(true, testSet.contains(extent2));
		assertEquals(true, testSet.contains(extent3));
		assertEquals(false, testSet.contains(extent4));
		assertEquals(false, testSet.contains(extent5));
		testSet.add(extent4);
		testSet.add(extent5);
		assertEquals(true, testSet.contains(extent1));
		assertEquals(true, testSet.contains(extent2));
		assertEquals(true, testSet.contains(extent3));
		assertEquals(true, testSet.contains(extent4));
		assertEquals(true, testSet.contains(extent5));
		
		ArrayList<Extent> testList = new ArrayList<Extent>();
		testList.add(extent1);
		testList.add(extent2);
		testList.add(extent3);
		assertEquals(true, testList.contains(extent1));
		assertEquals(true, testList.contains(extent2));
		assertEquals(true, testList.contains(extent3));
		assertEquals(false, testList.contains(extent4));
		assertEquals(false, testList.contains(extent5));
		testList.add(extent4);
		testList.add(extent5);
		assertEquals(true, testList.contains(extent1));
		assertEquals(true, testList.contains(extent2));
		assertEquals(true, testList.contains(extent3));
		assertEquals(true, testList.contains(extent4));
		assertEquals(true, testList.contains(extent5));
	}
}
