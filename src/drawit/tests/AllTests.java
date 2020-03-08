package drawit.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import drawit.IntVectorTest;

@RunWith(Suite.class)
@SuiteClasses({ DoublePointTest.class, DoubleVectorTest.class, IntPointTest.class, IntVectorTest.class,
		PointArraysTest.class, RoundedPolygonTest.class })

public class AllTests {
	// the class remains empty,
	// used only as a holder for the above annotations
}
