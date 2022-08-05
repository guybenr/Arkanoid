//ID 209207364

import arkanoid.animation.AnimationRunner;
import arkanoid.level.Level1;
import arkanoid.level.Level2;
import arkanoid.level.Level3;
import arkanoid.level.Level4;
import arkanoid.level.LevelInformation;
import arkanoid.level.GameFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**.
 * class creating a new game' initialize it and tuns it
 */
public class Ass6Game {
    /**
     * method gets an array of strings indicates the order of the levels that should
     * perform and return an array of levelInfomartion about the levels.
     * @param args the order of the levels that the game will activate
     * @return method return a list wtih the specific order that the game should run
     */
    public List<LevelInformation> getLevelsGame(String[] args) {
        // creating a map between 1 to 4 integers and all of the level Informations
        Map<Integer, LevelInformation> map = new HashMap<>();
        map.put(1, new Level1());
        map.put(2, new Level2());
        map.put(3, new Level3());
        map.put(4, new Level4());
        // a flag in order to know if atleast one valid level entered
        boolean oneLevelEntered = false;
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        // if no levels entered
        if (args.length == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
            return levels;
        }
        for (String s : args) {
            // try to convert the string to an integer
            try {
                int num = Integer.parseInt(s);
                if (num >= 1 && num <= 4) {
                    oneLevelEntered = true;
                    levels.add(map.get(num));
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        // if no valid levels entered then performing a recursive operation in order to return a normal levels array
        if (!oneLevelEntered) {
            String[] emptyArr = new String[0];
            return this.getLevelsGame(emptyArr);
        }
        return levels;
    }

    /**
     * the main initial the game with blocks and a ball and runs it.
     * @param args none
     */
    public static void main(String[] args) {
        Ass6Game game = new Ass6Game();
        List<LevelInformation> levels = game.getLevelsGame(args);
        AnimationRunner runner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(runner, runner.getKeyboardSensor());
        gameFlow.runLevels(levels);
    }
}