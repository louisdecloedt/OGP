package drawit;

//import java.util.Arrays;

public class PointArrays {
	
	//copy(IntPoint[])
	public static IntPoint[] copy(IntPoint[] points) {
		return points; //pass by value
	}

	//insert(IntPoint[], integer index, IntPoint) 
	public static IntPoint[] insert(IntPoint[] points, int index, IntPoint point) {
		IntPoint[] result = new IntPoint[points.length+1];
		for (int i = 0; i < index; i++) {
			result[i] = points[i];
			}
		result[index] = point; //double for loop for performance
		for (int i = index + 1; i < points.length; i++) {
			result[i] = points[i-1];
			}
		return result;
	}
	
	//update(IntPoint[], int, IntPoint)
	public static IntPoint[] update(IntPoint[] points, int index, IntPoint value) {
		IntPoint[] result = new IntPoint[points.length];
		result[index] = value;
		return result;
	}
	
	//remove
	public static IntPoint[] remove(IntPoint[] points, int index) {
		IntPoint[] result = new IntPoint[points.length-1];
		for (int i = 0; i < index; i++) {
			result[i] = points[i];
			}
		//double for loop for performance <-> a lot of repetitive if-statements
		for (int i = index + 1; i < points.length; i++) {
			result[i-1] = points[i];
			}
		return result;
	}
	
	//extra function to check if given point is in a given array
	//this exists for lists
	public static Boolean in(IntPoint[] points, IntPoint point) {
		for (int i = 0; i < points.length; i++) {
			if (point.getX() == points[i].getX()) {
				if( point.getY() == points[i].getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	//checkDefinesProperPolygon(IntPoint[]) - Static method in class drawit.PointArrays
	public static String checkDefinesProperPolygon(IntPoint[] points) {
		if (points.length < 3) {
			return "Not enough points to define a proper polygon.\n";
		}
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				//check for coinciding vertices
				if (points[i].getX() == points[j].getX()) {
					if (points[i].getY() == points[j].getY() && j != i) {
						return "At least two coinciding points, thus this sequence does not define a proper polygon. \n";
					}
				}
			}
			//check if one of the vertices lies on an edge
			for (int j = 0; j < points.length - 1; j++) {
				if (points[i].isOnLineSegment(points[j], points[j+1])){
					return "At least one vertex lies on an open line segment, defined by two other vertices. \n "
							+ "So this sequence of points does not define a proper polygon.\n";
				}
				
			}
			if (points[i].isOnLineSegment(points[0], points[points.length - 1])) {
				return "At least one vertex lies on an open line segment, defined by two other vertices. \n "
						+ "So this sequence of points does not define a proper polygon.\n";
			}
			//all this can compacter: add if statement for j = points.length - 1
		}
		//check for crossing edges
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = 0; j < points.length - 1; j++) {
				if (IntPoint.lineSegmentsIntersect(points[i], points[i+1], points[j], points[j+1])) {
					return "Intersecting Edges";
				}
			}
			if (IntPoint.lineSegmentsIntersect(points[i], points[i+1], points[0], points[points.length-1])) {
				return "At least one vertex lies on an open line segment, defined by two other vertices. \n "
						+ "So this sequence of points does not define a proper polygon.\n";
			}
		}//are all cases covered for the check of crossing edges? 
		
		return null;
	}
	
	
	
	
	
	
	
}
