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
 * The third level of the game.
 */
public class Level3 implements LevelInformation {

    private static final int HEIGHT_BLOCK = 20;
    private static final int WIDTH_BLOCK = 60;

    /**
     * .
     * Method return the number of ball in the level
     *
     * @return the number of ball in the level
     */
    @Override
    public int numberOfBalls() {
        return 2;
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
        Velocity v1 = new Velocity(-4, -4);
        Velocity v2 = new Velocity(4, -4);
        velocities.add(v1);
        velocities.add(v2);
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
        return 150;
    }

    /**
     * .
     * method return the name of the level
     *
     * @return the name of the level
     */
    @Override
    public String levelName() {
        return "DarkMode 3";
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
        background.setColor(Color.darkGray);
        return background;
    }

    /**.
     * method return the row's blocks color
     * @param row the specified row
     * @return row's blocks color
     */
    private Color getRowColor(int row) {
        Color c = null;
        if (row == 0) {
            c = Color.darkGray;
        } else if (row == 1) {
            c = Color.RED;
        } else if (row == 2) {
            c = Color.YELLOW;
        } else if (row == 3) {
            c = Color.BLUE;
        } else if (row == 4) {
            c = Color.ORANGE;
        } else if (row == 5) {
            c = Color.white;
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
        int count = 0;
        for (int i = 200; i < 320; i += HEIGHT_BLOCK) {
            // generate a new color for every line
            Color color = getRowColor(count);
            for (int j = 180 + count * WIDTH_BLOCK;
                        j < GameLevel.WIDTH - GameLevel.WIDTH_EDGE_FRAME; j += WIDTH_BLOCK) {
                // creating Blocks around the frames and adding them to the Sprites and Game Environment
                Block block = new Block(new Point(j, i), WIDTH_BLOCK, HEIGHT_BLOCK);
                block.setColor(color);
                blocks.add(block);
            }
            count++;
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
        Ball ball1 = new Ball(300, 400, 5, Color.white);
        ball1.setVelocity(initialBallVelocities().get(0));
        Ball ball2 = new Ball(500, 400, 5, Color.white);
        ball2.setVelocity(initialBallVelocities().get(1));
        balls.add(ball1);
        balls.add(ball2);
//        for (int i = 0; i < 50; i++) {
//            Ball ball = new Ball(i * 10, 400, 5, Color.white);
//            ball.setVelocity(initialBallVelocities().get(0));
//            balls.add(ball);
//        }
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
        return 45;
    }
}