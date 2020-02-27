package drawit;

public class RoundedPolygon {
	
	private int radius;
	private IntPoint[] vertices;

	//constructor: RoundedPolygon()
	public RoundedPolygon() {
	}
	
	//setRadius(integer)
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	//setVertices(IntPoint[])
	public void setRadius(IntPoint[] points) {
		this.vertices = points;
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
		
		return true;
	}
	
	//getDrawingCommands()
	
	
	//insert(int, intPoint)
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
