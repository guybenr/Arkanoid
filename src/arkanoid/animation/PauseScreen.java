// ID 209207364

package arkanoid.animation;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class creates and draws an animation pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**.
     * constructor
     * @param k the keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Method draws a pause frame.
     * @param d the drawsurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**.
     * if a the space key has been pressed, then return that animation should stop
     * @return true if the the animation should stop and false otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }
}