//ID 209207364

package arkanoid.sprites;
import arkanoid.shapes.Point;


/**
 *  Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**.
     * constructor
     * @param dx double
     * @param dy double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**.
     * Method return the dx value of the Velocity
     * @return double - dx value of the Velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**.
     * Method return the dy value of the Velocity
     * @return double - dy value of the Velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**.
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     * @param p Point with position (x,y)
     * @return Point with postion (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newPoint;
    }

    /**.
     * method gets an angle and speed and return a velocity
     * with dx and dy values based on the angle and the speed
     * @param angle double
     * @param speed double
     * @return Velocity with dx and dy that symbolize the speed and the angle
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // based on a calculation, stores the dx and dy values
        double dx = Math.round(speed * Math.sin(Math.toRadians(angle)));
        double dy = Math.round(speed * Math.sin(Math.toRadians(90 - angle)));
        return new Velocity(dx, -dy);
    }

    /**.
     * Method return the speed of the velocity
     * @return double, the speed of the velocity
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    /**.
     * return ture if this eauals to v
     * @param v the velocity to compare with
     * @return true if this eauals to v and false otherwise
     */
    public Boolean equals(Velocity v) {
        if (this.dx == v.getDx() && this.dy == v.getDy()) {
            return true;
        }
        return false;
    }
}