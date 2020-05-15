package drawit.shapegroups1;

import static org.junit.jupiter.api.Assertions.*;
import drawit.*;
import org.junit.jupiter.api.Test;
import drawit.shapegroups1.ShapeGroup.*;

class ShapeGroupTest {

	@Test
	void test() {
		RoundedPolygon triangle = new RoundedPolygon();
		triangle.setVertices(new IntPoint[] {new IntPoint(10, 10), new IntPoint(30, 10), new IntPoint(20, 20)});

		ShapeGroup leaf = new LeafShapeGroup(triangle);
		assert leaf.getExtent().getTopLeft().equals(new IntPoint(10, 10)) && leaf.getExtent().getBottomRight().equals(new IntPoint(30, 20));
		leaf.setExtent(Extent.ofLeftTopWidthHeight(0, 0, 20, 10));

		// For simplicity, we here ignore the constraint that a non-leaf ShapeGroup shall have at least two subgroups.
		ShapeGroup nonLeaf = new NonleafShapeGroup(new ShapeGroup[] {leaf});
		assert nonLeaf.getExtent().getTopLeft().equals(new IntPoint(0, 0)) && nonLeaf.getExtent().getBottomRight().equals(new IntPoint(20, 10));
		nonLeaf.setExtent(Extent.ofLeftTopWidthHeight(0, 0, 10, 5));
		
		leaf.setExtent(Extent.ofLeftTopWidthHeight(1000, 2000, 20, 10));
		assert leaf.getExtent().getTopLeft().equals(new IntPoint(1000, 2000)) && leaf.getExtent().getBottomRight().equals(new IntPoint(1020, 2010));
		assert nonLeaf.getExtent().getTopLeft().equals(new IntPoint(0, 0)); //&& nonLeaf.getExtent().getBottomRight().equals(new IntPoint(20, 10));
		//assert nonLeaf.getExtent().getBottomRight().equals(new IntPoint(20, 10));
		//I guess they mean original extent in the example, since we already changed nonLeafs extent to nonLeaf.setExtent(Extent.ofLeftTopWidthHeight(0, 0, 10, 5));
		assert nonLeaf.getOriginalExtent().getBottomRight().equals(new IntPoint(20, 10));
		
		//System.out.print("RightBottom " + Integer.toString(nonLeaf.getExtent().getRight()) + " " + Integer.toString(nonLeaf.getExtent().getBottom()) + " " + "\n");
		
		
		
		
		//leaf2
		//ShapeGroup leaf2 = new ShapeGroup(triangle);
		
		////toInnerCoordinates
		IntPoint globalCoordinates = new IntPoint(500, 1000); 
		IntPoint testInnerCoordinates = leaf.toInnerCoordinates(globalCoordinates);
		assert leaf.toInnerCoordinates(globalCoordinates).equals(new IntPoint(10, 10));
				System.out.print("TestInnerCoordinates: Should be 10 10 -- " + Integer.toString(testInnerCoordinates.getX()) 
				+ " " + Integer.toString(testInnerCoordinates.getY()) + " " + "\n");
		
		
		
		////toGlobalCoordinates
		
		IntPoint innerCoordinates = new IntPoint(10,10); 
		IntPoint testGlobalCoordinates = leaf.toGlobalCoordinates(innerCoordinates);
		assert leaf.toGlobalCoordinates(innerCoordinates).equals(new IntPoint(500, 1000));
		System.out.print("TestGlobalCoordinates: Should be 500 1000 -- " + Integer.toString(testGlobalCoordinates.getX()) 
		+ " " + Integer.toString(testGlobalCoordinates.getY()) + " " + "\n");
	}
}
