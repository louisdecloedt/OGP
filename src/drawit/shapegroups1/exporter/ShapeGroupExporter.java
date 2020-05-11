package drawit.shapegroups1.exporter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import drawit.IntPoint;
import drawit.shapegroups1.LeafShapeGroup;
import drawit.shapegroups1.NonLeafShapeGroup;
import drawit.shapegroups1.ShapeGroup;

//Assignment does not require documentation for this package or its contents.
abstract class ShapeGroupExporter {
	
	public static Object toPlainData(ShapeGroup shapeGroup) {	
		if(shapeGroup instanceof LeafShapeGroup) {	
			IntPoint[] arrayOfVertices = ((LeafShapeGroup) shapeGroup).getShape().getVertices();
			Color color = ((LeafShapeGroup) shapeGroup).getShape().getColor();
			ArrayList<Map> listOfVertices = new ArrayList<Map>();
			IntPoint vertex;
			for (int i = 0; i < arrayOfVertices.length; i++) {
				vertex = arrayOfVertices[i];
				listOfVertices.add(Map.of("x", vertex.getX(), "y", vertex.getY()));
			}
			Map result = Map.of(
					"originalExtent", Map.of("left", shapeGroup.getOriginalExtent().getLeft(),
							"top", shapeGroup.getOriginalExtent().getTop(),
							"right", shapeGroup.getOriginalExtent().getRight(),
							"bottom", shapeGroup.getOriginalExtent().getBottom()),
					"extent", Map.of("left", shapeGroup.getExtent().getLeft(),
							"top", shapeGroup.getExtent().getTop(),
							"right", shapeGroup.getExtent().getRight(),
							"bottom", shapeGroup.getExtent().getBottom()),
					"shape", Map.of("vertices", listOfVertices,
							"radius", ((LeafShapeGroup) shapeGroup).getShape().getRadius(),
							"color", Map.of("red", color.getRed(),
									"green", color.getGreen(),
									"blue", color.getBlue()))); 
			return result;
		}
		else{
			
			ArrayList<Map> subgroupMapsAsList = new ArrayList<Map>();
			java.util.List<ShapeGroup> subGroups = shapeGroup.getSubgroups();
			for (int i = 0; i < ((NonLeafShapeGroup) shapeGroup).getSubgroupCount(); i++) {
				subgroupMapsAsList.add((Map) ShapeGroupExporter.toPlainData(subGroups.get(i)));
			}
			Map result = Map.of(
					"originalExtent", Map.of("left", shapeGroup.getOriginalExtent().getLeft(),
							"top", shapeGroup.getOriginalExtent().getTop(),
							"right", shapeGroup.getOriginalExtent().getRight(),
							"bottom", shapeGroup.getOriginalExtent().getBottom()),
					"extent", Map.of("left", shapeGroup.getExtent().getLeft(),
							"top", shapeGroup.getExtent().getTop(),
							"right", shapeGroup.getExtent().getRight(),
							"bottom", shapeGroup.getExtent().getBottom()),
					"subgroups", subgroupMapsAsList);
			return result;
		}
	}
}
