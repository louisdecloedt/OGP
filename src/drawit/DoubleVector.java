package drawit;

//no formal documentation required for this class
public class DoubleVector {

    private final double xCoordinate;
    private final double yCoordinate;

    public double getX() {
        return this.xCoordinate;
    }

    public double getY() {
        return this.yCoordinate;
    }

    public DoubleVector(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    //returns angle between -PI and PI
    public double asAngle() {
        return Math.atan2(this.getY(), this.getX());
    }

    public double crossProduct(DoubleVector vector) {
        return this.xCoordinate * vector.getY() - this.yCoordinate * vector.getX();
    }

    public double dotProduct(DoubleVector vector) {
        return this.xCoordinate * vector.getX() + this.yCoordinate * vector.getY();
    }

    public double getSize() {
        return Math.sqrt(Math.pow(this.xCoordinate, 2) + Math.pow(this.yCoordinate, 2));
    }

    public DoubleVector plus(DoubleVector vector) {
        return new DoubleVector(this.xCoordinate + vector.getX(), this.yCoordinate + vector.getY());
    }

    public DoubleVector scale(double scalar) {
        return new DoubleVector(this.xCoordinate * scalar, this.yCoordinate * scalar);
    }
}
