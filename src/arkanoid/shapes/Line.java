//ID 209207364

package arkanoid.shapes;
import java.util.List;


/**.
 * A Line based on two Points,
 * this class
 */
public class Line {
    private Point start;
    private Point end;
    // this is the slope (m) of the equation y = mx + b, if the line is vertical then slope stores the line x value
    private double slope;
    // this is the intercept (b) of the equation y = mx + b, if the line is vertical, the intercept is null
    private double intercept;
    private boolean isVertical;

    /**
     * constructor using two Points.
     * @param p1 An initialized Point
     * @param p2 An initialized Point
     */
    public Line(Point p1, Point p2) {
        this.start = new Point(p1.getX(), p1.getY());
        this.end = new Point(p2.getX(), p2.getY());
        // if the line is vertical
        if (p2.getX() == p1.getX()) {
            this.slope = p1.getX();
            this.intercept = 0;
            this.isVertical = true;
            return;
        }
        // calculating the slope
        this.slope = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        // calculating the intercept based on the p1 Point
        this.intercept = p1.getY() - this.slope * p1.getX();
        this.isVertical = false;
    }

    /**
     * this constructor  using 4 doubles in order to build two Points: start, end.
     * @param x1 double
     * @param y1 double
     * @param x2 double
     * @param y2 double
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.start = p1;
        this.end = p2;
        // if the line is vertical
        if (p2.getX() == p1.getX()) {
            this.slope = p1.getX();
            this.intercept = 0;
            this.isVertical = true;
            return;
        }
        // calculating the slope
        this.slope = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        // calculating the intercept based on the p1 Point
        this.intercept = p1.getY() - this.slope * p1.getX();
        this.isVertical = false;
    }

    /**
     * This method return the length of the Line.
     * @return double - the length of the line.
     */
    public double length() {
        // return the distance between start and end (= length of the line)
        return this.start.distance(end);
    }

    /**
     * This middle return the middle Point of the line.
     * @return double - the middle Point of the line.
     */
    public Point middle() {
        // calculating the middle x value of the line
        double middleX = (this.start.getX() + this.end.getX()) / 2.0;
        // calculating the middle y value of the line
        double middleY = (this.start.getY() + this.end.getY()) / 2.0;
        // creating a point with the middle x and middle y values
        Point middle = new Point(middleX, middleY);
        return middle;
    }

    /**.
     * method return the start of the line
     * @return Point - the start of the line
     */
    public Point start() {
        // if the line is vertical, then Method return the point with the lower Y value
        if (this.isVertical && this.start.getY() < this.end.getY()) {
            return this.start;
        }
        if (this.isVertical && this.start.getY() >= this.end.getY()) {
            return this.end;
        }
        if (this.start.getX() < this.end.getX()) {
            return this.start;
        }
        return this.end;
    }

    /**.
     * return true if the line is vertical and false otherwise
     * @return true if the line is vertical and false otherwise
     */
    public boolean isVertical() {
        return this.isVertical;
    }
    /**.
     * method return the end of the Line
     * @return Point - the end of the Line
     */
    public Point end() {
        // if the line is vertical, then Method return the point with the higher Y value
        if (this.isVertical && this.start.getY() < this.end.getY()) {
            return this.end;
        }
        if (this.isVertical && this.start.getY() >= this.end.getY()) {
            return this.start;
        }
        if (this.start.getX() < this.end.getX()) {
            return this.end;
        }
        return this.start;
    }

    /**.
     * method return the slope of the Line
     * @return double - the slope of the Line
     */
    public double getSlope() {
        return this.slope;
    }

    /**.
     * method return the intercept of the Line
     * @return double - the intercept of the Line
     */
    public double getIntercept() {
        return this.intercept;
    }

    /**.
     * method is used in order to return the intersection
     * between a vertical and non-vertical Lines
     * @param other - non-vertical Line
     * @return the intersection Between the two lines
     */
    private Point verticalIntersection(Line other) {
            // the x value is the slope of the vertical line
            double x = this.slope;
            // the y value is based on the equation of a the line (y = mx + b)
            double y = other.slope * x + other.intercept;
            Point intersect = new Point(x, y);
            /*
            this condition checks if the x and y values of the intersection point
            are between the two Domain Lines.
             */
            if (intersect.isInLine(this) && intersect.isInLine(other)) {
                return intersect;
            }
            return null;
    }

    /**.
     * Method checks if two lines are intersecting
     * @param other An initialized Line.
     * @return true if the two lines intersecting and false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**.
     * Method checks if a line is a single point
     * @return true if the line is a point and false otherwise
     */
    private boolean isPoint() {
        // if end == start return true, else return false
        return this.end.equals(this.start);
    }

    /**.
     * Method gets two vertical Lines and return the intersection between them
     * @param line1 vertical line
     * @return Point, the intersection point between line1 and this.
     */
    private Point intersectionTwoVerticals(Line line1) {
        /*
        when lines are vertical, the slope value indicates
        the const x value of the line. therefore if the x values are different
        then there is no intersection point.
         */
        if (line1.slope != this.slope) {
            return null;
        }
        // if one of the lines are just a single point, then method checks if the point is on the line
        if (line1.isPoint() && line1.start().isInLine(this)) {
            return line1.start();
        }
        if (this.isPoint() && this.start().isInLine(line1)) {
            return this.start();
        }
        // if the start of one line is the end of another line.
        if (line1.start().equals(this.end())) {
            return line1.start();
        }
        if (line1.end().equals(this.start())) {
            return line1.end();
        }
        return null;
    }
    /**.
     * This method returns the intersection between two Lines
     * @param other - An initialized Line
     * @return Point if the two Lines has intersection and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // if both lines are vertical method return null
        if (this.isVertical && other.isVertical) {
            return intersectionTwoVerticals(other);
            // if one of the Lines is vertical
        } else if (this.isVertical && !other.isVertical) {
            return this.verticalIntersection(other);
        } else if (!this.isVertical && other.isVertical) {
            return other.verticalIntersection(this);
            // if both lines are parallel
        } else if (this.slope == other.slope) {
            // if the start of one ine is the end of another, it means the lines has one intersection point
            if (this.start().equals(other.end())) {
                return this.start();
            }
            if (this.end().equals(other.start())) {
                return this.end();
            }
            return null;
        }
        /*
        in this case both lines arent parallel, then they have an intersection.
        then the program stores the x and y values of their intersection
         */
        double x = (this.intercept - other.intercept) / (other.slope - this.slope);
        double y = this.slope * x + this.intercept;
        Point intersect = new Point(x, y);
        //checks if the intersection point between the domain lines
        if (intersect.isInLine(this) && intersect.isInLine(other)) {
            return intersect;
        }
        return null;
    }

    /**.
     * Method return the start of the trajectory
     * @return the start of the trajectory
     */
    public Point getStartTrajectory() {
        return this.start;
    }

    /**.
     * Method checks if both lines are equals
     * @param other An initialized Line
     * @return true if lines equals and false otherwise
     */
    public boolean equals(Line other) {
        return this.start().equals(other.start()) && this.end().equals(other.end());
    }

    /**
     *  Method return the closest intersection point with the rectangle
     *  to the start of the line.
     * @param rect rectangle
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int minIndex = 0;
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        double[] distances = new double[intersectionPoints.size()];
        // creating a distances array and initials it with the distances.
        for (int i = 0; i < distances.length; i++) {
            distances[i] = this.start.distance(intersectionPoints.get(i));
        }
        // gets the min distance's index
        for (int i = 0; i < distances.length; i++) {
            if (distances[minIndex] > distances[i]) {
                minIndex = i;
            }
        }
        return intersectionPoints.get(minIndex);
    }
}