package arkanoid.listeners;

import arkanoid.level.GameLevel;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**.
     * constructor
     * @param gameLevel the game to remove blocks from
     * @param remainingBlocks the remaining blocks from the game
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**.
     * Method remove the beinghit block from the game
     * @param beingHit the block that is being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
        beingHit.removeFromGame(gameLevel);
    }
}