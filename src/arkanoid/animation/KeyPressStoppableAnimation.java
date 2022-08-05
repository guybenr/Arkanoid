package arkanoid.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class wrap an animation and runs it until a specific key being pressed.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop = false;
    private boolean isAlreadyPressed = true;

    /**
     * constructor.
     * @param sensor the keyboard sensor of the game
     * @param key the string ket that will stop the animation if is pressed
     * @param animation the animation to run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * .
     * the method draws one frame on the drawsurface
     *
     * @param d the drawsurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        } else if (!isAlreadyPressed && this.keyboard.isPressed(this.key)) {
            this.stop = true;
        }
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