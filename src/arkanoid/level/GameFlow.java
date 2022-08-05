//ID 209207364

package arkanoid.level;

import arkanoid.animation.AnimationRunner;
import arkanoid.animation.EndScreen;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.listeners.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**.
 * class creates the game and moving through the stages
 */
public class GameFlow {
    private Counter scoreCounter;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;

    /**.
     * constructor
     * @param ar the animation runner of the game
     * @param ks the keyboard sensor of the game
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
        this.scoreCounter = new Counter();
    }

    /**.
     * class gets all of the available levels in the game and run the game
     * @param levels all of the available levels in the game
     */
    public void runLevels(List<LevelInformation> levels) {
        // flag variable, if the user failed a level then it turns to true
        boolean failed = false;
        for (LevelInformation levelInfo : levels) {
            // creating a new level and runs it
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.scoreCounter);
            level.initialize();
            level.run();
            // if the are no balls left in the game, then breaking the loop
            if (!level.isThereAreBalls()) {
                // creating a game over screen animation
                runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                                new EndScreen(this.scoreCounter, this.keyboard, false)));
                failed = true;
                break;
            }
        }
        // if the user didn't fail in any level, then creating a winner end screen
        if (!failed) {
            runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new EndScreen(this.scoreCounter, this.keyboard, true)));
        }
        // closing the gui
        this.runner.closeGui();
    }
}