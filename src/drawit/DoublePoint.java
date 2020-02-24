package drawit;

public class DoublePoint {
	
	private double xCoordinate;
	private double yCoordinate;
	
	
	//constructor: DoublePoint(double,double)
	public DoublePoint(double x, double y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}
	
	//getX()
	public double getX() {
		return xCoordinate;
	}
	
	//getY()
	public double getY() {
		return yCoordinate;
	}
	
	//minus(DoublePoint) -- displacement from other point to this one
	public DoubleVector minus(DoublePoint minusPoint) {
		return new DoubleVector(this.xCoordinate - minusPoint.getX(), this.yCoordinate - minusPoint.getY());
	}
	
	//plus(DoubleVector)
	public DoublePoint plus(DoubleVector displacement) {
		return new DoublePoint(this.xCoordinate + displacement.getX(), this.yCoordinate + displacement.getY());
	}
	
	//round() !!nearest integer
	public IntPoint round() {
		return new IntPoint(Math.round(this.xCoordinate),Math.round(this.yCoordinate));
	}
}
