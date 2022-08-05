//ID 209207364

package arkanoid.collections;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

import arkanoid.sprites.Sprite;
/**.
 * a colleaction of all arkanoid.sprites in the game
 */
public class SpriteCollection {
    // arraylist of arkanoid.sprites
    private List<Sprite> sprites;

    /**.
     * constructor, initial a new arraylist of arkanoid.sprites
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**.
     * add a new sprite to the collection
     * @param s the sprite to add to the list
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**.
     * Method used to change the animation of the sprite
     * like speed, location, color and more.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite: copySprites) {
            sprite.timePassed();
        }
    }

    /**.
     * method draws all of the arkanoid.sprites in the collection on a drawsurface
     * @param d the drawsurface to draw on
     */
    // call drawOn(d) on all arkanoid.sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: sprites) {
            sprite.drawOn(d);
        }
    }

    /**.
     * Method remove the given sprite from the spritesCollection
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
}