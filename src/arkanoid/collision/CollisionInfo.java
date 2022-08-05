//ID 209207364

package arkanoid.collision;

import arkanoid.shapes.Point;

/**.
 * class being used in order to describe a collision information
 * like the collision point and object
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**.
     * constructor
     * @param collisionPoint the point where the collision happend
     * @param collisionObject the object that collided
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**.
     * Method return the collision point
     * @return return the collision point
     */
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**.
     * Method return the collision object
     * @return return the collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    }
