package drawit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	DoubleVectorTest.class,
	DoublePointTest.class,
	IntVectorTest.class,
	IntPointTest.class,
	PointArraysTest.class,
	RoundedPolygonTest.class
})

public class AllTests {
	// the class remains empty,
	// used only as a holder for the above annotations
}
