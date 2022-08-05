package arkanoid.listeners;

import arkanoid.level.GameLevel;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**.
     * constructor
     * @param gameLevel the game to remove balls from
     * @param remainingBallsArg the remaining balls of game
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBallsArg) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBallsArg;
    }

    /**.
     * Method remove the beinghit ball from the game
     * @param beingHit the block that is being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //beingHit.removeHitListener(this);
        this.remainingBalls.decrease(1);
        hitter.removeFromGame(gameLevel);
    }
}