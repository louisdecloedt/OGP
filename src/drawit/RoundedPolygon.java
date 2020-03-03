package drawit;

public class RoundedPolygon {
	
	private int radius;
	private IntPoint[] vertices;

	//constructor: RoundedPolygon()
	public RoundedPolygon() {
		this.radius = 0;
		this.vertices = new IntPoint[0];
	}
	
	//setRadius(integer)
	/**
     * Initializes this object with the given coordinates.
     * 
     * @mutates | this
     * 
     * @throws IllegalArgumentException if the given radius is negative
     *
     * @post This object's radius equals the given radius
     *    | getX() == xCoordinate
     *
     */
	public void setRadius(int radius) throws IllegalArgumentException {
		if (radius < 0) {
			throw new IllegalArgumentException();
		}else {
			this.radius = radius;
		}
	}
	
	//setVertices(IntPoint[])
	/**
	 * 
	 * @throws IllegalArgumentException if the given vertices do not form a proper polygon
	 * 
	 * @post the vertices of the rounded polygon are now equal to the given array of points
	 */
	public void setVertices(IntPoint[] points) throws IllegalArgumentException {
		if (PointArrays.checkDefinesProperPolygon(points) != null) {
			throw new IllegalArgumentException();
		}else {
			this.vertices = points;
		}
	}
	
	//getRadius()
	//returns the radius of the given object
	public int getRadius() {
		return this.radius;
	}
	
	//getVertices()
	//returns the vertices of the given polygon
	public IntPoint[] getVertices() {
		return PointArrays.copy(this.vertices);
	}
	
	//contains(intPoint)
	/**
	 * 
	 * @pre the given array of the points must define a proper polygon
	 * 
	 * @return This returns true if the given IntPoint is contained within or on the boundaries of a given array of points which comprise a polygon,
	 * this function will return true if the given point is thus equal to a vertex on the polygon or on the edge of this polygon
	 */
	public Boolean contains(IntPoint point) {
		//point at a vertex
		for (int i = 0; i < this.vertices.length; i++) {
			if (this.vertices[i].equals(point)) {
				return true;
			}
		}
		//point at an edge
		for (int i = 0; i < this.vertices.length - 1; i++) {
			if (point.isOnLineSegment(this.vertices[i], this.vertices[i+1])) {
				return true;
			}
		}
		if (point.isOnLineSegment(this.vertices[0], this.vertices[this.vertices.length-1])) {
			return true;
		}
		int largeNumber = 100000;
		IntPoint exitPoint = new IntPoint(largeNumber,point.getY()); //WARNING: Is plus ook niet al goed genoeg, vermenigvuldiging zal het te groot maken en de volgende for loop te lang laten duren in mijn mening
		int numberOfLineIntersections = 0;
		int numberOfVerticesInPath = 0;
		for (int i = 0; i < this.vertices.length - 1; i ++) {
			if (this.vertices[i].isOnLineSegment(point, exitPoint)) {
				numberOfVerticesInPath++;
			}
		}
		if (numberOfVerticesInPath == 2) {return false;}
		for (int x = 0; x < (largeNumber - point.getX()); x ++) {
			for (int i = 0; i < this.vertices.length - 1; i ++) {
				for (int j = 0; j < this.vertices.length - 1; j ++) {
					IntPoint currentPoint = new IntPoint(x, point.getY());
					if (currentPoint.isOnLineSegment(this.vertices[i], this.vertices[j]) && i != j) {
						numberOfLineIntersections++;
					}
				}
			}
		}
		if (numberOfLineIntersections + numberOfVerticesInPath == 1) {return true;} else {return false;}
	}
	
	public String getDrawingCommands() {
		String result = "";
		if (this.vertices.length < 3) {return result;}
		for (int i = 0; i < this.vertices.length - 2; i ++) {
			double Rico = (this.vertices[i+1].getY() - this.vertices[i].getY())/(this.vertices[i+1].getX() - this.vertices[i].getX());
			double Alpha = -1/Rico;
			//KLOPT NOG NIET
		}
		return result;
	}
	
	//getDrawingCommands()
	/*
	 * public String getDrawingCommands() { String result = ""; if
	 * (this.vertices.length < 3) { return result; }
	 * 
	 * for (int i = 0; i < this.vertices.length - 1; i++) { IntVector vector1 =
	 * this.vertices[i+1].minus(this.vertices[i]); IntVector vector2 =
	 * this.vertices[i+2].minus(this.vertices[i+1]); int radius = max(this.radius, )
	 * 
	 * } //connecting first and last point
	 * 
	 * return result; }
	 */
	
	
	//insert(integer, intPoint)
	/**
	 * @mutates this
	 * 
	 * @post the given IntPoint is now a vertex in the given polygon
	 */
	public void insert(int index, IntPoint point) {
		this.vertices = PointArrays.insert(this.vertices, index, point);
	}
	
	
	//remove(IntPoint[], integer)
	/**
	 * @mutates this
	 * 
	 * @post the point in the polygon at the given index has now been removed from that polygon
	 */
	public void remove(int index) {
		this.vertices = PointArrays.remove(this.vertices, index);
	}
	
	
	//update(integer, IntPoint)
	public void update(int index, IntPoint point) {
		this.vertices = PointArrays.update(this.vertices, index, point);
	}
}
