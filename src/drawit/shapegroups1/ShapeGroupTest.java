package drawit.shapegroups1;

import static org.junit.jupiter.api.Assertions.*;
import drawit.*;
import org.junit.jupiter.api.Test;
//import drawit.shapegroups1.ShapeGroup.*;

class ShapeGroupTest {

	@Test
	void test() {
		RoundedPolygon triangle = new RoundedPolygon();
		triangle.setVertices(new IntPoint[] {new IntPoint(10, 10), new IntPoint(30, 10), new IntPoint(20, 20)});

		ShapeGroup leaf = new ShapeGroup(triangle);
		assert leaf.getExtent().getTopLeft().equals(new IntPoint(10, 10)) && leaf.getExtent().getBottomRight().equals(new IntPoint(30, 20));
		leaf.setExtent(Extent.ofLeftTopWidthHeight(0, 0, 20, 10));

		// For simplicity, we here ignore the constraint that a non-leaf ShapeGroup shall have at least two subgroups.
		ShapeGroup nonLeaf = new ShapeGroup(new ShapeGroup[] {leaf});
		assert nonLeaf.getExtent().getTopLeft().equals(new IntPoint(0, 0)) && nonLeaf.getExtent().getBottomRight().equals(new IntPoint(20, 10));
		nonLeaf.setExtent(Extent.ofLeftTopWidthHeight(0, 0, 10, 5));
		
		leaf.setExtent(Extent.ofLeftTopWidthHeight(1000, 2000, 20, 10));
	}

}
