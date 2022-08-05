//ID 209207364

package arkanoid.collision;

import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Velocity;


/**.
 * the interface implements all of the collidable objects in the game
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return a Rectangle which indicates
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     *  Notify the object that we collided with it at collisionPoint with
     *  a given velocity.
     *  The return is the new velocity expected after the hit (based on
     *  the force the object inflicted on us).
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity of the object
     * @param hitter the ball that hits the object
     * @return the new velocity after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}