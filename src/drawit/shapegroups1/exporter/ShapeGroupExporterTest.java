package drawit.shapegroups1.exporter;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import drawit.IntPoint;
import drawit.RoundedPolygon;
import drawit.shapegroups1.Extent;
import drawit.shapegroups1.LeafShapeGroup;
import drawit.shapegroups1.NonleafShapeGroup;
import drawit.shapegroups1.ShapeGroup;

class ShapeGroupExporterTest {

	@Test
	void test() {
//		Map refMap = Map.of(
//			    "originalExtent", Map.of("left", 10, "top", 20, "right", 100, "bottom", 200),
//			    "extent", Map.of("left", 5, "top", 7, "right", 99, "bottom", 88),
//			    "subgroups", List.of(
//			        Map.of(
//			            "originalExtent", Map.of("left", 30, "top", 40, "right", 90, "bottom", 190),
//			            "extent", Map.of("left", 40, "top", 50, "right", 60, "bottom", 70),
//			            "shape", Map.of(
//			                "vertices", List.of(
//			                    Map.of("x", 40, "y", 40),
//			                    Map.of("x", 50, "y", 40),
//			                    Map.of("x", 40, "y", 50)),
//			                "radius", 5,
//			                "color", Map.of("red", 255, "green", 255, "blue", 255))),
//			        Map.of(
//			            "originalExtent", Map.of("left", 35, "top", 45, "right", 95, "bottom", 195),
//			            "extent", Map.of("left", 45, "top", 55, "right", 65, "bottom", 75),
//			            "shape", Map.of(
//			                "vertices", List.of(
//			                    Map.of("x", 45, "y", 45),
//			                    Map.of("x", 55, "y", 45),
//			                    Map.of("x", 45, "y", 55)),
//			                "radius", 7,
//			                "color", Map.of("red", 128, "green", 128, "blue", 128)))));
		
		RoundedPolygon poly1 = new RoundedPolygon();
		poly1.setRadius(5);
		poly1.setColor(new Color(255,255,255));
		IntPoint[] vert1 = new IntPoint[3];
		vert1[0] = new IntPoint(40,40);
		vert1[1] = new IntPoint(50,40);
		vert1[2] = new IntPoint(40,50);
		poly1.setVertices(vert1);
		ShapeGroup shape1 = new LeafShapeGroup(poly1);
		shape1.setExtent(Extent.ofLeftTopRightBottom(40, 50, 60, 70));
		
		Object plainData1 = ShapeGroupExporter.toPlainData(shape1);
		assertEquals(((Map)plainData1).get("originalExtent"), 
				Map.of("left", 40, "top", 40, "right", 50, "bottom", 50));
		assertEquals(((Map)plainData1).get("extent"), 
				Map.of("left", 40, "top", 50, "right", 60, "bottom", 70));
		assertEquals(((Map) ((Map)plainData1).get("shape")).get("color"), 
				Map.of("red", 255, "blue", 255, "green", 255));
		
		
		
		RoundedPolygon poly2 = new RoundedPolygon();
		poly2.setRadius(10);
		poly2.setColor(new Color(255,0,0));
		IntPoint[] vert2 = new IntPoint[4];
		vert2[0] = new IntPoint(10,10);
		vert2[1] = new IntPoint(10,100);
		vert2[2] = new IntPoint(100,100);
		vert2[3] = new IntPoint(100,10);
		poly2.setVertices(vert2);
		ShapeGroup shape2 = new LeafShapeGroup(poly2);
		shape2.setExtent(Extent.ofLeftTopRightBottom(0, 0, 200, 200));
		
		
		Object plainData2 = ShapeGroupExporter.toPlainData(shape2);
		assertEquals(((Map)plainData2).get("originalExtent"), 
				Map.of("left", 10, "top", 10, "right", 100, "bottom", 100));
		assertEquals(((Map)plainData2).get("extent"), 
				Map.of("left", 0, "top", 0, "right", 200, "bottom", 200));
		assertEquals(((Map) ((Map)plainData2).get("shape")).get("color"), 
				Map.of("red", 255, "green", 0, "blue", 0));
		
		ArrayList<Map> testVertices = new ArrayList();
		testVertices.add(Map.of("x", 10, "y", 10));
		testVertices.add(Map.of("x", 10, "y", 100));
		testVertices.add(Map.of("x", 100, "y", 100));
		testVertices.add(Map.of("x", 100, "y", 10));
		assertEquals(((Map) ((Map)plainData2).get("shape")).get("vertices"), 
				testVertices);
		
		ShapeGroup shape3 = new NonleafShapeGroup(new ShapeGroup[]{shape1,shape2});
		Object plainData3 = ShapeGroupExporter.toPlainData(shape3);
		assertEquals( ((Map)plainData3).get("originalExtent"), 
				Map.of("left", 0, "top", 0, "right", 200, "bottom", 200));
		assertEquals( ((Map)plainData3).get("extent"), 
				Map.of("left", 0, "top", 0, "right", 200, "bottom", 200));
	}

}
