//ID 209207364

package arkanoid.animation;
import biuoop.DrawSurface;

/**.
 * the interface responsible on the animtaion in the game
 */
public interface Animation {

    /**.
     * the method draws one frame on the drawsurface
     * @param d the drawsurface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**.
     * Method return true if the game should stop and false otherwise
     * @return Method return true if the game should stop and false otherwise
     */
    boolean shouldStop();
}