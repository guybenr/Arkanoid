//ID 209207364

package arkanoid.shapes;

import java.util.ArrayList;
import java.util.List;

/**.
 * Class defines a rectangle and support
 * methods like get and intersection pooints of the rectangle
 * with other lines
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line upperLine;
    private Line downerLine;
    private Line leftLine;
    private Line rightLine;

    /**.
     * Create a new rectangle with location and width/height
     * and the 4 lines that define the rectangle
     * @param upperLeft the upper left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */

    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        // creates all the 4 lines that define the rectangle
        this.upperLine = new Line(upperLeft.getX(), upperLeft.getY(),
                      upperLeft.getX() + width, upperLeft.getY());
        this.downerLine = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.leftLine = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        this.rightLine = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperLeft = upperLeft;
    }

    /**.
     * Return the width of the rectangle
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**.
     * Return the hegiht f the rectangle
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**.
     * Return the upper left point of the rectangle
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**.
     * return the upper line of the rectangle
     * @return the upper line of the rectangle
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**.
     * return the downer line of the rectangle
     * @return the downer line of the rectangle
     */
    public Line getDownerLine() {
        return this.downerLine;
    }

    /**.
     * return the left line of the rectangle
     * @return the left line of the rectangle
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**.
     * return the right line of the rectangle
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**.
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line line that defined by start and end points
     * @return ArrayList of all of the intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<Point>();
        // for every line of the rectangle if there is an intersection then add it to the array
        if (line.isIntersecting(this.upperLine)) {
            intersectionPoints.add(line.intersectionWith(this.upperLine));
        }
        if (line.isIntersecting(this.downerLine)) {
            intersectionPoints.add(line.intersectionWith(this.downerLine));
        }
        if (line.isIntersecting(this.leftLine)) {
            intersectionPoints.add(line.intersectionWith(this.leftLine));
        }
        if (line.isIntersecting(this.rightLine)) {
            intersectionPoints.add(line.intersectionWith(this.rightLine));
        }
        return intersectionPoints;
    }
}