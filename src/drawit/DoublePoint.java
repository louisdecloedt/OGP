package drawit;

//no formal documentation required for this class
public class DoublePoint {
	
	private final double xCoordinate;
	private final double yCoordinate;
	
	public double getX() { return xCoordinate; }
	public double getY() { return yCoordinate; }
	
	public DoublePoint(double x, double y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	public DoubleVector minus(DoublePoint minusPoint) {
		return new DoubleVector(this.xCoordinate - minusPoint.getX(), this.yCoordinate - minusPoint.getY());
	}
	
	public DoublePoint plus(DoubleVector displacement) {
		return new DoublePoint(this.xCoordinate + displacement.getX(), this.yCoordinate + displacement.getY());
	}
	
	public IntPoint round() {
		return new IntPoint(Math.toIntExact(Math.round(this.xCoordinate)),Math.toIntExact(Math.round(this.yCoordinate)));
	}
}
