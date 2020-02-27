package drawit;

public class RoundedPolygon {
	
	private int radius;
	private IntPoint[] vertices;

	//constructor: RoundedPolygon()
	public RoundedPolygon() {
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
		if (PointArrays.in(this.vertices, point)) {
			return true;
		}
		//point at an edge
		for (int i = 0; i < this.vertices.length - 1; i++) {
			if (point.isOnlineSegment(this.vertices[i], this.vertices[i+1])) {
				return true;
			}
		}
		if (point.isOnlineSegment(this.vertices[0], this.vertices[this.vertices.length-1])) {
			return true;
		}
		//////////////// extra
		return false;
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
