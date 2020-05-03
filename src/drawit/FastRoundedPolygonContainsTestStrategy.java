package drawit;


public class FastRoundedPolygonContainsTestStrategy 
	implements RoundedPolygonContainsTestStrategy {

	@Override
	public boolean contains(RoundedPolygon polygon, IntPoint point) {
		return polygon.getExtent().contains(point);
	}
}
