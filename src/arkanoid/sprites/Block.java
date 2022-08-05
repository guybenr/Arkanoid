//ID 209207364

package arkanoid.sprites;

import arkanoid.listeners.HitListener;
import arkanoid.collision.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import arkanoid.collision.Collidable;
import arkanoid.level.GameLevel;

/**.
 * Class defines a block based on a rectangle and color
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private java.awt.Color color;

    /**.
     * constructor
     * @param upperLeft the upper left point of the block
     * @param width the width of the block
     * @param height the height of the block
     */
    public Block(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**.
     * constructor
     * @param rectangle rectangle that indicates the block measures.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**.
     * Method set a ball's color
     * @param c the color of the ball
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }

    /**.
     * Method draw the block on the drawsurface d
     * @param d a drawsurface to draw the block on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        d.fillRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * .
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
        return;
    }

    /**.
     * Return the "collision shape" of the object.
     *
     * @return a Rectangle which indicates
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the object
     * @param hitter the ball that hits the block
     * @return the new velocity after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // if the collision point is on the horizontal lines, then the new dy will multiply by minus 1
        if (collisionPoint.isOnLine(this.rectangle.getUpperLine())
                || collisionPoint.isOnLine((this.rectangle.getDownerLine()))) {
            dy = -dy;
        }
        // if the collision point is on the vertical lines, then the new dx will multiply by minus 1
        if (collisionPoint.isOnLine(this.rectangle.getLeftLine())
                || collisionPoint.isOnLine((this.rectangle.getRightLine()))) {
            dx = -dx;
        }
        this.notifyHit(hitter);
        // returning a new velocity with the new speed.
        return new Velocity(dx, dy);
    }

    /**.
     * Method gets a Game and add the block to the collections
     * GameEnvironment and Sprites
     * @param gameLevel the game to add the block into
     */
    public void addToGame(GameLevel gameLevel) {
        Collidable collidable = this;
        Sprite sprite = this;
        gameLevel.addCollidable(collidable);
        gameLevel.addSprite(sprite);
    }

    /**.
     * Method remove the block from the game
     * @param gameLevel the game to remove the block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener to add to the hit events
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * .
     * Remove hl from the list of listeners to hit events
     *
     * @param hl the listener to remove from the  listener list events
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**.
     * notify to all of the listenrs that the hitter hitted the block
     * @param hitter the ball that hits the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}