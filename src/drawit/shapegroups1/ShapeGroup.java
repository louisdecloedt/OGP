package drawit.shapegroups1;

import java.util.ArrayList;
import java.util.List;

//import drawit.RoundedPolygon;
import drawit.*;

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
		//this.extent =  Extent.ofLeftTopWidthHeight(0, 0, right - left, bottom - top);
	}
	
	public ShapeGroup(ShapeGroup[] subgroups) {
		List<ShapeGroup> listOfChildren = new ArrayList<ShapeGroup>();
		//this.shape = null; //redundant because default	
		//this.parent = null; //redundant because default	
		Extent extentI;
		int left = 1000000000, top = 100000000;
		int right = 0, bottom = 0;
		for (int i = 0; i < subgroups.length; i++) {
			extentI = subgroups[i].getExtent();
			if (extentI.getRight() > right ) {
				extentI.getRight();
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
		this.extent =  this.originalExtent;
		//this.extent =  Extent.ofLeftTopWidthHeight(0, 0, right - left, bottom - top);
		ShapeGroup tempShapeGroup;
		Extent temp;
		for (int i = 0; i < subgroups.length; i++) {
			tempShapeGroup = listOfChildren.get(i);
			temp = tempShapeGroup.getExtent();
			temp = Extent.ofLeftTopRightBottom(left - temp.getLeft(),
					top - temp.getTop(), right - temp.getLeft(), bottom - temp.getTop());
			tempShapeGroup.setExtent(temp);
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
		if (subgroups == null) {
			throw new IllegalArgumentException("No subgroups!");
		}
		return subgroups;
	}
	
	public int getSubgroupCount() {
		if (subgroups == null) {
			throw new IllegalArgumentException("No subgroups!");
		}
		return subgroups.size();
	}
	
	//TODO: make function
	public IntPoint toInnerCoordinates(IntPoint globalCoordinates) {
		return new IntPoint(0, 0);
	}
	
	//TODO: make function
	public IntPoint toGlobalCoordinates(IntPoint innerCoordinates) {
		return new IntPoint(0, 0);
	}
	
	//TODO: make function
	public IntVector toInnerCoordinates(IntVector relativeGlobalCoordinates) {
		return new IntVector(0, 0);
	}
	
	public ShapeGroup getSubGroupAt(IntPoint innerCoordinates) {
		for (int i = 0; i < getSubgroupCount(); i++) {
			if(this.originalExtent.contains(this.subgroups.get(i))){
				return this.subgroups.get(i);
			}
		}
		throw new IllegalArgumentException();
	}

	public void setExtent(Extent newExtent) {
		if (subgroups == null) {
			this.extent = newExtent;
		}
		//is this necessary??
		else {
			ShapeGroup temp;
			for (int i = 0; i < subgroups.size(); i++) {
				temp = subgroups.get(i);
				temp.extent = newExtent;
				subgroups.set(i, temp);
			}
		}
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
			//TODO: if?
			result += "pushTranslate" + " " + Integer.toString(- extent.getLeft()) +
					" " + Integer.toString( - extent.getTop()) + "\n"; // - & -
			result += "pushScale" + " " + Double.toString((double)extent.getWidth()/originalExtent.getWidth()) +
					" " + Double.toString((double)extent.getHeight()/originalExtent.getHeight()) + "\n";
			result +=
			result += shape.getDrawingCommands();
			result += "popTransform" + "\n";
			result += "popTransform" + "\n";
			return result;
		}
		ShapeGroup temp;
		for (int i = 0; i < subgroups.size(); i++) {
			temp = subgroups.get(i);
			result += "pushTranslate" + " " + Integer.toString(temp.extent.getLeft()) +
					" " + Integer.toString(temp.extent.getTop()) + "\n";
			result += "pushScale" + " " + Double.toString((double)temp.extent.getWidth()/temp.originalExtent.getWidth()) +
					" " + Double.toString((double)temp.extent.getHeight()/temp.originalExtent.getHeight()) + "\n";
			result += "pushTranslate" + " " + "implement_further" + " " + "\n";
			result += subgroups.get(i).getDrawingCommands();
			result += "popTransform" + "\n";
			result += "popTransform" + "\n";
			
		}
		return result;
	}
}



