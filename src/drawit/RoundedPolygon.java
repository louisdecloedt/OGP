package drawit;

public class RoundedPolygon {
	
	private int radius;
	private IntPoint[] vertices = new IntPoint[2];
	
	//constructor: RoundedPolygon()
	public RoundedPolygon() {
	}
	
	//setRadius(int)
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
		
		return;
	}
	
	//getDrawingCommands()
	
	
	//insert(int, intPoint)
	
	//remove(IntPoint[], int)
	
	
	
	//update(int, IntPoint)
	
	
	
	
	

}
