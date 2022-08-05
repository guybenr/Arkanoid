//ID: 209207364

package arkanoid.listeners;

import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;

/**.
 * object who needs to be notified whenever a hit is happen implements the interface
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that is being hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}