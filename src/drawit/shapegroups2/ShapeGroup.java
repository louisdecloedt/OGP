package drawit.shapegroups2;

import java.util.ArrayList;
import java.util.List;


import drawit.*;

/**
 * Trees of instances of this class can be built to represent ShapeGroups.
 * 
 * @invar This ShapeGroup does not have the same ShapeGroup as a subgroup twice.
 *    | LogicalList.distinct(getSubGroups())
 * @invar If this ShapeGroup has subgroups, these subgroups have this ShapeGroup as parent.
 *    | getSubgroups == null || getSubgroups().stream().allMatch(subgroup -> subgroup.getParentGroup() == this)
 * @invar This node is either a leaf group or else it has subgroups.
 *    | (getShape() == null) != (getSubgroups() == null)
 * @invar This ShapeGroup does not have itself as parent.
 *    | getParent() != this
 */
public class ShapeGroup {
	
	
	/**
	 * 
	 * @invar | LogicalList.distinct(subgroups)
	 * @invar | subgroups == null || subgroups.stream().allMatch(subgroup -> subgroup.getParentGroup() == this)
	 * @invar | (shape == null) != (subgroups == null)
	 * @invar | getParent() != this
	 * 
	 */
	private RoundedPolygon shape;
	/**
	 * @peerObject
	 */
	private ShapeGroup parent;
	/**
	 * @peerObjects
	 */
	private java.util.List<ShapeGroup> subgroups;
	private Extent extent;
	private Extent originalExtent;
	
	/**
	 * Initializes a ShapeGroup as having no subgroups or parent.
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
	public ShapeGroup(RoundedPolygon shape) {
		//System.out.print("ShapeGroup/LeafGroup()\n");
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
	}
	
	/**
	 * Initializes a ShapeGroup with the given subgroups, 
	 * 		the subgroups get this new ShapeGroup assigned as parent.
	 * 
	 * @post The new ShapeGroup has no parent.
	 *    | getParentGroup() == null
	 * @post This ShapeGroup does not contain a shape.
	 *    | getShape() == null
	 * @post This ShapeGroup does have subgroups.
	 *    | getSubgroups() != null
	 * @post Extent defined in inner and outer coordinates.
	 *    | getExtent() != null && getOriginalExtent() != null
	 * @post The subgroups have this ShapeGroup as parent.
	 *    | this.getSubgroups() == null || 
	 *    | this.getSubgroups().stream().allMatch(subgroup -> subgroup.getParentGroup() == this)
	 *
	 */
	public ShapeGroup(ShapeGroup[] subgroups) {
		List<ShapeGroup> listOfChildren = new ArrayList<ShapeGroup>();
		Extent extentI;
		int left = 1000000000, top = 100000000;
		int right = 0, bottom = 0;
		for (int i = 0; i < subgroups.length; i++) { //i = 0
			extentI = subgroups[i].getExtent();
			if (extentI.getRight() > right ) {
				right = extentI.getRight();
			}
			if (extentI.getLeft() < left ) {
				left = extentI.getLeft();
			}
			if (extentI.getBottom() > bottom ) {
				bottom = extentI.getBottom();
			}
			if (extentI.getTop() < top ) {
				top = extentI.getTop();
			}
			subgroups[i].parent = this;
			listOfChildren.add(subgroups[i]); 
		}
		this.subgroups = listOfChildren;
		this.originalExtent = Extent.ofLeftTopRightBottom(left, top, right, bottom);
		this.extent = this.originalExtent;
		//ShapeGroup tempShapeGroup;
		/*
		for (int i = 0; i < listOfChildren.size(); i++) {
			tempShapeGroup = listOfChildren.get(i);
			extentI = tempShapeGroup.getExtent();
			extentI = Extent.ofLeftTopRightBottom(extentI.getLeft() - left,
					extentI.getTop() - top, extentI.getRight() - left, extentI.getBottom() - top);
			tempShapeGroup.setExtent(extentI);
			this.subgroups.set(i,tempShapeGroup);
		}
		*/
	}
	
	/**
	 * Returns the extent of the ShapeGroup expressed in its outer coordinate system.
	 * 
	 * @inspects | this
	 * 
	 * @post
	 * 	  | result != null
	 */
	public Extent getExtent() {
		//System.out.print("getExtent()\n");
		return extent;
	}
	
	/**
	 * Returns the original extent of the ShapeGroup expressed.
	 * 
	 * @inspects | this
	 * 
	 * @post
	 * 	  | result != null
	 */
	public Extent getOriginalExtent() {
		//System.out.print("getOriginalExtent()\n");
		return this.originalExtent;
	}
	
	/**
	 * Returns returns the parentGroup or null otherwise.
	 * 
	 * @inspects | this
	 * 
	 */
	public ShapeGroup getParentGroup() {
		//System.out.print("getParentGroup()\n");
		return parent;
	}
	
	/**
	 * Returns returns the shape it contains or null otherwise.
	 * 
	 * @inspects | this
	 * 
	 */
	public RoundedPolygon getShape() {
		//System.out.print("getShape()\n");
		//if (shape == null) {
			//System.out.print("getShape()==null\n");
		//}
		return shape;
	}
	
	/**
	 * Returns returns the subgroup at the given index.
	 * 
	 * @throws IllegalArgumentException
	 *           | getSubgroups() == null
	 * @throws IllegalArgumentException
	 *           | index >= getSubgroups().size()
	 * @inspects | this
	 * 
	 */
	public ShapeGroup getSubgroup(int index) {
		//System.out.print("getSubgroup(index)\n");
		if (subgroups == null) {
			throw new IllegalArgumentException("No subgroups!");
		}
		//or must this be null
		if (subgroups.size() >= index) {
			throw new IllegalArgumentException("Index out of bound!");
		}
		return this.subgroups.get(index);
	}
	
	/**
	 * Returns returns the list of subgroups
	 * or null if the ShapeGroup doesn't have any.
	 * 
	 * @inspects | this
	 * 
	 */
	public java.util.List<ShapeGroup> getSubgroups() {
		//System.out.print("getAllSubgroups()\n");
		return subgroups;
	}
	
	/**
	 * Returns returns the number of subgroups.
	 * 
	 * @inspects | this
	 * 
	 * @post
	 * 			 | result >= 0
	 */
	public int getSubgroupCount() {
		//System.out.print("getSubgroupCount()\n");
		if (subgroups == null) {
			return 0;
		}
		return subgroups.size();
	}
	
	public IntPoint toInnerCoordinates(IntPoint globalCoordinates) {
		
		
		// /*
		if (parent == null) {
			double innerX = ((globalCoordinates.getX() - extent.getLeft())/(double)extent.getWidth())
					*originalExtent.getWidth();
			innerX += (double)originalExtent.getLeft();
			double innerY = ((globalCoordinates.getY() - extent.getTop())/(double)extent.getHeight())
					*originalExtent.getHeight();
			innerY += (double)originalExtent.getTop();
			return new IntPoint((int)Math.round(innerX), (int)Math.round(innerY));
		} 
		globalCoordinates = parent.toInnerCoordinates(globalCoordinates);
		double innerX = ((globalCoordinates.getX() - extent.getLeft())/(double)extent.getWidth())
				*originalExtent.getWidth();
		innerX += (double)originalExtent.getLeft();
		double innerY = ((globalCoordinates.getY() - extent.getTop())/(double)extent.getHeight())
				*originalExtent.getHeight();
		innerY += (double)originalExtent.getTop();
		return new IntPoint((int)Math.round(innerX), (int)Math.round(innerY));
		// */
	}
	
	public IntPoint toGlobalCoordinates(IntPoint innerCoordinates) {
		//System.out.print("toGlobalCoordinates()\n");
		
		// /*
		if (parent == null) {
			double x = innerCoordinates.getX(), y = innerCoordinates.getY();
			x -= originalExtent.getLeft();
			y -= originalExtent.getTop();
			x = x*((double)extent.getWidth()/(double)originalExtent.getWidth()) + extent.getLeft();
			y = y*((double)extent.getHeight()/(double)originalExtent.getHeight()) + extent.getTop();
			return new IntPoint( (int)Math.round(x), (int)Math.round(y));
		}
		double x = innerCoordinates.getX(), y = innerCoordinates.getY();
		x -= originalExtent.getLeft();
		y -= originalExtent.getTop();
		x = x*((double)extent.getWidth()/(double)originalExtent.getWidth()) + extent.getLeft();
		y = y*((double)extent.getHeight()/(double)originalExtent.getHeight()) + extent.getTop();
		IntPoint newInnerCoordinates = new IntPoint( (int)Math.round(x), (int)Math.round(y));
		return parent.toGlobalCoordinates(newInnerCoordinates);
		// */
	}
	
	public IntVector toInnerCoordinates(IntVector relativeGlobalCoordinates) {
		//System.out.print("toInnerCoordinates_vec()\n");
		if (parent == null) {
			double innerX = ((relativeGlobalCoordinates.getX())/(double)extent.getWidth())
					*originalExtent.getWidth();
			double innerY = ((relativeGlobalCoordinates.getY())/(double)extent.getHeight())
					*originalExtent.getHeight();
			return new IntVector((int)Math.round(innerX), (int)Math.round(innerY));
		} 
		relativeGlobalCoordinates = parent.toInnerCoordinates(relativeGlobalCoordinates);
		double innerX = ((relativeGlobalCoordinates.getX())/(double)extent.getWidth())
				*originalExtent.getWidth();
		double innerY = ((relativeGlobalCoordinates.getY())/(double)extent.getHeight())
				*originalExtent.getHeight();
		return new IntVector((int)Math.round(innerX), (int)Math.round(innerY));
		//
		//return new IntVector(relativeGlobalCoordinates.getX()*extent.getWidth()/originalExtent.getWidth(),
		//		relativeGlobalCoordinates.getY()*extent.getHeight()/originalExtent.getHeight());
	}
	
	public ShapeGroup getSubgroupAt(IntPoint innerCoordinates) {
		//System.out.print("getSubgroupAt(innerCoordinates)\n");
		// /*
		for (int i = 0; i < getSubgroupCount(); i++) {
			//TODO: check this
			if(subgroups.get(i).getExtent().contains(innerCoordinates)){
				return this.subgroups.get(i);
			}
		}
		// */
		return null;
	}
	
	/**
	 * Replaces the current extent by a new extent in outer coordinates.
	 * 
	 * @inspects | this
	 * 
	 * @mutates  | this
	 * 
	 * @post
	 * 	  | getExtent() == newExtent
	 */
	public void setExtent(Extent newExtent) {
		//System.out.print("setExtent()\n");
		this.extent = newExtent;
	}
	
	/**
	 * Brings this ShapeGroup to the front of its parents subgroup list.
	 * 
	 * @throws IllegalArgumentException if this ShapeGroup has no parent.
	 *    | getParentGroup() == null
	 *    
	 * @inspects | this
	 * 
	 * @mutates  | this.getParentGroup()
	 * 
	 * @post
	 * 	  | getParentGroup().getSubgroups().get(0) == this
	 */
	public void bringToFront() {
		if (parent == null) {
			throw new IllegalArgumentException();
		}
		List<ShapeGroup> tempParentSubgroups = this.parent.getSubgroups();
		int index = tempParentSubgroups.indexOf(this);
		List<ShapeGroup> newParentSubgroups = tempParentSubgroups;
		newParentSubgroups.add(0, tempParentSubgroups.get(index));
		newParentSubgroups.remove(index + 1);
		this.parent.subgroups = newParentSubgroups;
	}
	
	/**
	 * Brings this ShapeGroup to the back of its parents subgroup list.
	 * 
	 * @throws IllegalArgumentException if this ShapeGroup has no parent.
	 *    | getParentGroup() == null
	 *    
	 * @inspects | this
	 * 
	 * @mutates  | this.getParentGroup()
	 * 
	 * @post
	 * 	  | getParentGroup().getSubgroups().get(getParentGroup().getSubgroups().size()-1) == this
	 */
	public void sendToBack() {
		if (parent == null) {
			throw new IllegalArgumentException();
		}
		List<ShapeGroup> tempParentSubgroups = this.parent.getSubgroups();
		int index = tempParentSubgroups.indexOf(this);
		List<ShapeGroup> newParentSubgroups = tempParentSubgroups;
		newParentSubgroups.add(tempParentSubgroups.get(index));
		newParentSubgroups.remove(index);
		this.parent.subgroups = newParentSubgroups;
	}
	
	/**
	 * Returns returns a textual representation of the ShapeGroup and its subgroups.
	 * 
	 * @inspects | this
	 * 
	 */
	public java.lang.String getDrawingCommands(){
		String result = "";
		if (subgroups == null) {
			//TODO: reduce allocations
			Extent origE = this.originalExtent;
			Extent currentE = this.extent;
			result += "pushTranslate" + " " + Integer.toString(currentE.getLeft()) +
					" " + Integer.toString(currentE.getTop()) + "\n"; 
			result += "pushScale" + " " + Double.toString((double)extent.getWidth()/originalExtent.getWidth()) +
					" " + Double.toString((double)extent.getHeight()/originalExtent.getHeight()) + "\n";
			result += "pushTranslate" + " " + Integer.toString(-origE.getLeft()) +
					" " + Integer.toString(-origE.getTop()) + "\n";
			result += shape.getDrawingCommands();
			result += "popTransform" + "\n";
			result += "popTransform" + "\n";
			result += "popTransform" + "\n";	
			return result;
		} else {
			Extent origE = this.originalExtent;
			result += "pushTranslate" + " " + Integer.toString(extent.getLeft()) +
					" " + Integer.toString(extent.getTop()) + "\n"; 
			result += "pushScale" + " " + Double.toString((double)extent.getWidth()/originalExtent.getWidth()) +
					" " + Double.toString((double)extent.getHeight()/originalExtent.getHeight()) + "\n";
			result += "pushTranslate" + " " + Integer.toString(-origE.getLeft()) +
					" " + Integer.toString(-origE.getTop()) + "\n";
			for (int i = subgroups.size()-1; i >= 0; i--) {
			//for (int i = 0; i < this.subgroups.size(); i++) {
				result += subgroups.get(i).getDrawingCommands();
			}
			result += "popTransform" + "\n";
			result += "popTransform" + "\n";
			result += "popTransform" + "\n";
		}
		return result;
	}
}



