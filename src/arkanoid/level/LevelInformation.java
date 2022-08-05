package arkanoid.level;

import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.Velocity;

import java.util.List;

/**
 * The class defines a game level infomration like number of balls and their velocities,
 * all of the blocks in the level and such so.
 */
public interface LevelInformation {

    /**.
     * Method return the number of ball in the level
     * @return the number of ball in the level
     */
    int numberOfBalls();

    /**.
     * method initials all of the balls velocities
     * @return a list<velocity> of all of the velocites of the balls
     */
    List<Velocity> initialBallVelocities();

    /**.
     * Method return the speed of the paddle in the level
     * @return the speed of the paddle in the level
     */
    int paddleSpeed();

    /**.
     * Method return the width of the paddle in the level
     * @return the width of the paddle in the level
     */
    int paddleWidth();

    /**.
     * method return the name of the level
     * @return the name of the level
     */
    String levelName();

    /**.
     * method return a sprite with the background of the level
     * @return sprite with the background of the level
     */
    Sprite getBackground();

    /**.
     * method creates and return a list of blocks in the level
     * @return a list of blocks in the level
     */
    List<Block> blocks();

    /**.
     * method creates and return a list of balls in the level
     * @return a list of balls in the level
     */
    List<Ball> balls();

    /**.
     * Method return the number of blocks that should be removed in order to clear the level
     * @return the number of blocks that should be removed in order to clear the level
     */
    int numberOfBlocksToRemove();
}