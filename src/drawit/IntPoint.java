package drawit;

/**
 * Each instance of this class is an abstraction of an immutable 2D integer point.
 *
 * @immutable
 */
public class IntPoint {

    private final int xCoordinate;
    private final int yCoordinate;

    /**
     * Returns the x coordinate of the point.
     *
     * @inspects | this
     */
    public int getX() {
        return this.xCoordinate;
    }

    /**
     * Returns the y coordinate of the point.
     *
     * @inspects | this
     */
    public int getY() {
        return this.yCoordinate;
    }

    /**
     * Initializes this object with the given coordinates.
     *
     * @mutates | this
     * @post This object's X coordinate equal the given X coordinate
     * | getX() == xCoordinate
     * @post This object's Y coordinate equal the given Y coordinate
     * | getY() == yCoordinate
     */
    public IntPoint(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

    }

    /**
     * Returns the DoublePoint version of the instance.
     *
     * @post The result is a DoublePoint with the same coordinates but of type double.
     * | result.getX() == this.getX() && result.getY() == this.getY()
     */
    public DoublePoint asDoublePoint() {
        return new DoublePoint(this.xCoordinate, this.yCoordinate);
    }

    /**
     * Returns whether two IntPoints are equal.
     *
     * @pre Argument {@code point} is not {@code null}.
     * | point != null
     * @post The result is {@code true} iff this object's coordinates are equal to
     * the coordinates of the given point.
     * | result == (
     * |     this.getX() == point.getX() && this.getY() == point.getY() )
     */
    public boolean equals(IntPoint point) {
        if (point.getX() == this.xCoordinate & point.getY() == this.yCoordinate) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns whether given IntPoint is on the line segment created by Point B and Point C
     */
    public boolean isOnLineSegment(IntPoint pointB, IntPoint pointC) {
        IntPoint pointA = new IntPoint(this.xCoordinate, this.yCoordinate);
        IntVector IntVectorBA = pointA.minus(pointB);
        IntVector IntVectorBC = pointC.minus(pointB);
        if ((pointA.getX() == pointB.getX() && pointA.getY() == pointB.getX())
                || (pointA.getX() == pointC.getX() && pointA.getY() == pointC.getY())) {
            return false;
        }
        if (!IntVectorBA.isCollinearWith(IntVectorBC)) {
            return false;
        }
        if (IntVectorBA.dotProduct(IntVectorBC) < IntVectorBC.dotProduct(IntVectorBC)
                && IntVectorBA.dotProduct(IntVectorBC) > 0) {
            return true;
        }
        return false;
    }


    //lineSegmentsIntersect(IntPoint, IntPoint, IntPoint, IntPoint)

    /**
     * Returns whether the line segment created by points A and B, intersect with the line segment created by points C and D
     */
    public static boolean lineSegmentsIntersect(IntPoint pointA, IntPoint pointB, IntPoint pointC, IntPoint pointD) {

        float testA = Math.signum(pointC.minus(pointA).crossProduct(pointB.minus(pointA)))
                * Math.signum(pointD.minus(pointA).crossProduct(pointB.minus(pointA)));
        float testB = Math.signum(pointB.minus(pointC).crossProduct(pointD.minus(pointC)))
                * Math.signum(pointA.minus(pointC).crossProduct(pointD.minus(pointC)));
        if (testA < 0 && testB < 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns the displacement from a given point to the instance.
     *
     * @inspects | this
     * @creates | result
     * @pre Argument {@code this} is not {@code null}.
     * | this != null
     * @pre Argument {@code point} is not {@code null}.
     * | point != null
     * @post The result is an IntVector with the displacement from
     * the given point to this point as coordinates.
     * | result.getX() == this.getX() - point.getX() && result.getY() == this.getY() - point.getY()
     */
    public IntVector minus(IntPoint point) {
        return new IntVector(this.xCoordinate - point.getX(), this.yCoordinate - point.getY());
    }


	public IntPoint plus(IntVector vector) {
		return new IntPoint(this.getX()+vector.getX(), this.getY() + vector.getY());
	}
}

