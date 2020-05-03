package drawit;

import java.awt.Color;
import java.util.stream.IntStream;

import drawit.shapegroups1.Extent;

/**
 * Each instance of this class represents a time of day, at one-minute resolution.
 *
 * @invar This object's radius can not be negative.
 *    | 0 <= getRadius()
 */

public class RoundedPolygon {
	
	/**
     * @invar | 0 <= radius
     */
    private int radius;
    private IntPoint[] vertices;
    private Color color;
    private Extent extent;
    
    public java.awt.Color getColor(){
    	return color;
    }
    
    public void setColor(java.awt.Color color) throws IllegalArgumentException {
    	if (color == null) {
    		throw new IllegalArgumentException("Color can't be null!");
    	}
    	this.color = color;
    }
    
    /**
     * Initializes this object with default settings that define a proper polygon.
     * 
     * @mutates | this
     *
     */
    public RoundedPolygon() {
        this.radius = 10;
        this.vertices = new IntPoint[4];
        IntPoint point0 = new IntPoint(0, 0);
        IntPoint point1 = new IntPoint(100, 0);
        IntPoint point2 = new IntPoint(100, 100);
        IntPoint point3 = new IntPoint(0, 100);
        this.vertices[0] = point0;
        this.vertices[1] = point1;
        this.vertices[2] = point2;
        this.vertices[3] = point3;
        this.color = Color.yellow;
        this.extent = Extent.ofLeftTopRightBottom(0, 0, 100, 100);
    }
    
    /**
     * Sets this object's radius.
     *
     * @mutates | this
     * 
     * @throws IllegalArgumentException if the given radius is negative
     * | radius < 0
     * @post This object's radius equals the given radius
     * | getRadius() == radius
     */
    public void setRadius(int radius) throws IllegalArgumentException {
        if (radius < 0) {
            throw new IllegalArgumentException("Negative radius not valid!");
        } else {
            this.radius = radius;
        }
    }
    
    /**
     * 
     * @inspects| points
     * 
     * @mutates | this
     * 
     * @throws IllegalArgumentException if the given IntPoints do not form a proper polygon.
     * | PointArrays.checkDefinesProperPolygon(points) != null
     * @post the vertices of the rounded polygon are now equal to the given array of points
     * | IntStream.range(0, points.length).allMatch(i -> this.getVertices()[i].equals(points[i]))
     */
    public void setVertices(IntPoint[] points) throws IllegalArgumentException {
        if (PointArrays.checkDefinesProperPolygon(points) != null) {
            throw new IllegalArgumentException(PointArrays.checkDefinesProperPolygon(points));
        } 
        for (int i = 0; i < this.vertices.length; i++) {
        	if (this.vertices[i] == null) {
        		throw new IllegalArgumentException("NullPointer in setVertices!");
        	}
        }
        this.vertices = points;
        int left = vertices[0].getX(), top = vertices[0].getY();
		int right = left, bottom = top;
		for (int i = 1; i < vertices.length; i++) {
			if (vertices[i].getX() > right ) {
				right = vertices[i].getX();
			}
			if (vertices[i].getX() < left ) {
				left = vertices[i].getX();
			}
			if (vertices[i].getY() > bottom ) {
				bottom = vertices[i].getY();
			}
			if (vertices[i].getY() < top ) {
				top = vertices[i].getY();
			}
		}
		this.extent = Extent.ofLeftTopRightBottom(left, top, right, bottom);
    }
    
    /**
     * Returns the radius of the RoundedPolygon.
     * 
     * @inspects| this
     * 
     * @post Result equals the radius of the RoundedPolygon.
     */
    public int getRadius() {
        return this.radius;
    }
    
    /**
     * Returns the Extent of the RoundedPolygon.
     * 
     * @inspects| this
     * 
     * @post Result equals the Extent of the RoundedPolygon.
     */
    public Extent getExtent() {
        return this.extent;
    }
    
    /**
     * Returns the vertices of the RoundedPolygon.
     * 
     * @inspects| this
     * 
     * @creates | result
     * 
     * @post Result equals the vertices of the RoundedPolygon.
     * 
     */
    public IntPoint[] getVertices() {
        return PointArrays.copy(this.vertices);
    }
    
    /**
     * Returns whether if the given IntPoint is contained on the boundaries of a given RoundedPolygon.
     * 
     * @pre the given array of the points must define a proper polygon.
     */
    public boolean onEdgePolygon(IntPoint point) {
        if (PointArrays.in(this.vertices, point)) {
            return true;
        }
        for (int i = 0; i < this.vertices.length - 1; i++) {
            if (point.isOnLineSegment(this.vertices[i], this.vertices[i + 1])) {
                return true;
            }
        }
        if (point.isOnLineSegment(this.vertices[this.vertices.length - 1], this.vertices[0])) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns whether if the given IntPoint is contained within or 
     * on the boundaries of a given RoundedPolygon.
     * 
     * @pre the given array of the points must define a proper polygon.
     */
    public boolean contains(IntPoint point) {
    	if (this.onEdgePolygon(point)) {
            return true; }
    	boolean contains = false;
    	int largeNumber = 1000000;
    	IntPoint exitPoint = new IntPoint(largeNumber, point.getY());
    	IntVector exitDirection = exitPoint.minus(point);
    	IntPoint V = point;
    	int index = 0;
    	while(V.getY()== point.getY() && V.getX()>=point.getX()) {
    		if (index == this.vertices.length) {
    			return false; }
    		V = this.vertices[index];
    		index ++; }
    	IntPoint W = point;
    	int outerCount = index;
    	IntPoint firstV = V;
    	outer: while(W.equals(firstV) == false) {
    		int innerCount = outerCount;
    		W = point;
    		while(W.getY() == point.getY() && W.getX() >= point.getX()) {
    			if (innerCount == this.vertices.length+1) {
    				break outer; }
    			if (innerCount == this.vertices.length) {
    				W = firstV;
    				innerCount++; }
    			if (W.equals(firstV)) {
    				break; }
    			W = this.vertices[innerCount];
    			innerCount++; }
    		IntVector vecPV = V.minus(point);
        	IntVector vecPW = W.minus(point);
        	long cross1 = vecPV.crossProduct(exitDirection);
        	long cross2 = vecPW.crossProduct(exitDirection);
        	if (innerCount - outerCount == 1 && IntPoint.lineSegmentsIntersect(V, W, point, exitPoint)) {
        		contains = !contains; }
        	if (innerCount-outerCount > 1) {
        		if (Math.signum(cross1)*Math.signum(cross2) < 0) {
        			contains = !contains; }	}
        	outerCount = innerCount;
        	V = W;
    	}
    	return contains;
    }
    
    /**
     * Returns a string with drawing commands for RoundedPolygon.
     * 
     */
    public String getDrawingCommands() {
        String result = "";
        if (this.vertices.length < 3) {
            return result;
        }
        for (int i = 0; i < this.vertices.length; i++) {
            int a = i - 1, b = i, c = i + 1;
            if (i == 0) {
                a = this.vertices.length - 1;
            }
            if (i == this.vertices.length - 1) {
                c = 0;
            }
            
            IntVector vectorAB = this.vertices[b].minus(this.vertices[a]);
            IntVector vectorBC = this.vertices[c].minus(this.vertices[b]);
            if (vectorAB.isCollinearWith(vectorBC)) {
                result += "line ";
                result += Integer.toString(this.vertices[b].getX() - (vectorAB.getX() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getY() - (vectorAB.getY() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getX()) + " ";
                result += Integer.toString(this.vertices[b].getY()) + " ";
                result += "\n";
                result += "line ";
                result += Integer.toString(this.vertices[b].getX()) + " ";
                result += Integer.toString(this.vertices[b].getY()) + " ";
                result += Integer.toString(this.vertices[b].getX() + (vectorBC.getX() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getY() + (vectorBC.getY() / 2)) + " ";
                result += "\n";
            } else {
                DoubleVector doubleBA = vectorAB.asDoubleVector().scale(-1);
                DoubleVector doubleBC = vectorBC.asDoubleVector();
                DoubleVector BAU = doubleBA.scale(1 / doubleBA.getSize());
                DoubleVector BCU = doubleBC.scale(1 / doubleBC.getSize());
                DoubleVector BS = BAU.plus(BCU);
                DoubleVector BSU = BS.scale(1 / BS.getSize());
                double BAUCutOff = Math.abs(BAU.dotProduct(BSU));
                double unitRadius = Math.abs(BAU.crossProduct(BSU));
                double maxCutOff = Math.min(doubleBA.getSize() / 2, doubleBC.getSize() / 2);
                double minimumScale = Math.min(this.radius / unitRadius, maxCutOff / BAUCutOff);
                DoubleVector BSReal = BSU.scale(minimumScale);
                DoublePoint doubleB = new DoublePoint(this.vertices[b].getX(), this.vertices[b].getY());
                DoublePoint actualCenter = doubleB.plus(BSReal);
                IntPoint actualCenterInt = actualCenter.round();
                DoublePoint ABEnd = doubleB.plus(BAU.scale(minimumScale*BAUCutOff));
                DoublePoint BCBegin = doubleB.plus(BCU.scale(minimumScale * BAUCutOff));
                IntPoint ABEnd2 = ABEnd.round();
                IntPoint BCBegin2 = BCBegin.round();
                Double startAngle = ABEnd.minus(actualCenter).asAngle();
                Double endAngle = BCBegin.minus(actualCenter).asAngle();
                Double extentAngle = endAngle - startAngle;
                while (extentAngle < -Math.PI ) {
                	extentAngle += 2*Math.PI;
                }
                while (extentAngle > Math.PI) {
                	extentAngle -= 2*Math.PI;
                }
                IntVector vecAB = this.vertices[b].minus(this.vertices[a]);
                IntVector vecBC = this.vertices[c].minus(this.vertices[b]);
                result += "line ";
                result += Integer.toString(this.vertices[b].getX() - (vecAB.getX() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getY() - (vecAB.getY() / 2)) + " ";
                result += Integer.toString(ABEnd2.getX()) + " ";
                result += Integer.toString(ABEnd2.getY()) + " ";
                result += "\n";
                result += "arc ";
                result += Integer.toString(actualCenterInt.getX()) + " ";
                result += Integer.toString(actualCenterInt.getY()) + " ";
                result += Integer.toString(Math.toIntExact(Math.round(unitRadius * minimumScale))) + " ";
                result += Double.toString(startAngle) + " ";
                result += Double.toString(extentAngle) + " ";
                result += "\n";
                result += "line ";
                result += Integer.toString(BCBegin2.getX()) + " ";
                result += Integer.toString(BCBegin2.getY()) + " ";
                result += Integer.toString(this.vertices[b].getX() + (vecBC.getX() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getY() + (vecBC.getY() / 2)) + " ";
                result += "\n";
            }
        }
        result += "fill" + " " + Integer.toString(color.getRed()) + " " +
        Integer.toString(color.getGreen()) + " " + Integer.toString(color.getBlue()) + "\n";
        return result;
    }
    
    /**
     * @mutates | this
     * 
     * @pre Argument {@code point} is not {@code null}.
     * | point != null
     * @throws IllegalArgumentException if the new vertices would not form a proper polygon.
     * 
     * @post the given IntPoint is now a vertex at the given index in the polygon.
     * | getVertices()[index].equals(point) 
     * 
     */
    public void insert(int index, IntPoint point) {
        IntPoint[] testVertices = PointArrays.insert(this.vertices, index, point);
        if (PointArrays.checkDefinesProperPolygon(testVertices) != null) {
        	throw new IllegalArgumentException(PointArrays.checkDefinesProperPolygon(testVertices));
        }
        this.vertices = testVertices;
    }
    
    /**
     * @mutates | this
     * 
     * @throws IllegalArgumentException if the new vertices would not form a proper polygon.
     * 
     * @post the point in the polygon at the given index has now been removed from that polygon.
     */
    public void remove(int index) {
        IntPoint[] testVertices = PointArrays.remove(this.vertices, index);
        if (PointArrays.checkDefinesProperPolygon(testVertices) != null) {
        	throw new IllegalArgumentException(PointArrays.checkDefinesProperPolygon(testVertices));
        }
        this.vertices = testVertices;
    }
    
    /**
     * @mutates | this
     * 
     * @pre Argument {@code point} is not {@code null}.
     * | point != null
     * @throws IllegalArgumentException if the new vertices would not form a proper polygon.
     * 
     * @post the given IntPoint is now a vertex at the given index in the polygon.
     * | getVertices()[index].equals(point)
     * 
     */
    public void update(int index, IntPoint point) {
        IntPoint[] testVertices = PointArrays.update(this.vertices, index, point);
        if (PointArrays.checkDefinesProperPolygon(testVertices) != null) {
        	throw new IllegalArgumentException(PointArrays.checkDefinesProperPolygon(testVertices));
        }
        this.vertices = testVertices;
    }
}


