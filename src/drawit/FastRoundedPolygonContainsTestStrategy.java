package drawit;

/**
 * 
 * Each instance of this class is a FastRoundedPolygonContainsTestStrategy.
 * This class implements the RoundedPolygonContainsTestStrategy interface.
 * The used test strategy used for this part is fast,
 * and makes use of the Extent contains function.
 *
 */
public class FastRoundedPolygonContainsTestStrategy 
	implements RoundedPolygonContainsTestStrategy {

	
	/**
     * Checks if the given point is contained by the Extent of the given RoundedPolygon.
     * This method is a wrapper around the contains method the Extent class.
     *
     * @inspects | polygon
     *
     * @pre The given point and polygon are not {@code null}.
     *    | point != null && polygon != null
     * @post
     *    | result == polygon.getExtent().contains(point)
     */
	@Override
	public boolean contains(RoundedPolygon polygon, IntPoint point) {
		return polygon.getExtent().contains(point);
	}
}
