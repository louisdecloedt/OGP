package drawit.shapegroups1;

import drawit.*;


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
	 * @invar The top of an extent is nonnegative.
	 *      | 0 <= top
	 * @invar The bottom of an extent is nonnegative.
	 *      | 0 <= bottom
	 * @invar The bottom of an extent is greater than the top.
	 *      | bottom >= top
	 * @invar The right of an extent is greater than the left.
	 *      | right >= left
	 */
	private final int left;
	private final int top;
	private final int bottom;
	private final int right;
	
	private Extent(int left, int top, int width, int height) {
		this.left = left;
		this.top = top;
		this.bottom = top + height;
		this.right = left + width;
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
	 * @basic 
	 * 
	 * @post
	 *    | result >= 0
	 */
	public int getRight() {
		return right;
	}
	
	/**
	 * Returns the bottom of the extent.
	 * 
	 * @basic 
	 * 
	 * @post
	 *    | result >= 0
	 */
	public int getBottom() {
		return bottom;
	}
	
	/**
	 * Returns the width of the extent.
	 * 
	 * @post
	 *    | result >= 0
	 */
	public int getWidth() {
		return right - left;
	}
	
	/**
	 * Returns the height of the extent.
	 * 
	 * @post
	 *    | result >= 0
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
	 * 	  | result == ((point.getX() >= getLeft() && point.getX() <= getRight())
		  |		&& (point.getY() <= getBottom() && point.getY() >= getTop()))
	 */
	public boolean contains(IntPoint point) {
		return (point.getX() >= left && point.getX() <= (right))
				&& (point.getY() <= (bottom) && point.getY() >= top);
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
		if (0 > width || 0 > height) {
			throw new IllegalArgumentException();
		}
		if (0 > left || 0 > top) {
			throw new IllegalArgumentException();
		}
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
		if (left > right || top > bottom) {
			throw new IllegalArgumentException();
		}
		if (0 > left || 0 > top) {
			throw new IllegalArgumentException();
		}
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
		if (newLeft > right) {
			throw new IllegalArgumentException();
		}
		return new Extent(newLeft, this.top, this.right - newLeft, this.bottom - this.top);
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
		if (newTop > bottom) {
			throw new IllegalArgumentException();
		}
		return new Extent(this.left, newTop, this.right - this.left, this.bottom - newTop);
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
		if (newRight < left) {
			throw new IllegalArgumentException();
		}
		return new Extent(this.left, this.top, newRight - this.left, this.bottom - this.top);
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
		if (newBottom < top) {
			throw new IllegalArgumentException();
		}
		return new Extent(this.left, this.top, this.right - this.left, newBottom - this.top);
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
		if (newWidth < 0) {
			throw new IllegalArgumentException();
		}
		return new Extent(this.left, this.top, newWidth, this.bottom - this.top);
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
	public Extent withHeight(int newHeight) throws IllegalArgumentException {
		if (newHeight < 0) {
			throw new IllegalArgumentException();
		}
		return new Extent(this.left, this.top, this.right - this.left, newHeight);
	}

}
