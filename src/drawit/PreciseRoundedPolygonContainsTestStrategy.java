package drawit;

/**
 * 
 * Each instance of this class is a PreciseRoundedPolygonContainsTestStrategy.
 * This class implements the RoundedPolygonContainsTestStrategy interface.
 * The used test strategy used for this part is precise,
 * and makes use of the RoundedPolygon contains function.
 *
 */
public class PreciseRoundedPolygonContainsTestStrategy 
	implements RoundedPolygonContainsTestStrategy{

	/**
     * Checks if the given point is contained by the given RoundedPolygon.
     * This method is a wrapper around the contains method of the RoundedPolygon class.
     *
     * @inspects | polygon
     *
     * @throws If the given point is {@code null}.
     *    | point == null
     * @post
     *    | result == polygon.contains(point)
     */
	@Override
	public boolean contains(RoundedPolygon polygon, IntPoint point) {
		if (point == null) {
			throw new IllegalArgumentException();
		}
		return polygon.contains(point);
	}
}
