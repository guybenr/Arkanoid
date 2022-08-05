// ID 209207364

package arkanoid.sprites;
import arkanoid.level.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;

import java.util.Random;

import arkanoid.shapes.Line;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import arkanoid.collections.GameEnvironment;
import arkanoid.collision.CollisionInfo;


/**
 * Class of Ball with 4 fields: Point center, int radius and color
 * the class supports basic functions like get and draw.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Velocity velocity;
    private java.awt.Color color;
    private int[] frame;
    private GameEnvironment gameEnvironment;

    /**.
     * constructor
     * @param x double - x value of the center
     * @param y double - y value of the center
     * @param radius int
     * @param color the color of the ball
     * @param game the game Environment
     */
    public Ball(double x, double y, int radius, java.awt.Color color, GameEnvironment game) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.frame = new int[4];
        this.gameEnvironment = game;
    }

    /**.
     * constructor
     * @param center Point - the center of the ball
     * @param radius int - the radius of the ball
     * @param color the color of the ball
     * @param game the game Environment
     */
    public Ball(Point center, int radius, java.awt.Color color, GameEnvironment game) {
        // creating the ball using the previous constructor
        this(center.getX(), center.getY(), radius, color, game);
    }

    /**.
     * constructor
     * @param center Point - the center of the ball
     * @param radius int - the radius of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        // creating the ball using the previous constructor
        this(center.getX(), center.getY(), radius, color, new GameEnvironment());
    }

    /**.
     * constructor
     * @param x the x value of the center of the ball
     * @param y the y value of the center of the ball
     * @param radius int - the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        // creating the ball using the previous constructor
        this(x, y, radius, color, new GameEnvironment());
    }

    /**.
     * constructor
     * @param center the center point of the ball
     * @param radius the size(radius) of the ball
     */
    public Ball(Point center, int radius) {
        this(center.getX(), center.getY(), radius, Color.GRAY, new GameEnvironment());
    }

    /**.
     * @return int - the x value of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**.
     * @return int - the y value of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return int - the radius of the ball
     */
    public int getSize() {
        return (int) this.radius;
    }

    /**
     * @return int - the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**.
     * sets the velocity of the ball
     * @param dx double
     * @param dy double
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**.
     * sets the velocity of the ball
     * @param v Velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**.
     * gets the velocity of the ball
     * @return Velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**.
     * gets the start frame of the ball
     * @return start frame of the ball
     */
    public int getStartFrame() {
        return this.frame[0];
    }

    /**.
     * gets the end frame of the ball
     * @return end frame of the ball
     */
    public int getEndFrame() {
        return this.frame[1];
    }

    /**.
     * Methos sets the ball's frame
     * @param frame1 4 positive intgeres indicates the frame's size.
     */
    public void setFrame(int[] frame1) {
        this.frame = frame1;
    }

    /**.
     * Method sets a gameEnvironment for the ball
     * @param environment the game environment to add to the ball
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**.
     * method checks if the nextstep of the ball (based on his velocity)
     * will cause the ball to get in to the paddle
     * @param paddle the paddle of the game
     * @return true if the ball will get into the paddle in his next step and false otherwise
     */
    public boolean nextStepIsInPaddle(Paddle paddle) {
        // getting the next step of the ball
        Point nextStep = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        // the start x value of the paddle
        double startXPaddle = paddle.getCollisionRectangle().getUpperLine().start().getX();
        // the end x value of the paddle
        double endXPaddle = paddle.getCollisionRectangle().getUpperLine().end().getX();
        //the height of the paddle
        double paddleHeight = paddle.getCollisionRectangle().getUpperLeft().getY();
        // the condition checks if the values of the x and y of the ball are between the paddle
        if (nextStep.getX() > startXPaddle && nextStep.getX() < endXPaddle && nextStep.getY() > paddleHeight) {
            return true;
        }
        return false;
    }

    /**.
     * method change the center and velocity of the ball if it hits the paddle from the sides
     * when it's in a move
     * @param paddle the paddle of the game
     */
    public void hitPaddle(Paddle paddle) {
        Point newCenter = null;
        // the distances from the ball to the left and right top points of the paddle
        double dis1 = this.center.distance(paddle.getCollisionRectangle().getUpperLeft());
        double dis2 = this.center.distance(paddle.getCollisionRectangle().getUpperLine().end());
        // if true then the ball is closer to the the upper right point
        if (dis1 >= dis2) {
            // setting new velocity and a new center
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
            newCenter = new Point(this.center.getX() + 2 * paddle.getSpeed(), this.center.getY());
            this.center = newCenter;
            return;
        }
        newCenter = new Point(this.center.getX() - 2 * paddle.getSpeed(), this.center.getY());
        this.center = newCenter;
        this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
    }

    /**.
     * Methos sets the ball's frame
     * @param size positive integer indicates the ball's size.
     */
    public void setSize(int size) {
        this.radius = size;
    }

    /**.
     * The Method move the ball one step based on the ball's
     * velocity. in addition if there is a collision point
     * the method change the ball velocity
     */
    private void moveOneStep() {
        // gets the trajectory line of the ball based on his next step
        Line trajectory = this.getBallTrajectory();
        // gets a collision info
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);
        Paddle paddle = (Paddle) this.gameEnvironment.getIndex(0);
        // if there is no collision and if within the next step of the ball it will get into the paddle
        if (collision == null && nextStepIsInPaddle(paddle)) {
            // then changing center and velocity
            hitPaddle(paddle);
        }
        // if there is no collision at all
        if (collision == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        // if there is collision, change the ball's center based on the the point location on the collidable
        this.changeCenter(collision);
        // gets the new velocity after the hit
        Velocity newVelocity = collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity);
        //sets the new velocity
        this.setVelocity(newVelocity);
    }

    /**.
     * This method draws the ball on the given DrawSurface
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**.
     *
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Method creates a ball wtih size and color
     * that were passed as arguments, and a random position.
     * @param size the radius of the ball
     * @param color the color of the ball
     * @param startFrame the start of the frame
     * @param endFrame the end of the frame
     * @return a ball with random position and with size and color.
     */
    static Ball generateBall(int size, java.awt.Color color, int startFrame, int endFrame) {
        Random rand = new Random();
        // gets x and y values between the start and end frame's values
        int x = startFrame + rand.nextInt(endFrame - startFrame);
        int y = startFrame + rand.nextInt(endFrame - startFrame);
        // creating a new frame
        int[] frame1 = {startFrame, endFrame, startFrame, endFrame};
        Ball randBall =  new Ball(x, y, size, color);
        // sets the frame
        randBall.setFrame(frame1);
        return randBall;
    }

    /**.
     * Method return the ball's trajectory
     * @return the ball's trajectory
     */
    public Line getBallTrajectory() {
        // gets the dx and dy velocity of the ball
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        // creating a new point (x+dx, y+dy)
        Point end = new Point(this.getX() + dx, this.getY() + dy);
        // returning the line that start with the center of the ball and ends with the Point end.
        return new Line(this.center, end);
    }

    /**.
     * Based on the collision point and the collision object,
     * the Method change the center's x,y values
     * @param collisionInfo the collision information between the ball and the object
     */
    public void changeCenter(CollisionInfo collisionInfo) {
        double x = this.getX();
        double y = this.getY();
        Rectangle rectangle = collisionInfo.collisionObject().getCollisionRectangle();
        Point collPoint = collisionInfo.collisionPoint();

        /*.
        in these condition, the x,y values of the ball will change
        based on the location of the collision point on the rectangle
         */
        if (collPoint.isOnLine(rectangle.getUpperLine())) {
            y--;
        }
        if (collPoint.isOnLine(rectangle.getDownerLine())) {
            y++;
        }
        if (collPoint.isOnLine(rectangle.getLeftLine())) {
            x--;
        }
        if (collPoint.isOnLine(rectangle.getRightLine())) {
            x++;
        }
        this.center = new Point(x, y);
    }

    /**.
     * Method removes the ball from the game
     * @param gameLevel the game to remove the ball from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}