//ID: 209207364

package arkanoid.sprites;

import arkanoid.listeners.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**.
 * this sprites being used to track and draw the score of the user
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**.
     * constructor
     * @param scoreCount the Counter
     */
    public ScoreIndicator(Counter scoreCount) {
        this.scoreCounter = scoreCount;
    }


    /**
     * .
     * draw the sprite to the screen
     *
     * @param d the drawsurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        String scoreString = "Score: " + this.scoreCounter.getValue();
        d.drawText(150, 20, scoreString, 20);
    }

    /**
     * .
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
        return;
    }
}
