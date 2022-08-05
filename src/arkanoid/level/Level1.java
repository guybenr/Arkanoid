//ID 209207364

package arkanoid.level;

import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the class defines the first level of the game.
 */
public class Level1 implements LevelInformation {
    /**
     * .
     * Method return the number of ball in the level
     *
     * @return the number of ball in the level
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * .
     * method initials all of the balls velocities
     *
     * @return a list<velocity> of all of the velocites of the balls
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(0, -7));
        return velocities;
    }

    /**
     * .
     * Method return the speed of the paddle in the level
     *
     * @return the speed of the paddle in the level
     */
    @Override
    public int paddleSpeed() {
        return 7;
    }

    /**
     * .
     * Method return the width of the paddle in the level
     *
     * @return the width of the paddle in the level
     */
    @Override
    public int paddleWidth() {
        return 80;
    }

    /**
     * .
     * method return the name of the level
     *
     * @return the name of the level
     */
    @Override
    public String levelName() {
        return "Direct Hit 1";
    }

    /**
     * .
     * method return a sprite with the background of the level
     *
     * @return sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        Rectangle rect = new Rectangle(new Point(0, 30), GameLevel.WIDTH, GameLevel.HEIGHT);
        Block background = new Block(rect);
        background.setColor(Color.black);
        return background;
    }

    /**
     * .
     * method creates and return a list of blocks in the level
     *
     * @return a list of blocks in the level
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Point upperLeft = new Point((int) (GameLevel.WIDTH / 2) - 25, 150);
        Rectangle rectangle = new Rectangle(upperLeft, 50, 50);
        Block block = new Block(rectangle);
        block.setColor(Color.RED);
        blocks.add(block);
        return blocks;
    }

    /**
     * .
     * method creates and return a list of balls in the level
     *
     * @return a list of balls in the level
     */
    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<Ball>();
        Ball ball = new Ball((int) (GameLevel.WIDTH / 2), 530, 5, Color.WHITE);
        ball.setVelocity(initialBallVelocities().get(0));
        balls.add(ball);
        return balls;
    }

    /**
     * .
     * Method return the number of blocks that should be removed in order to clear the level
     *
     * @return the number of blocks that should be removed in order to clear the level
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}