package drawit;

public class RoundedPolygon {
	
	private int radius;
	private IntPoint[] vertices;

	//constructor: RoundedPolygon()
	public RoundedPolygon() {
		this.radius = 0;
		this.vertices = new IntPoint[0];
	}
	
	//setRadius(integer)
	/**
     * Initializes this object with the given coordinates.
     * 
     * @mutates | this
     * 
     * @throws IllegalArgumentException if the given radius is negative
     *
     * @post This object's radius equals the given radius
     *    | getRadius() == radius
     *
     */
	public void setRadius(int radius) throws IllegalArgumentException {
		if (radius < 0) {
			throw new IllegalArgumentException();
		}else {
			this.radius = radius;
		}
	}
	
	//setVertices(IntPoint[])
	/**
	 * 
	 * @throws IllegalArgumentException if the given vertices do not form a proper polygon
	 * 
	 * @post the vertices of the rounded polygon are now equal to the given array of points
	 */
	public void setVertices(IntPoint[] points) throws IllegalArgumentException {
		if (PointArrays.checkDefinesProperPolygon(points) != null) {
			throw new IllegalArgumentException();
		}else {
			this.vertices = points;
		}
	}
	
	//getRadius()
	//returns the radius of the given object
	public int getRadius() {
		return this.radius;
	}
	
	//getVertices()
	//returns the vertices of the given polygon
	public IntPoint[] getVertices() {
		return PointArrays.copy(this.vertices);
	}
	
	//contains(intPoint)
	/**
	 * 
	 * @pre the given array of the points must define a proper polygon
	 * 
	 * @return This returns true if the given IntPoint is contained within or on the boundaries of a given array of points which comprise a polygon,
	 * this function will return true if the given point is thus equal to a vertex on the polygon or on the edge of this polygon
	 */
	public Boolean contains(IntPoint point) {
		//point at a vertex
		for (int i = 0; i < this.vertices.length; i++) {
			if (this.vertices[i].equals(point)) {
				return true;
			}
		}
		//point at an edge
		for (int i = 0; i < this.vertices.length - 1; i++) {
			if (point.isOnLineSegment(this.vertices[i], this.vertices[i+1])) {
				return true;
			}
		}
		if (point.isOnLineSegment(this.vertices[0], this.vertices[this.vertices.length-1])) {
			return true;
		}
		int largeNumber = 100000;
		IntPoint exitPoint = new IntPoint(largeNumber,point.getY());
		int numberOfLineIntersections = 0;
		int numberOfVerticesInPath = 0;
		for (int i = 0; i < this.vertices.length - 1; i ++) {
			if (this.vertices[i].isOnLineSegment(point, exitPoint)) {
				numberOfVerticesInPath++;
			}
		}
		//if (numberOfVerticesInPath == 2) {return false;}
		for (int x = 0; x < (largeNumber - point.getX()); x ++) {
			for (int i = 0; i < this.vertices.length - 1; i ++) {
				for (int j = 0; j < this.vertices.length - 1; j ++) {
					IntPoint currentPoint = new IntPoint(x, point.getY());
					if (currentPoint.isOnLineSegment(this.vertices[i], this.vertices[j]) && i != j) {
						numberOfLineIntersections++;
					}
				}
			}
		}
		if (numberOfLineIntersections + numberOfVerticesInPath == 1) {return true;} else {return false;}
	}	
	
	
		public Boolean containsA(IntPoint point) {
			if (PointArrays.in(this.vertices, point)) { return true; }
			for (int i=0; i < this.vertices.length-1; i++) {
				if (point.isOnLineSegment(this.vertices[i], this.vertices[i+1])) {
					return true;}
			}
			if (point.isOnLineSegment(this.vertices[this.vertices.length-1], this.vertices[0])) {
				return true;}
			int largeNumber = 100000;
			IntPoint exitPoint = new IntPoint(largeNumber,point.getY());
			int countIntersections = 0;
			IntVector exitVector = exitPoint.minus(point);
			for (int i=0; i < this.vertices.length-1; i++) {
				if (IntPoint.lineSegmentsIntersect(point, exitPoint, this.vertices[i], this.vertices[i+1])) {
					countIntersections++;
				}
				IntVector vectorB = this.vertices[i+1].minus(this.vertices[i]);
				if (!this.vertices[i+1].isOnLineSegment(point, exitPoint) &&
						this.vertices[i].isOnLineSegment(point, exitPoint) &&
						!vectorB.isCollinearWith(exitVector))
				{ countIntersections++; }
			}
			if (IntPoint.lineSegmentsIntersect(point, exitPoint, this.vertices[this.vertices.length-1], this.vertices[0])) {
				countIntersections++; }
			IntVector vectorC = this.vertices[0].minus(this.vertices[this.vertices.length-1]);
			if (!this.vertices[0].isOnLineSegment(point, exitPoint) &&
					this.vertices[this.vertices.length-1].isOnLineSegment(point, exitPoint) 
					&& !vectorC.isCollinearWith(exitVector)) {countIntersections++;}
			return (countIntersections%2 != 0 && countIntersections > 0);
		}
		
		

		
		public static Boolean onSegment(IntPoint p, IntPoint q, IntPoint r) {
			if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
	                && q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) {
				return true;
			}
	        return false;
		}
		
		public static int orientation(IntPoint p, IntPoint q, IntPoint r)
	    {
	        int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
	        if (val == 0)
	            return 0;
	        return (val > 0) ? 1 : 2;
	    }
	 
	    public static boolean doIntersect(IntPoint p1, IntPoint q1, IntPoint p2, IntPoint q2)
	    {
	 
	        int o1 = orientation(p1, q1, p2);
	        int o2 = orientation(p1, q1, q2);
	        int o3 = orientation(p2, q2, p1);
	        int o4 = orientation(p2, q2, q1);
	        if (o1 != o2 && o3 != o4)
	            return true;
	        if (o1 == 0 && onSegment(p1, p2, q1))
	            return true;
	        if (o2 == 0 && onSegment(p1, q2, q1))
	            return true;
	        if (o3 == 0 && onSegment(p2, p1, q2))
	            return true;
	        if (o4 == 0 && onSegment(p2, q1, q2))
	            return true;
	        return false;
	    }
	 
		
		public Boolean contains35(IntPoint p) {
			int INF = 10000;
			int n = this.vertices.length;
			IntPoint[] points = PointArrays.copy(this.vertices);
			IntPoint extreme = new IntPoint(INF, p.getY());
	        int count = 0, i = 0;
	        do
	        {
	            int next = (i + 1) % n;
	            if (doIntersect(points[i], points[next], p, extreme))
	            {
	                if (orientation(points[i], p, points[next]) == 0)
	                    return onSegment(points[i], p, points[next]);
	 
	                count++;
	            }
	            i = next;
	        } while (i != 0);
	 
	        return (count & 1) == 1 ? true : false;
	    }
		
		public boolean contains3(IntPoint point) {
			boolean pHacks = false;
			IntPoint oldPoint = this.vertices[this.vertices.length-1];
			for (int i = 0; i < this.vertices.length ; i++) {
	
				IntPoint top = oldPoint.getY() < this.vertices[i].getY() ? this.vertices[i] : oldPoint;
		        IntPoint bottom = oldPoint.getY() < this.vertices[i].getY() ? oldPoint : this.vertices[i];
		        
				if (bottom.getY() <= point.getY() && top.getY() >= point.getY()) {
					int dx          = top.getX() - bottom.getX();
			        int dy          = top.getY() - bottom.getY();
			        int signedDist = dy * point.getX() - dx * point.getY() +
			                              top.getX() * bottom.getY() - bottom.getX() * top.getY();
			        
		            if (signedDist == 0) {
		            	
		            	IntPoint xTop = oldPoint.getX() < this.vertices[i].getX() ? this.vertices[i] : oldPoint;
			            IntPoint xBottom = oldPoint.getX() < this.vertices[i].getX() ? oldPoint : this.vertices[i];
			            
		            	if ((point.getY() >= bottom.getY() && point.getY() <= top.getY()) &&
				                 (point.getX() >= xBottom.getX() && point.getX() <= xTop.getX())) {
				                  return true; }
		            }
		           
		            if (signedDist >= 0) {
			                // Count corners only once
			             if (point.getY() != oldPoint.getY()) {
			                 pHacks = !pHacks;}
					}
				}
				oldPoint = this.vertices[i];
			}return pHacks;
		}
	 
//		public Boolean contains4(IntPoint point) {
//			boolean pHacks = false;
//			int largeNumber = 100000;
//			IntPoint exitPoint = new IntPoint(largeNumber, point.getY());
//			for (int i = 0; i < this.vertices.length-1; i++) {
//				IntPoint max_y_pt = this.vertices[i].getY() < this.vertices[i+1].getY() ? this.vertices[i+1] : this.vertices[i+1];
//		        IntPoint min_y_pt = this.vertices[i].getY() < this.vertices[i+1].getY() ? this.vertices[i] : this.vertices[i+1];
//				if (IntPoint.lineSegmentsIntersect(exitPoint, point, this.vertices[i], this.vertices[i+1])) {
//					int signedDist = (point.getY() - this.vertices[i].getY())*(this.vertices[i+1].getX()-point.getX())
//					- (point.getX() - this.vertices[i].getX())*(this.vertices[i+1].getX()-point.getY());
//					if (signedDist ==0) {
//						if (point.isOnLineSegment(this.vertices[i], this.vertices[i+1])) {
//							return true;
//						}
//					}
//					if (signedDist >= 0) {
//						if (point.equals(oldPoint)) {
//							pHacks = !pHacks;
//						}
//					}
//				}
//			}
//		}
	
	/*public String getDrawingCommands() {
		String result = "";
		if (this.vertices.length < 3) {return result;}
		for (int i = 0; i < this.vertices.length - 2; i ++) {
			double Rico = (this.vertices[i+1].getY() - this.vertices[i].getY())/(this.vertices[i+1].getX() - this.vertices[i].getX());
			double Alpha = -1/Rico;
			//KLOPT NOG NIET
		}
		return result;
	}*/
	
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
	/**
	 * @mutates this
	 * 
	 * @post the given IntPoint is now a vertex in the given polygon
	 */
	public void insert(int index, IntPoint point) {
		this.vertices = PointArrays.insert(this.vertices, index, point);
	}
	
	
	//remove(IntPoint[], integer)
	/**
	 * @mutates this
	 * 
	 * @post the point in the polygon at the given index has now been removed from that polygon
	 */
	public void remove(int index) {
		this.vertices = PointArrays.remove(this.vertices, index);
	}
	
	
	//update(integer, IntPoint)
	public void update(int index, IntPoint point) {
		this.vertices = PointArrays.update(this.vertices, index, point);
	}
}
