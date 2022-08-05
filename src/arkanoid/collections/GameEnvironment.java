//ID 209207364

package arkanoid.collections;

import java.util.ArrayList;
import java.util.List;


import arkanoid.shapes.Line;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import arkanoid.collision.CollisionInfo;
import arkanoid.collision.Collidable;


/**.
 * Class stores all of the collidable objects in the game
 */
public class GameEnvironment {
    // a list of all the collidables
    private List<Collidable> collidables;

    /**.
     * constrctor, creating a new arraylist
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**.
     * gets the i'th Collidble in the collection
     * @param i positive integer that indicates the index of the Collidable in the Collection
     * @return the Collidable with the index i
     */
    public Collidable getIndex(int i) {
        return this.collidables.get(i);
    }

    /**
     * add the given collidable to the environment.
     * @param c a Collidable to add into the array
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**.
     * Method checks if there is collision between
     * between the start point of the trajectory and the collidable in the game environment
     * @param trajectory indicates an object moving from start to end
     * @return the collision between the start point of the trajectory
     * and the collidable in the game environment array, otherwise null if there are no collisons
     */
    private Point getFirstCollisionInEnvironment(Line trajectory) {
        for (Collidable collidable : this.collidables) {
            // gets the collision rectangle
            Rectangle rect = collidable.getCollisionRectangle();
            // if there is intersection points between rect and trajectory
            if (!rect.intersectionPoints(trajectory).isEmpty()) {
                // return the first intersection point in the arrayList
                return trajectory.closestIntersectionToStartOfLine(rect);
            }
        }
        return null;
    }

    /**
     * Method return the colliosion info of the closeset collision with
     * trajectory and one of the collidables in the game environment.
     *
     * @param trajectory a line indicates an object moving from start to end
     * @return If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable = null;
        // gets the first Collision point in the Environment
        Point closestPoint = this.getFirstCollisionInEnvironment(trajectory);
        // if there are none, then return null
        if (closestPoint == null) {
            return null;
        }
        // this loop being used to get the closest collidable and collision point with trajectory
        for (Collidable c : collidables) {
            // gets the closest collision point between trajectory and the rectangle
            Point currPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            // if there is no collision then continue;
            if (currPoint == null) {
                continue;
            }
            // if the distance from the current point is smaller then from the closeset point then stores it
            if (trajectory.getStartTrajectory().distance(closestPoint)
                    >= trajectory.getStartTrajectory().distance(currPoint)) {
                closestCollidable = c;
                closestPoint = currPoint;
            }
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }

    /**.
     * Method remove the given collidable from the envrionment
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}