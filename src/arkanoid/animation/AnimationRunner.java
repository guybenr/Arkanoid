// ID 209207364

package arkanoid.animation;

import arkanoid.level.GameLevel;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**.
 * The class responsible to run the animation of the game
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**.
     * constructor
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**.
     * the method runs the main loop of the animation
     * @param animation the animation that is responsible to stop the loop
     * or to draws the specific frame.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**.
     * Method return a keyboard Sensor from the GUI
     * @return a keyboard Sensor from the GUI
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.gui.getKeyboardSensor();
    }

    /**.
     * Method close the gui
     */
    public void closeGui() {
        this.gui.close();
    }
}