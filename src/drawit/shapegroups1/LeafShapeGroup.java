package drawit.shapegroups1;

import drawit.IntPoint;
import drawit.PointArrays;
import drawit.RoundedPolygon;
import drawit.shapegroups1.Extent;



public class LeafShapeGroup extends ShapeGroup {
	
	private RoundedPolygon shape;
	
	
	public LeafShapeGroup(RoundedPolygon shape) {
		if (PointArrays.checkDefinesProperPolygon(shape.getVertices()) != null) {
			throw new IllegalArgumentException();
		}
		this.shape = shape;	
		IntPoint[] vertices = shape.getVertices();
		int left = vertices[0].getX(), top = vertices[0].getY();
		int right = left, bottom = top;
		for (int i = 1; i < vertices.length; i++) {
			if (vertices[i].getX() > right ) {
				right = vertices[i].getX();
			}
			if (vertices[i].getX() < left ) {
				left = vertices[i].getX();
			}
			if (vertices[i].getY() > bottom ) {
				bottom = vertices[i].getY();
			}
			if (vertices[i].getY() < top ) {
				top = vertices[i].getY();
			}
		}
		this.originalExtent = Extent.ofLeftTopRightBottom(left, top, right, bottom);
		this.extent = this.originalExtent;
		this.parent = null;
	}
	
	/**
	 * Returns returns the list of subgroups
	 * or null if the ShapeGroup doesn't have any.
	 * 
	 * @inspects | this
	 * 
	 * @basic
	 * 
	 */
	public java.util.List<ShapeGroup> getSubgroups() {
		return null;
	}
	
	/**
	 * Returns returns the shape it contains or null otherwise.
	 * 
	 * @inspects | this
	 * 
	 * @basic
	 * 
	 */
	public RoundedPolygon getShape() {
		return shape;
	}
	
	/**
	 * Returns returns a textual representation of the ShapeGroup and its subgroups.
	 * 
	 * @inspects | this, getSubgroups()
	 * 
	 */
	public java.lang.String getDrawingCommands(){
		String result = "";
		result += "pushTranslate" + " " + Integer.toString(extent.getLeft()) +
				" " + Integer.toString(extent.getTop()) + "\n"; 
		result += "pushScale" + " " + Double.toString((double)extent.getWidth()/originalExtent.getWidth()) +
				" " + Double.toString((double)extent.getHeight()/originalExtent.getHeight()) + "\n";
		result += "pushTranslate" + " " + Integer.toString(-originalExtent.getLeft()) +
				" " + Integer.toString(-originalExtent.getTop()) + "\n";
		result += shape.getDrawingCommands();
		result += "popTransform" + "\n";
		result += "popTransform" + "\n";
		result += "popTransform" + "\n";	
		return result;
	}
}
