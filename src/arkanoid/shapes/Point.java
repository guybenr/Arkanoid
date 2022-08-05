//ID 209207364

package arkanoid.shapes;


/**
 * A class of point with two double fields, x and y.
 * class supports basic operation - distance, getX, getY and equals.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     * @param x double
     * @param y double
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method calculating the distance between the two Points.
     * @param other An initialized Point.
     * @return double distance - the distance between the two points ( > 0).
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**.
     * This method retrun true if the two points equals and false otherwise
     * @param other An initialized Point.
     * @return true if the points equals and false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = Math.pow(10, -3);
        return ((Math.abs(this.x - other.x) < epsilon)  && (Math.abs(this.y - other.y) < epsilon));
    }

    /**
     * This method return the x value of the Point.
     * @return double - the x value of the Point
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method return the y value of the Point.
     * @return double - the y value of the Point
     */
    public double getY() {
        return this.y;
    }

    /**.
     * Method checks if point is on the line
     * @param line Line
     * @return boolean - true if the point is in the line and false otherwise.
     */
    public boolean isInLine(Line line) {
        double disStartToPoint = line.start().distance(this);
        double disEndToPoint = line.end().distance(this);
        double distanceLine = line.start().distance(line.end());
        double epsilon = Math.pow(10, -4);
        if (Math.abs((disStartToPoint + disEndToPoint) - distanceLine) < epsilon) {
            return true;
        }
        return false;
    }

    /**.
     * Method checks if the the point is on the line
     * @param line Line
     * @return true if the point is on the line, false otherwise
     */
    public boolean isOnLine(Line line) {
        // if the x,y values of the points are between start and end of the line
        if (!this.isInLine(line)) {
            return false;
        }
        // if the line is vertical
        if (line.isVertical()) {
            return true;
        }
        // creating a new point with x and y value based on the equation of the line
        double yValue = line.getSlope() * this.x + line.getIntercept();
        Point p = new Point(this.x, yValue);
        // if both line equals return true and false otherwise
        return this.equals(p);
    }

}