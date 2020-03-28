package drawit.shapegroups2;

import drawit.IntPoint;

public class Extent {
	
	//POSIITVE Y-AXIS POINTS DOWN
	//SO TOP HAS THE SMALLEST Y-COORDINATE
	
	private int left;
	private int top;
	private int width;
	private int height;
	
	private Extent(int left, int top, int width, int height) {
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	}
	
	public int getLeft() {
		return left;
	}
	
	public int getTop() {
		return top;
	}
	
	public int getRight() {
		return left + width;
	}
	
	public int getBottom() {
		return top + height;
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
		return new IntPoint(left + width, top + height);
	}
	
	//THROWS
	public boolean contains(IntPoint point) {
		return (point.getX() >= left && point.getX() <= (left + width))
				&& (point.getY() <= (top + height) && point.getY() >= top);
	}
	
	//CHECK: OGP NOTES: complexity_modularity_abstraction.md, section that discusses Fraction.of()
	public static Extent ofLeftTopWidthHeight(int left, int top, int width, int height) {
		return new Extent(left, top, width, height);
	}
	
	public static Extent ofLeftTopRightBottom(int left, int top, int right, int bottom) {
		return new Extent(left, top, right - left, bottom - top);
	}
	
	public Extent withLeft(int newLeft) {
		return new Extent(newLeft, this.top, this.width + this.left - newLeft, this.height);
	}
	
	public Extent withTop(int newTop) {
		return new Extent(this.left, newTop, this.width, this.height + this.top - newTop);
	}
	
	public Extent withRight(int newRight) {
		return new Extent(this.left, this.top, newRight - this.left, this.height);
	}
	
	public Extent withBottom(int newBottom) {
		return new Extent(this.left, this.top, this.width, newBottom - this.top);
	}
	
	public Extent withWidth(int newWidth) {
		return new Extent(this.left, this.top, newWidth, this.height);
	}
	
	public Extent withHeight(int newHeight) {
		return new Extent(this.left, this.top, this.width, newHeight);
	}

}
