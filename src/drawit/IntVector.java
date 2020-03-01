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
		return new DoubleVector(this.xCoordinate, this.yCoordinate); //have to be doubles --implicit?
	}
	
	
	//dotProduct(IntVector)
	public long dotProduct(IntVector vector) {
		return this.xCoordinate*vector.getX() + this.yCoordinate*vector.getY();
	}
	
	//crossProduct(IntVector)
	public long crossProduct(IntVector vector) {
		return this.xCoordinate*vector.getY() - this.yCoordinate*vector.getX();
	}
	
	
	//isCollinearWith(IntVector)
	public Boolean isCollinearWith(IntVector vector) {
		if (vector.getX() == 0 && vector.getY() == 0) {
			return false;
		}
		return this.crossProduct(vector) == 0;
	}
	
	//plus(IntVector)
	public IntVector plus(IntVector vector) {
		return new IntVector(this.xCoordinate + vector.getX(), this.yCoordinate + vector.getY());
	}
}
