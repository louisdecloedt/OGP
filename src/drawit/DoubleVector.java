package drawit;

public class DoubleVector {
	
	private double xCoordinate;
	private double yCoordinate;
	
	//constructor: DoubleVector(double,double)
	public DoubleVector(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	//getX()
	public double getX() {
		return this.xCoordinate;
	}
	
	//getY()
	public double getY() {
		return this.yCoordinate;
	}
	
	//asAngle
	//returns angle between -PI/PI
	public double asAngle() {
		return Math.atan2(this.getY(), this.getX());
	}
	
	//crossProduct(DoubleVector)
	public double crossProduct(DoubleVector vector) {
		return this.xCoordinate*vector.getY() - this.yCoordinate*vector.getX();
	}
	
	//dotProduct(DoubleVector)
	public double dotProduct(DoubleVector vector) {
		return this.xCoordinate*vector.getX() + this.yCoordinate*vector.getY();
	}
	
	//getSize() //lengte??
	public double getSize() {
		return Math.sqrt(Math.pow(this.xCoordinate,2) + Math.pow(this.yCoordinate,2));		
	}
	
	//plus(DoubleVector)
	public DoubleVector plus(DoubleVector vector) {
		return new DoubleVector(this.xCoordinate+vector.getX(), this.yCoordinate+vector.getY());
	}
	
	
	//scale(double)
	public DoubleVector scale(double scalar) {
		return new DoubleVector(this.xCoordinate*scalar, this.yCoordinate*scalar);
	}
}
