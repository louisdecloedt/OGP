package drawit;

/**
 * 
 * An interface which classes can implement to have an instance 
 * that checks whether a given RoundedPolygon contains a given IntPoint.
 *
 */
public interface RoundedPolygonContainsTestStrategy {
	
	/**
     * Checks if the given point is contained by the given RoundedPolygon.
     *
     * @inspects | polygon
     *
     */
	abstract boolean contains(RoundedPolygon polygon, IntPoint point);
}
