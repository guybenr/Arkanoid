//ID 209207364

package arkanoid.sprites;

import biuoop.DrawSurface;

/**.
 * an interface for all of the drawable objects
 * in the game
 */
public interface Sprite {
    /**.
     * draw the sprite to the screen
     * @param d the drawsurface to draw on
     */
    void drawOn(DrawSurface d);

    /**.
     * notify the sprite that time has passed
     */
    void timePassed();
}