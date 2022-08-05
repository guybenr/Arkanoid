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
 * class defines the second level of the game.
 */
public class Level2 implements LevelInformation {
    private static final int BLOCK_WIDTH = 40;
    private static final int BLOCK_HEIGHT = 20;

    /**
     * .
     * Method return the number of ball in the level
     *
     * @return the number of ball in the level
     */
    @Override
    public int numberOfBalls() {
        return 10;
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
        for (int i = 0; i < 10; i++) {
            if (i <= 4) {
                velocities.add(new Velocity(-5, 5));
            } else {
                velocities.add(new Velocity(5, 5));
            }
        }
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
        return 5;
    }

    /**
     * .
     * Method return the width of the paddle in the level
     *
     * @return the width of the paddle in the level
     */
    @Override
    public int paddleWidth() {
        return 650;
    }

    /**
     * .
     * method return the name of the level
     *
     * @return the name of the level
     */
    @Override
    public String levelName() {
        return "Wide Easy 2";
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
        background.setColor(Color.WHITE);
        return background;
    }

    /**.
     * Method return the block color based on its index
     * @param i the index of the block in the row
     * @return the color of the block
     */
    private Color getBlockColor(int i) {
        Color c = null;
        if (i <= 1) {
            c = Color.RED;
        } else if (i <= 3) {
            c = Color.ORANGE;
        } else if (i <= 5) {
            c = Color.YELLOW;
        } else if (i <= 7) {
            c = Color.GREEN;
        } else if (i <= 9) {
            c = Color.BLUE;
        } else if (i <= 11) {
            c = Color.PINK;
        } else if (i <= 13) {
            c = Color.CYAN;
        } else if (i <= 15) {
            c = Color.GRAY;
        } else if (i <= 17) {
            c = Color.BLACK;
        } else if (i <= 19) {
            c = Color.DARK_GRAY;
        }
        return c;
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
        for (int i = 0; i < 19; i++) {
            Point upperLeft = new Point(20 + (i * 40), (int) (350));
            Rectangle rectangle = new Rectangle(upperLeft, BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rectangle);
            block.setColor(this.getBlockColor(i));
            blocks.add(block);
        }
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
        for (int i = 0; i < 10; i++) {
            Ball ball = new Ball((250 + i * 40), 550, 5, Color.BLACK);
            if (true) { //i <= 4) {
                ball.setVelocity(Math.pow((-1), i) * 5, -5);
                balls.add(ball);
            } else {
                ball.setVelocity(5, -5);
                balls.add(ball);
            }
        }
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
        return 19;
    }
}