package drawit;

public class IntVector {
	private final int xCoordinate;
	private final int yCoordinate;
	
	//constructor: IntVector(int, int)
	public IntVector(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	//asDoubleVector
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
	
	//getX()
	public int getX() {
		return this.xCoordinate;
	}
	
	//getY()
	public int getY() {
		return this.yCoordinate;
	}
	
	//isCollinearWith(IntVector)
	public Boolean isCollinearWith(IntVector vector) {
		if (vector.getX() == 0 && vector.getY() == 0) {
			return false; //Do not know if required.
		}
		return this.crossProduct(vector) == 0;
	}
	
	//plus(IntVector)
	public IntVector plus(IntVector vector) {
		return new IntVector(this.xCoordinate + vector.getX(), this.yCoordinate + vector.getY());
	}
}
