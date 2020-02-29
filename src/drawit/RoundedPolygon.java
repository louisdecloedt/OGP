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
	public void setRadius(int radius) throws IllegalArgumentException {
		if (radius < 0) {
			throw new IllegalArgumentException();
		}else {
			this.radius = radius;
		}
	}
	
	//setVertices(IntPoint[])
	public void setVertices(IntPoint[] points) throws IllegalArgumentException {
		if (PointArrays.checkDefinesProperPolygon(points) != null) {
			throw new IllegalArgumentException();
		}else {
			this.vertices = points;
		}
	}
	
	//getRadius()
	public int getRadius() {
		return this.radius;
	}
	
	//getVertices()
	public IntPoint[] getVertices() {
		return PointArrays.copy(this.vertices);
	}
	
	//contains(intPoint)
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
		IntPoint exitPoint = new IntPoint(largeNumber*largeNumber,point.getY());
		int indexFirstVertex = -1;
		for (int i = 0; i < this.vertices.length; i ++) {
			if (! this.vertices[i].isOnLineSegment(point, exitPoint) && indexFirstVertex != -1) {
				indexFirstVertex = i;
			}
		}
		if (indexFirstVertex == -1) {return false;}
		int countIntersections = 0;
		//boolean loopFinished = false;
		int indexCurrentVertex = indexFirstVertex;
		int indexNextVertex = -1;
		for (int j = 0; j < this.vertices.length; j++) {
			for (int i = indexCurrentVertex; i < this.vertices.length; i++) {
				if (this.vertices[i].isOnLineSegment(point, exitPoint) && indexNextVertex != -1) {
					indexNextVertex = i;
				}
			}
			if (-1 != indexNextVertex) {
				if (IntPoint.lineSegmentsIntersect(point, exitPoint, this.vertices[indexCurrentVertex],
						this.vertices[indexCurrentVertex])) {
					countIntersections += 1;
				}
				indexCurrentVertex = indexNextVertex;
			}		
		}
		if (IntPoint.lineSegmentsIntersect(point, exitPoint, this.vertices[indexFirstVertex],
				this.vertices[indexNextVertex])) {
			countIntersections += 1;
		}
		System.out.print(countIntersections);
		if (countIntersections%2 != 0) {
			return true;
		}return false;
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
	public void insert(int index, IntPoint point) {
		this.vertices = PointArrays.insert(this.vertices, index, point);
	}
	
	
	//remove(IntPoint[], integer)
	public void remove(int index) {
		this.vertices = PointArrays.remove(this.vertices, index);
	}
	
	
	//update(integer, IntPoint)
	public void update(int index, IntPoint point) {
		this.vertices = PointArrays.update(this.vertices, index, point);
	}
}
