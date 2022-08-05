//ID 209207364
package arkanoid.level;


import arkanoid.animation.AnimationRunner;
import arkanoid.animation.Animation;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.animation.PauseScreen;
import arkanoid.animation.CountdownAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import arkanoid.listeners.Counter;
import arkanoid.listeners.HitListener;
import arkanoid.listeners.BlockRemover;
import arkanoid.listeners.BallRemover;
import arkanoid.sprites.Sprite;
import arkanoid.sprites.Block;
import arkanoid.sprites.ScoreIndicator;
import arkanoid.sprites.Paddle;
import arkanoid.sprites.Ball;
import arkanoid.sprites.LevelName;
import arkanoid.listeners.ScoreTrackingListener;
import arkanoid.shapes.Point;
import arkanoid.shapes.Rectangle;
import arkanoid.collections.SpriteCollection;
import arkanoid.collections.GameEnvironment;
import arkanoid.collision.Collidable;

/**.
 * the class initial and start a game with ball and blocks aroud the frame
 */
public class GameLevel implements Animation {
    // the sizes of the frame
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int WIDTH_EDGE_FRAME = 20;
    public static final int HEIGHT_BLOCK = 20;
    public static final int WIDTH_BLOCK = 60;

    private LevelInformation level;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreCounter;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private boolean thereAreBalls;

    /**.
     * constructor, creates a new game level with the given fields.
     * @param level the specific level of the game
     * @param keyboard the keyboard sensor
     * @param runner the animation runner of the game
     * @param scoreCounter a score counter
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter scoreCounter) {
        this.level = level;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.scoreCounter = scoreCounter;
        this.runner = runner;
        this.keyboard = keyboard;
        this.running = true;
        this.thereAreBalls = true;
    }

    /**.
     * Method add a Collidable to the game environment
     * @param c the Collidable to add in to the game environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**.
     * Method add a Sprite to the the Sprites in the game
     * @param s the sprite to add into the Sprites
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**.
     * Method remove the given collidable from the envrionment
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**.
     * Method remove the given sprite from the spritesCollection
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**.
     * Method creates all the blocks around the frame with a width of 20, and creates the death region
     * @param ballRemover the ball remover that will be add to the death-region
     */
    private void createFrameBlocks(HitListener ballRemover) {
        Block upperBlock = new Block(new Point(0, 30), WIDTH, WIDTH_EDGE_FRAME);
        upperBlock.setColor(Color.GRAY);
        upperBlock.addToGame(this);

        Block leftBlock = new Block(new Point(0, 30), WIDTH_EDGE_FRAME, HEIGHT);
        leftBlock.setColor(Color.GRAY);
        leftBlock.addToGame(this);

        // this is the death-region
        Block deathRegion = new Block(new Point(0, HEIGHT + 10), WIDTH, WIDTH_EDGE_FRAME);
        deathRegion.setColor(Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);

        Block rightBlock = new Block(new Point(WIDTH - WIDTH_EDGE_FRAME, 30), WIDTH_EDGE_FRAME, HEIGHT);
        rightBlock.setColor(Color.GRAY);
        rightBlock.addToGame(this);

    }


    /**.
     * Method creates all of the balls in the level
     */
    private void initialBalls() {
        List<Ball> balls = this.level.balls();
        for (Ball ball : balls) {
            this.addSprite(ball);
            ball.setGameEnvironment(this.environment);
            this.remainingBalls.increase(1);
        }
    }

    /**.
     * Method generate a Color
     * @return random color
     */
    private Color generateColor() {
        Random rand = new Random();
        int r = 0, g = 0, b = 0;
        r = rand.nextInt(255);
        g = rand.nextInt(255);
        b = rand.nextInt(255);
        return new Color(r, g, b);
    }

    /**.
     * Method creats a new paddle and adds it to the sprties collections
     */
    private void initialPaddle() {
        int xPaddle = (WIDTH / 2) - (this.level.paddleWidth() / 2);
        Point point = new Point(xPaddle, HEIGHT - 2 * WIDTH_EDGE_FRAME);
        Paddle paddle = new Paddle(new Rectangle(point, this.level.paddleWidth(), 20), Color.YELLOW,
                this.keyboard, this.level.paddleSpeed());
        paddle.addToGame(this);
    }

    /**.
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        // creates the background
        this.sprites.addSprite(this.level.getBackground());
        this.sprites.addSprite(new LevelName(this.level.levelName()));

        // creating all of the listeners
        HitListener blockRemover = new BlockRemover(this, this.remainingBlocks);
        HitListener ballRemover = new BallRemover(this, this.remainingBalls);
        HitListener scoreListener = new ScoreTrackingListener(this.scoreCounter);

        // creating the paddle
        this.initialPaddle();

        // adds the score indicator
        Sprite scoreIndicator = new ScoreIndicator(this.scoreCounter);
        this.sprites.addSprite(scoreIndicator);

        // creating all of the blocks.
        List<Block> blocks = this.level.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
            this.remainingBlocks.increase(1);
        }

        // creating all of the frame blocks.
        this.createFrameBlocks(ballRemover);
    }

    /**
     * The method Run the game -- start the animation loop.
     */
    public void run() {
        this.initialBalls();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * .
     * the method draws one frame on the drawsurface
     *
     * @param d the drawsurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("פ") || this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        } else if (this.remainingBalls.getValue() == 0) {
            this.running = false;
            this.thereAreBalls = false;
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
        return !this.running;
    }

    /**.
     * Method return true if there are balls left in the game and false otherwise
     * @return true if there are balls left in the game and false otherwise
     */
    public boolean isThereAreBalls() {
        return this.thereAreBalls;
    }
}