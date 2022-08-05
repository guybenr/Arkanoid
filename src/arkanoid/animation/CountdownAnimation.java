// ID 209207364

package arkanoid.animation;

import arkanoid.level.GameLevel;
import arkanoid.collections.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;


/**.
 * Class responsible on a countdown animation before starting a new level
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int counter;
    private SpriteCollection gameScreen;

    /**.
     * constructor
     * @param numOfSeconds the total number of second to show the countdown animation
     * @param countFrom the first count number to starts from
     * @param gameScreen the sprites that is need to be shown
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.counter = countFrom + 1;
    }

    /**.
     * the method shows one frame of the countdown animation
     * @param d the drawsurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d);
        // draws the text while every frame decreasing the countdown
        d.drawText(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 5, --counter + "...", 32);
        // no sleep for the first index to show
        if (this.counter != this.countFrom) {
            sleeper.sleepFor(((long) ((numOfSeconds * 1000) / countFrom)));
        }
    }

    /**.
     * method return true if the animation needs to be stopped and false otherwise
     * @return true if the animation needs to be stopped and false otherwise
     */
    public boolean shouldStop() {
        // if the counter reach to 0 then stopping the animation
        if (this.counter == 0) {
            return true;
        }
        return false;
    }
}