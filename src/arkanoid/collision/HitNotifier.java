//ID:209207364

package arkanoid.collision;

import arkanoid.listeners.HitListener;

/**.
 * object who implements the interface send notifications when they are being hit:
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl the listener to add to the hit events
     */
    void addHitListener(HitListener hl);

    /**.
     * Remove hl from the list of listeners to hit events
     * @param hl the listener to remove from the  listener list events
     */
    void removeHitListener(HitListener hl);
}