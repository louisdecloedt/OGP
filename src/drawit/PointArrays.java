package drawit;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PointArrays {

    /**
     * Prevents that an instance of the class PointArrays gets initialized.
     */
    private PointArrays() {
    }

    /**
     * Returns the copy of an array of IntPoints.
     *
     * @creates | result
     * @pre Length of the given array is greater than zero.
     * | points.length >= 0
     * @pre The given IntPoint array does not contain any null.
     * | Arrays.stream(points).allMatch(e -> e != null)
     * @post The resulting IntPoint array does not contain any null.
     * | Arrays.stream(result).allMatch(e -> e != null)
     * @post The resulting array has the same length as the original one.
     * | points.length == result.length
     * @post The resulting array is equal to the given array of IntPoints.
     * | IntStream.range(0, points.length).allMatch(i -> result[i].equals(points[i]))
     */
    public static IntPoint[] copy(IntPoint[] points) {
        return points; //pass by value
    }

    /**
     * Returns a new array with a point inserted on a given index.
     *
     * @creates |result
     * @pre The given index is greater than or equal to zero
     * and not greater than the length of the current array.
     * | index >= 0 && index < points.length + 1
     * @pre Argument {@code point} is not {@code null}.
     * | point != null
     * @pre The given IntPoint array does not contain any null.
     * | Arrays.stream(points).allMatch(e -> e != null)
     * @post The resulting IntPoint array does not contain any null.
     * | Arrays.stream(result).allMatch(e -> e != null)
     * @post The resulting array has a length 1 larger than the given array.
     * | points.length + 1 == result.length
     * @post The points before the index remain the same.
     * | IntStream.range(0, index).allMatch(i -> result[i].equals(points[i]))
     * @post The point result[index] is equal to the given point to insert.
     * | result[index].equals(point)
     * @post The points behind the given index are shifted 1 location to the right.
     * | IntStream.range(index+1, points.length + 1).allMatch(i -> result[i].equals(points[i-1]))
     */
    public static IntPoint[] insert(IntPoint[] points, int index, IntPoint point) {
        IntPoint[] result = new IntPoint[points.length + 1];
        for (int i = 0; i < index; i++) {
            result[i] = points[i];
        }
        result[index] = point; //double for loop for performance
        for (int i = index + 1; i < points.length; i++) {
            result[i] = points[i - 1];
        }
        return result;
    }


    /**
     * Returns a new array with an updated IntPoint on a given index.
     *
     * @creates |result
     * 
     * @pre The given index is greater than or equal to zero
     * and smaller than the length of the current array.
     * | index >= 0 && index < points.length
     * @pre Argument {@code point} is not {@code null}.
     * | value != null
     * @pre The given IntPoint array does not contain any null.
     * | Arrays.stream(points).allMatch(e -> e != null)
     * @post The resulting IntPoint array does not contain any null.
     * | Arrays.stream(result).allMatch(e -> e != null)
     * @post The resulting array has the same length as the given array.
     * | points.length  == result.length
     * @post The points before the index remain the same.
     * | IntStream.range(0, index).allMatch(i -> result[i].equals(points[i]))
     * @post The point result[index] is equal to the given point to update.
     * | result[index].equals(value)
     * @post The points behind the given index stay the same.
     * | IntStream.range(index+1, points.length).allMatch(i -> result[i].equals(points[i]))
     */
    public static IntPoint[] update(IntPoint[] points, int index, IntPoint value) {
        IntPoint[] result = new IntPoint[points.length];
        for (int i = 0; i < points.length; i++) {
            if (i != index) {
                result[i] = points[i];
            } else {
                result[i] = value;
            }
        }
        return result;
    }

    /**
     * Returns a new array with a point deleted on a given index.
     *
     * @creates | result
     * @pre The given array is of length greater than or equal to zero.
     * | points.length >= 1
     * @pre The given index is greater than or equal to zero
     * and smaller than the length of the current array.
     * | index >= 0 && index < points.length
     * @pre The given IntPoint array does not contain any null.
     * | Arrays.stream(points).allMatch(e -> e != null)
     * @post The resulting IntPoint array does not contain any null.
     * | Arrays.stream(result).allMatch(e -> e != null)
     * @post The resulting array has a length 1 smaller than the given array.
     * | points.length == result.length  + 1
     * @post The points before the index remain the same.
     * | IntStream.range(0, index).allMatch(i -> result[i].equals(points[i]))
     * @post The points starting from the given index + 1 are shifted 1 location to the left.
     * | IntStream.range(index + 1, points.length).allMatch(i -> points[i].equals(result[i-1]))
     */
    public static IntPoint[] remove(IntPoint[] points, int index) {
        IntPoint[] result = new IntPoint[points.length - 1];
        for (int i = 0; i < index; i++) {
            result[i] = points[i];
        }
        //double for loop for performance <-> a lot of repetitive if-statements
        for (int i = index + 1; i < points.length; i++) {
            result[i - 1] = points[i];
        }
        return result;
    }


    /**
     * Returns whether an given IntPoint is in a given IntPoint array.
     *
     * @pre Argument {@code point} is not {@code null}.
     * | point != null
     * @pre The given IntPoint array does not contain any null.
     * | Arrays.stream(points).allMatch(e -> e != null)
     * @post The given IntPoins is in the given IntPoint array at least one time.
     * | result == Arrays.stream(points).anyMatch(e -> e.equals(point))
     */
    public static Boolean in(IntPoint[] points, IntPoint point) {
        for (int i = 0; i < points.length; i++) {
            if (point.getX() == points[i].getX()) {
                if (point.getY() == points[i].getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    /*False Polygon if:	- Fewer than 3 points
     * 					- Multiple points at same location
     * 					- Edges are not the lines between the points
     * 					- Edges in
    /**
     * Returns whether a given array of IntPoints forms a proper polygon
     *
     * @pre The length of the array is greater than 2
     * @pre No 2 points coincide
     * @pre None of the vertices lie on an edge created by other vertices
     * @pre No 2 edges shall cross/intersect
     *
     * @post If the conditions for a proper polygon are not met, the reason as to why the failure is printed out
     */
    public static String checkDefinesProperPolygon(IntPoint[] points) {
        if (points.length < 3) {
            return "Not enough points to define a proper polygon.\n";
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (points[i].getX() == points[j].getX()) {
                    if (points[i].getY() == points[j].getY() && j != i) {
                        return "At least two coinciding points.\n";
                    }
                }
            }
            for (int j = 0; j < points.length - 1; j++) {
                if (points[i].isOnLineSegment(points[j], points[j + 1])) {
                    return "At least one vertex lies on an other edge.\n";
                }
            }
            if (points[i].isOnLineSegment(points[0], points[points.length - 1])) {
                return "At least one vertex lies on an other edge.\n";
            }
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = 0; j < points.length - 1; j++) {
                if (IntPoint.lineSegmentsIntersect(points[i], points[i + 1], points[j], points[j + 1])) {
                    return "Intersecting Edges.\n";
                }
            }
            if (IntPoint.lineSegmentsIntersect(points[i], points[i + 1], points[0], points[points.length - 1])) {
                return "Intersecting Edges.\n";
            }
        }
        return null;
    }
}
