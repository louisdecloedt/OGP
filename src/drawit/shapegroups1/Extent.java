package drawit.shapegroups1;

import drawit.*;
import drawit.shapegroups1.ShapeGroup;
//import drawit.shapegroups2.Extent;

/**
 * An instance of this class stores the extent of a ShapeGroup.
 * 
 * @invar The left of an extent is nonnegative.
 *      | 0 <= getLeft()
 * @invar The right of an extent is nonnegative.
 *      | 0 <= getRight()
 * @invar The top of an extent is nonnegative.
 *      | 0 <= getTop()
 * @invar The bottom of an extent is nonnegative.
 *      | 0 <= getBottom()
 * @invar The height of an extent is nonnegative.
 *      | 0 <= getHeight()
 * @invar The width of an extent is nonnegative.
 *      | 0 <= getWidth()
 */
public class Extent {
	
	
	
	/**
	 * @invar The left of an extent is nonnegative.
	 *      | 0 <= left
	 * @invar The right of an extent is nonnegative.
	 *      | 0 <= right
	 * @invar The top of an extent is nonnegative.
	 *      | 0 <= top
	 * @invar The bottom of an extent is nonnegative.
	 *      | 0 <= bottom
	 * @invar The bottom of an extent is greater than the top.
	 *      | bottom >= top
	 * @invar The right of an extent is greater than the left.
	 *      | right >= left
	 */
	private int left;
	private int top;
	private int bottom;
	private int right;
	
	private Extent(int left, int top, int width, int height) {
		this.left = left;
		this.top = top;
		this.bottom = top + height;
		this.right = left + width;
	}
	
	/**
	 * Returns the left of the extent.
	 */
	public int getLeft() {
		return left;
	}
	
	/**
	 * Returns the top of the extent.
	 */
	public int getTop() {
		return top;
	}
	
	/**
	 * Returns the right of the extent.
	 */
	public int getRight() {
		return right;
	}
	
	/**
	 * Returns the bottom of the extent.
	 */
	public int getBottom() {
		return bottom;
	}
	
	/**
	 * Returns the width of the extent.
	 */
	public int getWidth() {
		return right - left;
	}
	
	/**
	 * Returns the height of the extent.
	 */
	public int getHeight() {
		return bottom - top;
	}
	
	/**
	 * Returns the TopLeft corner of the extent.
	 * 
	 * @post 
	 *    | result.getX() == getLeft() && result.getY() == getTop()
	 */
	public IntPoint getTopLeft() {
		return new IntPoint(left,top);
	}
	
	/**
	 * Returns the BottomRight corner of the extent.
	 * 
	 * @post 
	 *    | result.getX() == getRight() && result.getY() == getBottom()
	 */
	public IntPoint getBottomRight() {
		return new IntPoint(right, bottom);
	}
	
	/**
	 * Returns whether an given IntPoint is contained by this extent.
	 * 
	 * @post
	 * 	  | result == (point.getX() >= getLeft() && point.getX() <= getRight())
		  |		&& (point.getY() <= getBottom() && point.getY() >= getTop())
	 */
	public boolean contains(IntPoint point) {
		return (point.getX() >= left && point.getX() <= (right))
				&& (point.getY() <= (bottom) && point.getY() >= top);
	}
	
	//CHECK: OGP NOTES: complexity_modularity_abstraction.md, section that discusses Fraction.of()
	public static Extent ofLeftTopWidthHeight(int left, int top, int width, int height) {
		return new Extent(left, top, width, height);
	}
	
	public static Extent ofLeftTopRightBottom(int left, int top, int right, int bottom) {
		return new Extent(left, top, right - left, bottom - top);
	}
	
	public Extent withLeft(int newLeft) {
		return new Extent(newLeft, this.top, this.right - newLeft, this.bottom - this.top);
	}
	
	public Extent withTop(int newTop) {
		return new Extent(this.left, newTop, this.right - this.left, this.bottom - newTop);
	}
	
	public Extent withRight(int newRight) {
		return new Extent(this.left, this.top, newRight - this.left, this.bottom - this.top);
	}
	
	public Extent withBottom(int newBottom) {
		return new Extent(this.left, this.top, this.right - this.left, newBottom - this.top);
	}
	
	//TODO: check this
	public Extent withWidth(int newWidth) {
		return new Extent(this.left, this.top, newWidth, this.bottom - this.top);
	}
	
	
	public Extent withHeight(int newHeight) {
		return new Extent(this.left, this.top, this.right - this.left, newHeight);
	}

}
