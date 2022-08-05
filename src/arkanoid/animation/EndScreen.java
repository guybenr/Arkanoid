//ID 209207364

package arkanoid.animation;

import arkanoid.listeners.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * class defines an end screen in two modes: and winner end screen or a game over one
 */
public class EndScreen implements Animation {
    private Counter scoreCounter;
    private KeyboardSensor keyboard;
    private boolean clearedGame;
    private boolean stop = false;

    /**.
     * constructor, creates a new end screen with the given counter and keyboard,
     * if clearedGame == true then the screen is a winner screen, otherwise it's a game over screen
     * @param scoreCounter the score of the user
     * @param keyboard the keyboard sensor
     * @param clearedGame if the user cleared the game
     */
    public EndScreen(Counter scoreCounter, KeyboardSensor keyboard, boolean clearedGame) {
        this.scoreCounter = scoreCounter;
        this.keyboard = keyboard;
        this.clearedGame = clearedGame;
    }

    /**
     * .
     * the method draws one frame on the drawsurface
     *
     * @param d the drawsurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // creating a winner screen
        if (this.clearedGame) {
            d.drawText(200, d.getHeight() / 2, "You Win! Your score is "
                                        + this.scoreCounter.getValue(), 32);
            return;
        }
        // creating a game over screen
        d.drawText(200, d.getHeight() / 2, "Game Over Your score is "
                + this.scoreCounter.getValue(), 32);
    }

    /**
     * .
     * Method return true if the game should stop and false otherwise
     *
     * @return Method return true if the game should stop and false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}