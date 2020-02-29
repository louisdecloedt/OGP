package drawit;

public class IntPoint {
	private int xCoordinate;
	private int yCoordinate;
	
	//constructor: IntPoint(integer, integer)
	public IntPoint(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
	}
	
	//asDoublePoint
	public DoublePoint asDoublePoint() {
		return new DoublePoint(this.xCoordinate, this.yCoordinate); // have to be doubles
	}
	
	//equals(IntPoint)
	public Boolean equals(IntPoint point) {
		if (point.getX() == this.xCoordinate & point.getY() == this.yCoordinate) {
			return true;
		}
		return false;
	}
	
	//getX()
	public int getX() {
		return this.xCoordinate;
	}
	
	//getY()
	public int getY() {
		return this.yCoordinate;
	}

	

	
	//I guess this is what they mean in the given documentation
	public Boolean isOnLineSegment(IntPoint pointB, IntPoint pointC) {
		IntPoint pointA = new IntPoint(this.xCoordinate, this.yCoordinate);
		IntVector IntVectorBA = pointA.minus(pointB);
		IntVector IntVectorBC = pointC.minus(pointB);		
		if (pointA.getX() == pointB.getX() || pointA.getX() == pointC.getX()) {
			return false;
		}

		if ( ! IntVectorBA.isCollinearWith(IntVectorBC)) {
			return false;
		}
		if (  IntVectorBA.dotProduct(IntVectorBC) < IntVectorBC.dotProduct(IntVectorBC) 
				&& IntVectorBA.dotProduct(IntVectorBC) > 0) {
			return true;
		}
		return false;
	}
	
	
	//lineSegmentsIntersect(IntPoint, IntPoint, IntPoint, IntPoint)
	public static Boolean lineSegmentsIntersect(IntPoint pointA, IntPoint pointB, IntPoint pointC, IntPoint pointD) {
		
		float testA = Math.signum(pointC.minus(pointA).crossProduct(pointB.minus(pointA)))
				*Math.signum(pointD.minus(pointA).crossProduct(pointB.minus(pointA)));
		float testB = Math.signum(pointB.minus(pointC).crossProduct(pointD.minus(pointC)))
				*Math.signum(pointA.minus(pointC).crossProduct(pointD.minus(pointC)));
		if (testA < 0 && testB < 0) {
			return true;
		}
		return false;
	}
	
	
	//minus(IntPoint)
	public IntVector minus(IntPoint point) {
		return new IntVector(this.xCoordinate - point.getX(), this.yCoordinate - point.getY());
	}
}
