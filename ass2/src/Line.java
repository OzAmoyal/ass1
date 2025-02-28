/**
 * @author ozamoyal
 */
public class Line {
    private Point start;
    private Point end;
    private Double slope;
    private Double b;
    // constructors
    /**
     * constructor for a line with two points objects.
     * @param start start point of the line
     * @param end end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        //if it's a vertical line
        if (this.start.getX() == this.end.getX()) {
            this.slope = null;
            return;
    }
    //calculate the slope using the slope formula y2-y1/x2-x1
        this.slope = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        //calculate the b of the line equation y=ax+b
        this.b = start.getY() - (this.slope * start.getX());
     }
     /**
      * line constructor using two pairs of doubles repressiting the coordinates of the points.
      * @param x1 first x coordinate
      * @param y1 first y coordintate
      * @param x2 second x coordinate
      * @param y2 second y coordinate
      */
    public Line(double x1, double y1, double x2, double y2) {
        //create two points and call Point objects constructor
        this(new Point(x1, y1), new Point(x2, y2));
     }
    /**
     * getter for x value of the start point of the line.
     * @return x value of start.
     * */
    public double getStartX() {
        return start.getX();
    }
    /**
     * getter for y value of the start point of the line.
     * @return y value of start.
     * */
    public double getStartY() {
        return start.getY();
    }
    /**
     * getter for x value of the end point of the line.
     * @return x value of end.
     * */
    public double getEndX() {
        return end.getX();
    }
    /**
     * getter for y value of the end point of the line.
     * @return y value of start.
     * */
    public double getEndY() {
        return end.getY();
    }

    /**
     * calculate the distance between two points with the distance function from Point class.
     * @return the length of the line
     * */
    public double length() {
        return start.distance(end);
     }

    /**
     * return a point object of the middle of the line.
     * @return middle the middle point of the line
     */
    public Point middle() {
        Point middle = new Point(((start.getX() + end.getX()) / 2), ((start.getY() + end.getY()) / 2));
        return middle;
     }

    /**
     * getter for the start point of the line.
     * @return the start point object
     * */
    public Point start() {
        return this.start;
     }

    /**
     * getter for the end point of the line.
     * @return the end point object
     */
    public Point end() {
        return this.end;
     }

    /**
     * checks if two lines object intersect with each other.
     * @param other - the other line to check if intersecting
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //if the slopes are null its a vertical line
        if (this.slope != null && other.slope != null) {
            //if the lines are Parallel therfore never intersect /same line
            if (this.slope.equals(other.slope)) {
                return false;
        }
        //using a temp variables to calculate if an intersection is happening
        //the method is just like in y1 = ax+b, y2=cx+d you calculate the a-c and b-d and divide.
        Double tempSlope = this.slope - other.slope;
        Double tempB = other.b - this.b;
        Double tempX = tempB / tempSlope;
        //if the intersection point is located in the bounds of the start and end point
        if ((this.start.getX() <= tempX && tempX.doubleValue() <= this.end.getX())
        || (this.end.getX() <= tempX.doubleValue() && tempX.doubleValue() <= this.start.getX())) {
            return true;
        }

    }
    //if the first line is vertical
if (this.slope == null) {
    //calculate the intersection Point of the vertical and unvertical line
    double intersectionY = other.slope * this.start.getX() + other.b;
    double intersectionX = this.start.getX();
    //if the intersection point is located between the lines start and end point
    if (((this.start.getY() <= intersectionY && intersectionY <= this.end.getY())
    || this.end.getY() <= intersectionY && intersectionY <= this.start.getY())
    && (other.start.getX() <= intersectionX && intersectionX <= other.end.getX()
    || other.end.getX() <= intersectionX && intersectionX <= other.start.getX())) {
    return true;
    }
}
//if the second line is vertical
if (other.slope == null) {
    //calculate the intersection Point of the vertical and unvertical line
    double intersectionY = this.slope * other.start.getX() + this.b;
    double intersectionX = other.start.getX();
    //if the intersection point is located between the lines start and end point
    if (((other.start.getY() <= intersectionY && intersectionY <= other.end.getY())
    || other.end.getY() <= intersectionY && intersectionY <= other.start.getY())
    && (this.start.getX() <= intersectionX && intersectionX <= this.end.getX()
    || this.end.getX() <= intersectionX && intersectionX <= this.start.getX())) {
    return true;
    }
}
//otherwise return false
return false;

     }
    /**
     * the function checks if two lines intersect and if they do it returns the intersection point.
     * @param other other line
     * @return the intersection point if the lines intersectand null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!other.isIntersecting(this)) {
            return null;
        }
        //if the first line is vertical
        if (this.slope == null) {
        //for x take the start point of the vertical line (same as all x values on it.)
        //for y -multiplie the x value of the vertical line with the slope of the second and add b to the y coordinate.
          Point intersection = new Point(this.start.getX(), other.slope * this.start.getX() + other.b);
          return intersection;
        }
        //if the second line is vertical
        if (other.slope == null) {
        //for x take the start point of the vertical line (same as all x values on it.)
        //for y -multiplie the x value of the vertical line with the slope of the second and add b to the y coordinate.
          Point intersection = new Point(other.start.getX(), this.slope * other.start.getX() + this.b);
          return intersection;
        }
        //using a temp variables to calculate if an intersection is happening
        //the method is just like in y1 = ax+b, y2=cx+d you calculate the a-c and b-d and divide.
        Double tempSlope = this.slope - other.slope;
        Double tempB = other.b - this.b;
        Double tempX = tempB / tempSlope;
        //take the intersection x value and put in any of the line functions
        Point intersection = new Point(tempX, this.slope * tempX + this.b);
        return intersection;
     }


 }