package drawit.shapegroups1;

import drawit.IntPoint;
import drawit.PointArrays;
import drawit.RoundedPolygon;
import drawit.shapegroups1.Extent;


/**
 * LeafShapeGroup extends ShapeGroup and represents objects without subgroups.
 * 
 * @invar A LeafShapeGroup has no subgroups.
 *    | getSubgroups() == null
 */
public class LeafShapeGroup extends ShapeGroup {
	
	/**
	 * 
	 * @invar | shape != null
	 * 
	 */
	private RoundedPolygon shape;
	
	/**
	 * Initializes a LeafGroupShapeGroup as having no parent.
	 * 
	 * @mutates this
	 * 
	 * @throws IllegalArgumentException
	 *    | PointArrays.checkDefinesProperPolygon(shape.getVertices()) != null
	 * @post ShapeGroup has no parent.
	 *    | getParentGroup() == null
	 * @post This leaf contains a shape.
	 *    | getShape() != null
	 * @post This ShapeGroup does not have subgroups.
	 *    | getSubgroups() == null
	 * @post Extent defined in inner and outer coordinates.
	 *    | getExtent() != null && getOriginalExtent() != null
	 *
	 */
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
	 * Returns null.
	 * 
	 * @post
	 *    | result == null
	 * 
	 */
	public java.util.List<ShapeGroup> getSubgroups() {
		return null;
	}
	
	/**
	 * Returns the shape of the LeafShapeGroup.
	 * 
	 * @inspects | this
	 * 
	 * @basic
	 * 
	 * @post
	 *    | result != null
	 * 
	 */
	public RoundedPolygon getShape() {
		return shape;
	}
	
	/**
	 * Returns returns a textual representation of the LeafShapeGroup.
	 * 
	 * @inspects | this, getSubgroups()
	 * 
	 * @post 
	 *    | result != null
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
