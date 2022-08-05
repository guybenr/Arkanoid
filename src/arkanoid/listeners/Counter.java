
package arkanoid.listeners;

/**
 * a class being used for counting things.
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**.
     * method add the number to current count
     * @param number the number to add to the current count
     */
    // add number to current count.
    public void increase(int number) {
        this.counter += number;
    }

    /**.
     * Method subtract a number from the current count
     * @param number the number to subtract to the current count
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**.
     * Method return the current count value
     * @return the cureent count value
     */
    public int getValue() {
        return this.counter;
    }
}