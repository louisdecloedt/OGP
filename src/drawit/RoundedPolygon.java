package drawit;

public class RoundedPolygon {

    private int radius;
    private IntPoint[] vertices;

    //constructor: RoundedPolygon()
    public RoundedPolygon() {
        this.radius = 10;
        this.vertices = new IntPoint[4];
        IntPoint point0 = new IntPoint(0, 0);
        IntPoint point1 = new IntPoint(100, 0);
        IntPoint point2 = new IntPoint(100, 100);
        IntPoint point3 = new IntPoint(0, 100);
        this.vertices[0] = point0;
        this.vertices[1] = point1;
        this.vertices[2] = point2;
        this.vertices[3] = point3;
    }

    //setRadius(integer)

    /**
     * Initializes this object with the given coordinates.
     *
     * @throws IllegalArgumentException if the given radius is negative
     * @mutates | this
     * @post This object's radius equals the given radius
     * | getRadius() == radius
     */
    public void setRadius(int radius) throws IllegalArgumentException {
        if (radius < 0) {
            throw new IllegalArgumentException();
        } else {
            this.radius = radius;
        }
    }

    //setVertices(IntPoint[])

    /**
     * @throws IllegalArgumentException if the given vertices do not form a proper polygon
     * @post the vertices of the rounded polygon are now equal to the given array of points
     */
    public void setVertices(IntPoint[] points) throws IllegalArgumentException {
        if (PointArrays.checkDefinesProperPolygon(points) != null) {
            throw new IllegalArgumentException();
        } else {
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
     * @return This returns true if the given IntPoint is contained within or on the boundaries of a given array of points which comprise a polygon,
     * this function will return true if the given point is thus equal to a vertex on the polygon or on the edge of this polygon
     * @pre the given array of the points must define a proper polygon
     */


    public Boolean onEdgePolygon(IntPoint point) {
        if (PointArrays.in(this.vertices, point)) {
            return true;
        }
        for (int i = 0; i < this.vertices.length - 1; i++) {
            if (point.isOnLineSegment(this.vertices[i], this.vertices[i + 1])) {
                return true;
            }
        }
        if (point.isOnLineSegment(this.vertices[this.vertices.length - 1], this.vertices[0])) {
            return true;
        }
        return false;
    }

    public Boolean contains(IntPoint point) {
        if (this.onEdgePolygon(point)) {
            return true;
        }
        boolean pHacks = false;
        int largeNumber = 100000, firstVertex = -1, lastVertex = -1;
        ;
        IntPoint exitPoint = new IntPoint(largeNumber, point.getY());
        for (int i = 0; i < this.vertices.length; i++) {
            if (!this.vertices[i].isOnLineSegment(point, exitPoint)) {
                if (firstVertex == -1) {
                    firstVertex = i;
                }
                lastVertex = i;
            }
        }
        if (firstVertex == -1 || firstVertex == lastVertex) {
            return false;
        }
        if (IntPoint.lineSegmentsIntersect(this.vertices[lastVertex], this.vertices[firstVertex], point, exitPoint)) {
            pHacks = !pHacks;
        }
        lastVertex = firstVertex;
        for (int i = firstVertex + 1; i < this.vertices.length - 1; i++) {
            if (!this.vertices[i].isOnLineSegment(point, exitPoint)) {
                firstVertex = i;
                if (IntPoint.lineSegmentsIntersect(point, exitPoint, this.vertices[firstVertex], this.vertices[lastVertex])) {
                    pHacks = !pHacks;
                }
                lastVertex = firstVertex;
            }
        }
        return pHacks;
    }


    //getDrawingCommands()

    public String getDrawingCommands() {
        String result = "";
        if (this.vertices.length < 3) {
            return result;
        }
        for (int i = 0; i < this.vertices.length; i++) {
            int a = i - 1, b = i, c = i + 1;
            if (i == 0) {
                a = this.vertices.length - 1;
            }
            if (i == this.vertices.length - 1) {
                c = 0;
            }
            IntVector vectorAB = this.vertices[b].minus(this.vertices[a]);
            IntVector vectorBC = this.vertices[c].minus(this.vertices[b]);
            if (vectorAB.isCollinearWith(vectorBC)) {
                result += "line ";
                result += Integer.toString(this.vertices[b].getX() - (vectorAB.getX() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getY() - (vectorAB.getY() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getX()) + " ";
                result += Integer.toString(this.vertices[b].getY()) + " ";
                result += "\n";
                result += "line ";
                result += Integer.toString(this.vertices[b].getX()) + " ";
                result += Integer.toString(this.vertices[b].getY()) + " ";
                result += Integer.toString(this.vertices[b].getX() + (vectorBC.getX() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getY() + (vectorBC.getY() / 2)) + " ";
                result += "\n";
            } else {
                DoubleVector doubleBA = vectorAB.asDoubleVector().scale(-1);
                DoubleVector doubleBC = vectorBC.asDoubleVector();
                DoubleVector BAU = doubleBA.scale(1 / doubleBA.getSize());
                DoubleVector BCU = doubleBC.scale(1 / doubleBC.getSize());
                DoubleVector BS = BAU.plus(BCU);
                DoubleVector BSU = BS.scale(1 / BS.getSize());
                double BAUCutOff = BAU.crossProduct(BSU);
                double unitRadius = Math.abs(BAU.crossProduct(BSU));
                double maxCutOff = Math.min(doubleBA.getSize() / 2, doubleBC.getSize() / 2);
                double minimumScale = Math.min(this.radius / unitRadius, maxCutOff / BAUCutOff);
                DoubleVector BSReal = BSU.scale(minimumScale);
                DoublePoint doubleB = new DoublePoint(this.vertices[b].getX(), this.vertices[b].getY());
                DoublePoint actualCenter = doubleB.plus(BSReal);
                IntPoint actualCenterInt = actualCenter.round();
                DoublePoint ABEnd = doubleB.plus(BAU.scale(minimumScale * BAUCutOff));
                DoublePoint BCBegin = doubleB.plus(BCU.scale(minimumScale * BAUCutOff));
                IntPoint ABEnd2 = ABEnd.round();
                IntPoint BCBegin2 = BCBegin.round();

                Double startAngle = ABEnd.minus(actualCenter).asAngle();
                Double endAngle = BCBegin.minus(actualCenter).asAngle();
                Double extentAngle = endAngle - startAngle;

                IntVector vecAB = this.vertices[b].minus(this.vertices[a]);
                IntVector vecBC = this.vertices[c].minus(this.vertices[b]);
                result += "line ";
                result += Integer.toString(this.vertices[b].getX() - (vecAB.getX() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getY() - (vecAB.getY() / 2)) + " ";
                result += Integer.toString(ABEnd2.getX()) + " ";
                result += Integer.toString(ABEnd2.getY()) + " ";
                result += "\n";
                result += "arc ";
                result += Integer.toString(actualCenterInt.getX()) + " ";
                result += Integer.toString(actualCenterInt.getY()) + " ";
                result += Integer.toString(Math.toIntExact(Math.round(unitRadius * minimumScale))) + " ";
                result += Double.toString(startAngle) + " ";
                result += Double.toString(extentAngle) + " ";
                result += "line ";
                result += Integer.toString(BCBegin2.getX()) + " ";
                result += Integer.toString(BCBegin2.getY()) + " ";
                result += Integer.toString(this.vertices[b].getX() + (vecBC.getX() / 2)) + " ";
                result += Integer.toString(this.vertices[b].getY() + (vecBC.getY() / 2)) + " ";
                result += "\n";
            }
        }
        return result;
    }


    //insert(integer, intPoint)

    /**
     * @mutates this
     * @post the given IntPoint is now a vertex in the given polygon
     */
    public void insert(int index, IntPoint point) {
        this.vertices = PointArrays.insert(this.vertices, index, point);
    }


    //remove(IntPoint[], integer)

    /**
     * @mutates this
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
