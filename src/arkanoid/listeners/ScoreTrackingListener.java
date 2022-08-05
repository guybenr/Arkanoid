
package arkanoid.listeners;


import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;

/**.
 * Class tracks the scores of the user in the game
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**.
     * constructor
     * @param scoreCounter the counter of the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**.
     * every hit the score number is increase with 5
     * @param beingHit the block that is being hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}