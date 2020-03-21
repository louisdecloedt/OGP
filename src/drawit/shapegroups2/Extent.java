package drawit.shapegroups2;

import drawit.IntPoint;

public class Extent {
	
	private int left;
	private int top;
	private int width;
	private int height;
	
	public int getLeft() {
		return left;
	}
	
	//DIFFERENT FROM GIVEN DOCUMENTATION -- CHECK
	public int getTop() {
		return top;
	}
	
	public int getRight() {
		return left + width;
	}
	
	//DIFFERENT FROM GIVEN DOCUMENTATION -- CHECK
	public int getBottom() {
		return top - height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public IntPoint getTopLeft() {
		return new IntPoint(left,top);
	}
	
	public IntPoint getBottomRight() {
		return new IntPoint(left + width, top - height);
	}
	
	//THROWS
	public boolean contains(IntPoint point) {
		return (point.getX() >= left && point.getX() <= (left + width))
				&& (point.getY() >= (top - height) && point.getY() <= top);
	}
	
	
	//CHECK: OGP NOTES: complexity_modularity_abstraction.md, section that discusses Fraction.of()
	//NO IDEA: if this is necessary or correct
	private Extent(int left, int top, int width, int height) {
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	}
	
	//CHECK: OGP NOTES: complexity_modularity_abstraction.md, section that discusses Fraction.of()
	public static Extent ofLeftTopWidthHeight(int left, int top, int width, int height) {
		return new Extent(left, top, width, height);
	}
	
	public static Extent ofLeftTopRightBottom(int left, int top, int right, int bottom) {
		return new Extent(left, top, right - left, top - bottom);
	}
	
	public Extent withLeft(int newLeft) {
		return new Extent(newLeft, this.top, this.width + this.left - newLeft, this.height);
	}
	
	public Extent withTop(int newTop) {
		return new Extent(this.left, newTop, this.width, this.height + newTop - this.top);
	}
	
	public Extent withRight(int newRight) {
		return new Extent(this.left, this.top, newRight - this.left, this.height);
	}
	
	public Extent withBottom(int newBottom) {
		return new Extent(this.left, this.top, this.width, this.top - newBottom);
	}
	
	public Extent withWidth(int newWidth) {
		return new Extent(this.left, this.top, newWidth, this.height);
	}
	
	public Extent withHeight(int newHeight) {
		return new Extent(this.left, this.top, this.width, newHeight);
	}

}
