package drawit.shapegroups1;

import java.util.ArrayList;
import java.util.List;

//import drawit.RoundedPolygon;
import drawit.*;

/**
 * Trees of instances of this class can be built to represent ShapeGroups.
 */
public class ShapeGroup {
	
	private RoundedPolygon shape;
	private ShapeGroup parent;
	private java.util.List<ShapeGroup> subgroups;
	private Extent extent; //in parent
	private Extent originalExtent;
	
	public ShapeGroup(RoundedPolygon shape) {
		this.shape = shape;
		//this.parent = null; //redundant because default	
		//this.subgroups = null; //redundant because default	
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
	
	public ShapeGroup(ShapeGroup[] subgroups) {
		List<ShapeGroup> listOfChildren = new ArrayList<ShapeGroup>();
		//this.shape = null; //redundant because default	
		//this.parent = null; //redundant because default	
		Extent extentI; // = subgroups[0].getExtent();
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
		ShapeGroup tempShapeGroup;
		//Extent extentI;
		for (int i = 0; i < listOfChildren.size(); i++) {
			tempShapeGroup = listOfChildren.get(i);
			extentI = tempShapeGroup.getExtent();
			extentI = Extent.ofLeftTopRightBottom(extentI.getLeft() - left,
					extentI.getTop() - top, extentI.getRight() - left, extentI.getBottom() - top);
			tempShapeGroup.setExtent(extentI);
			this.subgroups.set(i,tempShapeGroup);
		}
	}
	
	public Extent getExtent() {
		return this.extent;
	}
	
	public Extent getOriginalExtent() {
		return this.originalExtent;
	}
	
	public ShapeGroup getParentGroup() {
		return parent;
	}
	
	public RoundedPolygon getShape() {
		return shape;
	}
	
	public ShapeGroup getSubgroup(int index) {
		if (subgroups == null) {
			throw new IllegalArgumentException("No subgroups!");
		}
		return this.subgroups.get(index);
	}
	
	public java.util.List<ShapeGroup> getSubGroups() {
		return subgroups;
	}
	
	public int getSubgroupCount() {
		if (subgroups == null) {
			return 0;
			//throw new IllegalArgumentException("No subgroups!");
		}
		return subgroups.size();
	}
	
	//TODO:
	public IntPoint toInnerCoordinates(IntPoint globalCoordinates) {
		return new IntPoint(0,0);
		/*
		if (parent == null) {
			Extent temp = this.extent;
			IntPoint result = new IntPoint(globalCoordinates.getX() - temp.getLeft(), globalCoordinates.getY() - temp.getTop());
			return result;
		} 
		IntVector vector = new IntVector( - this.extent.getLeft(), - this.extent.getTop());
		return parent.toInnerCoordinates(globalCoordinates).plus(vector);
		*/
	}
	
	//TODO: make function
	public IntPoint toGlobalCoordinates(IntPoint innerCoordinates) {
		return new IntPoint(0,0);
		/*
		if (parent == null) {
			return innerCoordinates;
		} 
		IntVector vector = new IntVector( this.extent.getLeft(), this.extent.getTop());
		return this.parent.toGlobalCoordinates(innerCoordinates).plus(vector);
		*/
	}
	
	//TODO: make function
	public IntVector toInnerCoordinates(IntVector relativeGlobalCoordinates) {
		return new IntVector(0, 0);
	}
	
	//TODO: check this
	public ShapeGroup getSubgroupAt(IntPoint innerCoordinates) {
		for (int i = 0; i < getSubgroupCount(); i++) {
			if(this.subgroups.get(i).extent.contains(innerCoordinates)){
				return this.subgroups.get(i);
			}
		}
		//throws not catched for this function!!
		//throw new IllegalArgumentException();
		return null;
	}

	public void setExtent(Extent newExtent) {
		this.extent = newExtent;
	}
	
	public void bringToFront() {
		if (parent == null) {
			throw new IllegalArgumentException();
		}
		List<ShapeGroup> tempParentSubgroups = this.parent.getSubGroups();
		int index = tempParentSubgroups.indexOf(this);
		List<ShapeGroup> newParentSubgroups = tempParentSubgroups;
		newParentSubgroups.remove(index);
		newParentSubgroups.add(0, tempParentSubgroups.get(index));
		//TODO: does this work?
		this.parent.subgroups = newParentSubgroups;
	}
	
	public void sendToBack() {
		if (parent == null) {
			throw new IllegalArgumentException();
		}
		List<ShapeGroup> tempParentSubgroups = this.parent.getSubGroups();
		int index = tempParentSubgroups.indexOf(this);
		List<ShapeGroup> newParentSubgroups = tempParentSubgroups;
		newParentSubgroups.add(tempParentSubgroups.get(index));
		newParentSubgroups.remove(index);
		//TODO: does this work?
		this.parent.subgroups = newParentSubgroups;
	}
	
	public java.lang.String getDrawingCommands(){
		String result = "";
		if (subgroups == null) {
			Extent origE = this.originalExtent;
			Extent newE = this.extent;
			result += "pushTranslate" + " " + Integer.toString(newE.getLeft()) +
					" " + Integer.toString(newE.getTop()) + "\n"; 
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
			Extent newE = this.extent;
			result += "pushTranslate" + " " + Integer.toString(newE.getLeft()) +
					" " + Integer.toString(newE.getTop()) + "\n"; 
			result += "pushScale" + " " + Double.toString((double)extent.getWidth()/originalExtent.getWidth()) +
					" " + Double.toString((double)extent.getHeight()/originalExtent.getHeight()) + "\n";
			for (int i = 0; i < this.subgroups.size(); i++) {
				
				result += subgroups.get(i).getDrawingCommands();
			}
			result += "popTransform" + "\n";
			result += "popTransform" + "\n";
		}
		//System.out.print("total group: print \n");
		//System.out.print(result);
		return result;
		
	}
}



