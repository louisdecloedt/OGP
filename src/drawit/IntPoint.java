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
		return new DoublePoint(this.xCoordinate, this.yCoordinate);
	}
	
	//equals(IntPoint)
	public Boolean equals(IntPoint point) {
		if (point.getX() == this.xCoordinate & point.getY() == this.yCoordinate) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//getX()
	public int getX() {
		return this.xCoordinate;
	}
	
	//getY()
	public int getY() {
		return this.yCoordinate;
	}

	//isOnLineSegment(IntPoint, IntPoint)
	public Boolean isOnLineSegment(IntPoint point1, IntPoint point2) {
		if (point1.getX() < point2.getX()) {
			IntPoint temp = new IntPoint(point1.getX(), point1.getY());
			point1 = point2;
			point2 = temp;
		}
		else if (point1.getX() == point2.getY()) { // to avoid dividing by zero in the next step
			return false; 
		}
		if (this.xCoordinate > point1.getX() & point2.getX() > this.xCoordinate) {
			int slope = (point2.getY() - point1.getY()) / (point2.getX() - point1.getX());
			//assumption, because shouldn't be comparing doubles
			if ((this.xCoordinate - point1.getX())*slope + point1.getY() == this.yCoordinate) {
				return true;
			}
		}
		return false;
		
	}
	
	//lineSegmentsIntersect(IntPoint, IntPoint, IntPoint, IntPoint)
	public static Boolean lineSegmentsIntersect(IntPoint point1, IntPoint point2, IntPoint point3, IntPoint point4) {
		
		return false;
	}
	//minus(IntPoint)
	public IntPoint minus(IntPoint point) {
		return new IntPoint(this.xCoordinate - point.getX(), this.yCoordinate - point.getY());
	}
	
	
}
