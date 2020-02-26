package drawit;

public class IntVector {
	private int xCoordinate;
	private int yCoordinate;
	
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
	public int dotProduct(IntVector vector) {
		return this.xCoordinate*vector.getX() + this.yCoordinate*vector.getY();
	}
	
	//crossProduct(IntVector)
	public int crossProduct(IntVector vector) {
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
		int slopeA = this.getY()/this.getX();
		int slopeB = vector.getY()/vector.getX();
		if (slopeA == slopeB || slopeA == - slopeB) {
			return true;
		}
		return false;
	}
	
	//plus(IntVector)
	public IntVector plus(IntVector vector) {
		return new IntVector(this.xCoordinate + vector.getX(), this.yCoordinate + vector.getY());
	}
}
