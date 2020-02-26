package drawit;

public class IntPoint {
	private int xCoordinate;
	private int yCoordinate;
	
	//constructor: IntPoint(int, int)
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

	
	
	//// different approach required, check given documentation
	//isOnLineSegment(IntPoint, IntPoint)
	/*
	 * public Boolean isOnLineSegment(IntPoint point1, IntPoint point2) { if
	 * (point1.getX() < point2.getX()) { IntPoint temp = new IntPoint(point1.getX(),
	 * point1.getY()); point1 = point2; point2 = temp; } else if (point1.getX() ==
	 * point2.getY()) { // to avoid dividing by zero in the next step return false;
	 * } if (this.xCoordinate > point1.getX() & point2.getX() > this.xCoordinate) {
	 * int slope = (point2.getY() - point1.getY()) / (point2.getX() -
	 * point1.getX()); //assumption, because shouldn't be comparing doubles if
	 * ((this.xCoordinate - point1.getX())*slope + point1.getY() ==
	 * this.yCoordinate) { return true; } } return false;
	 * 
	 * }
	 */
	
	//I guess this is what they mean in the given documentation
	public Boolean isOnlineSegment(IntPoint pointB, IntPoint pointC) {
		//scalair product: u*v = |u||v|*cosA
		IntPoint pointA = new IntPoint(this.xCoordinate, this.yCoordinate);
		IntVector IntVectorAB = pointB.minus(pointA);
		IntVector IntVectorBC = pointC.minus(pointB);
		
		if (pointA.getX() == pointB.getX() || pointA.getX() == pointC.getX()) {
			return false;
		}

		if ( ! IntVectorAB.isCollinearWith(IntVectorBC)) {
			return false;
		}
		if (  IntVectorAB.dotProduct(IntVectorBC) < IntVectorBC.dotProduct(IntVectorBC) 
				&& IntVectorAB.dotProduct(IntVectorBC) > 0) {
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
