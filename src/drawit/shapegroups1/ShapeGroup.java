package drawit.shapegroups1;

import java.util.List;
import drawit.IntPoint;
import drawit.IntVector;
import drawit.shapegroups1.Extent;


/**
 * ShapeGroup is an abstract superclass for Leaf- and NonLeafShapeGroups.
 * 
 * @invar If a ShapeGroup has subgroups then it is a NonLeafShapeGroup 
 *   and these subgroups have the given NonLeafShapeGroup as parent.
 *    | getSubgroups() == null || getSubgroups().stream().allMatch(subgroup -> subgroup.getParentGroup() == this)
 * @invar This ShapeGroup does not have itself as parent.
 *    | getParentGroup() != this
 */
public abstract class ShapeGroup {
	
	/**
	 * 
	 * @invar | getParentGroup() != this
	 * 
	 */
	protected Extent extent;
	protected Extent originalExtent;
	/**
	 * @peerObject
	 */
	protected NonLeafShapeGroup parent;
	
	/**
	 * Returns the extent of the ShapeGroup expressed in its outer coordinate system.
	 * 
	 * @inspects | this
	 * 
	 * @basic
	 * 
	 * @post
	 * 	  | result != null
	 */
	public Extent getExtent() {
		return extent;
	}
	
	/**
	 * Returns the original extent of the ShapeGroup expressed.
	 * 
	 * @inspects | this
	 * 
	 * @basic
	 * 
	 * @post
	 * 	  | result != null
	 */
	public Extent getOriginalExtent() {
		return this.originalExtent;
	}
	
	/**
	 * Returns returns the ParentGroup or null otherwise.
	 * 
	 * @inspects | this
	 * 
	 * @basic
	 * 
	 */
	public ShapeGroup getParentGroup() {
		return parent;
	}
	
	/**
	 * Converses the given global coordinates to this ShapeGroups inner coordinate system.
	 * 
	 * @inspects | this, getParentGroup()
	 * 
	 * @throws IllegalArgumentException
	 *    | globalCoordinates == null
	 * 
	 */
	public IntPoint toInnerCoordinates(IntPoint globalCoordinates) throws IllegalArgumentException {
		if (globalCoordinates == null) {
			throw new IllegalArgumentException();
		}
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
	}
	
	/**
	 * Converses the given inner coordinates in this ShapeGroups inner coordinate system to 
	 * global coordinates (outer coordinate system of this ShapeGroups ancestor).
	 * 
	 * @inspects | this, getParentGroup()
	 *
	 * @throws IllegalArgumentException
	 *    | innerCoordinates == null
	 */
	public IntPoint toGlobalCoordinates(IntPoint innerCoordinates) throws IllegalArgumentException {
		if (innerCoordinates == null) {
			throw new IllegalArgumentException();
		}
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
	}
	
	/**
	 * Converses the given relative global coordinates to relative inner coordinates.
	 * This means that translations of the extent relative to the original extent are ignored.
	 * 
	 * @inspects | this, getParentGroup()
	 * 
	 * @throws IllegalArgumentException
	 *    | relativeGlobalCoordinates == null
	 */
	public IntVector toInnerCoordinates(IntVector relativeGlobalCoordinates) throws IllegalArgumentException {
		if (relativeGlobalCoordinates == null) {
			throw new IllegalArgumentException();
		}
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
	}
	
	/**
	 * Replaces the current extent by a new extent in outer coordinates.
	 * 
	 * @mutates  | this
	 * 
	 * @throws IllegalArgumentException
	 *    | newExtent == null
	 * @post
	 * 	  | getExtent() == newExtent
	 */
	public void setExtent(Extent newExtent) throws IllegalArgumentException {
		if (newExtent == null) {
			throw new IllegalArgumentException();
		}
		this.extent = newExtent;
	}
	
	/**
	 * Brings this ShapeGroup to the front of its parents subgroup list.
	 * 
	 * @throws IllegalArgumentException if this ShapeGroup has no parent.
	 *    | getParentGroup() == null
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
	 * Returns returns a textual representation of the ShapeGroup.
	 * 
	 * @inspects | this, getSubgroups()
	 * 
	 */
	public abstract String getDrawingCommands();
	
	
	/**
	 * Returns returns null if the object is a LeafShapeGroup and
	 * the list of subgroups if it is a NonLeafShapeGroup.
	 * 
	 * @inspects | this
	 * 
	 * @basic
	 * 
	 */
	public abstract java.util.List<ShapeGroup> getSubgroups();
}
