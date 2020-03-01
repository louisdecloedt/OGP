package drawit;


/**
 * Each instance of this class is an abstraction of an immutable 2D integer vector.
 *
 * @immutable
 */
public class IntVector {
	private final int xCoordinate;
	private final int yCoordinate;

	public int getX() { return this.xCoordinate; }
	public int getY() { return this.yCoordinate; }
	
	/**
     * Initializes this object with the given coordinates.
     * 
     * @mutates | this
     *
     * @post This object's X coordinate equal the given X coordinate
     *    | getX() == xCoordinate
     * @post This object's Y coordinate equal the given Y coordinate
     *    | getY() == yCoordinate
     *
     */
	public IntVector(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	//no formal documentation required
	public DoubleVector asDoubleVector() {
		return new DoubleVector(this.xCoordinate, this.yCoordinate);
	}
	
	/**
     * Returns the dot product of the instance with a given IntVector as a long.
     *
     * @pre Argument {@code vector} is not {@code null}.
     *    | vector != null
     * @post The result is an integer containing the calculated dot product.
     *    | result == this.getX()*vector.getX() + this.getY()*vector.getY()
     */
	public long dotProduct(IntVector vector) {
		return this.xCoordinate*vector.getX() + this.yCoordinate*vector.getY();
	}
	
	
	/**
     * Returns the 2D cross product of the instance with a given IntVector as a long.
     *
     * @pre Argument {@code vector} is not {@code null}.
     *    | vector != null
     * @post The result is an integer containing the calculated 2D cross product.
     *    | result == this.getX()*vector.getY() - this.getY()*vector.getX()
     */
	public long crossProduct(IntVector vector) {
		return this.xCoordinate*vector.getY() - this.yCoordinate*vector.getX();
	}
	
	
	/**
     * Returns whether two IntVectors are collinear.
     *
     * @pre Argument {@code this} is not {@code null}.
     *    | this != null
     * @pre Argument {@code vector} is not {@code null}.
     *    | vector != null
     * @post
     *      The result is {@code false} if one of the vectors is (0,0).
     * @post 
     * 		For two non zero vectors {@code true} the method returns true
     * 		iff the cross product of the two vectors equals zero.
     * 	  | result == (this.crossProduct(vector) == 0)
     */
	public Boolean isCollinearWith(IntVector vector) {
		if ((vector.getX() == 0 && vector.getY() == 0)
				|| (this.getX() == 0 && this.getY() == 0)) {
			return false;
		}
		return this.crossProduct(vector) == 0;
	}
	
	/**
     * Returns the sum of two vectors.
     *
     * @pre Argument {@code this} is not {@code null}.
     *    | this != null
     * @pre Argument {@code vector} is not {@code null}.
     *    | vector != null
     * @post The result is an integer containing the calculated 2D cross product.
     *    | result.getX() == this.getX() + vector.getX() && result.getY() == this.getY() + vector.getY()
     */
	public IntVector plus(IntVector vector) {
		return new IntVector(this.xCoordinate + vector.getX(), this.yCoordinate + vector.getY());
	}
}
