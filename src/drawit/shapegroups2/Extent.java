package drawit.shapegroups2;

import drawit.IntPoint;

/**
 * An instance of this class stores the extent of a ShapeGroup.
 * @immutable
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
	 * @invar The width of an extent is nonnegative.
	 *      | 0 <= width
	 * @invar The height of an extent is nonnegative.
	 *      | 0 <= height
	 */
	private final int left;
	private final int top;
	private final int width;
	private final int height;
	
	private Extent(int left, int top, int width, int height) {
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns the left of the extent.
	 * 
	 * @basic 
	 * 
	 * @post
	 *    | result >= 0
	 */
	public int getLeft() {
		return left;
	}
	
	/**
	 * Returns the top of the extent.
	 * 
	 * @basic 
	 * 
	 * @post
	 *    | result >= 0
	 */
	public int getTop() {
		return top;
	}
	
	/**
	 * Returns the right of the extent.
	 * 
	 * @post
	 *    | result >= 0
	 */
	public int getRight() {
		return left + width;
	}
	
	/**
	 * Returns the bottom of the extent.
	 * 
	 * @post
	 *    | result >= 0
	 */
	public int getBottom() {
		return top + height;
	}
	
	/**
	 * Returns the width of the extent.
	 * 
	 * @basic
	 * 
	 * @post
	 *    | result >= 0
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the height of the extent.
	 * 
	 * @basic
	 * 
	 * @post
	 *    | result >= 0
	 */
	public int getHeight() {
		return height;
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
		return new IntPoint(left + width, top + height);
	}
	
	/**
	 * Returns whether an given IntPoint is contained by this extent.
	 * 
	 * @post
	 * 	  | result == (point.getX() >= getLeft() && point.getX() <= getRight())
		  |		&& (point.getY() <= getBottom() && point.getY() >= getTop())
	 */
	public boolean contains(IntPoint point) {
		
		return (point.getX() >= left && point.getX() <= (left + width))
				&& ((point.getY()) <= (top + height) && point.getY() >= top);
	}
	
	/**
	 * Returns an extent with given properties.
	 * 
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | 0 > width || 0 > height
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | 0 > left || 0 > top
	 * @post
	 * 	  | result.getLeft() == left && result.getTop() == top
	 * @post
	 *    | result.getWidth() == width && result.getHeight() == height 
	 * 
	 */
	public static Extent ofLeftTopWidthHeight(int left, int top, int width, int height) {
		return new Extent(left, top, width, height);
	}
	
	/**
	 * Returns an extent with given properties.
	 * 
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | left > right || top > bottom
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | 0 > left || 0 > top
	 * @post
	 * 	  | result.getLeft() == left && result.getTop() == top
	 * @post
	 *    | result.getRight() == right && result.getBottom() == bottom 
	 * 
	 */
	public static Extent ofLeftTopRightBottom(int left, int top, int right, int bottom) {
		return new Extent(left, top, right - left, bottom - top);
	}
	
	/**
	 * Returns a new extent with a new left coordinate.
	 * 
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | newLeft > getRight()
	 * @post
	 * 	  | result.getLeft() == newLeft && result.getTop() == getTop()
	 * @post
	 *    | result.getRight() == getRight() && result.getBottom() == getBottom() 
	 * 
	 */
	public Extent withLeft(int newLeft) {
		return new Extent(newLeft, this.top, this.width + this.left - newLeft, this.height);
	}
	
	/**
	 * Returns a new extent with a new top coordinate.
	 * 
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | newTop > getBottom()
	 * @post
	 * 	  | result.getLeft() == getLeft() && result.getTop() == newTop
	 * @post
	 *    | result.getRight() == getRight() && result.getBottom() == getBottom() 
	 * 
	 */
	public Extent withTop(int newTop) {
		return new Extent(this.left, newTop, this.width, this.height + this.top - newTop);
	}
	
	/**
	 * Returns a new extent with a new right coordinate.
	 * 
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | newRight < getLeft()
	 * @post
	 * 	  | result.getLeft() == getLeft() && result.getTop() == getTop()
	 * @post
	 *    | result.getRight() == newRight && result.getBottom() == getBottom() 
	 * 
	 */
	public Extent withRight(int newRight) {
		return new Extent(this.left, this.top, newRight - this.left, this.height);
	}
	
	/**
	 * Returns a new extent with a new bottom coordinate.
	 * 
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | newBottom < getTop()
	 * @post
	 * 	  | result.getLeft() == getLeft() && result.getTop() == getTop()
	 * @post
	 *    | result.getRight() == getRight() && result.getBottom() == newBottom 
	 */
	public Extent withBottom(int newBottom) {
		return new Extent(this.left, this.top, this.width, newBottom - this.top);
	}
	
	/**
	 * Returns a new extent with the same left, top and height
	 * but a new width.
	 * 
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | newWidth < 0
	 * @post
	 * 	  | result.getLeft() == getLeft() && result.getTop() == getTop()
	 * @post
	 *    | result.getHeight() == getHeight() && result.getWidth() == newWidth 
	 * 
	 */
	public Extent withWidth(int newWidth) {
		return new Extent(this.left, this.top, newWidth, this.height);
	}
	
	/**
	 * Returns a new extent with the same left, top and width
	 * but a new height.
	 * 
	 * @throws IllegalArgumentException if would not result in proper extent.
	 *    | newHeight < 0
	 * @post
	 * 	  | result.getLeft() == getLeft() && result.getTop() == getTop()
	 * @post
	 *    | result.getHeight() == newHeight && result.getWidth() == getWidth() 
	 * 
	 */
	public Extent withHeight(int newHeight) {
		return new Extent(this.left, this.top, this.width, newHeight);
	}

}
