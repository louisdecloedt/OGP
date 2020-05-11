package drawit.shapegroups2;

import java.util.ArrayList;
import java.util.List;

import drawit.IntPoint;
import drawit.shapegroups1.Extent;






public class NonLeafShapeGroup extends ShapeGroup {
	
	
	protected java.util.List<ShapeGroup> subgroups;
	
	public NonLeafShapeGroup(ShapeGroup[] subgroups) {
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
		this.parent = null;
	}
	
	/**
	 * Returns returns the subgroup at the given index.
	 * 
	 * @inspects | this
	 * 
	 * @throws IllegalArgumentException
	 *           | getSubgroups() == null
	 * @throws IllegalArgumentException
	 *           | index >= getSubgroups().size()
	 * @inspects | this
	 * 
	 */
	public ShapeGroup getSubgroup(int index) {
		if (subgroups == null) {
			throw new IllegalArgumentException("This ShapeGroup has no subgroups!");
		}
		if (subgroups.size() <= index) {
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
	 * @basic
	 * 
	 */
	public java.util.List<ShapeGroup> getSubgroups() {
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
		if (subgroups == null) {
			return 0;
		}
		return subgroups.size();
	}
	
	/**
	 * Returns the first subgroup that contains the given innerCoordinates.
	 * 
	 * @inspects | this, getSubgroups()
	 * 
	 * @throws IllegalArgumentException
	 *    | innerCoordinates == null
	 * @post
	 * 			 | result == null || result.getExtent().contains(innerCoordinates)
	 */
	public ShapeGroup getSubgroupAt(IntPoint innerCoordinates) throws IllegalArgumentException {
		if (innerCoordinates == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < getSubgroupCount(); i++) {
			if(subgroups.get(i).getExtent().contains(innerCoordinates)){
				return this.subgroups.get(i);
			}
		}
		//TODO: return null or throw?
		return null;
	}
	
	/**
	 * Returns returns a textual representation of the ShapeGroup and its subgroups.
	 * 
	 * @inspects | this, getSubgroups()
	 * 
	 */
	public java.lang.String getDrawingCommands(){
		String result = "";
		Extent origE = this.originalExtent;
		result += "pushTranslate" + " " + Integer.toString(extent.getLeft()) +
				" " + Integer.toString(extent.getTop()) + "\n"; 
		result += "pushScale" + " " + Double.toString((double)extent.getWidth()/originalExtent.getWidth()) +
				" " + Double.toString((double)extent.getHeight()/originalExtent.getHeight()) + "\n";
		result += "pushTranslate" + " " + Integer.toString(-origE.getLeft()) +
				" " + Integer.toString(-origE.getTop()) + "\n";
		for (int i = subgroups.size()-1; i >= 0; i--) {
			result += subgroups.get(i).getDrawingCommands();
		}
		result += "popTransform" + "\n";
		result += "popTransform" + "\n";
		result += "popTransform" + "\n";
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
