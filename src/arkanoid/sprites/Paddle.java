//ID 209207364

package arkanoid.sprites;

import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import arkanoid.collision.Collidable;
import arkanoid.level.GameLevel;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**.
 * The class defines a paddled that is controlled by the keyboard
 */
public class Paddle implements Sprite, Collidable {

    private int speed;
    private int width;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;

    /**.
     * constructor
     * @param rectangle the "shape" of the paddle
     * @param color the color of the paddle
     * @param keyboard the keyboard sensor
     * @param speed the speed of the paddle
     */
    public Paddle(Rectangle rectangle, Color color, KeyboardSensor keyboard, int speed) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = speed;
        }

    /**.
     * method return the speed of the paddle
     * @return the speed of the paddle
     */
    public int getSpeed() {
        return this.speed;
    }

    /**.
     * Method move the paddle to the left
     */
    public void moveLeft() {
        // if there is no left for the paddle then return
        if (this.rectangle.getUpperLeft().getX() < GameLevel.WIDTH_EDGE_FRAME) {
            return;
        }
        // new x - 3
        double newX = this.rectangle.getUpperLeft().getX() - this.speed;
        double y = this.rectangle.getUpperLeft().getY();
        // creating a new rectangle with x value - 3
        this.rectangle = new Rectangle(new Point(newX, y), this.rectangle.getWidth(),
                this.rectangle.getHeight());
    }

    /**.
     * Method move the paddle to the right
     */
    public void moveRight() {
        // if there is no right for the paddle to move
        if (this.rectangle.getUpperLine().end().getX() > GameLevel.WIDTH - GameLevel.WIDTH_EDGE_FRAME) {
            return;
        }
        double newX = this.rectangle.getUpperLeft().getX() + this.speed;
        double y = this.rectangle.getUpperLeft().getY();
        this.rectangle = new Rectangle(new Point(newX, y), this.rectangle.getWidth(),
                this.rectangle.getHeight());
    }

    /**.
     * if the user pressed left then method move the paddle to the left
     * and if pressed right then moves to the right
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**.
     * Method draws the paddle on the drawsurface
     * @param d the drawsurface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        d.fillRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**.
     * Method return the rectangle of the paddle
     * @return the rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**.
     * method divie the upper line of the paddle to 5 equals regions
     * and return an integer that indicates the number of the region that the point on
     * @param point a point that is on the paddle
     * @return int (0-5) that indicates the number of the region that the point on
     */
    private int getRegion(Point point) {
        // if the point is not on the upper line
        if (!point.isOnLine(this.rectangle.getUpperLine())) {
            return 0;
        }
        // divide the length of the upper line to 5
        double lengthOfRegion = this.rectangle.getUpperLine().length() / 5;
        // every condtion checks the distance between the point to the upperleft point
        if (point.distance(this.rectangle.getUpperLeft()) <= lengthOfRegion) {
            return 1;
        } else if (point.distance(this.rectangle.getUpperLeft()) <= 2 * lengthOfRegion) {
            return 2;
        } else if (point.distance(this.rectangle.getUpperLeft()) <= 3 * lengthOfRegion) {
            return 3;
        } else if (point.distance(this.rectangle.getUpperLeft()) <= 4 * lengthOfRegion) {
            return 4;
        }
        return 5;
    }

    /**.
     * Method return the new velocity based on the collsionPoint and the currentVelocity
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity of the object
     * @param hitter the ball that hits the paddle
     * @return the new velocity based on the region of the point in the paddle
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // gets the speed of the velocity
        double velSpeed = currentVelocity.getSpeed();
        // gets the region of the point
        int region = this.getRegion(collisionPoint);
        // if the point is not on the upper line
        if (region == 0) {
            return new Velocity(-dx, dy);
        } else if (region == 1) {
            return Velocity.fromAngleAndSpeed(300, velSpeed);
        } else if (region == 2) {
            return Velocity.fromAngleAndSpeed(330, velSpeed);
        } else if (region == 3) {
            return new Velocity(dx, -dy);
        } else if (region == 4) {
            return Velocity.fromAngleAndSpeed(30, velSpeed);
        }
        return Velocity.fromAngleAndSpeed(60, velSpeed);
    }

    /**.
     * Method adds the paddle to the game
     * @param g the game to add the paddle into
     */
    public void addToGame(GameLevel g) {
        Collidable collidablePaddle = this;
        Sprite spritePaddle = this;
        g.addCollidable(collidablePaddle);
        g.addSprite(spritePaddle);
    }
}