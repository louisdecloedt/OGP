package drawit;

import java.util.Arrays;

public class PointArrays {
	
	//copy(IntPoint[])
	static public IntPoint[] copy(IntPoint[] points) {
		return points; //pass by value
	}

	//insert(IntPoint[], integer index, IntPoint) 
	static IntPoint[] insert(IntPoint[] points, int index, IntPoint point) {
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
	static IntPoint[] update(IntPoint[] points, int index, IntPoint value) {
		IntPoint[] result = new IntPoint[points.length];
		result[index] = value;
		return result;
	}
	
	//remove
	static IntPoint[] remove(IntPoint[] points, int index) {
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
	
	//checkDefinesProperPolygon(IntPoint[]) - Static method in class drawit.PointArrays
	//static String checkDefinesProperPolygon(IntPoint[] points) {
		
		
	//	return result;
	//}
}
