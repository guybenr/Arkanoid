// ID 209207364

package arkanoid.sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**.
 * class creates and draws the name of the level
 */
public class LevelName implements Sprite {
    private String levelName;

    /**.
     * constructor
     * @param levelName the name of the level
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
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
        String scoreString = "Level Name: " + this.levelName;
        d.drawText(500, 20, scoreString, 20);
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